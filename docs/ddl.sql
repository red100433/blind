create table subject (
	id int primary key auto_increment,
    name varchar(50) not null
    );
    
create table student (
	id int primary key auto_increment,
    name varchar(50) not null,
    birth varchar(50) not null
    );

create table employee (
	id int primary key auto_increment,
    name varchar(50) not null,
    birth varchar(50) not null
    );

create table teacher (
	id int primary key auto_increment,
    name varchar(50) not null,
    birth varchar(50) not null,
    subId int,
	FOREIGN KEY (subId)
        REFERENCES subject(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
	);
    
create table grade (
	id int primary key auto_increment,
	stuId int not null,
    subId int not null,
    score int default 0,
    
    unique key (stuId, subId),
    FOREIGN KEY (stuId)
        REFERENCES student(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY (subId)
        REFERENCES subject(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
        );


insert into subject(name) values ('java') , ('c++'), ('c');

insert into student(name, birth) values ('jang', '19990228');
insert into student(name, birth) values ('jin', '19980201');
insert into student(name, birth) values ('seo', '19970128');

insert into employee(name, birth) values ('kim', '19701128');
insert into employee(name, birth) values ('lee', '19871225');
insert into employee(name, birth) values ('jong', '19850418');

insert into teacher(name, birth) values ('su', '19840317');
insert into teacher(name, birth, subId) values ('mina', '19830510',1);
insert into teacher(name, birth, subId) values ('jina', '19820117',2);

insert into grade(stuId, subId, score) values (1,1,30);
insert into grade(stuId, subId, score) values (2,1,40);
insert into grade(stuId, subId, score) values (3,1,70);

insert into grade(stuId, subId, score) values (1,2,80);
insert into grade(stuId, subId, score) values (2,2,75);
insert into grade(stuId, subId, score) values (3,2,73);

select * from subject;
select * from student;
select * from employee;
select * from teacher;
select * from grade;

drop table teacher;
drop table grade;
drop table subject;
drop table employee;
drop table student;
SELECT subId, subjectName FROM subject WHERE subId=3;
