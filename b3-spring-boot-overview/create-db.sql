drop database if exists product_management; 
create database product_management;
use product_management;

create table category (
	id mediumint unsigned primary key auto_increment,
    name varchar(100),
    code varchar(10) unique,
    status tinyint,
    description varchar(255)
);

create table product (
	id mediumint unsigned primary key auto_increment,
	name varchar(100),
    price float,
    sku varchar(20) unique,
    status tinyint,
    description varchar(255),
    category_id mediumint unsigned,
    foreign key (category_id) references category (id)
);

create table district (
	id mediumint unsigned primary key auto_increment,
    code varchar(10) unique,
    name varchar(100)
);

create table province (
	id mediumint unsigned primary key auto_increment,
	code varchar(10) unique,
    name varchar(100)
);
	
create table warehouse (
	id mediumint unsigned primary key auto_increment,
	name varchar(100),
    address varchar(255),
    status tinyint,
    province_id mediumint unsigned,
    district_id mediumint unsigned,
    foreign key (province_id) references province (id),
    foreign key (district_id) references district (id)
);

create table warehouse_product (
	id mediumint unsigned primary key auto_increment,
    product_id mediumint unsigned,
    warehouse_id mediumint unsigned,
    inventory mediumint,
    total_import mediumint unsigned,
    total_export mediumint unsigned,
    start_date datetime,
    expired_date datetime,
    foreign key (product_id) references product(id),
    foreign key (warehouse_id) references warehouse(id)
);


	