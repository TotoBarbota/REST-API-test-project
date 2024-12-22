insert into user_details(id, birth_date, name)
values(10001, current_date(), 'Tini Anasta');
insert into user_details(id, birth_date, name)
values(10002, current_date(), 'Toto Kara');

insert into post(id, description, user_id)
values(20001, 'Learning Spring', 10002);
insert into post(id, description, user_id)
values(20002, 'Learning Java', 10002);
insert into post(id, description, user_id)
values(20003, 'Searching for Master', 10001);
insert into post(id, description, user_id)
values(20004, 'Searching for Bachelor', 10001);