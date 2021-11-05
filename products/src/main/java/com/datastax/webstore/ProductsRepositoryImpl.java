package com.datastax.webstore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

@ApplicationScoped
public class ProductsRepositoryImpl implements ProductsRepository {

    @Inject
    private QuarkusCqlSession cqlSession;

    @Override
    public Optional<Products> findByItemId(String id) {
        return Optional.ofNullable(
                cqlSession.execute(SimpleStatement.newInstance("SELECT * from products where inventory_itemid = ?", id)).one()
        ).map(this::mapToProducts);
    }

    @Override
    public Products insert(Products Products) {
        cqlSession.execute(SimpleStatement.newInstance("INSERT INTO products ( inventory_itemid, catalog_name, catalog_description, catalog_price, inventory_quantity) VALUES (?,?,?,?,?)",
        Products.getItemId(), Products.getName(), Products.getDescription(), Products.getPrice(), Products.getQuantity()));
        return Products;
    }

    @Override
    public void deleteByItemId(String id) {
        cqlSession.execute(SimpleStatement.newInstance("DELETE from products where inventory_itemid = ?", id));
    }

    public void deleteAll() {
        cqlSession.execute(SimpleStatement.newInstance("TRUNCATE products"));
    }

    @Override
    public List<Products> findAll() {
        ResultSet result = cqlSession.execute(SimpleStatement.newInstance("SELECT * FROM products"));
        return result.all().stream().map(this::mapToProducts).collect(Collectors.toList());
    }

    @Override
    public Products update(Products Products) {
        cqlSession.execute(SimpleStatement.newInstance("UPDATE products SET catalog_name = ?, catalog_description = ?, catalog_price = ?, inventory_quantity = ? WHERE inventory_itemid = ?",
        Products.getName(), Products.getDescription(), Products.getPrice(), Products.getQuantity(), Products.getItemId()));
        return findByItemId(Products.getItemId()).orElseThrow(RuntimeException::new);
    }


    private Products mapToProducts(Row row) {
        return new Products(row.getString("inventory_itemid"), row.getString("catalog_name"), row.getString("catalog_description"), row.getDouble("catalog_price"), row.getInt("inventory_quantity"));
    }
}