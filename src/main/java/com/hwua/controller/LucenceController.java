package com.hwua.controller;

import com.hwua.pojo.Product;
import com.hwua.services.impl.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LucenceController {
    @Autowired
    private LuceneService luceneService;
    @ResponseBody
    @PostMapping("/searchProduct")
    public List<Product> searchProduct(String searchContent)throws  Exception{
        List<Product>  products =luceneService.findProductByIndex("productName",searchContent,"10");
        return products;
    }
}
