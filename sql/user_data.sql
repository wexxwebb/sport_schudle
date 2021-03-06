/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 16-���-2018 7:40:40 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

 

/* Drop Tables */

DROP TABLE IF EXISTS user_data CASCADE
;

/* Create Tables */

CREATE TABLE user_data
(
	value serial NOT NULL,
	person_id integer NOT NULL,
	user_login varchar(50) NOT NULL,
	user_password varchar(50) NOT NULL,
	state_id integer NOT NULL   DEFAULT 1,
	date_reg date NOT NULL   DEFAULT NOW()
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE user_data ADD CONSTRAINT PK_users
	PRIMARY KEY (value)
;

ALTER TABLE user_data 
  ADD CONSTRAINT login_unique UNIQUE (user_login)
;

/* Create Foreign Key Constraints */

ALTER TABLE user_data ADD CONSTRAINT FK_users_persons
	FOREIGN KEY (person_id) REFERENCES person (value) ON DELETE Restrict ON UPDATE Cascade
;

ALTER TABLE user_data ADD CONSTRAINT FK_users_s_state
	FOREIGN KEY (state_id) REFERENCES state (value) ON DELETE Restrict ON UPDATE Cascade
;

/* Create Table Comments, Sequences for Autonumber Columns */

 