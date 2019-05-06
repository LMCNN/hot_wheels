-- psql -U postgres -d hot_wheels -a -f install.sql

drop table if exists customer cascade;
drop sequence if exists customer_id;

drop table if exists cars cascade;
drop sequence if exists car_id;

drop table if exists telnumber cascade;

drop table if exists certification cascade;
drop sequence if exists certification_id;

drop table if exists mechanic cascade;
drop sequence if exists mechanic_id;

drop table if exists mechanic_certifications cascade;

drop table if exists part cascade;
drop sequence if exists part_id;

drop table if exists repair_type cascade;
drop sequence if exists repair_id;

drop table if exists repair_type_parts cascade;

drop table if exists maintenance_record cascade;
drop sequence if exists maintenance_id;

CREATE SEQUENCE Customer_Id;
CREATE TABLE Customer
(
	Id INT DEFAULT nextval('Customer_Id') NOT NULL,
	Name VARCHAR(60) NOT NULL,
	Address VARCHAR(256) NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE (Name)
);

CREATE SEQUENCE Car_Id;
CREATE TABLE Cars
(
	Id INT DEFAULT nextval('Car_Id') NOT NULL,
	Year VARCHAR(16) NOT NULL,
	Make VARCHAR(32) NOT NULL,
	Model VARCHAR(32) NOT NULL,
	Customer_Id INT NOT NULL,
	PRIMARY KEY (Id),
	FOREIGN KEY (Customer_Id) REFERENCES Customer(Id)
);

CREATE TABLE TelNumber
(
	Customer_Id INT NOT NULL,
	tel_number VARCHAR(32) NOT NULL,
	PRIMARY KEY (tel_number, Customer_Id),
	FOREIGN KEY (Customer_Id) REFERENCES Customer(Id)
);

CREATE SEQUENCE Certification_Id;
CREATE TABLE Certification
(
	Id INT DEFAULT nextval('Certification_Id') NOT NULL,
	description VARCHAR(256) NOT NULL,
	PRIMARY KEY (Id)
);

CREATE SEQUENCE Mechanic_Id;
CREATE TABLE Mechanic
(
	Id INT DEFAULT nextval('Mechanic_Id') NOT NULL,
	Name VARCHAR(60) NOT NULL,
	hourly_rate INT NOT NULL,
	PRIMARY KEY (Id)
);

CREATE TABLE Mechanic_Certifications
(
	Mechanics_Id INT NOT NULL,
	Certifications_Id INT NOT NULL,
	FOREIGN KEY (Mechanics_Id) REFERENCES Mechanic(Id),
	FOREIGN KEY (Certifications_Id) REFERENCES Certification(Id)
);

CREATE SEQUENCE Part_Id;
CREATE TABLE Part
(
	Id INT DEFAULT nextval('Part_Id') NOT NULL,
	Cost INT NOT NULL,
	Name VARCHAR(60) NOT NULL,
	PRIMARY KEY (Id)
);

CREATE SEQUENCE Repair_Id;
CREATE TABLE Repair_Type
(
	Id INT DEFAULT nextval('Repair_Id') NOT NULL,
	description VARCHAR(256) NOT NULL,
	hours INT NOT NULL,
	Certification_Id INT NOT NULL,
	PRIMARY KEY (Id),
	FOREIGN KEY (Certification_Id) REFERENCES Certification(Id)
);

CREATE TABLE Repair_Type_Parts
(
	Repair_Types_Id INT NOT NULL,
	Parts_Id INT NOT NULL,
	FOREIGN KEY (Repair_Types_Id) REFERENCES Repair_Type(Id),
	FOREIGN KEY (Parts_Id) REFERENCES Part(Id)
);

CREATE SEQUENCE Maintenance_Id;
CREATE TABLE Maintenance_Record
(
	Id INT DEFAULT nextval('Maintenance_Id') NOT NULL,
	Record_Date VARCHAR(60) NOT NULL,
	Car_Id INT NOT NULL,
	Mechanic_Id INT NOT NULL,
	Repair_Id INT NOT NULL,
	PRIMARY KEY (Id),
	FOREIGN KEY (Car_Id) REFERENCES Cars(Id),
	FOREIGN KEY (Mechanic_Id) REFERENCES Mechanic(Id),
	FOREIGN KEY (Repair_Id) REFERENCES Repair_Type(Id)
);
