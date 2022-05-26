create keyspace if not exists orders WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3' };


create table if not exists orders.orders (
	orderId text,
    total text,
    creditCard text,
    billingAddress text,
    name text,
    PRIMARY KEY(orderId)
)