-- psql -U postgres -d hot_wheels -a -f load.sql

insert into customer (Name, address) values ('Mingchi Li', 'smith landing');
insert into customer (Name, address) values ('Guangchen Li', 'maple');
insert into customer (Name, address) values ('Li Xing', 'foxridge');

insert into cars (Year, Make, Model, Customer_Id) values ('1995', 'BMW', 'M3', 1);
insert into cars (Year, Make, Model, Customer_Id) values ('1996', 'BMW', 'M6', 1);
insert into cars (Year, Make, Model, Customer_Id) values ('1997', 'Ford', '150', 2);
insert into cars (Year, Make, Model, Customer_Id) values ('1998', 'Ford', '250', 2);
insert into cars (Year, Make, Model, Customer_Id) values ('1999', 'BYD', 'F1', 3);
insert into cars (Year, Make, Model, Customer_Id) values ('2000', 'BYD', 'F3', 3);


insert into telnumber (Customer_Id, Tel_Number) values (1, '540-111-1111');
insert into telnumber (Customer_Id, Tel_Number) values (1, '540-111-2222');
insert into telnumber (Customer_Id, Tel_Number) values (2, '540-222-1111');
insert into telnumber (Customer_Id, Tel_Number) values (2, '540-222-2222');
insert into telnumber (Customer_Id, Tel_Number) values (3, '540-333-1111');
insert into telnumber (Customer_Id, Tel_Number) values (3, '540-333-2222');


insert into certification (description) values ('rebuild transmissions');
insert into certification (description) values ('rebuild engine');
insert into certification (description) values ('rebuild electric circuit');

insert into mechanic (name, hourly_rate) values ('Xukun Cai', 10);
insert into mechanic (name, hourly_rate) values ('Kris Wu', 15);

insert into mechanic_certifications (mechanics_id,  certifications_id) values (1, 1);
insert into mechanic_certifications (mechanics_id,  certifications_id) values (1, 2);
insert into mechanic_certifications (mechanics_id,  certifications_id) values (2, 1);
insert into mechanic_certifications (mechanics_id,  certifications_id) values (2, 2);
insert into mechanic_certifications (mechanics_id,  certifications_id) values (2, 3);

insert into part (cost, name) values (500, 'computer');
insert into part (cost, name) values (100, 'wire');
insert into part (cost, name) values (1500, 'transmission');
insert into part (cost, name) values (2000, 'engine');

insert into repair_type (description, hours, certification_id) values ('upgrade computer',15, 3);
insert into repair_type (description, hours, certification_id) values ('upgrade transmission', 25, 1);
insert into repair_type (description, hours, certification_id) values ('upgrade engine', 30, 2);

insert into repair_type_parts (repair_types_id, parts_id) values (1, 1);
insert into repair_type_parts (repair_types_id, parts_id) values (1, 2);
insert into repair_type_parts (repair_types_id, parts_id) values (2, 3);
insert into repair_type_parts (repair_types_id, parts_id) values (3, 4);

insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-05-01', 1, 1, 3);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-05-02', 1, 1, 2);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-05-03', 2, 2, 1);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-04-01', 3, 1, 1);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-04-20', 3, 2, 2);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-06-01', 4, 2, 1);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-12-05', 5, 1, 1);
insert into maintenance_record (record_date, car_id, mechanic_id, repair_id) values ('2019-08-11', 6, 2, 2);