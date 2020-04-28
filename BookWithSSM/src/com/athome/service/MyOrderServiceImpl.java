package com.athome.service;

import com.athome.mapper.MyOrderMapper;
import com.athome.pojo.MyOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOrderServiceImpl implements MyOrderService {

    MyOrderMapper myOrderMapper;
    @Override
    public List<MyOrder> getAllMyOrder() {
        List<MyOrder> list = myOrderMapper.getAllMyOrder();
        return list;
    }

    @Override
    public List<MyOrder> getAllMyOrderByName(String name) {
        List<MyOrder> myOrders = myOrderMapper.getAllMyOrderByName(name);
        return myOrders;
    }


    @Override
    public void InsertMyorder(MyOrder myOrder) {
        myOrderMapper.InsertMyorder(myOrder);
    }
}
