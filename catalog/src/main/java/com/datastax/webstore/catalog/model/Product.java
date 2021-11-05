package com.datastax.webstore.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private UUID itemId;
	private String name;
	private String desc;
	private int price;
	private int quantity;
}
