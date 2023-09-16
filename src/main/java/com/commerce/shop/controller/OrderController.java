package com.commerce.shop.controller;

import com.commerce.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/order")
    public ResponseEntity<?> getMyOrder(){


        return ResponseEntity.ok(orderService.getMyOrder());
    }
}
