package com.datastax.webstore.catalog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    private UUID id;
    private String itemId;
    private String location;
    private int quantity;
    private String link;
    
}