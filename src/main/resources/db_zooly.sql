-- create schema
create schema zooly;

-- create new roles
insert into zooly.user_role values (1, 'ROLE_ADMIN');
insert into zooly.user_role values (2, 'ROLE_MANAGER');
insert into zooly.user_role values (3, 'ROLE_DOCTOR');
insert into zooly.user_role values (4, 'ROLE_OPERATOR');

select * from zooly.user_role ur;

-- create sample user

insert into zooly.user (cd_user, name, email, password, cd_role, active, creation_date) values (1, 'King Arthur', 'kingdom@reign.com', '123', 1, true, CURRENT_TIMESTAMP);
insert into zooly.user (cd_user, name, email, password, cd_role, active, creation_date) values (4, 'Operator', 'operator@zooly.com', '123', 4, true, CURRENT_TIMESTAMP);
insert into zooly.user (cd_user, name, email, password, cd_role, active, creation_date) values (2, 'Manager', 'manager@zooly.com', '123', 2, true, CURRENT_TIMESTAMP);
insert into zooly.user (cd_user, name, email, password, cd_role, active, creation_date) values (3, 'Doctor', 'doctor@zooly.com', '123', 3, true, CURRENT_TIMESTAMP);

select * from zooly.user;
delete from zooly.user;

-- delete schema
drop schema zooly cascade;