package com.hwua.services.impl;

import com.hwua.mapper.UserMapper;
import com.hwua.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class UserServices implements com.hwua.services.UserServices {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryAllUser() throws Exception {
        return userMapper.queryAllUser();
    }

    @Override
    public User queryUserByUser(User user) throws Exception {
        return userMapper.queryUserByUser(user);
    }

    @Override
    public Integer registerUser(User user) throws Exception {
        return userMapper.registerUser(user);
    }

    @Override
    public User queryUserByName(String name) throws Exception {
        return userMapper.queryUserByName(name);
    }

    @Override
    public User queryUserById(String id) throws Exception {
        return userMapper.queryUserById(id);
    }
}
