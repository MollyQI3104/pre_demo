create schema tododb;
create table task
(
  id          int auto_increment
    primary key,
  
  content     varchar(255) null,
  create_date datetime     null,
  status      varchar(255) null,
  task_date   date         not null,
  task_name   varchar(255) null,
  user_id     int          null
);
create table user
(
  id       int auto_increment
    primary key,
  password varchar(255) null,
  username varchar(255) null
);