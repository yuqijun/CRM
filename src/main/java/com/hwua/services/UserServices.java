package com.hwua.services;

import com.hwua.pojo.User;

import java.util.List;

public interface UserServices {
    //查询出所有用户
    public List<User> queryAllUser() throws Exception;
    //根据 name,pwd查询用户
    public User queryUserByUser(User user)throws  Exception;
    //注册用户
    public Integer registerUser(User user)throws  Exception;
    //根据用户名查找用户
    public User queryUserByName(String name)throws  Exception;
    //根据用户id查找用户
    public User queryUserById(String id)throws  Exception;
}
