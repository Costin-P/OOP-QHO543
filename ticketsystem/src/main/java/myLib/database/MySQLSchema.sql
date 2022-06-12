drop table if exists ticket;
drop table if exists ticketmachine;
drop table if exists gate;
drop table if exists station;
drop table if exists zone;

CREATE TABLE zone
(
    id int primary key not null
);
insert into zone(id)values
(1),
(2),
(3),
(4),
(5),
(6);

create table station(
    id int AUTO_INCREMENT not null primary key,
    zoneid int not null references zone(id) match full on delete cascade on update cascade,
    stationname varchar(500) not null,
    unique(zoneid,stationname)
);
insert into station(zoneid,stationname)values
(1,'1a'),
(1,'1b'),
(1,'1c'),
(2,'2a'),
(2,'2b'),
(2,'2c'),
(3,'3a'),
(3,'3b'),
(3,'3c'),
(4,'4a'),
(4,'4b'),
(4,'4c'),
(5,'5a'),
(5,'5b'),
(5,'5c'),
(6,'6a'),
(6,'6b'),
(6,'6c');

CREATE TABLE gate
(
    id int AUTO_INCREMENT not null primary key,
    zoneid int NOT NULL,
    stationname varchar not null,
    foreign key(zoneid,stationname) references station(zoneid,stationname) match full on delete cascade on update cascade,
    gateno varchar(10) NOT NULL,
    unique (zoneid, stationname,gateno)
);

CREATE TABLE ticketmachine
(
    id int AUTO_INCREMENT primary key not null,
    machineid varchar(100) NOT NULL unique,
    zoneid int NOT NULL,
    stationname varchar not null,
    gateno varchar NOT NULL,
    FOREIGN KEY (zoneid,stationname,gateno)REFERENCES gate(zoneid,stationname,gateno) MATCH full
        ON UPDATE CASCADE
        ON DELETE cascade
);

create table ticket(
    id int AUTO_INCREMENT primary key not null,
    fzoneid int NOT NULL,
    fstationname varchar not null,
    fgateno varchar not null,
    foreign key(fzoneid,fstationname,fgateno) references gate(zoneid,stationname,gateno) match full on update cascade on delete cascade,
    machineid varchar not null references ticketmachine(machineid) match full on update cascade on delete cascade,
    tzoneid int NOT NULL,
    tstationname varchar not null,
    foreign key(tzoneid,tstationname)references station(zoneid,stationname) match full on delete cascade on update cascade,
    dt timestamp with time zone not null,
    tickettype varchar(7) check(tickettype in('peak','offpeak')) not null,
    zonecount int not null check(zonecount>0),
    validdt timestamp with time zone not null,
    price numeric(15,2) not null check(price>0)
);

drop table if exists holiday;
CREATE TABLE holiday
(
    d date NOT NULL primary key,
    remark varchar(1000)
);

drop table if exists ticketcharge;
create table ticketcharge(
    id int AUTO_INCREMENT primary key not null,
    pickcharge numeric(15,2) NOT NULL CHECK (pickcharge >= 0),
    offpickcharge numeric(15,2) NOT NULL CHECK (offpickcharge >= 0),
    pickfromtime time with time zone not null,
    picktotime time with time zone not null
);
insert into ticketcharge(pickcharge,offpickcharge,pickfromtime,picktotime)values(100,50,'10:00+5:30','17:30+5:30');
