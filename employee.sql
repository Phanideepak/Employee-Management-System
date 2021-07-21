drop database if exists employee_ms;
create database employee_ms;
use employee_ms;

CREATE TABLE department(id INT AUTO_INCREMENT, 
 dept_name VARCHAR(40) UNIQUE,
 created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
 created_by VARCHAR(40), 
 last_updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
 last_updated_by VARCHAR(40) NOT NULL,
 PRIMARY KEY(id)
);
alter table department auto_increment=14;
drop table employee;
drop table department;
select * from department;
select * from employee;
delete  from department where id=8;

CREATE TABLE employee(
id INT AUTO_INCREMENT,
first_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
phone_number VARCHAR(20) NOT NULL,
job VARCHAR(100) NOT NULL,
date_of_birth VARCHAR(100) NOT NULL,
joining_date Date NOT NULL,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
created_by VARCHAR(40) NOT NULL, 
last_updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
last_updated_by VARCHAR(40) NOT NULL,
salary INT NOT NULL,
department_id INT,
PRIMARY KEY(id), 
FOREIGN KEY(department_id) references department(id) ON DELETE SET NULL
);
show tables like "department";
ALTER TABLE employee AUTO_INCREMENT=2000;

insert into department(dept_name,created_by,last_updated_by)
values("HR","admin","admin"), ("Product","Director-Engineering","admin"), ("Finance","CFO","admin"),
("Tech","Associate Program Manager","Manager"), ("Bussiness","admin","admin"), ("QA","admin","Testing"),
("Risk-Analytics","admin","admin");

insert into employee(first_name,last_name,email,phone_number,job,date_of_birth,joining_date,salary,
department_id,created_by,last_updated_by)
values ("Phani","Deepak","phanideepak679@gmail.com","+918500160586","SDE1","1999-12-10","2021-06-21",1100000,4,'HR','admin'),
("Sai","Satvik","saisatvik56@gmail.com","+917580169586","APM","1994-11-10","2021-06-23",1600000,2,'HR','admin'),
("Mohan","Reddy","mohanreddy@gmail.com","+919492715025","Director","1980-06-01","2021-06-19",4000000,5,
'HR','admin'),
("Chandra","Teja","chandrateja89@gmail.com","+918700654321","SDE2","1996-08-02","2021-04-07","1600000",4,
'HR','admin'),
("Siva","Shankar","shivshankarreddy@gmail.com","+917654321067","SDE1","1999-07-01","2021-06-30","1000000",
4,'HR','admin'),
("Arjun","Kumar","arjunkumar@gmail.com","+918463207865","Senior Software Engineer","1992-07-08","2021-07-01","1800000",
4,'HR','admin'),
("Manoj","Gupta","manojgupta13@gmail.com","+919493567410","SDE2","1996-09-17","2021-06-13","1650000",4,
'HR','admin'),
("Vishal","Tiwari","vishaltiwari@gmail.com","+918765302190","Associate Product Manager","1995-09-14",
"2021-06-13","1900000",2,'HR','admin'),
("Shruti","Shetty","shrutishetty78@gmail.com","+917845104987","Bussiness Development Analyst","1998-06-13",
"2021-06-13","1000000",5,'HR','admin'),
("Arun","Shetty","arunshetty78@gmail.com","+917049104087","Bussiness Development Analyst","1998-07-09",
"2021-06-13","1000000",5,'HR','admin')
;
/** Update queries **/
update employee set salary=salary+1000 where department_id=4;
update employee join department on employee.department_id=department.id set salary=salary+25000 
where department.dept_name="Tech";
update employee set department_id=2 where department_id is NULL;

/** select queries **/
select first_name,last_name,salary from employee where department_id;
select first_name,salary from employee;
select first_name,department.dept_name,salary from employee join department on department_id=department.id 
where department.dept_name="Tech";
select count(*),department_id,id from employee group by employee.department_id;
select * from employee;
select * from department;
delete from department where id>0;
alter table department auto_increment=1;
select min(salary) as minimum_salary from employee;
select max(salary) as maximum_salary from employee;
select  min(salary) as minimum_salary,max(salary) as max_salary, avg(salary) as average_salary
from employee;
select * from employee where id=105 or 1=1;  /* SQL Injection Based on ""="" is Always True */


/** delete queries**/
delete from department where id=2;

/** CREATING SQL VIEW of employee personal details ***/
create view employee_personal_details as (select first_name,last_name,email,phone_number,date_of_birth
from employee);
create view employee_work_details as (select employee.id,first_name,email,phone_number,job,salary,department.dept_name
from employee join department on employee.department_id=department.id);
create view department_strength as (select dept_name, count(*) as no_of_employees, avg(salary) from employee join 
department on employee.department_id=department.id group by employee.department_id );

select * from employee_personal_details;
select * from employee_work_details;
select * from department_strength;

/** Working on stored procedure **/
drop  procedure get_nth_highest_salary;
create procedure get_nth_highest_salary(n int) 
(select first_name,salary from (select * from ems.employee order by salary desc limit n) as sub_employee
 order by salary asc limit 1
   );
call get_nth_highest_salary(3);
call get_nth_highest_salary(1);

drop procedure giveBonusToTechTeam;
create procedure giveBonusToTechTeam()
 update employee join department on employee.department_id=department.id
 set salary=salary*1.1 where department.dept_name="Tech"
;
update employee set salary = salary+400000 where id= 7;

call giveBonusToTechTeam();

create procedure getEmployeeWhoJoinedInLastMonth()
 select * from employee where joining_date between '2021-06-01' AND '2021-06-30';
 ;
/* drop procedure getEmployeeWhoJoinedInLastMonth; */
call getEmployeeWhoJoinedInLastMonth();

create procedure calculateEmployeeAge(x int)
  select date_format(from_days(datediff(now(),date_of_birth)),'%Y')+0 as Age from employee where
  id=x;
;
drop procedure calculateEmployeeAge;
call calculateEmployeeAge(2003); /** employee_id is passed as parameter **/

/** Get Employyes between certain age group **/
/**
create procedure findEmployeesBetweenAgeGroups(A int,B int)
  select * from employee where calculateEmployeeAge(employee.id) between A and B;
;  
call findEmployeesBetweenAgeGroups(30,40);
**/

/*** working on user defined functions **/
