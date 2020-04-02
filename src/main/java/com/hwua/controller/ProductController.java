package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.Product;
import com.hwua.services.impl.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    //    查看所有角色
    @Autowired
    private ProductServices productServices;

    @GetMapping("/product_list")
    @SystemControllerLog(description = "showAllProduct")
    public ModelAndView showAllProduct() throws Exception {
        ModelAndView md = new ModelAndView();
        List<Product> productList = productServices.queryAllProduct();
        md.addObject("productList", productList);
        md.setViewName("pages/product-list");
        return md;
    }
    @ResponseBody
    @SystemControllerLog(description = "add_product")
    @PostMapping("/add_product")
    public Map<String,Object>  adaProduct(Product product) throws Exception {
        System.out.println("进入了adaProduct控制器");
        Map<String,Object> map = new HashMap<>();
        Integer resualt=null;
        System.out.println("传进来的product"+product);
        if(product!=null) {
             resualt = productServices.addProduct(product);
        }
        System.out.println("输出的resualt"+resualt);

        if(resualt!=null&&resualt!=0){
            map.put("info","success");
        }else{
            map.put("info","fail");
        }
        System.out.println("输出map331312"+map);
        return map;

    }
    @ResponseBody
    @PutMapping("/changeProductStatus")
    @SystemControllerLog(description = "changeProductStatus")
    public String changeProductStatus(HttpServletRequest request){
        String ids = request.getParameter("idlist");
        String status = request.getParameter("status");
        String row = null;
        String [] idlist = ids.split(",");
        row =  String.valueOf(productServices.updateProducStatustById(status,idlist));
        return row;
    }
    @ResponseBody
    @DeleteMapping("/deleteProductById")
    @SystemControllerLog(description = "deleteProductById")
    public Map<String,Object> deleteProductById(String [] idlist)throws  Exception{
        System.out.println("遍历idlist-------");
        for(String id : idlist){
            System.out.println(id);
        }
        Integer resualt = null;
        Map<String,Object> map = new HashMap<>();
        resualt = productServices.deleteProductById(idlist);
        System.out.println("删除的商品数量:"+resualt);
        if(resualt!=0&&resualt!=null){
            map.put("info","success");
        }else{
            map.put("info","fail");
        }
        System.out.println("返回的map1545dasdas"+map);
        return map;
    }

}