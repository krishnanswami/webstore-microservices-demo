package com.datastax.webstore;

import java.util.ArrayList;
import java.util.List;

import com.datastax.webstore.Cart;


public class ShoppingCart {
    private double cartItemTotal = 0.0f;
    private double cartItemPromoSavings = 0.0f;
    private double shippingTotal = 0.0f;
    private double shippingPromoSavings = 0.0f;
    private double cartTotal = 0.0f;
    private String cartId;
    private List<Cart> shoppingCartItemList = new ArrayList<Cart>();

    public ShoppingCart() {}

    public ShoppingCart(String cartId){
        this.cartId = cartId;
    }

    public ShoppingCart(double cartItemTotal, double cartItemPromoSavings, double shippingTotal, double shippingPromoSavings, double cartTotal, String cartId, List<Cart> shoppingCartItemList) {
        this.cartItemTotal = cartItemTotal;
        this.cartItemPromoSavings = cartItemPromoSavings;
        this.shippingTotal = shippingTotal;
        this.shippingPromoSavings = shippingPromoSavings;
        this.cartTotal = cartTotal;
        this.cartId = cartId;
        this.shoppingCartItemList = shoppingCartItemList;
    }


    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<Cart> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<Cart> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    public void resetShoppingCartItemList() {
        shoppingCartItemList = new ArrayList<Cart>();
    }

    public void addShoppingCartItem(Cart sci) {
        if (sci != null) {
            shoppingCartItemList.add(sci);
        }
    }

    public boolean removeShoppingCartItem(Cart sci) {
        boolean removed = false;
        if (sci != null) {
            removed = shoppingCartItemList.remove(sci);
        }
        return removed;
    }

 
    public double getCartItemTotal() {
        return cartItemTotal;
    }

    public void setCartItemTotal(double cartItemTotal) {
        this.cartItemTotal = cartItemTotal;
    }

 
    public double getShippingTotal() {
        return shippingTotal;
    }

    public void setShippingTotal(double shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

  
    public double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }


    public double getCartItemPromoSavings() {
        return cartItemPromoSavings;
    }

    public void setCartItemPromoSavings(double cartItemPromoSavings) {
        this.cartItemPromoSavings = cartItemPromoSavings;
    }


    public double getShippingPromoSavings() {
        return shippingPromoSavings;
    }

    public void setShippingPromoSavings(double shippingPromoSavings) {
        this.shippingPromoSavings = shippingPromoSavings;
    }

}
