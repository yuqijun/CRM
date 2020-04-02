package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private String orderNum;
    private String orderTime;
    private String peopleCount;
    private String orderDesc;
    private String payType;
    private String orderStatus;
    private String productId;
    private String memberId;
    private Product product;
    private Member member;
    private List<Traveller> travellers;

}
