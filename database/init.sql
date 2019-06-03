create database `advertisement_screen`;

use `advertisement_screen`;

create table if not exist `advertisements` (
	advertisement_id int primary key auto_increment,
	advertisement_name varchar(255) not null,
	advertisement_data_type varchar(255) not null,
	advertisement_source text not null,
	advertisement_time int not null
);

create table if not exist `news`(
	news_id int primary key auto_increment,
	news_name varchar(255) not null,
	news_date date not null,
	news_time int not null
);