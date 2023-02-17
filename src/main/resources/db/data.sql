insert into user_tb(username, password, fullname, created_at) values('ssar', '1234', '쌀',now());
insert into user_tb(username, password, fullname, created_at) values('cos', '1234', '코스',now());

insert into account_tb(number, password, balance, user_id, created_at) values('1111', '1234', 900, 1, now());
insert into account_tb(number, password, balance, user_id, created_at) values('2222', '1234', 1100, 2, now());
insert into account_tb(number, password, balance, user_id, created_at) values('3333', '1234', 1000, 1, now());

insert into history_tb(amount, w_balance, d_balance, w_account_id, d_account_id, created_at) values(100, 900, 1100, 1, 2, now());
insert into history_tb(amount, w_balance, d_balance, w_account_id, d_account_id, created_at) values(100, 800, null, 1, null, now());
insert into history_tb(amount, w_balance, d_balance, w_account_id, d_account_id, created_at) values(100, null, 900, null, 1, now());

commit; 