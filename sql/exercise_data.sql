/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 16-���-2018 7:40:40 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

 

/* Drop Tables */

DROP TABLE IF EXISTS exercise_data CASCADE
;

/* Create Tables */

CREATE TABLE exercise_data
(
	value serial NOT NULL,
	name varchar(100) NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE exercise_data ADD CONSTRAINT PK_s_exercises
	PRIMARY KEY (value)
;

ALTER TABLE exercise_data 
  ADD CONSTRAINT name_unique UNIQUE (name)
;

/* Create Table Comments, Sequences for Autonumber Columns */

 