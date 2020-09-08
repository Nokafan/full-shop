package com.internet.shop.service.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao cartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return cartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart get(Long id) {
        return cartDao.get(id).get();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return cartDao.getAll();
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return cartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().remove(product);
        return cartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return cartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        return cartDao.update(shoppingCart);
    }

    @Override
    public boolean delete(Long id) {
        return cartDao.delete(id);
    }
}
