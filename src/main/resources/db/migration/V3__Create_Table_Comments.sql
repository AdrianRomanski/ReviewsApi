
CREATE TABLE Comments (
    commentID int(10) NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    commentDescription varchar(1000) NOT NULL,
    createdTime TIMESTAMP NOT NULL,
    reviewID int(10) NOT NULL,
    primary key (commentID),
    constraint comment_review_fk
    foreign key(reviewID) references Reviews (reviewID)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
