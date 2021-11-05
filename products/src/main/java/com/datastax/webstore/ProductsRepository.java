package com.datastax.webstore;

import java.util.Optional;
import java.util.UUID;

interface ProductsRepository {
    Optional<Products> findByItemId(String itemId);

    Products insert(Products body);

    Products update(Products body);

    void deleteByItemId(String id);

    void deleteAll();

    Iterable<Products> findAll();
}

