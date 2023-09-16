package com.commerce.shop.controller;

import com.commerce.shop.model.User;
import com.commerce.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //API get my prodile
    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        return ResponseEntity.ok(user);
    }

    //API comment product

    @PostMapping("/user/comment")
    public ResponseEntity<?> commentProduct(@RequestBody Map<String, String> body) {
        int idProduct = Integer.parseInt(body.get("product"));
        String comment = body.get("comment");
        int reating = Integer.parseInt(body.get("rating"));
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();


        return ResponseEntity.ok(userService.userComment(comment, idProduct, reating));
    }


    @PostMapping("/user/checkout")
    public ResponseEntity<?> checkout(@RequestBody Map<String, String> body) {

        String address = body.get("address");
        String country = body.get("country");
        String state = body.get("state");
        String payment = body.get("payment");
        boolean isSave = Boolean.getBoolean(body.get("isSaveAddress"));
       boolean result =  userService.checkout(address, country, state, payment, isSave);

       if (result)
           return new ResponseEntity<>(true, HttpStatus.OK);

        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

    }


}
