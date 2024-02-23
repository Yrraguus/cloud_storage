# drop schema if exists cloud_service;
# create schema cloud_service;

create table if not exists cloud_service.users
(
    id int auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    primary key (id)
);

create table if not exists cloud_service.files
(
    id int primary key auto_increment,
    filename varchar(255) not null,
    data BLOB not null,
    size long,
    user_id int not null,
    FOREIGN KEY (user_id) REFERENCES cloud_service.users (id)
);

create table if not exists cloud_service.roles (
    id                    int auto_increment,
    name                  varchar(50) not null,
    primary key (id)
);

CREATE TABLE if not exists cloud_service.users_roles (
user_id               int not null,
role_id               int not null,
primary key (user_id, role_id),
foreign key (user_id) references cloud_service.users (id),
foreign key (role_id) references cloud_service.roles (id)
);

# insert into roles (name)
#            values
#                ('ROLE_USER'), ('ROLE_ADMIN');
#
# insert into users (username, password)
# values
#     ('user1', '$2a$12$iYfReD4haV7Xw0ZUNn0u3ue4Hu3e.M5ShzqBrw0bTSUj5WMShneB2'), --101
#     ('user2', '$2a$12$gdD.Au.o4W70D1C59i8gAuOo3v4k.C86iV0gLHqIhs9x5JGZEAw8K'); --102
#
#
# insert into users_roles (user_id, role_id)
# values
#     (1, 1),
#     (2, 2);