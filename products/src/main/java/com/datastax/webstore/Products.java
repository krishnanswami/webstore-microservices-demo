package com.datastax.webstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.datastax.oss.driver.api.mapper.annotations.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @PartitionKey
	@CqlName(value = "inventory_itemid")
    private String itemId;

	@CqlName(value = "catalog_name")
    private String name;
	
    @CqlName(value = "catalog_description")
    private String description;

	@CqlName(value = "catalog_price")
    private double price;

    @CqlName(value = "inventory_quantity")
    private double quantity;

    
}
