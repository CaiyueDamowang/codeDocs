CREATE DATABASE IF NOT EXISTS cumt;

CREATE TABLE IF NOT EXISTS cumt.post_img(
  img_id int auto_increment,
  img_url varchar(255) DEFAULT NULL,
  post_id int NOT NULL,
  base_str text NOT NULL,
  image_name varchar(50)  DEFAULT NULL,
  state int DEFAULT 0,
  PRIMARY KEY (img_id)
) engine="innodb" DEFAULT charset="utf8";