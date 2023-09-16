package com.commerce.shop.repository;

import com.commerce.shop.model.Cart;
import com.commerce.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, String> {

}
