insert into cloud_service.roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN');

insert into cloud_service.users (username, password)
values
    ('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
    ('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
-- 100
-- 101?

insert into cloud_service.users_roles (user_id, role_id)
values
    (1, 1),
    (2, 2);