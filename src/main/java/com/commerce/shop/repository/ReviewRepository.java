package com.commerce.shop.repository;

import com.commerce.shop.model.Product;
import com.commerce.shop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer > {

    List<Review> findByProduct(Product product);
}
