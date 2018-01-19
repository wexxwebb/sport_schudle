/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 13.0 		*/
/*  Created On : 16-���-2018 7:40:40 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Sequences for Autonumber Columns */

 

/* Drop Tables */

DROP TABLE IF EXISTS state CASCADE
;

/* Create Tables */

CREATE TABLE state
(
	id serial NOT NULL,
	state varchar(50) NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE state ADD CONSTRAINT PK_s_state
	PRIMARY KEY (id)
;

ALTER TABLE state 
  ADD CONSTRAINT state_unique UNIQUE (state)
;

/* Create Table Comments, Sequences for Autonumber Columns */

 