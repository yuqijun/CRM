package com.hwua.mapper;

import com.hwua.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Component
@MapperScan
public interface ProductMapper {
//    查询所有商品
    public List<Product> queryAllProduct() throws  Exception;

//    根据id查找对应的产品
    public Product queryProductById(String productId)throws  Exception;

//    添加产品
    public Integer addProduct(Product product)throws  Exception;
    //根据产品id值改变产品的上/下架状态
    public Integer updateProducStatustById( @PathVariable(name = "status")String status,@PathVariable(name = "idlist") String [] idlist);

    //根据id删除产品
    public Integer deleteProductById(@Param( value = "idlist") String []  idlist)throws  Exception;
}
