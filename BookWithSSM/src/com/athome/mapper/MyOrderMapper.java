package com.athome.mapper;

import com.athome.pojo.MyOrder;

import java.util.List;

public interface MyOrderMapper {

    public List<MyOrder> getAllMyOrder();


    public List<MyOrder> getAllMyOrderByName(String name);

    public void InsertMyorder(MyOrder myOrder);

}
