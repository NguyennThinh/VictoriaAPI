package com.commerce.shop.repository;

import com.commerce.shop.model.Category;
import com.commerce.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

    @Query("from Product order by quantitySold desc limit 8")
    List<Product> findProductTrending();

    @Query("from Product order by pid desc limit 8")
    List<Product> findProductLatest();

    @Query("from Product order by function('newid') limit 6")
    List<Product> findOtherProduct();
}
