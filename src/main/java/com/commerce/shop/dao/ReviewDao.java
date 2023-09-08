package com.commerce.shop.dao;

import com.commerce.shop.exception.NotFoundException;
import com.commerce.shop.model.Product;
import com.commerce.shop.model.Review;
import com.commerce.shop.repository.ProductRepository;
import com.commerce.shop.repository.ReviewRepository;
import com.commerce.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewDao implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Review> findReviewByProductId(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Not found product"));


        return reviewRepository.findByProduct(product);
    }
}
