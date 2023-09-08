package com.commerce.shop.repository;

import com.commerce.shop.model.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCartRepository extends JpaRepository<ItemCart, Integer> {


}
