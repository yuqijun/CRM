package com.hwua.mapper;

import com.hwua.pojo.Traveller;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@MapperScan
public interface TravellerMapper {
    //根据  Traveller 的id查询出对应的  Traveller
    public List<Traveller> queryTravellersById(String travellerid)throws Exception;
}
