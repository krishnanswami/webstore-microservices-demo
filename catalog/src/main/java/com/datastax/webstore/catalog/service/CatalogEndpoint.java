package com.datastax.webstore.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.datastax.webstore.catalog.model.Product;
import java.util.List;
import java.util.UUID;

import com.datastax.webstore.catalog.service.CatalogService;

@Controller
@RequestMapping("/api")
public class CatalogEndpoint {

    @Autowired
    private CatalogService catalogService;

    @ResponseBody
    @GetMapping("/products")
    @CrossOrigin
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<List<Product>>(catalogService.readAll(),HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    @CrossOrigin
    public ResponseEntity<Product> read(@PathVariable("id") UUID id) {
        return new ResponseEntity<Product>(catalogService.read(id),HttpStatus.OK);
    }

}
