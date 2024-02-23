insert into cloud_service.roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into cloud_service.users (username, password)
values ('user1', '$2a$12$iYfReD4haV7Xw0ZUNn0u3ue4Hu3e.M5ShzqBrw0bTSUj5WMShneB2'), #101
       ('user2', '$2a$12$gdD.Au.o4W70D1C59i8gAuOo3v4k.C86iV0gLHqIhs9x5JGZEAw8K'); #102
insert into cloud_service.users_roles (user_id, role_id)
values (1, 1),
       (2, 2);