create sequence customer_id_seq;
create sequence place_id_seq;
create sequence place_type_id_seq;
create sequence reservation_id_seq;

create table Customer (
    id bigint NOT NULL PRIMARY KEY DEFAULT NEXTVAL('customer_id_seq'),
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table Place (
    id bigint NOT NULL PRIMARY KEY DEFAULT NEXTVAL('place_id_seq'),
    location varchar(255),
    placeType_id bigint,
    primary key (id)
);

create table PlaceType (
    id bigint NOT NULL PRIMARY KEY DEFAULT NEXTVAL('place_type_id_seq'),
    description varchar(255),
    title varchar(255),
    primary key (id)
);

create table Reservation (
    id bigint NOT NULL PRIMARY KEY DEFAULT NEXTVAL('reservation_id_seq'),
    reservation_end date,
    reservation_start date,
    customer_id bigint,
    place_id bigint,
    primary key (id)
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

INSERT INTO PlaceType (title, description) VALUES
    ('Hotel Room', 'Standard hotel accommodation'),
    ('Apartment', 'Fully furnished apartment for short-term stay'),
    ('Villa', 'Luxurious villa with private amenities'),
    ('Camping Site', 'Outdoor space for camping and recreational vehicles'),
    ('Cottage', 'Cozy rural or semi-rural house for vacation');

INSERT INTO Place (location, placeType_id) VALUES
    ('New York City', 1),
    ('Los Angeles', 2),
    ('Miami Beach', 3),
    ('Yellowstone National Park', 4),
    ('Lake Tahoe', 5);

INSERT INTO Customer (first_name, last_name, email, username, password) VALUES
    ('John', 'Doe', 'john.doe@example.com', 'johnd', 'Password@123'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'janes', 'SecurePass1!'),
    ('Alice', 'Johnson', 'alice.j@example.com', 'alicej', 'Pass1234!'),
    ('Bob', 'Brown', 'bob.brown@example.com', 'bobb', 'BrownPass1#'),
    ('Emma', 'Wilson', 'emma.w@example.com', 'emmaw', 'WilsonPass2$');

INSERT INTO Reservation (reservation_start, reservation_end, customer_id, place_id) VALUES
    ('2023-10-01 08:00:00', '2023-10-01 08:30:00', 1, 1),
    ('2023-10-02 09:00:00', '2023-10-02 09:30:00', 2, 2),
    ('2023-10-03 10:00:00', '2023-10-03 10:30:00', 3, 3),
    ('2023-10-04 14:00:00', '2023-10-04 14:30:00', 4, 4),
    ('2023-10-05 19:00:00', '2023-10-05 19:30:00', 5, 5);
