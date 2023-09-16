package com.commerce.shop.controller;

import com.commerce.shop.security.request.SecurityConstants;
import com.commerce.shop.service.ProductService;
import com.commerce.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> findProductByCategory(@RequestParam("category-id") int id,@RequestParam("page") int page,@RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(productService.findProductByCategory(id, pageable));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> findProductByCategory(@PathVariable int id){

        return ResponseEntity.ok(productService.findAllProductByCategory(id));
    }
    //API detail product
    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable int id){


        return ResponseEntity.ok(productService.findProductById(id));
    }

    //API list product trending
    @GetMapping("/trending-index")
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

    @GetMapping("/sale")
    public ResponseEntity<?> findProductSale(){


        return ResponseEntity.ok(productService.findProductSale());
    }


    @GetMapping("/reviews")
    public ResponseEntity<?> findReviewByIdProduct(@RequestParam("product")int id){


        return ResponseEntity.ok(reviewService.findReviewByProductId(id));
    }

    @GetMapping("/shop-trending")
    public ResponseEntity<?> findProductTrending(@RequestParam("page")int page, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(page, limit);

        return ResponseEntity.ok(productService.findProductPage(pageable));


    }
    @GetMapping("/shop-sale")
    public ResponseEntity<?> findProductSale(@RequestParam("page")int page, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(page, limit);

        return ResponseEntity.ok(productService.findSaleProductPage(pageable));


    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllProduct(){


        return ResponseEntity.ok(productService.findAllProduct());
    }

}
