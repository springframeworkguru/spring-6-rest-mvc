drop table if exists category;
drop table if exists beer_category;

create table category
(
    id                 varchar(36) NOT NULL PRIMARY KEY,
    description        varchar(50),
    created_date       timestamp,
    last_modified_date datetime(6) DEFAULT NULL,
    version            bigint      DEFAULT NULL
) ENGINE = InnoDB;

create table beer_category
(
    beer_id     varchar(36) NOT NULL,
    category_id varchar(36) NOT NULL,
    primary key (beer_id, category_id),
    constraint pc_beer_id_fk FOREIGN KEY (beer_id) references beer (id),
    constraint pc_category_id_fk FOREIGN KEY (category_id) references category (id)
) ENGINE = InnoDB;

