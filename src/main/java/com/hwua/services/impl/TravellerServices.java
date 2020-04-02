package com.hwua.services.impl;

import com.hwua.mapper.TravellerMapper;
import com.hwua.pojo.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TravellerServices implements com.hwua.services.TravellerServices {
    @Autowired
    private TravellerMapper travellerMapper;
    @Override
    public List<Traveller> queryTravellersById(String travellerid) throws Exception {
        return travellerMapper.queryTravellersById(travellerid);
    }
}
