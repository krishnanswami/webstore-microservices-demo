create keyspace if not exists inventory WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3' };

use inventory;

create table if not exists inventory_data(
	id UUID,
	itemid text,
    location text,
    quantity int,
 	link text,
    PRIMARY KEY(id, itemid)
);


create table if not exists inventory(
	id UUID,
	itemid text,
    location text,
    quantity int,
 	link text,
    PRIMARY KEY(id, itemid)
);


INSERT INTO inventory (id, itemid, link, location, quantity) values (53e055ee-39f6-11ec-8d3d-0242ac130003, '329299', 'http://maps.google.com/?q=Raleigh', 'Raleigh', 736);
INSERT INTO inventory (id, itemid, link, location, quantity) values (5e4cab36-39f6-11ec-8d3d-0242ac130003, '329199', 'http://maps.google.com/?q=Boston', 'Boston', 512);
INSERT INTO inventory (id, itemid, link, location, quantity) values (6a8f6898-39f6-11ec-8d3d-0242ac130003, '165613', 'http://maps.google.com/?q=Seoul', 'Seoul', 256);
INSERT INTO inventory (id, itemid, link, location, quantity) values (75a76b9a-39f6-11ec-8d3d-0242ac130003, '165614', 'http://maps.google.com/?q=Singapore', 'Singapore', 54);
INSERT INTO inventory (id, itemid, link, location, quantity) values (7c097668-39f6-11ec-8d3d-0242ac130003, '165954', 'http://maps.google.com/?q=London', 'London', 87);
INSERT INTO inventory (id, itemid, link, location, quantity) values (835d3d3c-39f6-11ec-8d3d-0242ac130003, '444434', 'http://maps.google.com/?q=NewYork', 'New York', 443);
INSERT INTO inventory (id, itemid, link, location, quantity) values (8bd29e94-39f6-11ec-8d3d-0242ac130003, '444435', 'http://maps.google.com/?q=Paris', 'Paris', 600);
INSERT INTO inventory (id, itemid, link, location, quantity) values (92685366-39f6-11ec-8d3d-0242ac130003, '444437', 'http://maps.google.com/?q=Tokyo', 'Tokyo', 230);