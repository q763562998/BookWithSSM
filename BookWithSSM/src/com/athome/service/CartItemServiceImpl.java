package com.athome.service;

import com.athome.mapper.CartItemMapper;
import com.athome.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartItemServiceImpl implements CartItem {

    @Autowired
    CartItemMapper cartItemMapper;


//    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertCartItem(Cart cart) {
        cartItemMapper.insertCartItem(cart);

    }

    @Override
    public void updateCartItem(Cart cart) {
        cartItemMapper.updateCartItem(cart);
    }

    @Override
    public List<Cart> getAllCartByName(String name) {
        List<Cart> list = cartItemMapper.getAllCartByName(name);
        return list;
    }

    @Override
    public List<Cart> getAllCart() {
        List<Cart> allCart = cartItemMapper.getAllCart();
        return allCart;
    }

    @Override
    public void deleteCartItem(String orderNumber) {
        cartItemMapper.deleteCartItem(orderNumber);
    }

    @Override
    public void deleteAllCartItem() {
        cartItemMapper.deleteAllCartItem();
    }


}
