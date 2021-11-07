package com.datastax.webstore;

public class Product {

    private String itemId;


    private String name;
	

    private String description;

    private double price;

    private double quantity;



    public Product() {

    }


    public Product(String itemId, String name, String description, double price, double quantity) {
        super();
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = price;
    }


    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
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
    public void setDesc(String description) {
        this.description = description;
    }


    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    
}
