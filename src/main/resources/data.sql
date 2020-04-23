DROP TABLE IF EXISTS product;

CREATE TABLE product (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              name VARCHAR(250) NOT NULL,
                              price INT(25) ,
                              description VARCHAR(250),
                              category VARCHAR(250),
                              unitsInStock INT (20),
                                unitsInOrder INT (20)

);

INSERT INTO product ( name, price, description,category,unitsInStock,unitsInOrder) VALUES
('Aliko', 20, 'desc','kkk', 234, 432)


