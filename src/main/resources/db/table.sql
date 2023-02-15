create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    fullname varchar not null,
    created_at timestamp not null
);
create table account_tb(
    id int auto_increment primary key,
    number varchar not null unique,
    password varchar not null,
    balance bigint not null,
    user_id int,
    created_at timestamp not null
);
create table history_tb(
    id int auto_increment primary key,
    amount bigint not null,
    w_balance bigint,
    d_balance bigint,
    w_account_id int,
    d_account_id int,
    created_at timestamp not null
);