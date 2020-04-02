package com.hwua.services;

import com.hwua.pojo.Traveller;

import java.util.List;

public interface TravellerServices {
    //根据  Traveller 的id查询出对应的  Traveller
    public List<Traveller> queryTravellersById(String travellerid)throws Exception;
}
