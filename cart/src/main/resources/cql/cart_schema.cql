create keyspace if not exists cart WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3' };

use cart;

create table if not exists cart_items(
	cart_id UUID,
    inventory_itemid text,
    catalog_name text,
    catalog_description text,
    catalog_price double,
    inventory_quantity int,
    PRIMARY KEY(inventory_itemid)
);


create table if not exists inventory(
	id UUID,
	itemid text,
    location text,
    quantity int,
 	link text,
    PRIMARY KEY(id, itemid)
);
