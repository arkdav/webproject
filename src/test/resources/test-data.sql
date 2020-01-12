CREATE SCHEMA `testdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `testdb`.`roles` (
   `role_id` INT NOT NULL,
    `rolename` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`role_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
INSERT INTO `testdb`.`roles` (`role_id`, `rolename`) VALUES ('1', 'ROLE_CUSTOMER');
INSERT INTO `testdb`.`roles` (`role_id`, `rolename`) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `testdb`.`roles` (`role_id`, `rolename`) VALUES ('3', 'ROLE_BUSINESS_USER');

CREATE TABLE `testdb`.`users` (
    `login` VARCHAR(225) NOT NULL,
    `password` VARCHAR(225) NOT NULL,
    `role_id` INT NOT NULL,
    `name` VARCHAR(225) NOT NULL,
    `surname` VARCHAR(225) NOT NULL,
    `email` VARCHAR(225) NOT NULL,
    `phone` VARCHAR(225) NOT NULL,
    `birthdate` DATE NOT NULL,
    `address` VARCHAR(225) NOT NULL,
    `status` TINYINT(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`login`),
    FOREIGN KEY (`role_id`) REFERENCES `testdb`.`roles`(`role_id`) ON UPDATE CASCADE ON DELETE CASCADE )
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO `testdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('natali', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Kate','Gorn','gorn@mail.ru','3089078', '2003-09-25','Minsk, 98а-4', '1');
INSERT INTO `testdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('kolya', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','May','Gorn','gorn@mail.ru','3089078', '2003-09-25', 'Minsk, 98а-4', '1');
INSERT INTO `testdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('misha', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','3','Due','Gorn','gorn@mail.ru','3089078', '2003-09-25', 'Minsk, 98а-4', '1');

CREATE TABLE `testdb`.`catalogversion` (
                                          `catver_id` INT NOT NULL AUTO_INCREMENT,
                                          `name` VARCHAR(225) NOT NULL,
                                          PRIMARY KEY (`catver_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO `testdb`.`catalogversion` (`catver_id`, `name`) VALUES ('1', 'online');
INSERT INTO `testdb`.`catalogversion` (`catver_id`, `name`) VALUES ('2', 'offline');

CREATE TABLE `testdb`.`products` (
                                    `product_id` INT NOT NULL AUTO_INCREMENT,
                                    `name` VARCHAR(225) NOT NULL,
                                    `description` VARCHAR(225) NOT NULL,
                                    `imageurl` VARCHAR(225) NOT NULL,
                                    `catver_id` INT NOT NULL,
                                    `user_login` VARCHAR(225) NOT NULL,
                                    PRIMARY KEY (`product_id`),
                                    FOREIGN KEY (`catver_id`) REFERENCES `testdb`.`catalogversion`(`catver_id`) ON UPDATE CASCADE ON DELETE CASCADE,
                                    FOREIGN KEY (`user_login`) REFERENCES `testdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO `testdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('1', 'Pencil', 'information about pencil','Pencil.jpg','1', 'misha');
INSERT INTO `testdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('2', 'Pen', 'information about pen','Pen.jpg','1', 'misha');
INSERT INTO `testdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('3', 'Sketchbook', 'information about sketchbook','Sketchbook.jpg','2', 'misha');
INSERT INTO `testdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('4', 'Paints', 'information about paints','Paints.jpg','1', 'misha');

CREATE TABLE `testdb`.`price` (
                                 `product_id` INT NOT NULL,
                                 `price` DOUBLE NOT NULL,
                                 PRIMARY KEY (`product_id`),
                                 FOREIGN KEY (`product_id`) REFERENCES `testdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
INSERT INTO `testdb`.`price` (`product_id`, `price`) VALUES ('1', '5');
INSERT INTO `testdb`.`price` (`product_id`, `price`) VALUES ('2', '6');
INSERT INTO `testdb`.`price` (`product_id`, `price`) VALUES ('3', '4');
INSERT INTO `testdb`.`price` (`product_id`, `price`) VALUES ('4', '10');

CREATE TABLE `testdb`.`cart` (
                                `cart_id` INT NOT NULL AUTO_INCREMENT,
                                `user_login`  VARCHAR(225) NOT NULL,
                                `date` DATE NOT NULL,
                                `totalprice` DOUBLE NOT NULL,
                                PRIMARY KEY (`cart_id`),
                                FOREIGN KEY (`user_login`) REFERENCES `testdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE )
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO `testdb`.`cart` (`cart_id`, `user_login`, `date`, `totalprice`) VALUES ('1', 'natali', '2019-11-05', '13');
INSERT INTO `testdb`.`cart` (`cart_id`, `user_login`, `date`, `totalprice`) VALUES ('2', 'kolya', '2019-12-06', '25');

CREATE TABLE `testdb`.`cartentry` (
                                     `cartentry_id` INT NOT NULL AUTO_INCREMENT,
                                     `cart_id` INT NOT NULL,
                                     `product_id` INT NOT NULL,
                                     `amount` INT NOT NULL,
                                     PRIMARY KEY (`cartentry_id`),
                                     FOREIGN KEY (`cart_id`) REFERENCES `testdb`.`cart`(`cart_id`) ON UPDATE CASCADE ON DELETE CASCADE ,
                                     FOREIGN KEY (`product_id`) REFERENCES `testdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO `testdb`.`cartentry` (`cartentry_id`, `cart_id`, `product_id`, `amount`) VALUES ('1', '1', '1', '1');
INSERT INTO `testdb`.`cartentry` (`cartentry_id`, `cart_id`, `product_id`, `amount`) VALUES ('2', '1', '3', '2');
INSERT INTO `testdb`.`cartentry` (`cartentry_id`, `cart_id`, `product_id`, `amount`) VALUES ('3', '2', '1', '5');


CREATE TABLE `testdb`.`orderstatus` (
                                       `status_id` INT NOT NULL AUTO_INCREMENT,
                                       `statusname`  VARCHAR(225) NOT NULL,
                                       PRIMARY KEY (`status_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

INSERT INTO `testdb`.`orderstatus` (`status_id`, `statusname`) VALUES ('1', 'processing');
INSERT INTO `testdb`.`orderstatus` (`status_id`, `statusname`) VALUES ('2', 'collected');

CREATE TABLE `testdb`.`orderbundle` (
                                       `orderbundle_id` INT NOT NULL AUTO_INCREMENT,
                                       `user_login`  VARCHAR(225) NOT NULL,
                                       `date` DATE NOT NULL,
                                       `totalprice` DOUBLE NOT NULL,
                                       `ordernote` VARCHAR(225) NOT NULL,
                                       PRIMARY KEY (`orderbundle_id`),
                                       FOREIGN KEY (`user_login`) REFERENCES `testdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE )
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `testdb`.`orders` (
                                  `order_id` INT NOT NULL AUTO_INCREMENT,
                                  `price` DOUBLE NOT NULL,
                                  `status_id`  INT NOT NULL DEFAULT '1',
                                  `orderbundle_id` INT NOT NULL,
                                  `owner_login` VARCHAR(225) NOT NULL,
                                  PRIMARY KEY (`order_id`),
                                  FOREIGN KEY (`orderbundle_id`) REFERENCES `testdb`.`orderbundle`(`orderbundle_id`) ON UPDATE CASCADE ON DELETE CASCADE,
                                  FOREIGN KEY (`status_id`) REFERENCES `testdb`.`orderstatus`(`status_id`) ON UPDATE CASCADE ON DELETE CASCADE,
                                  FOREIGN KEY (`owner_login`) REFERENCES `testdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `testdb`.`orderentry` (
                                      `orderentry_id` INT NOT NULL AUTO_INCREMENT,
                                      `order_id` INT NOT NULL,
                                      `product_id` INT NOT NULL,
                                      `amount` INT NOT NULL,
                                      PRIMARY KEY (`orderentry_id`),
                                      FOREIGN KEY (`order_id`) REFERENCES `testdb`.`orders`(`order_id`) ON UPDATE CASCADE ON DELETE CASCADE,
                                      FOREIGN KEY (`product_id`) REFERENCES `testdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;