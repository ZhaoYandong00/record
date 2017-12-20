drop table production;
CREATE TABLE production (
    id              INTEGER       PRIMARY KEY AUTOINCREMENT
                                  NOT NULL
                                  UNIQUE,
    name            VARCHAR (20)  NOT NULL,
    specification   VARCHAR (20)  NOT NULL,
    product_number  VARCHAR (30)  UNIQUE
                                  NOT NULL,
    producer        VARCHAR (20)  NOT NULL,
    production_date DATE          NOT NULL,
    inspector       VARCHAR (20)  NOT NULL,
    remark          TEXT,
    salesman        VARCHAR (20),
    sale_date       DATE,
    purchaser       VARCHAR (40),
    sale_position   VARCHAR (100) 
);
