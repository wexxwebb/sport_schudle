/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 16-���-2018 7:40:40 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

 

/* Drop Tables */

DROP TABLE IF EXISTS person CASCADE
;

/* Create Tables */

CREATE TABLE person
(
	value serial NOT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	birthday date NOT NULL,
	sex_id integer NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE person ADD CONSTRAINT PK_persons
	PRIMARY KEY (value)
;

/* Create Foreign Key Constraints */

ALTER TABLE person ADD CONSTRAINT FK_person_s_sex
	FOREIGN KEY (sex_id) REFERENCES sex (value) ON DELETE Restrict ON UPDATE Cascade
;

/* Create Table Comments, Sequences for Autonumber Columns */

 