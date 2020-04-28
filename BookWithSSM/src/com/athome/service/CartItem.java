package com.athome.service;

import com.athome.pojo.Cart;

import java.util.List;

public interface CartItem {

    public void insertCartItem(Cart cart);

    public void updateCartItem(Cart cart);

    public List<Cart> getAllCartByName(String name);

    public List<Cart> getAllCart();

    public void deleteCartItem(String orderNumber);

    public void deleteAllCartItem();
}
