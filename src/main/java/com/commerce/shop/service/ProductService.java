package com.commerce.shop.service;

import com.commerce.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findProductByCategory(int categoryId);

    Product findProductById(int id);

    List<Product> findProductTrending();

    List<Product> findProductLatest();

    List<Product> findOtherProduct();
}
