-- Create users table
create table GLGSERVICE_USERS
(
  USER_ID VARCHAR2(50) not null,
  ENABLED NUMBER not null
);
alter table GLGSERVICE_USERS
  add primary key (USER_ID)
  using index;

-- Create authorities table
create table GLGSERVICE_AUTHORITIES
(
  USER_ID   VARCHAR2(50) not null,
  AUTHORITY VARCHAR2(50) not null
);
alter table GLGSERVICE_AUTHORITIES
  add constraint FK_AUTHORITIES_USERS foreign key (USER_ID)
  references GLGSERVICE_USERS (USER_ID);

-- Create index
create unique index IX_AUTH_USER_ID on GLGSERVICE_AUTHORITIES (USER_ID, AUTHORITY);
