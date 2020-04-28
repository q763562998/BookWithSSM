package com.athome.pojo;

import java.util.List;

public class MyOrder {
    String dateTime;
    String orderNumber;
    String userName;
    List<Cart> cartItemList;

    public MyOrder(String dateTime, String orderNumber, String userName, List<Cart> cartItemList) {
        this.dateTime = dateTime;
        this.orderNumber = orderNumber;
        this.userName = userName;
        this.cartItemList = cartItemList;
    }

    public MyOrder() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Cart> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<Cart> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "dateTime='" + dateTime + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", cartItemList=" + cartItemList +
                '}';
    }
}
