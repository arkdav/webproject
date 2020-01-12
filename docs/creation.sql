/*SET GLOBAL time_zone="+3:00";
DROP SCHEMA `webdb`;*/
CREATE SCHEMA `webdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `webdb`.`roles` (
  `role_id` INT NOT NULL,
  `rolename` VARCHAR(225) NOT NULL,
   PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
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
  `address` VARCHAR(225) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`login`),
  FOREIGN KEY (`role_id`) REFERENCES `webdb`.`roles`(`role_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('admin', '$2a$11$3zXtCcXetBpTE0NAK0O55eWDfdlp3uYTNq5U5z2TPWKivhLthyw0S','2','Petr','petrov','petrov@mail.ru','2385624', '1992-09-19','', '1');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('natali', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Kate','Gorn','gorn@mail.ru','3089078', '2016-05-12', 'Minsk, Ivanovski st. 251-99', '1');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('andrey', '$2a$11$pAc9QgTxxF8TD8XETP/SSeosaRx9ZGGI/Xdurga.OEeQnkI1L4cLa','3','Andrey','Ivanov','ivanov.a@mail.ru','2879838', '2000-06-23', 'Borisov, Lion st. 4/1-82', '1');

INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('lpetrova', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Larisa','Petrova','larka@mail.ru','2554677', '1990-01-04', 'Minsk, Perova st. 36-50', '1');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('kira', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','3','Kira','Truma','truma@mail.ru','4501010', '1991-01-04', 'Minsk, Perova st. 36-50', '1');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('egor99', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','3','Egor','Somov','somov.e@mail.ru','2348890', '1992-01-04', 'Minsk, Perova st. 36-50', '0');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('yulia_belosh', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Yulia','Belosh','y_belosh@mail.ru','2526678', '1990-01-04', 'Minsk, Perova st. 36-50', '0');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('konyash', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Konya','Ovoshch','petrova9999@mail.ru','7743233', '1991-01-04', 'Minsk, Perova st. 36-50', '1');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('kotina67', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Алена','Котина','kotina_al@mail.ru','4456777', '1990-01-04', 'Минск, ул. Жигунова 78-1', '1');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('zotov', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Марина','Зотова','kotina_al@mail.ru','334567', '1990-07-21', 'Борисов, ул. Независимости 41/2-9', '0');
INSERT INTO `webdb`.`users` (`login`,`password`,`role_id`,`name`,`surname`,`email`,`phone`,`birthdate`, `address`, `status`)
VALUES ('kolya', '$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq','1','Kolya','Iven','kotina_al@mail.ru','2341123', '1970-06-21', 'Minsk, Perova st. 36-50', '1');


CREATE TABLE `webdb`.`catalogversion` (
    `catver_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`catver_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO `webdb`.`catalogversion` (`catver_id`, `name`) VALUES ('1', 'online');
INSERT INTO `webdb`.`catalogversion` (`catver_id`, `name`) VALUES ('2', 'offline');

CREATE TABLE `webdb`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `imageurl` VARCHAR(225) NOT NULL,
  `catver_id` INT NOT NULL,
  `user_login` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`catver_id`) REFERENCES `webdb`.`catalogversion`(`catver_id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`user_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('1', 'Sketchbook #2', 'A sketchbook is a book or pad with blank pages for sketching and is frequently used by artists for drawing or painting as a part of their creative process.','album1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('2', 'Pen #30', 'A pen is a writing instrument used to apply ink to a surface, usually paper, for writing or drawing. Historically, reed pens, quill pens, and dip pens were used, with a nib dipped in ink. Ruling pens allow precise adjustment of line width, and still find a few specialized uses, but technical pens such as the Rapidograph are more commonly used. ','pen30.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('3', 'Sketchbook #66', 'A sketchbook is a book or pad with blank pages for sketching and is frequently used by artists for drawing or painting as a part of their creative process.','album66.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('4', 'Sketchbook #67', 'A sketchbook is a book or pad with blank pages for sketching and is frequently used by artists for drawing or painting as a part of their creative process.','album55.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('5', 'True Home Easel', 'An easel is an upright support used for displaying and/or fixing something resting upon it, at an angle of about 20° to the vertical. In particular, easels are traditionally used by painters to support a painting while they work on it, normally standing up, and are also sometimes used to display finished paintings.','easel1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('6', 'Blue Marker Pen #32', 'A permanent marker consists of a container (glass, aluminum or plastic) and a core of an absorbent material. This filling serves as a carrier for the ink.','marker1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('7', 'Pencil Maped #71', 'A pencil is an implement for writing or drawing, constructed of a narrow, solid pigment core in a protective casing that prevents the core from being broken and/or marking the users hand.','pencil1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('8', 'Pencil New Arrow', 'A pencil is an implement for writing or drawing, constructed of a narrow, solid pigment core in a protective casing that prevents the core from being broken and/or marking the users hand.','pencil2.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('9', 'Scotch Tape Americans', 'Scotch Tape is a brand name used for pressure-sensitive tapes manufactured by 3M as part of the companys Scotch brand. Their magnetic recording tape products were also sold under the Scotch brand','scotch1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('10', 'Gouache 9 colors', ' Gouache is designed to be used with opaque methods of painting. Gouache has a considerable history, going back over 600 years. It is used most consistently by commercial artists for posters, illustrations, comics, and other design work.','gouache1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('11', 'Gouache 12 colors', ' Gouache is designed to be used with opaque methods of painting. Gouache has a considerable history, going back over 600 years. It is used most consistently by commercial artists for posters, illustrations, comics, and other design work.','gouache2.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('12', 'Ruler 15 sm', 'A ruler, sometimes called a rule or line gauge, is a device used in geometry and technical drawing, as well as the engineering and construction industries, to measure or draw straight lines.','ruler1.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('13', 'Sketchbook Fixiki', 'A sketchbook is a book or pad with blank pages for sketching and is frequently used by artists for drawing or painting as a part of their creative process.','album2.jpg','1', 'andrey');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('14', 'CardBoard Mary', 'Cardboard is a generic term for heavy-duty paper-based products having greater thickness and superior durability or other specific mechanical attributes to paper; such as foldability, rigidity and impact resistance. ','cardboard1.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('15', 'Pencil #2', 'A pencil is an implement for writing or drawing, constructed of a narrow, solid pigment core in a protective casing that prevents the core from being broken and/or marking the users hand.','pencil3.jpg','2', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('16', 'Pen Eleven Styles', 'A pen is a writing instrument used to apply ink to a surface, usually paper, for writing or drawing. Historically, reed pens, quill pens, and dip pens were used, with a nib dipped in ink. Ruling pens allow precise adjustment of line width, and still find a few specialized uses, but technical pens such as the Rapidograph are more commonly used. ','pen1.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('17', 'Pen Normal', 'A pen is a writing instrument used to apply ink to a surface, usually paper, for writing or drawing. Historically, reed pens, quill pens, and dip pens were used, with a nib dipped in ink. Ruling pens allow precise adjustment of line width, and still find a few specialized uses, but technical pens such as the Rapidograph are more commonly used. ','pen2.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('18', 'Pen For Students', 'A pen is a writing instrument used to apply ink to a surface, usually paper, for writing or drawing. Historically, reed pens, quill pens, and dip pens were used, with a nib dipped in ink. Ruling pens allow precise adjustment of line width, and still find a few specialized uses, but technical pens such as the Rapidograph are more commonly used. ','pen3.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('19', 'Easel For School', 'An easel is an upright support used for displaying and/or fixing something resting upon it, at an angle of about 20° to the vertical.In particular, easels are traditionally used by painters to support a painting while they work on it, normally standing up, and are also sometimes used to display finished paintings. ','easel2.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('20', 'Home easel', 'An easel is an upright support used for displaying and/or fixing something resting upon it, at an angle of about 20° to the vertical. In particular, easels are traditionally used by painters to support a painting while they work on it, normally standing up, and are also sometimes used to display finished paintings. ','easel3.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('21', 'Brush Red', 'A brush is a common tool with bristles, wire or other filaments. It generally consists of a handle or block to which filaments are affixed in either a parallel or perpendicular orientation, depending on the way the brush is to be gripped during use. The material of both the block and bristles or filaments is chosen to withstand hazards of its intended use, such as corrosive chemicals, heat or abrasion.','brush1.jpg','1', 'egor99');
INSERT INTO `webdb`.`products` (`product_id`,`name`, `description`,`imageurl`,`catver_id`, `user_login`) VALUES ('22', 'Brush Small', 'A brush is a common tool with bristles, wire or other filaments. It generally consists of a handle or block to which filaments are affixed in either a parallel or perpendicular orientation, depending on the way the brush is to be gripped during use. The material of both the block and bristles or filaments is chosen to withstand hazards of its intended use, such as corrosive chemicals, heat or abrasion.','brush2.jpg','1', 'egor99');
CREATE TABLE `webdb`.`price` (
  `product_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('1', '2.30');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('2', '4.7');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('3', '5');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('4', '10.5');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('5', '3.96');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('6', '11');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('7', '10.5');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('8', '15.4');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('9', '15');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('10', '19');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('11', '17.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('12', '10.2');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('13', '18.5');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('14', '13');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('15', '14');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('16', '19');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('17', '22');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('18', '15.1');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('19', '19');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('20', '8');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('21', '5.3');
INSERT INTO `webdb`.`price` (`product_id`, `price`) VALUES ('22', '7');

CREATE TABLE `webdb`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `user_login`  VARCHAR(225) NOT NULL,
  `date` DATE NOT NULL,
  `totalprice` DOUBLE NOT NULL,
  PRIMARY KEY (`cart_id`),
  FOREIGN KEY (`user_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `webdb`.`cartentry` (
  `cartentry_id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`cartentry_id`),
  FOREIGN KEY (`cart_id`) REFERENCES `webdb`.`cart`(`cart_id`) ON UPDATE CASCADE ON DELETE CASCADE ,
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `webdb`.`orderstatus` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `statusname`  VARCHAR(225) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

INSERT INTO `webdb`.`orderstatus` (`status_id`, `statusname`) VALUES ('1', 'processing');
INSERT INTO `webdb`.`orderstatus` (`status_id`, `statusname`) VALUES ('2', 'collected');

CREATE TABLE `webdb`.`orderbundle` (
  `orderbundle_id` INT NOT NULL AUTO_INCREMENT,
  `user_login`  VARCHAR(225) NOT NULL,
  `date` DATE NOT NULL,
  `totalprice` DOUBLE NOT NULL,
  `ordernote` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`orderbundle_id`),
  FOREIGN KEY (`user_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `webdb`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NOT NULL,
  `status_id`  INT NOT NULL DEFAULT '1',
  `orderbundle_id` INT NOT NULL,
  `owner_login` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`orderbundle_id`) REFERENCES `webdb`.`orderbundle`(`orderbundle_id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`status_id`) REFERENCES `webdb`.`orderstatus`(`status_id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`owner_login`) REFERENCES `webdb`.`users`(`login`) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `webdb`.`orderentry` (
  `orderentry_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`orderentry_id`),
  FOREIGN KEY (`order_id`) REFERENCES `webdb`.`orders`(`order_id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`product_id`) REFERENCES `webdb`.`products`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;