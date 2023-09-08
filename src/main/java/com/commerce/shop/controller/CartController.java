package com.commerce.shop.controller;

import com.commerce.shop.model.Cart;
import com.commerce.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

@Autowired
private CartService cartService;

    @GetMapping("/items")
    public ResponseEntity<?> getItemCart(){

        Cart cart = cartService.viewCart();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestParam("pid")int id, @RequestParam("qty") int qty){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String uEmail = authentication.getName();
           Cart cart= cartService.addToCart(id, qty, uEmail);
            return ResponseEntity.ok(cart);
        }
        return new ResponseEntity<>("Có lỗi", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/item/remove")
    public ResponseEntity<?> removeItem(@RequestParam("item-id")int id){


        cartService.removeItem(id);

        return  new ResponseEntity<>("Delete success", HttpStatus.OK);
    }
}
