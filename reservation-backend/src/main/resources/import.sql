DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Place;
DROP TABLE IF EXISTS PlaceType;
DROP TABLE IF EXISTS Customer;

DROP SEQUENCE IF EXISTS reservation_id_seq;
DROP SEQUENCE IF EXISTS place_id_seq;
DROP SEQUENCE IF EXISTS place_type_id_seq;
DROP SEQUENCE IF EXISTS customer_id_seq;

CREATE SEQUENCE IF NOT EXISTS customer_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS place_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS place_type_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS reservation_id_seq START WITH 1 INCREMENT BY 1;

create table Customer (
    id BIGINT DEFAULT NEXT VALUE FOR customer_id_seq PRIMARY KEY,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table Place (
    id BIGINT DEFAULT NEXT VALUE FOR place_id_seq PRIMARY KEY,
    location varchar(255),
    placeType_id bigint,
    primary key (id)
);

create table PlaceType (
    id BIGINT DEFAULT NEXT VALUE FOR place_type_id_seq PRIMARY KEY,
    description varchar(255),
    title varchar(255),
    primary key (id)
);

create table Reservation (
    id BIGINT DEFAULT NEXT VALUE FOR reservation_id_seq PRIMARY KEY,
    reservation_end timestamp(6),
    reservation_start timestamp(6),
    customer_id bigint,
    place_id bigint
);

alter table if exists Place
    add constraint FK_Place_PlaceType
    foreign key (placeType_id)
    references PlaceType;

alter table if exists Reservation
    add constraint FK_Reservation_Customer
    foreign key (customer_id)
    references Customer;

alter table if exists Reservation
    add constraint FK_Reservation_Place
    foreign key (place_id)
    references Place;

alter table if exists Customer
    add constraint UK_Customer_Email
    unique (email);

alter table if exists Customer
    add constraint UK_Customer_Username
    unique (username);

alter table if exists Customer
    add constraint CK_Customer_FirstName
    check (length(first_name) >= 1);

alter table if exists Customer
    add constraint CK_Customer_LastName
    check (length(last_name) >= 1);

alter table if exists Customer
    add constraint CK_Customer_Username
    check (length(username) >= 3 and length(username) <= 100);

alter table if exists PlaceType
    add constraint CK_PlaceType_Title
    check (title is not null);

alter table if exists PlaceType
    add constraint CK_PlaceType_Description
    check (length(description) >= 1);

alter table if exists Place
    add constraint CK_Place_Location
    check (location is not null);

alter table if exists Reservation
    add constraint CK_Reservation_Dates
    check (reservation_end > reservation_start);

INSERT INTO PlaceType (id, title, description) VALUES 
    (nextval('place_type_id_seq'), 'Hotel Room', 'Standard hotel accommodation'),
    (nextval('place_type_id_seq'), 'Apartment', 'Fully furnished apartment for short-term stay'),
    (nextval('place_type_id_seq'), 'Villa', 'Luxurious villa with private amenities'),
    (nextval('place_type_id_seq'), 'Camping Site', 'Outdoor space for camping and recreational vehicles'),
    (nextval('place_type_id_seq'), 'Cottage', 'Cozy rural or semi-rural house for vacation');

INSERT INTO Place (id, location, placeType_id) VALUES 
    (nextval('place_id_seq'), 'New York City', 1),
    (nextval('place_id_seq'), 'Los Angeles', 2),
    (nextval('place_id_seq'), 'Miami Beach', 3),
    (nextval('place_id_seq'), 'Yellowstone National Park', 4),
    (nextval('place_id_seq'), 'Lake Tahoe', 5);

INSERT INTO Customer (id, first_name, last_name, email, username, password) VALUES 
    (nextval('customer_id_seq'), 'John', 'Doe', 'john.doe@example.com', 'johnd', 'Password@123'),
    (nextval('customer_id_seq'), 'Jane', 'Smith', 'jane.smith@example.com', 'janes', 'SecurePass1!'),
    (nextval('customer_id_seq'), 'Alice', 'Johnson', 'alice.j@example.com', 'alicej', 'Pass1234!'),
    (nextval('customer_id_seq'), 'Bob', 'Brown', 'bob.brown@example.com', 'bobb', 'BrownPass1#'),
    (nextval('customer_id_seq'), 'Emma', 'Wilson', 'emma.w@example.com', 'emmaw', 'WilsonPass2$');

INSERT INTO Reservation (id, reservation_start, reservation_end, customer_id, place_id) VALUES 
    (nextval('reservation_id_seq'), TIMESTAMP '2024-12-01 08:00:00', TIMESTAMP '2024-12-01 10:00:00', 1, 1),
    (nextval('reservation_id_seq'), TIMESTAMP '2024-12-02 09:00:00', TIMESTAMP '2024-12-02 11:00:00', 2, 2),
    (nextval('reservation_id_seq'), TIMESTAMP '2024-12-03 10:00:00', TIMESTAMP '2024-12-03 12:00:00', 3, 3),
    (nextval('reservation_id_seq'), TIMESTAMP '2024-12-04 14:00:00', TIMESTAMP '2024-12-04 16:00:00', 4, 4),
    (nextval('reservation_id_seq'), TIMESTAMP '2024-12-05 19:00:00', TIMESTAMP '2024-12-05 21:00:00', 5, 5);
