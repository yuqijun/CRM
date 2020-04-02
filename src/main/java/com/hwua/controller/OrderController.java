package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.Order;
import com.hwua.pojo.Product;
import com.hwua.services.impl.OrderServices;
import com.hwua.services.impl.ProductServices;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    @GetMapping("/orders_page_list")
    @SystemControllerLog(description = "showAllOrder")
    public ModelAndView showAllOrder(Integer pageNum,Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        ModelAndView md = new ModelAndView();
        List<Order> orders= orderServices.queryAllOrder();
        PageInfo<Order> pageInfo =  new PageInfo<>(orders);
        md.addObject("pageInfo",pageInfo);
        md.setViewName("/pages/orders-page-list");
        return md;
    }
    @SystemControllerLog(description = "showAllOrderById")
    @GetMapping("/orderlist/{id}")
    public ModelAndView showAllOrderById(HttpServletRequest request,@PathVariable(name = "id")String id) throws Exception {
//        String id = request.getParameter("id");
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderServices.queryOrderByProductId(id);
        mv.addObject("ordersList",orders);
        mv.setViewName("/pages/orders-list");
        return  mv;
//        return "login";
    }

    @SystemControllerLog(description = "showOrderDetailById")
    @GetMapping("/order_detail")
    public ModelAndView showOrderDetailById(HttpServletRequest request ) throws Exception {
        String orderID = request.getParameter("id");
        ModelAndView mv = new ModelAndView();
        Order orderInfo = orderServices.queryOrderDetailMessageByOrdertId(orderID);
        mv.addObject("orders",orderInfo);
        mv.setViewName("/pages/orders-show");
        return mv;
    }
    @ResponseBody
    @SystemControllerLog(description = "changeOrderStatus")
    @PutMapping("/changeOrderStatus")
    public Map<String,Object> changeOrderStatus(String [] ids,String status)throws  Exception{
        System.out.println("传过来的ids"+ids+"传过来的status"+status);
        Map<String,Object> map = new HashMap<>();
        Integer resualt = orderServices.changeOrderStatus(ids,status);
//        System.out.println("-----遍历输出ids----------");
        for(String id :ids){
            System.out.println(id);
        }
        System.out.println("更改的记录行数---"+resualt);

        if(resualt!=0&&resualt!=null){
            map.put("info","success");
        }else{
            map.put("info","fail");
        }
        System.out.println("返回的map3231233"+map);
        return map;
    }
}
