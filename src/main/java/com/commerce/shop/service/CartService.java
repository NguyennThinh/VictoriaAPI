package com.commerce.shop.service;

import com.commerce.shop.model.Cart;
import com.commerce.shop.model.ItemCart;
import com.commerce.shop.model.Product;
import com.commerce.shop.model.dto.ListItemCheckout;

import java.util.List;

public interface CartService {


    Cart viewCart();
    Cart addToCart(int idProduct, int qty, String uEmail);

    void removeItem(int id);


}
