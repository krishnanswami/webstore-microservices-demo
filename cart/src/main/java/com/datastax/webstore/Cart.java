package com.datastax.webstore;


public class Cart {

    private double price = 0.0f;
    private int quantity = 0;
    private double promoSavings = 0.0f;
    private Product product;

    public Cart() {}

    public Cart(Product product, double price, int quantity, double promoSavings) {
        this.price = price;
        this.quantity = quantity;
        this.promoSavings = promoSavings;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }


    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPromoSavings() {
        return promoSavings;
    }
    public void setPromoSavings(double promoSavings) {
        this.promoSavings = promoSavings;
    }
    
}
