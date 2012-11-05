CREATE DATABASE cst425Lab4;
USE cst425Lab4;
CREATE TABLE newsItems (id int PRIMARY KEY AUTO_INCREMENT, title varchar(255), story text, create_date TIMESTAMP, reporter varchar(255));
CREATE TABLE favorites (id int PRIMARY KEY AUTO_INCREMENT, username varchar(255), item_id int);