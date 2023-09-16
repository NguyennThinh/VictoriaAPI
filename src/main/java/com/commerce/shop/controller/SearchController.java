package com.commerce.shop.controller;

import com.commerce.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private ProductService productService;
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam("keyword") String keyword){


        return ResponseEntity.ok(productService.searchProduct(keyword));
    }
}
