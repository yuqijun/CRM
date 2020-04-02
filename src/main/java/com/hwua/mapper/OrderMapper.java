package com.hwua.mapper;

import com.hwua.pojo.Order;
import net.sf.jsqlparser.statement.execute.Execute;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@MapperScan
public interface OrderMapper {
//    查询所有订单
    public List<Order> queryAllOrder()throws  Exception;
    //根据产品id 查找该产品的所有订单
    public List<Order>queryOrderByProductId(String id)throws  Exception;
    //根据订单id  显示该订单的详细信息
//    public List<Order>queryOrderDetailMessageByOrdertId(String id)throws  Exception;
    public Order queryOrderDetailMessageByOrdertId(String id)throws  Exception;

    //改变订单 状态
    public Integer changeOrderStatus(String [] ids,String status)throws Exception;
}
