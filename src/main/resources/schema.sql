drop table if exists preference_keyword;
drop table if exists preference_user;
drop table if exists preference;
drop table if exists availability;
drop table if exists allocation;
drop table if exists project_keyword;
drop table if exists project;
drop table if exists user;

create table user
(
	id varchar(32) not null primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	email varchar(250) not null,
	telephone1 varchar(20) not null,
	telephone2 varchar(20),
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table project
(
	id varchar(32) not null primary key,
	title varchar(50) not null,
	description text not null,
  location varchar(100) not null,
  start datetime not null,
  end datetime not null,
  status varchar(10) not null,
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table project_keyword
(
	id varchar(32) not null primary key,
	project_id varchar(32) not null,
	value varchar(50) not null,
	notes varchar(250),
	auto_generated integer not null,
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table allocation
(
	id varchar(32) not null primary key,
	user_id varchar(32) not null,
	project_id varchar(32) not null,
	start datetime not null,
	end datetime not null,
	strength double not null,
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table availability
(
	id varchar(32) not null primary key,
	user_id varchar(32) not null,
	start datetime not null,
	end datetime not null,
	notes varchar(250),
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table preference
(
	id varchar(32) not null primary key,
	user_id varchar(32) not null,
	type varchar(10) not null,
	notes varchar(250),
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table preference_keyword
(
	id varchar(32) not null primary key,
	preference_id varchar(32) not null,
	value varchar(50) not null,
	notes varchar(250),
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);

create table preference_user
(
	id varchar(32) not null primary key,
	preference_id varchar(32) not null,
	user_id varchar(32) not null,
	notes varchar(250),
	created_by varchar(32) not null,
	created_on datetime not null,
	modified_by varchar(32) not null,
	modified_on datetime not null
);