package com.datastax.webstore;

import java.util.Optional;
import java.util.UUID;

interface InventoryRepository {
    Optional<Inventory> findById(UUID id);

    Inventory insert(Inventory body);

    Inventory update(Inventory body);

    void deleteById(UUID id);

    void deleteAll();

    Iterable<Inventory> findAll();
}

