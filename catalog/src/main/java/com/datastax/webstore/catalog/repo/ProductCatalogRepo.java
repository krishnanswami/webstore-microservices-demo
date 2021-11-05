package com.datastax.webstore.catalog.repo;


import java.util.UUID;
import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.datastax.webstore.catalog.entity.ProductCatalog;


@Repository
@Transactional
public interface ProductCatalogRepo extends CassandraRepository<ProductCatalog, UUID> {

 
  ProductCatalog findByKeyItemId(UUID itemId);

  List<ProductCatalog> findAll();
    
}
