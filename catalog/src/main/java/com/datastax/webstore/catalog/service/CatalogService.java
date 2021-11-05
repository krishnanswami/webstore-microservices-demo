package com.datastax.webstore.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.datastax.webstore.catalog.model.Product;
import com.datastax.webstore.catalog.repo.ProductCatalogRepo;
import com.datastax.webstore.catalog.client.InventoryClient;
import com.datastax.webstore.catalog.entity.ProductCatalog;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    @Autowired
    private ProductCatalogRepo repository;

    //TODO: Autowire Inventory Client
    @Autowired
    InventoryClient inventoryClient;

    public Product read(UUID id)  {
        ProductCatalog productCatalog = repository.findByKeyItemId(id);
        Product product = new Product(productCatalog.getItemId(), productCatalog.getName(), productCatalog.getDescription(), productCatalog.getPrice(), 0);
        //TODO: Update the quantity for the product by calling the Inventory service
        JSONArray jsonArray = new JSONArray(inventoryClient.getInventoryStatus(productCatalog.getItemId()));
        List<String> quantity = IntStream.range(0, jsonArray.length())
            .mapToObj(index -> ((JSONObject)jsonArray.get(index))
            .optString("quantity")).collect(Collectors.toList());
        product.setQuantity(Integer.parseInt(quantity.get(0)));
        return product;
    }

    public List<Product> readAll()  {

        
            List<ProductCatalog> productList = repository.findAll();
            List<Product> products = new ArrayList<>();
            //TODO: Update the quantity for the products by calling the Inventory service
         
            for ( ProductCatalog productCatalog : productList ) {
                Product product = new Product(productCatalog.getItemId(), productCatalog.getName(), productCatalog.getDescription(), productCatalog.getPrice(), 0);
                JSONArray jsonArray = new JSONArray(inventoryClient.getInventoryStatus(productCatalog.getItemId())); 
                    List<String> quantity = IntStream.range(0, jsonArray.length())
                        .mapToObj(index -> ((JSONObject)jsonArray.get(index))
                        .optString("quantity")).collect(Collectors.toList());
                    product.setQuantity(Integer.parseInt(quantity.get(0)));
               
                products.add(product);
                }
            
            return products; 
        
    }

    //TODO: Add Callback Factory Component


}
