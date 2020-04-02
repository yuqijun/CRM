package com.hwua.services.impl;

import com.hwua.mapper.OrderMapper;
import com.hwua.pojo.Order;
import com.hwua.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices implements com.hwua.services.OrderServices {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> queryAllOrder() throws Exception {
        return orderMapper.queryAllOrder();
    }

    @Override
    public List<Order> queryOrderByProductId(String id) throws Exception {
        return orderMapper.queryOrderByProductId(id);
    }

    @Override
    public Order queryOrderDetailMessageByOrdertId(String id) throws Exception {
        return orderMapper.queryOrderDetailMessageByOrdertId(id);
    }

    @Override
    public Integer changeOrderStatus(String[] ids, String status) throws Exception {
        return orderMapper.changeOrderStatus(ids,status);
    }
}
