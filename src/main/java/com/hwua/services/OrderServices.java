package com.hwua.services;

import com.hwua.pojo.Order;
import com.hwua.pojo.Product;

import java.util.List;

public interface OrderServices {
    public List<Order> queryAllOrder()throws  Exception;
    //根据产品id查找对应订单
    public List<Order> queryOrderByProductId(String id) throws Exception;
    //根据订单id  显示该订单的详细信息
    public  Order queryOrderDetailMessageByOrdertId(String id)throws  Exception;
    //改变订单 状态
    public Integer changeOrderStatus(String [] ids,String status)throws Exception;
}
