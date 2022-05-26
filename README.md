Create a Database name it anything. I named fe_cdc_demo and created catalog keyspace


use catalog;

create table if not exists products (
	inventory_itemid text,
    catalog_name text,
    catalog_description text,
    catalog_price double,
    inventory_quantity int,
    PRIMARY KEY(inventory_itemid)
);

INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('329299', 'DataStax T-shirt', '', 10.00, 736);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('329199', 'DataStax pouch', '', 9.00, 512);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('165613', 'DataStax bottle', '',4.15, 256);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('165614', 'DataStax pen', '', 14.45, 54);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('165954', 'DataStax Diary', '', 6.00, 87);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('444434', 'Apache Cassandra T-shirt', '', 9.00, 443);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('444435',  'DatStax Coffee Mug', '',13.00, 600);
INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) values ('444437', 'DataStax Socks', '', 2.75, 230);
