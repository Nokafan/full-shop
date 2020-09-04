package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Long shoppingCartId;
    private List<Product> products;
    private Long userId;

    public ShoppingCart(Long userId) {
        products = new ArrayList<>();
        this.userId = userId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
