CREATE TABLE User (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
    email VARCHAR(80) NOT NULL,
    password CHAR(41) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1 ,
    PRIMARY KEY (id),
    UNIQUE INDEX (email));

CREATE TABLE Board(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    userId int not null
);
CREATE TABLE Comment(
	id BIGINT NOT NULL AUTO_INCREMENT,
    comment varchar(500) NOT NULL,
    Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    boardId BIGINT not null,
    userId int not null,
    PRIMARY KEY(id)
);

CREATE TABLE user_role (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(45) NOT NULL,
  role varchar(45) NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (id),
  UNIQUE KEY uni_email_role (role,email),
  KEY fk_email_idx (email),
  CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES user (email));
  
  
  
  
drop table Board;
drop table User;
drop table Comment;
drop table user_role;


insert into User(name, email, password, enabled) 
values ('jang', 'jang@naver.com', 'j', true);
insert into User(name, email, password, enabled) 
values ('kang', 'kang@naver.com', 'k',true);
insert into User(name, email, password) 
values ('sung', 'sung@naver.com', 's');

select name,email,password from user where email='jang@naver.com';

select * from user;
select * from board order by id desc;
select * from comment;
select * from user_role;

insert into Board(title, content, userId) 
values ('test1', '1번째 테스트입니당.', 1);
insert into Board(title, content, userId) 
values ('test2', '2번째 테스트입니당.', 2);
insert into Board(title, content, userId) 
values ('test3', '3번째 테스트입니당.', 1);

insert into Comment(comment, boardId, userId)
values ('그럼 가시던가요. 님때문에 다른사람들도 그렇게 생각할 거임 ㅇㅇ', 1, 2);
insert into Comment(comment, boardId, userId)
values ('뭔소리지?', 1, 2);
insert into Comment(comment, boardId, userId)
values ('님은 누군가에게 크나큰 아량을 보이신적 있으신가요? 그것부터 하셈', 2, 1);

SELECT userId, comment, date FROM comment WHERE boardId=1;


insert into user_role(email, role) values ('jang@naver.com', 'ROLE_USER');
insert into user_role(email) values ('kang@naver.com');

SELECT email, role FROM user_role WHERE email='jang@naver.com';

delete from board where id=1;