create keyspace if not exists catalog WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3' };

use catalog;

create table if not exists products (
	inventory_itemid text,
    catalog_name text,
    catalog_description text,
    catalog_price double,
    inventory_quantity int,
    PRIMARY KEY(inventory_itemid)
);


create table if not exists catalog (
	id UUID,
	inventory_itemid text,
    catalog_name text,
 	catalog_description text,
    catalog_price double,
    PRIMARY KEY(id, inventory_itemid)
);


INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('329299', 'DataStax T-shirt', '', 10.00, 736);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('329199', 'DataStax pouch', '', 9.00, 512);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('165613', 'DataStax bottle', '',4.15, 256);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('165614', 'DataStax pen', '', 14.45, 54);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('165954', 'DataStax Diary', '', 6.00, 87);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('444434', 'Apache Cassandra T-shirt', '', 9.00, 443);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('444435',  'DatStax Coffee Mug', '',13.00, 600);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('444437', 'DataStax Socks', '', 2.75, 230);


insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (5bfc9b48-3dd9-11ec-9bbc-0242ac130002, '329299', 'DataStax T-shirt', '', 10.00);
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (63d18d2e-3dd9-11ec-9bbc-0242ac130002, '329199', 'DataStax pouch', '', 9.00);
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (7576d2a8-3ddb-11ec-9bbc-0242ac130002, '165613', 'DataStax bottle', '',4.15);
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (7e67d4fc-3ddb-11ec-9bbc-0242ac130002, '165614', 'DataStax pen', '', 14.45);
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (83dadf74-3ddb-11ec-9bbc-0242ac130002, '165954', 'DataStax Diary', '', 6.00);
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (8d9dd5ca-3ddb-11ec-9bbc-0242ac130002, '444434', 'Apache Cassandra T-shirt', '', 9.00);
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (94efd5bc-3ddb-11ec-9bbc-0242ac130002, '444435', 'DatStax Coffee Mug', '',13.00 );
insert into catalog (id, inventory_itemid, catalog_name, catalog_description, catalog_price) values (9c0fd37e-3ddb-11ec-9bbc-0242ac130002, '444437', 'DataStax Socks', '', 2.75);