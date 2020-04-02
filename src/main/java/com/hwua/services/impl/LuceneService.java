package com.hwua.services.impl;

import com.hwua.configuration.LuceneConfing;
import com.hwua.pojo.Product;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Component
@Service
public class LuceneService implements com.hwua.services.LuceneService {
    @Autowired
    private ProductServices productServices;
    @Override
    public void createIndex() throws Exception {
        //数据源
        List<Product> products = productServices.queryAllProduct();

        //获取数据源
//        //分词
//        IndexWriter indexWriter = new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
        //文档集合
        List<Document> documentList = new ArrayList<>();
        //写入到索引库
        for(Product product:products){
            Document document = new Document();
            //创建字段
            String id = product.getId();
            String productNum = product.getProductNum();
            String productName = product.getProductName();
            String cityName = product.getCityName();
            String departureTime = product.getDepartureTime();
            String productPrice = product.getProductPrice();
            String productDesc = product.getProductDesc();
            Integer productStatus = product.getProductStatus();
            //将字段添加进文档
            document.add(new StringField("id",id, Field.Store.YES));
            document.add(new StringField("productNum",productNum, Field.Store.YES));
            document.add(new TextField("productName",productName, Field.Store.YES));
            document.add(new StringField("cityName",cityName, Field.Store.YES));
            document.add(new StringField("departureTime",departureTime, Field.Store.YES));
            document.add(new StringField("productPrice",productPrice, Field.Store.YES));
            document.add(new StringField("productDesc",productDesc, Field.Store.YES));
            document.add(new StringField("productStatus",productStatus+"", Field.Store.YES));
            //将文档添加进索引库
            documentList.add(document);
        }
        //创建分词器
        Analyzer analyzer = new IKAnalyzer();
        Directory directory = FSDirectory.open(new File(LuceneConfing.INDEXPATH).toPath());
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        for(Document doc:documentList){
            indexWriter.addDocument(doc);
        }
        indexWriter.close();
    }

    @Override
    public List<Product> findProductByIndex(String fileName,String searchContent,String searchCount) throws Exception {
        //用来承载返回数据的容器
        List<Product> products = new ArrayList<>();
        //创建索引读取
        Directory directory = FSDirectory.open(new File(LuceneConfing.INDEXPATH).toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询对象
        Query  query = new TermQuery(new Term("productName",searchContent));
        //执行查询
        TopDocs topDocs = indexSearcher.search(query,10);
    //        System.out.println("共有"+topDocs.totalHits+"条记录");
        //遍历找到的所有文档
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            //新建product对象
            Product product = new Product();
            //获取文档id
            int docid = scoreDoc.doc;
            //根据id找到对应的文档
            Document document =  indexSearcher.doc(docid);
            //得到文档里面的所有字段，将对应的值赋给product对象
            product.setId(document.get("id"));
            product.setProductNum(document.get("productNum"));
            product.setProductName(document.get("productName"));
            product.setCityName(document.get("cityName"));
            product.setDepartureTime(document.get("departureTime"));
            product.setProductPrice(document.get("productPrice"));
            product.setProductDesc(document.get("productDesc"));
            product.setProductStatus(Integer.parseInt(document.get("productStatus")));
            //将product 对象添加到 要返回的数据容器  products中
            products.add(product);
        }

        indexReader.close();
        return products;
    }
}
