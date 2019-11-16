CREATE SCHEMA `webdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `webdb`.`catalogversion` (
    `catver_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`catver_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`catalogversion` (`catver_id`, `name`) VALUES ('1', 'present');
INSERT INTO `webdb`.`catalogversion` (`catver_id`, `name`) VALUES ('2', 'notpresent');

CREATE TABLE `webdb`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `description` VARCHAR(225) NOT NULL,
  `type` VARCHAR(225) NOT NULL,
  `catver_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`catver_id`) REFERENCES `webdb`.`catalogversion`(`catver_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('1', 'Album #2', 'infaboutalbum2','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('2', 'Pen #30', 'infaboutpen33','pen','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('3', 'Album #66', 'infaboutalbum66','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('4', 'Album #67', 'infaboutalbum67','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('5', 'Album #68', 'infaboutalbum68','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('6', 'Album #32', 'infaboutalbum32','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('7', 'Album #71', 'infaboutalbum71','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('8', 'Album #72', 'infaboutalbum72','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('9', 'Album #73', 'infaboutalbum73','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('10', 'Album #74', 'infaboutalbum74','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('11', 'Album #75', 'infaboutalbum75','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('12', 'Album #76', 'infaboutalbum76','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('13', 'Album #77', 'infaboutalbum77','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('14', 'Album #78', 'infaboutalbum78','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('15', 'Album #79', 'infaboutalbum79','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('16', 'Album #80', 'infaboutalbum80','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('17', 'Album #81', 'infaboutalbum81','album','1');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`type`,`catver_id`) VALUES ('18', 'Album #82', 'infaboutalbum82','album','1');


CREATE TABLE `webdb`.`images` (
  `image_id` INT NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(225) NULL,
  `product_id` INT NULL,
  PRIMARY KEY (`image_id`),
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album1.jpg', '1');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('pen30.jpg', '2');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '3');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album55.jpg', '3');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '4');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '5');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '6');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '7');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '8');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '9');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '10');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '11');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '12');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '13');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '14');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '15');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '16');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '17');
INSERT INTO `webdb`.`images` (`link`, `product_id`) VALUES ('album66.jpg', '18');

CREATE TABLE `webdb`.`price` (
  `product_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('1', '20');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('2', '99');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('3', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('4', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('5', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('6', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('7', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('8', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('9', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('10', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('11', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('12', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('13', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('14', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('15', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('16', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('17', '33.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('18', '33.1');

CREATE TABLE `webdb`.`roles` (
  `role_id` INT NOT NULL,
  `rolename` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
INSERT INTO `webdb`.`roles` (`role_id`, `rolename`) VALUES ('1', 'ROLE_USER');
INSERT INTO `webdb`.`roles` (`role_id`, `rolename`) VALUES ('2', 'ROLE_ADMIN');

CREATE TABLE `webdb`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(225) NULL,
  `password` VARCHAR(225) NULL,
  `role_id` INT NULL,
  `name` VARCHAR(225) NULL,
  `surname` VARCHAR(225) NULL,
  `email` VARCHAR(225) NULL,
  `phone` VARCHAR(225) NULL,
  `birthdate` DATE NULL,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`role_id`) REFERENCES `webdb`.`roles`(`role_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`users` (`username`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`) VALUES ('tttttt', '$2a$11$tUBWjMmV7xMKjsgCI1sg0eWT/7FSRicP7BeUipoIIrkt2ztJ3Fq56','1','Kate','Gorn','gorn@mail.ru','3089078', '2016-05-12');

CREATE TABLE `webdb`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`cart_id`),
  FOREIGN KEY (`user_id`) REFERENCES `webdb`.`users`(`user_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE `webdb`.`cartentry` (
  `entry_id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`entry_id`),
  FOREIGN KEY (`cart_id`) REFERENCES `webdb`.`cart`(`cart_id`) ON UPDATE CASCADE ON DELETE CASCADE ,
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


CREATE TABLE `webdb`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `totalprice` DOUBLE NOT NULL,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`user_id`) REFERENCES `webdb`.`users`(`user_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE `webdb`.`orderentry` (
  `orderentry_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`orderentry_id`),
  FOREIGN KEY (`order_id`) REFERENCES `webdb`.`orders`(`order_id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;