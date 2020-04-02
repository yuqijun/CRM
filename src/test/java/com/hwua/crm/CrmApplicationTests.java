package com.hwua.crm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.configuration.LuceneConfing;
import com.hwua.mapper.LogMapper;
import com.hwua.mapper.RoleMapper;
import com.hwua.pojo.Order;
import com.hwua.pojo.Product;
import com.hwua.pojo.User;
import com.hwua.services.RoleServices;
import com.hwua.services.impl.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CrmApplicationTests {
    @Autowired
    private TravellerServices travellerServices;
    @Autowired
    private MemberServices memberServices;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private PermissionServices permissionServices;
    @Autowired
    private OrderServices orderServices;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private UserServices userServices;
    @Autowired
    private User_RoleServices user_roleServices;
    @Autowired
    private Role_PermissionService role_permissionService;
    @Test
    void contextLoads() throws Exception {
        System.out.println(userServices.queryAllUser());
    }
    @Test
    public void queryUserBuser() throws Exception {
        User user  = new User();
        user.setUsername("chenhao");
        user.setPassword("123456");
        userServices.queryUserByUser(user);
        System.out.println("查询结果------------------------"+userServices.queryUserByUser(user));
    }
    @Test
//    查询所有角色
    public void queryAllRole() throws Exception {
        System.out.println(roleServices.queryAllRole());
    }
    @Test
//    查询所有产品
    public void queryAllProduct() throws Exception {
        System.out.println("查询所有商品-----------------"+productServices.queryAllProduct());
    }
//    根据id查找产品
    @Test
    public void queryProductById() throws Exception {
        System.out.println(productServices.queryProductById("2"));
    }
    @Test
//    根据id号更新产品上/下架状态
    public void updateProductById(){
        String [] idlist = {"1","2"};
        productServices.updateProducStatustById("0",idlist);
    }
    //添加产品
    @Test
    public void addProduct() throws Exception {

         String productNum ="0008";
         String productName ="上海——重庆一日游";
         String cityName =  "上海";
         String departureTime ="2019-07-27 10:45:00" ;
         String productPrice = "5000";
         String productDesc = "携带好证件";
         Integer productStatus=0;
        Product product = new Product();
        product.setCityName(cityName);
//        product.setde
        product.setProductDesc(productDesc);
        product.setProductName(productName);
        product.setProductNum(productNum);
        product.setProductPrice(productPrice);
        product.setProductStatus(productStatus);

        System.out.println("新增产品种类-----"+productServices.addProduct(product));
    }
    @Test
    //查询所有订单
//    public void queryAllOrder() throws Exception {
//        System.out.println(orderServices.queryAllOrder());
//    }
    public void queryAllOrder() throws Exception {
//        PageHelper.startPage(1,3);
        List<Order>  orders = orderServices.queryAllOrder();
        PageInfo<Order>  pageInfo = new PageInfo<>(orders);
        List<Order> orderList = pageInfo.getList();
        System.out.println(orderList);
    }
    @Test
    public void queryiOrderdetailByOrderId() throws Exception {
        System.out.println(orderServices.queryOrderDetailMessageByOrdertId("1"));
    }
    @Test
    public void queryOrderByProductId() throws Exception {
        System.out.println("产品id为1的订单"+orderServices.queryOrderByProductId("1"));
    }
    @Test
//    查询所有权限
    public void queryAllPermission() throws Exception {
        System.out.println(permissionServices.queryAllPermission());
    }
    //根据id号删除 资源权限
    @Test
    public void deletePermissionById() throws Exception {
        System.out.println(permissionServices.deletePermissionById("5"));
    }
    @Test
    //查看所有日志
    public  void queryAllLog() throws Exception {
        System.out.println("所有日志记录------------"+logMapper.queryAllLog());
    }

    //根据id查找会员
    @Test
    public void queryMemberById() throws Exception {
        System.out.println("menber-----------"+memberServices.queryMemberById("2"));
    }
    //根据id 查找对应的旅行者
    @Test
    public void queryTravellersById() throws Exception {
        System.out.println(travellerServices.queryTravellersById("2"));
    }
//    给用户添加角色
    @Test
    public void userAddRole() throws Exception {
        Integer [] roleid={1,3};
        user_roleServices.insertUser_RoleByUserId("2",roleid);
    }
//    @Test
//    public void registerUser() throws Exception {
//        User user = new User(null,"57634543@qq.com","chengyi","123456","178232131",0);
//        System.out.println("穿进去的user-----"+user);
//        Integer row = userServices.registerUser(user);
//        System.out.println("注册用户"+row+"个");
//    }
    //根据用户名查找用户
    @Test
    public void queryUserByName() throws Exception {
        System.out.println(userServices.queryUserByName("小张"));
    }
    //根据用户id查找他所具有的所有角色
    @Test
    public void queryRoleByUserId()throws  Exception{
        System.out.println(roleServices.queryRoleByUserId("3"));
    }
    @Test
    //根据用户id查找用户
    public void queryUserById()throws  Exception {
        System.out.println(userServices.queryUserById("3"));
    }

    @Test
    public void createIndex() throws Exception {
        //数据源
        List<Product> products = productServices.queryAllProduct();
        //创建索引
        Directory directory = FSDirectory.open(new File(LuceneConfing.INDEXPATH).toPath());
        IndexWriter indexWriter = new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
        //创建文档
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
            document.add(new StringField("productName",productName, Field.Store.YES));
            document.add(new StringField("cityName",cityName, Field.Store.YES));
            document.add(new StringField("departureTime",departureTime, Field.Store.YES));
            document.add(new StringField("productPrice",productPrice, Field.Store.YES));
            document.add(new StringField("productDesc",productDesc, Field.Store.YES));
            document.add(new StringField("productStatus",productStatus+"", Field.Store.YES));

            System.out.println("已将product--------"+product+"放入文档");
            //将文档添加进索引库
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
        indexWriter.close();
    }

   @Test
    public void findProductByIndex() throws Exception {
        List<Product> products = new ArrayList<>();
       Directory directory = FSDirectory.open(new File(LuceneConfing.INDEXPATH).toPath());
       IndexReader indexReader = DirectoryReader.open(directory);
       IndexSearcher indexSearcher = new IndexSearcher(indexReader);
       //4.创建Query查询对象，,
       Query query = new MatchAllDocsQuery();
//       Query query = new TermQuery(new Term("productName","重庆-厦门三日游"));
       //5.执行查询方法
       TopDocs topDocs = indexSearcher.search(query, 10);

       for(ScoreDoc scoreDoc:topDocs.scoreDocs){
           int docid = scoreDoc.doc;//获取文档的id
           Document document = indexSearcher.doc(docid);//得到文档对象
           System.out.println("document------------"+document.toString());
           Product product = new Product();
           product.setId(document.get("id"));
           product.setProductNum(document.get("productNum"));
           product.setProductName(document.get("productName"));
           product.setCityName(document.get("cityName"));
           product.setDepartureTime(document.get("departureTime"));
           product.setProductPrice(document.get("productPrice"));
           product.setProductDesc(document.get("productDesc"));
           product.setProductStatus(Integer.valueOf(document.get("productStatus")));
           products.add(product);
       }
        System.out.println("products--"+products);
        indexReader.close();
//        return products;
    }


//    public void createIndex() throws Exception {
//        //数据源
//        List<Product> products = productServices.queryAllProduct();
//        //创建索引
//        Directory directory = FSDirectory.open(new File(LuceneConfing.INDEXPATH).toPath());
//        IndexWriter indexWriter = new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
//        //创建文档
//        Document document = new Document();
//        for(Product product:products){
//            //创建字段
//            String id = product.getId();
//            String productNum = product.getProductNum();
//            String productName = product.getProductName();
//            String cityName = product.getCityName();
//            String departureTime = product.getDepartureTime();
//            String productPrice = product.getProductPrice();
//            String productDesc = product.getProductDesc();
//            Integer productStatus = product.getProductStatus();
//            //将字段添加进文档
//            document.add(new StringField("id",id, Field.Store.YES));
//            document.add(new StringField("productNum",productNum, Field.Store.YES));
//            document.add(new StringField("productName",productName, Field.Store.YES));
//            document.add(new StringField("cityName",cityName, Field.Store.YES));
//            document.add(new StringField("departureTime",departureTime, Field.Store.YES));
//            document.add(new StringField("productPrice",productPrice, Field.Store.YES));
//            document.add(new StringField("productDesc",productDesc, Field.Store.YES));
//            document.add(new StringField("productStatus",productStatus+"", Field.Store.YES));
//            //将文档添加进索引库
//            indexWriter.addDocument(document);
//        }
//        indexWriter.commit();
//        indexWriter.close();
//    }

//    @Test
//    public void findProductByIndex() throws Exception {
//        List<Product> products = new ArrayList<>();
//        Directory directory = FSDirectory.open(new File(LuceneConfing.INDEXPATH).toPath());
//        IndexReader indexReader = DirectoryReader.open(directory);
//        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
////        System.out.println("服务层的searchContent"+searchContent);
//        Query  query = new TermQuery(new Term("productName","北京三日游"));         //text 北京三日游
//        TopDocs topDocs = indexSearcher.search(query,1);   //n   1
//        System.out.println("共有"+topDocs.totalHits+"条记录");
//        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
//            Product product = new Product();
//            int docid = scoreDoc.doc;
//            Document document =  indexSearcher.doc(docid);
//            product.setId(document.get("id"));
//            product.setProductNum(document.get("productNum"));
//            product.setProductName(document.get("productName"));
//            product.setCityName(document.get("cityName"));
//            product.setDepartureTime(document.get("departureTime"));
//            product.setProductPrice(document.get("productPrice"));
//            product.setProductDesc(document.get("productDesc"));
//            product.setProductStatus(Integer.parseInt(document.get("productStatus")));
//            products.add(product);
//        }
//
//        System.out.println("服务层的products"+products);
//        indexReader.close();
////        return products;
//    }
    @Test
    public void addRole_Permission()throws  Exception{
        String [] list = {"1","2"};
        role_permissionService.addRole_Permission("2",list);
    }
}
