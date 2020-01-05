CREATE TABLE Products (
    productID int (10) NOT NULL AUTO_INCREMENT,
    productName varchar(100) NOT NULL,
    productDescription varchar(500) NOT NULL,
    manufacturer varchar(50) NOT NULL,
    category varchar(25) NOT NULL,
    price int(10) NOT NULL,
    guarantee int(3) DEFAULT NULL,
    rating int(2) DEFAULT NULL,
    createdTime timestamp NOT NULL,
    primary key (productID)
);
