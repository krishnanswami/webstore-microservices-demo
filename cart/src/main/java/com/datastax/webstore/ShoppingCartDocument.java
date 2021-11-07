package com.datastax.webstore;


public class ShoppingCartDocument {
    public String documentId;
    public ShoppingCart data;

    public String getDocumentId() {
        return documentId;
    }
    
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public ShoppingCart getData() {
        return data;
    }
    
    public void setData(ShoppingCart data) {
        this.data = data;
    }
    
}

