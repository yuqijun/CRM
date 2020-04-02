package com.hwua.services.impl;

import com.hwua.annotation.SystemServiceLog;
import com.hwua.mapper.ProductMapper;
import com.hwua.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServices implements com.hwua.services.ProductServices {
    @Autowired
    private ProductMapper productMapper;
    @Override
    @SystemServiceLog(description = "查询所有产品--服务层")
    public List<Product> queryAllProduct() throws Exception {
        return productMapper.queryAllProduct();
    }

    @Override
    public Product queryProductById(String id) throws Exception {
        return productMapper.queryProductById(id);
    }

    @Override
    public Integer addProduct(Product product) throws Exception {
        return productMapper.addProduct(product);
    }

    @Override
    public Integer updateProducStatustById(String status, String[] idlist) {
        return productMapper.updateProducStatustById(status,idlist);
    }

    @Override
    public Integer deleteProductById(String [] idlist) throws Exception {
        return productMapper.deleteProductById(idlist);
    }
}
