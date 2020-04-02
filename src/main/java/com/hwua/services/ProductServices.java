package com.hwua.services;

import com.hwua.pojo.Product;

import java.util.List;

public interface ProductServices {
    public List<Product> queryAllProduct() throws  Exception;
    //    根据id查找对应的产品
    public Product queryProductById(String id)throws  Exception;
    //    添加产品
    public Integer addProduct(Product product)throws  Exception;
//    根据产品id更新产品状态
    public Integer updateProducStatustById(String status,String [] idlist);

    //根据id删除产品
    public Integer deleteProductById(String[] idlist)throws  Exception;
}
