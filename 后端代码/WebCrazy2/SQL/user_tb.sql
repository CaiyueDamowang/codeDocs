CREATE DATABASE IF NOT EXISTS cumt;


CREATE TABLE IF NOT EXISTS cumt.user_tb (
  user_id int auto_increment,
  user_name varchar(50) NOT NULL,
  password varchar(200) NOT NULL,
  email varchar(20) NOT NULL UNIQUE,
  is_superuser int NOT NULL,
  sex varchar(10) NULL,
  photo_url varchar(200) NULL,
  PRIMARY KEY (user_id)
) engine="innodb" DEFAULT charset="utf8";
