create table firetruck(
type VARCHAR(30) constraint pk_firetruck PRIMARY KEY,
licenseplate VARCHAR(10),
numSeats INT
);

insert into firetruck(type, licenseplate, numSeats) values('KLF', 'L666TE', 8);
insert into firetruck(type, licenseplate, numSeats) values('LF-W', 'L298GO', 5);
insert into firetruck(type, licenseplate, numSeats) values('TSA', 'L122FJ', 4);
insert into firetruck(type, licenseplate, numSeats) values('LF', 'L983KO', 7);
insert into firetruck(type, licenseplate, numSeats) values('TLF', 'L961DU',8 );
insert into firetruck(type, licenseplate, numSeats) values('ULF', 'L098QW', 3);
insert into firetruck(type, licenseplate, numSeats) values('FLF', 'L153NZ', 2);

select * from FIRETRUCK;