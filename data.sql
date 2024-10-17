create table Customer (
    id bigint not null,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table Place (
    id bigint not null,
    location varchar(255),
    placeType_id bigint,
    primary key (id)
);

create table PlaceType (
    id bigint not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);

create table Reservation (
    id bigint not null,
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

INSERT INTO PlaceType (id, title, description) VALUES (1, 'Hotel Room', 'Standard hotel accommodation');
INSERT INTO PlaceType (id, title, description) VALUES (2, 'Apartment', 'Fully furnished apartment for short-term stay');
INSERT INTO PlaceType (id, title, description) VALUES (3, 'Villa', 'Luxurious villa with private amenities');
INSERT INTO PlaceType (id, title, description) VALUES (4, 'Camping Site', 'Outdoor space for camping and recreational vehicles');
INSERT INTO PlaceType (id, title, description) VALUES (5, 'Cottage', 'Cozy rural or semi-rural house for vacation');

INSERT INTO Place (id, location, placeType_id) VALUES (1, 'New York City', 1);
INSERT INTO Place (id, location, placeType_id) VALUES (2, 'Los Angeles', 2);
INSERT INTO Place (id, location, placeType_id) VALUES (3, 'Miami Beach', 3);
INSERT INTO Place (id, location, placeType_id) VALUES (4, 'Yellowstone National Park', 4);
INSERT INTO Place (id, location, placeType_id) VALUES (5, 'Lake Tahoe', 5);

INSERT INTO Customer (id, first_name, last_name, email, username, password) VALUES (1, 'John', 'Doe', 'john.doe@example.com', 'johnd', 'Password@123');
INSERT INTO Customer (id, first_name, last_name, email, username, password) VALUES (2, 'Jane', 'Smith', 'jane.smith@example.com', 'janes', 'SecurePass1!');
INSERT INTO Customer (id, first_name, last_name, email, username, password) VALUES (3, 'Alice', 'Johnson', 'alice.j@example.com', 'alicej', 'Pass1234!');
INSERT INTO Customer (id, first_name, last_name, email, username, password) VALUES (4, 'Bob', 'Brown', 'bob.brown@example.com', 'bobb', 'BrownPass1#');
INSERT INTO Customer (id, first_name, last_name, email, username, password) VALUES (5, 'Emma', 'Wilson', 'emma.w@example.com', 'emmaw', 'WilsonPass2$');

INSERT INTO Reservation (id, reservation_start, reservation_end, customer_id, place_id) VALUES
(1, '2023-10-01 08:00:00', '2023-10-01 08:30:00', 101, 201),
(2, '2023-10-02 09:00:00', '2023-10-02 09:30:00', 102, 202),
(3, '2023-10-03 10:00:00', '2023-10-03 10:30:00', 103, 203),
(4, '2023-10-04 14:00:00', '2023-10-04 14:30:00', 104, 204),
(5, '2023-10-05 19:00:00', '2023-10-05 19:30:00', 105, 205);
