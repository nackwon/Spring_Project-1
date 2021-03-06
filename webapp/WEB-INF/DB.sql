// member start//
drop table users;
drop sequence seq_users_no;


create table users (
  no number,
  name varchar2(80),
  email varchar2(100) unique not null,
  password varchar2(20) not null,
  gender  varchar2(10),
  primary key(no)
);

UPDATE users SET name = '낙원', password = '12345', gender = 'female' WHERE no LIKE 11;
SELECT name, no FROM users WHERE email LIKE 'test' AND password LIKE 'test';
DELETE FROM users WHERE no LIKE 6;
create sequence seq_users_no
increment by 1
start with 1
nocache;

insert into users
values (seq_users_no.nextval, 'jimmy', 'iremys@gmail.com', '1234' , 'male');

insert into users
values (seq_users_no.nextval, 'Ȳ�Ͽ�', 'jungusung@gmail.com', '1234' , 'male');

select no, name, email, password, gender
from users
order by no desc;

SELECT email 
FROM users
WHERE email LIKE 'jimmy1';
		
select no, name, email, password, gender
from users
where no = 4;

select no, name, email, gender
from users
where email = 'iremys@gmail.com'
and password = '1234' ;

update users
set name = 'Ȳ�Ͽ�',
    email = 'leehry@gmail.com',
    password = '1234',
    gender ='female'
where no = 4;
// member end//

// Board start//
DROP TABLE board;
CREATE TABLE board(
	no number,
	title VARCHAR2(500),
	content VARCHAR2(4000),
	hit number,
	reg_date DATE,
	user_no NUMBER,
	PRIMARY KEY(no),
	CONSTRAINT c_board_fk FOREIGN KEY (user_no)
	REFERENCES users(no)
);

INSERT INTO board VALUES (seq_board_no.nextval,'hello','hello!',0,sysdate,9);

CREATE SEQUENCE seq_board_no
INCREMENT BY 1
START WITH 1
NOCACHE;

SELECT *  FROM board;

SELECT b.no, b.title, u.name as user_name, b.hit, b.reg_date, b.user_no
FROM board b, users u
WHERE u.no = b.user_no
ORDER BY b.no DESC;

SELECT b.title, b.content, b.no
FROM board b, users u
WHERE u.no = 13 AND b.user_no = 9;
SELECT u.name, b.title
FROM users u, board b
WHERE u.no = b.user_no
AND b.title = 'eee';

SELECT b.no, b.title, b.hit, b.reg_date
FROM board b
WHERE b.title LIKE 'eee';

UPDATE board SET hit = nvl(hit,0)+1 WHERE no LIKE 43;
SELECT count(*) FROM board;

SELECT x.no, x.title, x.name, x.hit, x.reg_date, x.user_no 
					 FROM (SELECT rownum as num, a.no, a.title, a.name, a.hit, a.reg_date, a.user_no
					 	FROM (SELECT b.no, b.title, u.name, b.hit, b.reg_date, b.user_no
					 		FROM board b, users u 
					 		WHERE u.no = b.user_no
					 		ORDER BY b.no DESC) a
					 	WHERE rownum <= 5) x 
					WHERE x.num > 1;

// Board end//

// GuestBook start //

CREATE TABLE guest_tbl(
	no number PRIMARY KEY,
	name VARCHAR2(80) not null,
	password VARCHAR2(20) not null,
	content VARCHAR2(2000) not null,
	reg_date DATE 
);

CREATE SEQUENCE seq_guest_no
INCREMENT BY 1
START WITH 1
NOCACHE;

DROP TABLE guest_tbl;
DROP SEQUENCE seq_guest_no;
SELECT * FROM guest_tbl;
DELETE FROM guest_tbl;
SELECT no, name, password, content, reg_date
FROM (SELECT rownum as num, no, name, password, content, reg_date
	  FROM (SELECT no,name,password,content,reg_date  
	  	    FROM guest_tbl
	  	    ORDER BY no DESC)
	  WHERE rownum <= 5)
WHERE num >= 1;

DECLARE
	i NUMBER := 1;
BEGIN
	while i<=100 loop
	
	INSERT INTO guest_tbl (no, name, password, content, reg_date)
	VALUES(
		seq_guest_no.nextval
		,'jimmy'
		,'1234'
		,i||'째 방명록입니다.'
		,sysdate);
		
		i := i+1;
	END loop;
END;

commit
	
// GuestBook end//

// Gallary start//
CREATE TABLE gallary_tbl(
	no number PRIMARY KEY,
	filepath VARCHAR2(100) not null,
	orgname VARCHAR2(100) not null,
	savename VARCHAR2(100) not null,
	filesize number,
	user_no number,
	CONSTRAINT g_board_fk FOREIGN KEY (user_no)
	REFERENCES users(no)
);
CREATE SEQUENCE seq_gallary_no
INCREMENT BY 1
START WITH 1
NOCACHE;

DROP SEQUENCE seq_gallary_no;
DROP TABLE gallary_tbl;

SELECT no, filepath, orgname, savename, filesize
			FROM gallary_tbl;

// Gallary end//

