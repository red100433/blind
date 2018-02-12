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
values ('카페 사람 수 실화임?', '아니 저기요 너무 많아요. 좀 나가주실래요? 천국가기 싫으시면?', 1);
insert into Board(title, content, userId) 
values ('네이버 포인트 왜이리 짜냐 와 너무 한거 아니냐?', '아니 리얼루다가 처음에는 퍼다 줘서 인지도 높이더니 이제 좀 유명해지고 다른 은행이랑 연결 되니까 바로 그냥 바닷물처럼 개 짜네 ㅡ,.ㅡ', 2);
insert into Board(title, content, userId) 
values ('3Test입니당', '3번째 테스트입니당.', 1);

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