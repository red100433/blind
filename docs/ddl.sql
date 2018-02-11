drop table teacher;
drop table grade;
drop table subject;
drop table employee;
drop table student;
create table subject (
	sub_id int primary key auto_increment,
    sub_name varchar(50) not null
    );
    
create table student (
	stu_id int primary key auto_increment,
    stu_name varchar(50) not null,
    stu_birth varchar(50) not null
    );

create table employee (
	emp_id int primary key auto_increment,
    emp_name varchar(50) not null,
    emp_birth varchar(50) not null
    );

create table teacher (
	tea_id int primary key auto_increment,
    tea_name varchar(50) not null,
    tea_birth varchar(50) not null,
    tea_subId int,
	FOREIGN KEY (tea_subId)
        REFERENCES subject(sub_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
	);
    
create table grade (
	gra_id int primary key auto_increment,
	gra_stuId int not null,
    gra_subId int not null,
    gra_score int default 0,
    
    unique key (gra_stuId, gra_subId),
    FOREIGN KEY (gra_stuId)
        REFERENCES student(stu_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY (gra_subId)
        REFERENCES subject(sub_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
        );


insert into subject(sub_name) values ('java') , ('c++'), ('c');

insert into student(stu_name, stu_birth) values ('jang', '19990228');
insert into student(stu_name, stu_birth) values ('jin', '19980201');
insert into student(stu_name, stu_birth) values ('seo', '19970128');

insert into employee(emp_name, emp_birth) values ('kim', '19701128');
insert into employee(emp_name, emp_birth) values ('lee', '19871225');
insert into employee(emp_name, emp_birth) values ('jong', '19850418');

insert into teacher(tea_name, tea_birth) values ('su', '19840317');
insert into teacher(tea_name, tea_birth, tea_subId) values ('mina', '19830510',1);
insert into teacher(tea_name, tea_birth, tea_subId) values ('jina', '19820117',2);

insert into grade(gra_stuId, gra_subId, gra_score) values (1,1,30);
insert into grade(gra_stuId, gra_subId, gra_score) values (2,1,40);
insert into grade(gra_stuId, gra_subId, gra_score) values (3,1,70);

insert into grade(gra_stuId, gra_subId, gra_score) values (1,2,80);
insert into grade(gra_stuId, gra_subId, gra_score) values (2,2,75);
insert into grade(gra_stuId, gra_subId, gra_score) values (3,2,73);

select * from subject;
select * from student;
select * from employee;
select * from teacher;
select * from grade;


SELECT subId, subjectName FROM subject WHERE subId=3;

INSERT INTO subject (id,name) VALUES (1,'kkk') ON DUPLICATE KEY UPDATE name='kkk';


SELECT g.gra_id
		, stu.stu_id
		, stu.stu_name
		, sub.sub_id
		, sub.sub_name
		, g.gra_score
		FROM grade g
		INNER JOIN student stu ON stu.stu_id = g.gra_stuId
		INNER JOIN subject sub ON sub.sub_id = g.gra_subId;
        
SELECT s.id AS stuId, AVG(score) AS average
		FROM grade g
		INNER JOIN student s ON
		g.stuId = s.id
		GROUP BY g.stuId;
