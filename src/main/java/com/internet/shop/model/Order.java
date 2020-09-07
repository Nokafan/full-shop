package com.internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private Long userId;
    private List<Product> products;

    public Order(Long userId) {
        this.userId = userId;
        products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return Objects.equals(id, order.id)
                && Objects.equals(userId, order.userId)
                && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, products);
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", userId=" + userId
                + ", products=" + products
                + '}';
    }
}
