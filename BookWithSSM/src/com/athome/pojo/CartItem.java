package com.athome.pojo;

import java.util.ArrayList;
import java.util.List;

public class CartItem {

    List<Cart> carts=new ArrayList<>();

    public CartItem() {
    }

    public CartItem(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "carts=" + carts +
                '}';
    }
}
