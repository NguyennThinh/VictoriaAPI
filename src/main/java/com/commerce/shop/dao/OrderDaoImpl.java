package com.commerce.shop.dao;

import com.commerce.shop.exception.NotFoundException;
import com.commerce.shop.model.Order;
import com.commerce.shop.model.User;
import com.commerce.shop.repository.OrderRepository;
import com.commerce.shop.repository.UserRepository;
import com.commerce.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDaoImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Order> getMyOrder() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Not found user"));

        return user.getOrders();
    }
}
