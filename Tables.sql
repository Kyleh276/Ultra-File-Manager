--tables

--user
--order
--technician

--user one to many orders
--technican one to many orders

CREATE TABLE USER (
    user_id     NUMBER(4); 
    username    VARCHAR2(30) NOT NULL,
    password    VARCHAR2(30) NOT NULL,
    firstname   VARCHAR2(30) NOT NULL,
    lastname    VARCHAR2(30) NOT NULL,
    email       VARCHAR2(60) NOT NULL,
    phone       VARCHAR2(15) 
    
    PRIMARY KEY (user_id),
    UNIQUE (user_id),
    UNIQUE (username),
    UNIQUE (email),
    UNIQUE (phone)
    );
    
