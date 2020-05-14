-- DROP TABLE IF EXISTS product;
--
-- create table PRODUCT
-- (
--     product_id   INTEGER auto_increment,
--     product_name VARCHAR not null,
--     description  VARCHAR not null,
--     quantity     INTEGER,
--     price        DECIMAL not null
-- );

INSERT INTO PRODUCT (product_name, description, quantity, price)
VALUES ('Soap', 'Pears baby soap for Kids', 1, 35.75);
INSERT INTO PRODUCT ( product_name, description, quantity, price)
VALUES ('Tooth Brush', 'Signal Tooth Brushes Size in (L, M, S)', 5, 34.50);
INSERT INTO PRODUCT ( product_name, description, quantity, price)
VALUES ('Shirt', 'Casual Shirt imported from France', 3, 1500.00);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (4, 'Office Bag', 'Leather bag imported from USA', 40, 1000.00);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (5, 'Bottle', 'Hot Water Bottles', 80, 450.45);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (6, 'Wrist Watch', 'Imported wrist watches from swiss', 800, 2500.00);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (7, 'Mobile Phone', '3G/4G capability', 700, 45000.00);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (8, 'Shampoo', 'Head and Shoulders Shampoo', 500, 300.00);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (9, 'Leather Wallets', 'Imported Leather Wallets from AUS', 1000, 500.00);
-- INSERT INTO PRODUCT (product_id, product_name, description, quantity, price)
-- VALUES (10,'Camera', 'Imported Canon camera from USA', 10, 85000.00);

insert into cart ( grand_total)
values (1);
insert into  items (quantity, total_price, product_id, cart_id)
values ( 1,1,1,1);





-- CREATE TABLE `Cart` (
--                         `cart_id` int(11),
--                         PRIMARY KEY (`cart_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
--
-- CREATE TABLE `Items` (
--                          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
--                          `cart_id` int(11) unsigned NOT NULL,
--                          PRIMARY KEY (`id`),
--                          KEY `cart_id` (`cart_id`),
--                          CONSTRAINT `items_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `Cart` (`cart_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
