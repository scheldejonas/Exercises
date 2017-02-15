CREATE SCHEMA `chatone` DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE chatone.User (
  id bigint not null auto_increment,
  userName varchar(255),
  passWord varchar(255),
  isActive bit,
  primary key (id)
);