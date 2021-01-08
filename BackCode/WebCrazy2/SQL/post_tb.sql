CREATE DATABASE IF NOT EXISTS cumt;

CREATE TABLE IF NOT EXISTS cumt.post_tb (
  post_id int auto_increment,
  title varchar(255) NOT NULL,
  user_id int NOT NULL,
  heat int  DEFAULT 0,
  thumbs int DEFAULT 0,
  state int  DEFAULT 0,
  post_content TinyText DEFAULT NULL,
  PRIMARY KEY (post_id)
) engine="innodb" DEFAULT charset="utf8";
