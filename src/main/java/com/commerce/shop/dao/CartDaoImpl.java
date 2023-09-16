package com.commerce.shop.dao;

import com.commerce.shop.exception.NotFoundException;
import com.commerce.shop.model.Cart;
import com.commerce.shop.model.ItemCart;
import com.commerce.shop.model.Product;
import com.commerce.shop.model.User;
import com.commerce.shop.model.dto.ListItemCheckout;
import com.commerce.shop.repository.CartRepository;
import com.commerce.shop.repository.ItemCartRepository;
import com.commerce.shop.repository.ProductRepository;
import com.commerce.shop.repository.UserRepository;
import com.commerce.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDaoImpl  implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemCartRepository itemRepository;



    @Override
    public Cart viewCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        return cartRepository.findById(user.getUid()).orElseThrow(() -> new NotFoundException("Không tìm thấy giỏ hàng"));
    }

    @Override
    public Cart addToCart(int idProduct, int qty, String uEmail) {

        Product product = productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));

        User user = userRepository.findByEmail(uEmail).orElseThrow(() ->new NotFoundException("Không tìm thấy người dùng") );

        Cart cart =cartRepository.findById(user.getUid()).orElseThrow(() -> new NotFoundException("Không tìm thấy giỏ hàng"));

        ItemCart itemCart = new ItemCart();
        itemCart.setProduct(product);
        itemCart.setQuantity(qty);
        itemCart.setCart(cart);
        cart.addToChildren(itemCart);



        return cartRepository.save(cart);
    }

    @Override
    public void removeItem(int id) {
        ItemCart itemCart = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy item"));
        itemRepository.delete(itemCart);

        itemCart.getCart().getItems().remove(itemCart);

        cartRepository.save(itemCart.getCart());

    }


}
