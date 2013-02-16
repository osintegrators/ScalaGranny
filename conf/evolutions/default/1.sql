# Address schema

# --- !Ups

CREATE SEQUENCE address_id_seq;
CREATE TABLE address (
	id integer NOT NULL DEFAULT nextval('address_id_seq'),
	name varchar(255),
	address varchar(255),
	phone varchar(255),
	email varchar(255)
);

# --- !Downs

DROP TABLE address
DROP SEQUENCE address_id_seq;
