package com.hwua.services;

import com.hwua.pojo.Product;
import net.sf.jsqlparser.statement.execute.Execute;

import java.util.List;

public interface LuceneService {
    public void createIndex()throws  Exception;
    public List<Product> findProductByIndex(String fileName,String searchContent,String searchCount)throws Exception;
}
