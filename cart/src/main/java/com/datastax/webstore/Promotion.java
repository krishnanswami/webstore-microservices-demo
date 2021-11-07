package com.datastax.webstore;


public class Promotion {

    private String itemId;
    private double percentOff = 0.0f;


    public Promotion(String itemId, double percentOff) {
        super();
        this.itemId = itemId;
        this.percentOff = percentOff;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }
    
}
