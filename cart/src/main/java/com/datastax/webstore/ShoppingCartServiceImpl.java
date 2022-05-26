package com.datastax.webstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;



@ApplicationScoped
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @ConfigProperty(name = "astradb.token") 
    String token;

    //private final String token = "AstraCS:eXDPNNydhasfGStboxPDfAJH:96c1072290a1a62b80850c034ee36bdeb6aa92d7d93f257157692998d5b3f159";

    //private final String token = "b2e70572-0427-47f1-a119-16e99cb65592";

    @Inject
    PromotionService ps;

    @Inject
    ShippingService ss;

    @RestClient
    ShoppingDocumentService sds;

    @RestClient
    OrderService ors;

    private Map<String, Product> productMap = new HashMap<>();

    
    @Override
    public ShoppingCart getShoppingCart(String cartId) {
        //ShoppingCart cart = new ShoppingCart(cartId);
        /*
        if (!carts.containsKey(cartId)) {
            ShoppingCart cart = new ShoppingCart(cartId);
            //carts.put(cartId, cart);
            //this.put("AstraCS:dhdIZyEWUmvHsPXgUUZtQZnS:88fa2ad6e59d9ff7ed18617519834411d289b9c83ac11fa8a5358a852f6c5ae1",cartId, cart);
            return cart;
        }
        */

        try {
            ShoppingCart cart = sds.get(token,"cart", "cart", cartId).getData();
            log.info("Item found so pricing to the shopping cart {} ", cartId);
            priceShoppingCart(cart);
            sds.put(token, "cart", "cart", cartId, cart);
            return cart;
        } catch (Exception ex) {
            if (ex.getMessage().contains("status code 404"))
            {
                log.info("No item found so adding to the shopping cart {} ", cartId);
                ShoppingCart cart = new ShoppingCart(cartId);
                sds.put(token, "cart", "cart", cartId, cart);
                return cart;
            }
            else{
                throw ex;
            }
            
        }

        //ShoppingCart cart = sds.get(token,"cart", "cart", cartId).getData();
        //priceShoppingCart(cart);
        //sds.put(token, "cart", "cart", cartId, cart);
        //return cart;
    
        //return null;

    }

    public void priceShoppingCart(ShoppingCart sc) {
        if (sc != null) {
            initShoppingCartForPricing(sc);

            if (sc.getShoppingCartItemList() != null && sc.getShoppingCartItemList().size() > 0) {
                ps.applyCartItemPromotions(sc);

                for (Cart sci : sc.getShoppingCartItemList()) {
                    sc.setCartItemPromoSavings(sc.getCartItemPromoSavings() + sci.getPromoSavings() * sci.getQuantity());
                    sc.setCartItemTotal(sc.getCartItemTotal() + sci.getPrice() * sci.getQuantity());
                }

                ss.calculateShipping(sc);
            }

            ps.applyShippingPromotions(sc);
            sc.setCartTotal(sc.getCartItemTotal() + sc.getShippingTotal());
        }
    }

    void initShoppingCartForPricing(ShoppingCart sc) {
        sc.setCartItemTotal(0);
        sc.setCartItemPromoSavings(0);
        sc.setShippingTotal(0);
        sc.setShippingPromoSavings(0);
        sc.setCartTotal(0);

        for (Cart sci : sc.getShoppingCartItemList()) {
            Product p = getProduct(sci.getProduct().getItemId());

            //if product exist, create new product to reset price
            if (p != null) {
                sci.setProduct(new Product(p.getItemId(), p.getName(), p.getDescription(), p.getPrice(), p.getQuantity()));
                sci.setPrice(p.getPrice());
            }

            sci.setPromoSavings(0);
        }
    }

    @Override
    public Product getProduct(String itemId) {
        if (!productMap.containsKey(itemId)) {
            // Fetch and cache products. TODO: Cache should expire at some point!
            productMap = getProducts().stream().collect(Collectors.toMap(Product::getItemId, Function.identity()));
        }

        return productMap.get(itemId);
    }

    private List<Product> getProducts() {
        // Mock Method for catalog service
        List<Product> products = new ArrayList<>();
        products.add(new Product("329299", "DataStax T-shirt", "", 10.00, 736));
        products.add(new Product("329199", "DataStax pouch", "", 9.00, 512));
        products.add(new Product("165613", "DataStax bottle", "",4.15, 256));
        products.add(new Product("165614", "DataStax pen", "", 14.45, 54));
        products.add(new Product("165954", "DataStax Diary", "", 6.00, 87));
        products.add(new Product("444434", "Apache Cassandra T-shirt", "", 9.00, 443));
        products.add(new Product("444435",  "DataStax Coffee Mug", "",13.00, 600));
        products.add(new Product("444437", "DataStax Socks", "", 2.75, 230));

        return products;
    }

    @Override
    public ShoppingCart deleteItem(String cartId, String itemId, int quantity) {
        List<Cart> toRemoveList = new ArrayList<>();

        ShoppingCart cart = getShoppingCart(cartId);

        cart.getShoppingCartItemList().stream()
                .filter(sci -> sci.getProduct().getItemId().equals(itemId))
                .forEach(sci -> {
                    if (quantity >= sci.getQuantity()) {
                        toRemoveList.add(sci);
                    } else {
                        sci.setQuantity(sci.getQuantity() - quantity);
                    }
                });

        toRemoveList.forEach(cart::removeShoppingCartItem);
        priceShoppingCart(cart);
        //carts.put(cartId, cart);
        sds.put(token,"cart", "cart", cartId, cart);
        return cart;
    }

    

    @Override
    public ShoppingCart checkout(String cartId, Order order) {
        ShoppingCart cart = getShoppingCart(cartId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String orderjson = mapper.writeValueAsString(order);
            log.info("Placing an Order {} ", orderjson); 
            ors.post(token, "orders", "orders", orderjson);
        }
        catch (Exception ex) {
            ex.printStackTrace();
         }
        
        cart.resetShoppingCartItemList();
        priceShoppingCart(cart);
        //carts.put(cartId, cart);
        sds.put(token,"cart", "cart", cartId, cart);
        return cart;
    }
    

    @Override
    public ShoppingCart addItem(String cartId, String itemId, int quantity) {
        log.info("Request to get added to the shopping cart {} {} ", cartId, itemId);
        ShoppingCart cart = getShoppingCart(cartId);
        //ShoppingCart cart = new ShoppingCart(cartId);
    
        Product product = getProduct(itemId);

        log.info("Product Identified {} {} ", product.getName(), product.getPrice());

        if (product == null) {
            log.warn("Invalid product {} request to get added to the shopping cart. No product added", itemId);
            return cart;
        }


        Cart sci = new Cart (product, product.getPrice(), quantity, 0.0f);
        cart.addShoppingCartItem(sci);

        log.info("Cart created {} {} ", cart.getCartId(), cart.getCartItemPromoSavings());

        try {
            priceShoppingCart(cart);
            cart.setShoppingCartItemList(dedupeCartItems(cart));
        } catch (Exception ex) {
            cart.removeShoppingCartItem(sci);
            throw ex;
        }

        log.info("Cart created with price {} {} ", cart.getCartId(), cart.getShoppingCartItemList().size());

        //carts.put(cartId, cart);
        sds.put(token,"cart", "cart",cartId, cart);
        return cart;
    }

    @Override
    public ShoppingCart set(String cartId, String tmpId) {

        ShoppingCart cart = getShoppingCart(cartId);
        ShoppingCart tmpCart = getShoppingCart(tmpId);

        if (tmpCart != null) {
            cart.resetShoppingCartItemList();
            cart.setShoppingCartItemList(tmpCart.getShoppingCartItemList());
        }

        try {
            priceShoppingCart(cart);
            cart.setShoppingCartItemList(dedupeCartItems(cart));
        } catch (Exception ex) {
            throw ex;
        }

        sds.put(token,"cart", "cart", cartId, cart);
        return cart;
    }

    List<Cart> dedupeCartItems(ShoppingCart sc) {
        List<Cart> result = new ArrayList<>();
        Map<String, Integer> quantityMap = new HashMap<>();
        for (Cart sci : sc.getShoppingCartItemList()) {
            if (quantityMap.containsKey(sci.getProduct().getItemId())) {
                quantityMap.put(sci.getProduct().getItemId(), quantityMap.get(sci.getProduct().getItemId()) + sci.getQuantity());
            } else {
                quantityMap.put(sci.getProduct().getItemId(), sci.getQuantity());
            }
        }

        for (String itemId : quantityMap.keySet()) {
            Product p = getProduct(itemId);
            Cart newItem = new Cart();
            newItem.setQuantity(quantityMap.get(itemId));
            newItem.setPrice(p.getPrice());
            newItem.setProduct(p);
            result.add(newItem);
        }

        return result;
    }
}