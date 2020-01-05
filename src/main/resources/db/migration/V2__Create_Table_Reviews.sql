CREATE TABLE Reviews (
    reviewID int(10) NOT NULL AUTO_INCREMENT,
    reviewerName varchar(50) NOT NULL,
    reviewDescription varchar(1000) NOT NULL,
    createdTime TIMESTAMP NOT NULL,
    productID INT NOT NULL,
    reviewRating INT NOT NULL,
    primary key (reviewID),
    constraint review_product_fk
    foreign key(productID) references Products (productID)
    );