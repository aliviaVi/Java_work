CREATE TABLE customer (
  customer_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR (45) NOT NULL ,
  PRIMARY KEY  (customer_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE shipment (
  shipment_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NOT NULL,
  customer_id SMALLINT UNSIGNED,
  PRIMARY KEY  (shipment_id),
  FOREIGN KEY (customer_id)
  REFERENCES customer(customer_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tracking(
  tracking_id SMALLINT UNSIGNED NOT NULL ,
  shipment_id SMALLINT  UNSIGNED,
  status ENUM ('shipped','returned','delivered','cancelled'),
  PRIMARY KEY (tracking_id),
  FOREIGN KEY (shipment_id)
  REFERENCES shipment(shipment_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO customer (customer_id, first_name, last_name)
VALUES (1,'Sergey','Lukichev'),
       (2,'Ivan','Petrov'),
       (3,'Anna','Ivanova'),
       (4,'Alla','Petrova'),
       (5,'Dmitri','Sokolov'),
       (6,'Oleg','Smirnov'),
       (7,'Olga','Ivanova');

INSERT INTO shipment (shipment_id, title, customer_id)
VALUES (1, 'letter', 3),
       (2, 'packet', 4),
       (3, 'expressEasy', 6),
       (4, 'International Package', 7);

INSERT INTO tracking(tracking_id, shipment_id, status)

VALUES (3,1,'shipped'),
       (5,3,'returned'),
       (8,4,'delivered'),
       (6,2,'cancelled');
