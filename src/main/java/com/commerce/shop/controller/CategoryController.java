package com.commerce.shop.controller;

import com.commerce.shop.security.request.SecurityConstants;
import com.commerce.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecurityConstants.CATEGORY_MAPPING)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        return ResponseEntity.ok(categoryService.findAll());
    }
}
