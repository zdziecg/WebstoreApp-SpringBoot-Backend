-- DROP TABLE IF EXISTS product;

-- create table PRODUCT
-- (
--     product_id   INTEGER auto_increment,
--     product_name VARCHAR not null,
--     category varchar,
--     description  VARCHAR not null,
--     unitsInStock     INTEGER,
--     price        DECIMAL not null
-- );

INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Soap', 'Pears baby soap for Kids','Health', 1, 35.75);
INSERT INTO PRODUCT ( product_name, description, category, unitsinstock, price)
VALUES ('Tooth Brush', 'Signal Tooth Brushes Size in (L, M, S)', 'Health', 5, 34.50);
INSERT INTO PRODUCT ( product_name, description, category, unitsinstock, price)
VALUES ('Shirt', 'Casual Shirt imported from France','Fashion', 3, 15.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ( 'Office Bag', 'Leather bag imported from USA', 'Fashion', 40, 1000.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Tablet', 'Tablet with a stylus for drawing ', 'Electronics',80, 450.45);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Wrist Watch', 'Imported wrist watches from swiss', 'Electronics',800, 250.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Mobile Phone', '3G/4G capability', 'Electronics',700, 4500.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Shampoo', 'Head and Shoulders Shampoo', 'Health',500, 300.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Leather Wallets', 'Imported Leather Wallets from AUS', 'Fashion',100, 5.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Shoes', 'Kasketball shoes from USA', 'Fashion',10, 850.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Ball', 'Head and Shoulders Shampoo', 'Sport',500, 300.00);
INSERT INTO PRODUCT (product_name, description, category, unitsinstock, price)
VALUES ('Laptop', '8GB RAM, i7-850', 'Electronics',1000, 5000.00);


insert into cart (status,  grand_total)
values ('created',1);
insert into  items (quantity, product_id, cart_id)
values ( 99,1,1);
insert into  items (quantity, product_id, cart_id)
values ( 2,2,1);





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
