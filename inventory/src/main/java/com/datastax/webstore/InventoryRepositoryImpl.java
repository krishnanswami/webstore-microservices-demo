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
public class InventoryRepositoryImpl implements InventoryRepository {

    @Inject
    private QuarkusCqlSession cqlSession;

    @Override
    public Optional<Inventory> findById(UUID id) {
        return Optional.ofNullable(
                cqlSession.execute(SimpleStatement.newInstance("SELECT * from inventory where id = ?", id)).one()
        ).map(this::mapToInventory);
    }

    @Override
    public Inventory insert(Inventory inventory) {
        cqlSession.execute(SimpleStatement.newInstance("INSERT INTO inventory (id, itemid, location, quantity, link) VALUES (?,?,?,?,?)",
        inventory.getId(), inventory.getItemId(), inventory.getLocation(), inventory.getQuantity(), inventory.getLink()));
        return inventory;
    }

    @Override
    public void deleteById(UUID id) {
        cqlSession.execute(SimpleStatement.newInstance("DELETE from inventory where id = ?", id));
    }

    public void deleteAll() {
        cqlSession.execute(SimpleStatement.newInstance("TRUNCATE inventory"));
    }

    @Override
    public List<Inventory> findAll() {
        ResultSet result = cqlSession.execute(SimpleStatement.newInstance("SELECT * FROM inventory"));
        return result.all().stream().map(this::mapToInventory).collect(Collectors.toList());
    }

    @Override
    public Inventory update(Inventory inventory) {
        cqlSession.execute(SimpleStatement.newInstance("UPDATE inventory SET itemid = ?, location = ?, quantity = ?, link = ? WHERE id = ?",
        inventory.getId(), inventory.getItemId(), inventory.getLocation(), inventory.getQuantity(), inventory.getLink()));
        return findById(inventory.getId()).orElseThrow(RuntimeException::new);
    }


    private Inventory mapToInventory(Row row) {
        return new Inventory(row.getUuid("id"), row.getString("itemId"), row.getString("location"), row.getInt("quantity"), row.getString("link"));
    }
}