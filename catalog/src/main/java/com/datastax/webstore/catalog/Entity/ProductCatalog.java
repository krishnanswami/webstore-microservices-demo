package com.datastax.webstore.catalog.entity;

import org.springframework.data.cassandra.core.mapping.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Table(value = "product_catalog")
@Data
public class ProductCatalog implements Serializable{


    @PrimaryKey
	@Column(value = "itemid")
    private UUID itemId;

	@Column(value = "product_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;
	
    @Column(value = "product_desc")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

	@Column(value = "product_price")
    @CassandraType(type = CassandraType.Name.INT)
    private int price;
	
	/*
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public UUID getItemId() {
		return itemId;
	}
	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
    }
    */
}
