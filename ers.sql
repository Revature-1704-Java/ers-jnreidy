--new sql script for ers
CREATE USER ers
IDENTIFIED BY thisisthepassword
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to ers;
GRANT resource to ers;
GRANT create session TO ers;
GRANT create table TO ers;
GRANT create view TO ers;

create table employees (
    id number,
    name varchar2(20),
    pwd varchar2(200)
);

create table reimbursements (
    id number primary key,
    eid number, 
    amount number,
    status varchar2(200),
    constraint fk_restraint foreign key (eid) references employees(id)
);

alter table employees add manager number;

alter table employees add constraint checkmanager check (manager between 0 and 1);
    
alter table employees add constraint pk_restraint
    primary key (id);
    
create sequence autoincrement start with 1;

create or replace trigger autopktrigger 
before insert on employees 
for each row
begin 
    select autoincrement.nextval into :new.id from dual;
end;
/
create sequence autoincrement2 start with 1;

create or replace trigger autopktrigger2 
before insert on reimbursements 
for each row
begin 
    select autoincrement2.nextval into :new.id from dual;
end;
/
insert into employees(name, pwd, manager) values ('jack', 'password', 0);
insert into employees(name, pwd, manager) values ('pat', 'p', 0);
insert into employees(name, pwd, manager) values ('ed', 'e', 0);
insert into employees(name, pwd, manager) values ('rita', 'r', 1);

insert into reimbursements(eid, amount, status) values(6, 50, 'pending');
insert into reimbursements(eid, amount, status) values(7, 50, 'pending');
insert into reimbursements(eid, amount, status) values(8, 50, 'pending');


commit;
