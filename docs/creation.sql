/*SET GLOBAL time_zone="+3:00";
DROP SCHEMA `webdb`;*/
CREATE SCHEMA `webdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `webdb`.`roles` (
  `role_id` INT NOT NULL,
  `rolename` VARCHAR(225) NOT NULL,
   PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
INSERT INTO `webdb`.`roles` (`role_id`, `rolename`) VALUES ('1', 'ROLE_CUSTOMER');
INSERT INTO `webdb`.`roles` (`role_id`, `rolename`) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `webdb`.`roles` (`role_id`, `rolename`) VALUES ('3', 'ROLE_BUSINESS_USER');

CREATE TABLE `webdb`.`users` (
  `login` VARCHAR(225) NOT NULL,
  `password` VARCHAR(225) NOT NULL,
  `role_id` INT NOT NULL,
  `name` VARCHAR(225) NOT NULL,
  `surname` VARCHAR(225) NOT NULL,
  `email` VARCHAR(225) NOT NULL,
  `phone` VARCHAR(225) NOT NULL,
  `birthdate` DATE NOT NULL,
  PRIMARY KEY (`login`),
  FOREIGN KEY (`role_id`) REFERENCES `webdb`.`roles`(`role_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`)
VALUES ('natali', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Kate','Gorn','gorn@mail.ru','3089078', '2016-05-12');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`)
VALUES ('andrey', '$2a$11$pAc9QgTxxF8TD8XETP/SSeosaRx9ZGGI/Xdurga.OEeQnkI1L4cLa','3','Andrey','Ivanov','ivanov.a@mail.ru','2879838', '2000-06-23');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`)
VALUES ('aadmin', '$2a$11$3zXtCcXetBpTE0NAK0O55eWDfdlp3uYTNq5U5z2TPWKivhLthyw0S','2','Petr','petrov','petrov@mail.ru','2385624', '1992-09-19');

CREATE TABLE `webdb`.`catalogversion` (
    `catver_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`catver_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`catalogversion` (`catver_id`, `name`) VALUES ('1', 'online');
INSERT INTO `webdb`.`catalogversion` (`catver_id`, `name`) VALUES ('2', 'offline');

CREATE TABLE `webdb`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `description` VARCHAR(225) NOT NULL,
  `imageurl` VARCHAR(225) NOT NULL,
  `catver_id` INT NOT NULL,
  `user_login` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`catver_id`) REFERENCES `webdb`.`catalogversion`(`catver_id`) ON UPDATE CASCADE ON DELETE CASCADE,
   FOREIGN KEY (`user_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('1', 'Album #2', 'infaboutalbum2','album1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('2', 'Pen #30', 'infaboutpen33','pen30.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('3', 'Album #66', 'infaboutalbum66','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('4', 'Album #67', 'infaboutalbum67','album55.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('5', 'Album #68', 'infaboutalbum68','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('6', 'Album #32', 'infaboutalbum32','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('7', 'Album #71', 'infaboutalbum71','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('8', 'Album #72', 'infaboutalbum72','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('9', 'Album #73', 'infaboutalbum73','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('10', 'Album #74', 'infaboutalbum74','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('11', 'Album #75', 'infaboutalbum75','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('12', 'Album #76', 'infaboutalbum76','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('13', 'Album #77', 'infaboutalbum77','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('14', 'Album #78', 'infaboutalbum78','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('15', 'Album #79', 'infaboutalbum79','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('16', 'Album #80', 'infaboutalbum80','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('17', 'Album #81', 'infaboutalbum81','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('18', 'Album #82', 'infaboutalbum82','album66.jpg','1', 'andrey');

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


CREATE TABLE `webdb`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `user_login`  VARCHAR(225) NOT NULL,
  `date` DATE NOT NULL,
  `totalprice` DOUBLE NOT NULL,
  PRIMARY KEY (`cart_id`),
  FOREIGN KEY (`user_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE `webdb`.`cartentry` (
  `cartentry_id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`cartentry_id`),
  FOREIGN KEY (`cart_id`) REFERENCES `webdb`.`cart`(`cart_id`) ON UPDATE CASCADE ON DELETE CASCADE ,
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


CREATE TABLE `webdb`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_login`  VARCHAR(225) NOT NULL,
  `date` DATE NOT NULL,
  `totalprice` DOUBLE NOT NULL,
  `ordernote` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`user_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE `webdb`.`orderentry` (
  `orderentry_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`orderentry_id`),
  FOREIGN KEY (`order_id`) REFERENCES `webdb`.`orders`(`order_id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;