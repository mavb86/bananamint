CREATE SCHEMA `bananamintdb` ;

USE mysql;

CREATE USER 'banana_user'@'%' IDENTIFIED BY 'bmint123';

GRANT ALL PRIVILEGES ON bananamintdb.* TO 'banana_user'@'%';
FLUSH PRIVILEGES;