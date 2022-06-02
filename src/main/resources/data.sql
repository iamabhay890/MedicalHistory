create table if not exists users(
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(255),
email varchar(255),
phone varchar(20),
age int(3),
password varchar(255),
gender varchar(20)
);

alter table users add unique(email);

insert into users(id,name,email,phone,age,password,gender)
SELECT * FROM (SELECT 1 as id,'Administrator' as name,'admin' as email,'9839112345' as phone,35,'admin' as password,'male'as gender)as temp
where  not exists(select email from users where email='admin') limit 1;