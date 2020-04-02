package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    public String id ;
    public String productNum;
    public String productName;
    public String cityName;
    public String departureTime;
    public String productPrice;
    public String productDesc;
    public Integer productStatus;
    public Product(String productNum, String productName,String cityName,String departureTime,String productPrice, String productDesc,Integer productStatus){
        this.productNum = productNum;
        this.productName = productName;
        this.cityName=cityName;
        this.departureTime=departureTime;
        this.productPrice = productPrice;
        this.productDesc=productDesc;
        this.productStatus=productStatus;
    }

}
