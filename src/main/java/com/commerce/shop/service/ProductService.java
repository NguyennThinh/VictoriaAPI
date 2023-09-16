package com.commerce.shop.service;

import com.commerce.shop.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findProductByCategory(int categoryId, Pageable pageable);

    List<Product> findAllProductByCategory(int categoryId);

    Product findProductById(int id);

    List<Product> findProductTrending();

    List<Product> findProductLatest();

    List<Product> findOtherProduct();

    List<Product> findProductSale();

    List<Product> findAllProduct();

    List<Product>  findProductPage(Pageable pageable);
    List<Product>  findSaleProductPage(Pageable pageable);

    List<Product>  searchProduct(String keyword);
}
