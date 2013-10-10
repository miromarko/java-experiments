CREATE TABLE USERS ( principal_id VARCHAR(64) primary key,password VARCHAR(64));
CREATE TABLE ROLES ( principal_id VARCHAR(64),user_role VARCHAR(64),role_group VARCHAR(64));

Insert into USERS values('user1','user1');
Insert into USERS values('user2','user2');

Insert into ROLES values('user1','ADMIN','ADMINS');
Insert into ROLES values('user2','USER','USERS');