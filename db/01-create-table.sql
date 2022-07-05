create table bus_driver (
     id int primary key,
     name nvarchar2(2000) not null,
     address nvarchar2(2000) not null,
     phone_number nvarchar2(15) not null,
     driver_level nvarchar2(5) not null
);

create table bus_line (
    id int primary key,
    distance number not null,
    stop_station_number number not null
);


create table driver_bus_management (
    driver_id number not null,
    bus_line_id number not null,
    round_number number not null default 0,
    primary key(driver_id, bus_line_id),
    CONSTRAINT driver_id_fk FOREIGN KEY (driver_id) REFERENCES buss_driver(id),
    CONSTRAINT bus_line_id_fk FOREIGN KEY (bus_line_id) REFERENCES bus_line(id)
);
