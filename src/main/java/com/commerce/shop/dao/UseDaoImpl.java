package com.commerce.shop.dao;

import com.commerce.shop.exception.ConflictException;
import com.commerce.shop.exception.NotFoundException;
import com.commerce.shop.model.*;
import com.commerce.shop.model.dto.Account;
import com.commerce.shop.model.dto.RandomCode;
import com.commerce.shop.repository.ProductRepository;
import com.commerce.shop.repository.ReviewRepository;
import com.commerce.shop.repository.RoleRepository;
import com.commerce.shop.repository.UserRepository;
import com.commerce.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UseDaoImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found User by email:" +email));
    }

    @Override
    public User register(Account account) {

        User user = userRepository.findByEmail(account.getEmail()).orElse(null);
        if (user != null){
            throw new ConflictException("User exists!");
        }
        Cart cart = new Cart();
        user = new User();
        user.setUid(RandomCode.getRandomString(12));
        user.setFirstName(account.getFirstName());
        user.setLastName(account.getLastName());
        user.setEmail(account.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        user.setDelete(false);

        Role role = roleRepository.findByRoleName("ROLE_EMPLOYEE");
        user.setRole(role);

        cart.setUser(user);
        user.setCart(cart);
        System.out.println(cart);
        return  userRepository.save(user);
    }

    @Override
    public Review userComment(String comment, int idProduct, int rating) {

        Product product = productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Not found product"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getPrincipal().toString()).orElseThrow(() -> new NotFoundException("Not found user"));

        Review review = new Review();
        review.setComment(comment);
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);

        return reviewRepository.save(review);
    }
}
