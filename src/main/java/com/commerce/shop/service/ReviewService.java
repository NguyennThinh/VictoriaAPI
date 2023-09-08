package com.commerce.shop.service;

import com.commerce.shop.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findReviewByProductId(int productId);
}
