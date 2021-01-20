CREATE SCHEMA IF NOT EXISTS reimbursement;
set schema 'reimbursement';


create table ers_reimbursement_status(reimb_status_id int primary key, reimb_status text);
create table ers_reimbursement_type(reimb_type_id int primary key, reimb_type text);
create table ers_users_roles(ers_user_role_id int primary key, user_role text);
create table ers_users(ers_users_id serial primary key, ers_username text, ers_password text, user_first_name text, user_last_name text, user_email text, user_role_id int references ers_users_roles(ers_user_role_id));
create table ers_reimbursement(reimb_id serial primary key, reimb_amount numeric(10,2), reimb_submitted timestamp, reimb_resolved timestamp, reimb_description text, reimb_receipt bytea, reimb_author int references ers_users(ers_users_id), reimb_resolver int references ers_users(ers_users_id), reimb_status_id int references ers_reimbursement_status(reimb_status_id), reimb_type_id int references ers_reimbursement_type(reimb_type_id) );

 
insert into ers_reimbursement_type values(1, 'Travel Expenses');
insert into ers_reimbursement_type values(4, 'Lodging Expenses');
insert into ers_reimbursement_type values(2, 'Medical Expenses');
insert into ers_reimbursement_type values(3, 'Books and Equipment Expenses');
insert into ers_reimbursement_type values(5, 'Auto payments');



insert into ers_users_roles values (1, 'Finance manager');
insert into ers_users_roles values (2, 'Employee');
insert into ers_reimbursement_status values (1, 'Pending');
insert into ers_reimbursement_status values (2, 'On hold');
insert into ers_reimbursement_status values (3, 'Approved');
insert into ers_reimbursement_status values (4, 'Rejected');



insert into ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values('testfinancialmanager', 'password', 'Ted', 'Doe', 'ted@outlook.com', 1);
insert into ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values('testemployee', 'password', 'John', 'Smith', 'john@outlook.com', 2);
