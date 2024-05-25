create table beer_audit (
      audit_id varchar(36) not null,
      id varchar(36) not null,
      beer_name varchar(50) not null,
      beer_style smallint not null,
      created_date datetime(6),
      price decimal(38,2) not null,
      quantity_on_hand integer,
      upc varchar(255) not null,
      update_date datetime(6),
      version integer,
      created_date_audit datetime(6),
      principal_name varchar(255),
      audit_event_type varchar(255),
      primary key (audit_id)
) engine=InnoDB;