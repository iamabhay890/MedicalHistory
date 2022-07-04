create table if not exists users(
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(255),
email varchar(255),
phone varchar(20),
age int(3),
password varchar(255),
address varchar(600),
adhar_no varchar(255),
gender varchar(20),
status int(3),
created_date varchar(25),
modified_date varchar(25)
);

alter table users add unique(email);

insert into users(id,name,email,phone,age,password,address,adhar_no,gender,status,created_date,modified_date)
SELECT * FROM (SELECT -1 as id,
'Administrator' as name,
'admin' as email,
'9839112345' as phone,
35 as age,
'$2a$10$3qw7ve3q3bY2O49U92b4U.2R6YeEo0DjbHLxrWe60v2s9R9uYtMVe' as password,
'Noida' as address,
'1234567890123456' as adhar_no,
'male'as gender,
0 as status,
CURRENT_TIMESTAMP() as created_date,
CURRENT_TIMESTAMP() as modified_date)as temp
where  not exists(select email from users where email='admin') limit 1;

create table if not exists role(
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(40)
);

insert into role(id,name)
SELECT * FROM(SELECT -1 as id,'ROLE_ADMIN' as name)as temp
where not exists(select name from role where name='ROLE_ADMIN') limit 1;

create table if not exists users_roles(
user_id int,
role_id int PRIMARY KEY
);
 insert into users_roles(user_id,role_id)
 SELECT * FROM(select -1 as user_id,-1 as role_id)as temp
 where not exists(select role_id from users_roles where role_id=-1) limit 1;
