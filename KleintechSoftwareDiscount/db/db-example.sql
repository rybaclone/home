CREATE DATABASE `test`;

CREATE TABLE `test`.`order` (
  `order_id` INT NOT NULL,
  `units` INT NULL,
  `price` DECIMAL(10,2) NULL,
  `amount` DECIMAL(10,2) NULL,
  `total` DECIMAL(10,2) NULL,
  `discount` INT NULL,
  `customer_id` INT NOT NULL,
  `units_name` VARCHAR(45) NULL,
  PRIMARY KEY (`order_id`));
  
  CREATE TABLE `test`.`customer` (
  `customer_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`));
commit;
  INSERT INTO `test`.`customer`
(`customer_id`,`name`) VALUES
(1, 'SIA ABC'), (2, 'SIA AAA'), (3, 'SIA BACD');

--fill in test customers
INSERT INTO `test`.`order`
(`order_id`,`units`,`price`,`amount`,`total`,`discount`,`customer_id`,`units_name`)
VALUES
(1, 100, 20.0, 2000, 1800.0, 10 , 1,  'units_name' ),
(2, 101, 20.0, 2000, 1600.0, 20 , 1,  'units_name' ),
(3, 51, 20.99, 1070.49, 963.44, 10 , 2,  'other_units_name' );
commit;

--fill jsp customer dropdown
select customer_id from `test`.customer

--get new customer order
select customer_id from `test`.customer
where 
	customer_id not in(select customer_id from `test`.order );