CREATE DATABASE `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `roles` (
                         `role_id` bigint NOT NULL AUTO_INCREMENT,
                         `role_name` varchar(11) NOT NULL DEFAULT 'USER',
                         `role_deleted` tinyint NOT NULL DEFAULT '0',
                         PRIMARY KEY (`role_id`)
);

INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('ADMIN');
INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('USER');

CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` bigint NOT NULL,
                              KEY `user_id_fk_idx` (`user_id`),
                              KEY `roles_role_id_fk_idx` (`role_id`),
                              CONSTRAINT `roles_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
                              CONSTRAINT `roles_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

CREATE TABLE `users` (
                         `user_id` bigint NOT NULL AUTO_INCREMENT,
                         `user_name` varchar(225) NOT NULL,
                         `login` varchar(225) NOT NULL,
                         `user_password` varchar(225) NOT NULL,
                         `salt` varchar(255) DEFAULT NOT NULL,
                         `user_deleted` tinyint(1) NOT NULL DEFAULT '0',
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `login_UNIQUE` (`login`)
);

CREATE TABLE `shopping_carts` (
                                  `cart_id` bigint NOT NULL AUTO_INCREMENT,
                                  `user_id` bigint NOT NULL,
                                  `cart_deleted` tinyint NOT NULL DEFAULT '0',
                                  PRIMARY KEY (`cart_id`),
                                  KEY `user_id_fk_idx` (`user_id`),
                                  KEY `cart_user_id_fk_idx` (`user_id`),
                                  CONSTRAINT `cart_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

CREATE TABLE `shopping_carts_products` (
                                           `id` int NOT NULL AUTO_INCREMENT,
                                           `cart_id` bigint NOT NULL,
                                           `product_id` bigint NOT NULL,
                                           PRIMARY KEY (`id`),
                                           KEY `cart_product_id_fk_idx` (`product_id`),
                                           KEY `cart_cart_id_fk_idx` (`cart_id`),
                                           CONSTRAINT `cart_cart_id_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`),
                                           CONSTRAINT `cart_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);

CREATE TABLE `products` (
                            `product_id` bigint NOT NULL AUTO_INCREMENT,
                            `product_name` varchar(255) NOT NULL,
                            `product_price` decimal(13,2) NOT NULL,
                            `product_deleted` tinyint(1) NOT NULL DEFAULT '0',
                            PRIMARY KEY (`product_id`)
);

CREATE TABLE `orders` (
                          `order_id` bigint NOT NULL AUTO_INCREMENT,
                          `user_id` bigint NOT NULL,
                          `order_deleted` tinyint NOT NULL DEFAULT '0',
                          PRIMARY KEY (`order_id`),
                          KEY `orders_users_fk_idx` (`user_id`),
                          CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

CREATE TABLE `order_products` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `order_id` bigint NOT NULL,
                                  `product_id` bigint NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `orders_order_id_fk_idx` (`order_id`),
                                  KEY `orders_product_id_fk_idx` (`product_id`),
                                  CONSTRAINT `orders_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
                                  CONSTRAINT `orders_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);








