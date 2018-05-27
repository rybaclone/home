
CREATE DATABASE `test`;

CREATE TABLE `test`.`book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_book_name_author` (`name`,`author`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tieto homework v.01';