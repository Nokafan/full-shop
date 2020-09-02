package com.internet.shop.service;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Product;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).orElseThrow(() ->
                new IllegalArgumentException(" This " + id + " doesn't exist!"));
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        try {
            return productDao.delete(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(" This " + id + " doesn't exist!");
        }
    }
}
