package com.athome.service;

import com.athome.pojo.MyOrder;

import java.util.List;

public interface MyOrderService {

    public List<MyOrder> getAllMyOrder();

    public List<MyOrder> getAllMyOrderByName(String name);

    public void InsertMyorder(MyOrder myOrder);
}
