create table if not exists internal_tb(
    internal_id int auto_increment not null ,
    internal_people varchar(70) ,
    internal_msg text not null ,
    internal_contact varchar(100),
    internal_startTime date not null ,
    internal_link varchar(999) not null,
    primary key (internal_id)
    ) engine = "innodb"  default char set = "utf8";

