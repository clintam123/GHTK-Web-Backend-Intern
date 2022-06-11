create database student_ghtk;
use student_ghtk;

create table first_round_score (
	id bigint primary key auto_increment,
    first_year tinyint unsigned,
    second_year tinyint unsigned,
    third_year tinyint unsigned,
    fourth_year tinyint unsigned,
    fifth_year tinyint unsigned,
    priority_score tinyint unsigned
);

create table student (
	id bigint primary key auto_increment,
    full_name varchar(20) not null,
    student_code varchar(20) not null,
    school varchar(20),
    class varchar(20),
    birthday date,
    sex varchar(20),
    birthplace varchar(20),
    ethnic varchar(20),
    address varchar(50),
    phone varchar(11),
    score_id bigint,
    description varchar(255),
    foreign key (score_id) references first_round_score(id)
);