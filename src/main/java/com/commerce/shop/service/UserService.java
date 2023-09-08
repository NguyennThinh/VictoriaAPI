package com.commerce.shop.service;

import com.commerce.shop.model.Review;
import com.commerce.shop.model.User;
import com.commerce.shop.model.dto.Account;

public interface UserService {

    User findUserByEmail(String email);

    User register(Account account);

    Review userComment(String comment, int idProduct, int rating);

}



