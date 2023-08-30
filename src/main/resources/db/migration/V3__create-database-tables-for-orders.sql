CREATE TABLE beer_order (
    id                  VARCHAR(36) PRIMARY KEY,
    created_date        DATETIME(6) DEFAULT NULL,
    customer_ref        VARCHAR(255) DEFAULT NULL,
    last_modified_date  DATETIME(6) DEFAULT NULL,
    version             BIGINT DEFAULT NULL,
    customer_id         VARCHAR(36) DEFAULT NULL,
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE = InnoDB;

CREATE TABLE beer_order_line (
    id                  VARCHAR(36) PRIMARY KEY,
    beer_id             VARCHAR(36) DEFAULT NULL,
    created_date        DATETIME(6) DEFAULT NULL,
    last_modified_date  DATETIME(6) DEFAULT NULL,
    order_quantity      INT DEFAULT NULL,
    quantity_allocated  INT DEFAULT NULL,
    version             BIGINT DEFAULT NULL,
    beer_order_id       VARCHAR(36) DEFAULT NULL,
    CONSTRAINT FOREIGN KEY (beer_order_id) REFERENCES beer_order (id),
    CONSTRAINT FOREIGN KEY (beer_id) REFERENCES beer (id)
) ENGINE = InnoDB;
