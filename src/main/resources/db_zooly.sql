-- create new roles
insert into zooly.user_role values (1, 'ADMIN');
insert into zooly.user_role values (2, 'MANAGER');
insert into zooly.user_role values (3, 'DOCTOR');
insert into zooly.user_role values (4, 'OPERATOR');

select * from zooly.user_role ur;

-- create sample user

insert into zooly.`user` (cd_user, name, email, password, cd_role, active, creation_date) values (1, 'King Arthur', 'kingdom@reign.com', 'imtheking123', 1, true, CURRENT_TIMESTAMP());

select * from zooly.`user`;
delete from zooly.`user`;

-- delete schema
drop schema zooly;
