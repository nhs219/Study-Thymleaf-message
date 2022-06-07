package com.marketProject.service;

import com.marketProject.jpa.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> findProducts(String productCode);
    public Product findProduct(String productCode);
}
