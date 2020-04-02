package com.hwua.services.impl;

import com.hwua.mapper.User_roleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_RoleServices implements com.hwua.services.User_RoleServices {
    @Autowired
    private User_roleMapper user_roleMapper;
    @Override
    public Integer insertUser_RoleByUserId(String userid, Integer[] roleid) throws Exception {
        return user_roleMapper.insertUser_RoleByUserId(userid,roleid);
    }
}
