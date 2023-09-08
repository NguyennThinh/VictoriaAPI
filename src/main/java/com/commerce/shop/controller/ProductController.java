package com.commerce.shop.controller;

import com.commerce.shop.security.request.SecurityConstants;
import com.commerce.shop.service.ProductService;
import com.commerce.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SecurityConstants.PRODUCT_MAPPING)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<?> findProductByCategory(@RequestParam("category-id") int id){


        return ResponseEntity.ok(productService.findProductByCategory(id));
    }

    //API detail product
    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable int id){


        return ResponseEntity.ok(productService.findProductById(id));
    }

    //API list product trending
    @GetMapping("/product-trending")
    public ResponseEntity<?> findProductTrending(){


        return ResponseEntity.ok(productService.findProductTrending());
    }

    //API list product new
    @GetMapping("/latest")
    public ResponseEntity<?> findProductLatest(){


        return ResponseEntity.ok(productService.findProductLatest());
    }

    //API list product random
    @GetMapping("/other")
    public ResponseEntity<?> findOtherProduct(){


        return ResponseEntity.ok(productService.findOtherProduct());
    }
    //API list product random
    @GetMapping("/reviews")
    public ResponseEntity<?> findReviewByIdProduct(@RequestParam("product")int id){


        return ResponseEntity.ok(reviewService.findReviewByProductId(id));
    }
}
