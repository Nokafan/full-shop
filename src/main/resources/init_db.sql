CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `products` (
                            `product_id` bigint NOT NULL AUTO_INCREMENT,
                            `product_name` varchar(255) NOT NULL,
                            `product_price` decimal(13,2) NOT NULL,
                            `product_deleted` tinyint(1) NOT NULL DEFAULT '0',
                            PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;