package com.hwua.mapper;

import com.hwua.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@MapperScan
public interface UserMapper {
    public List<com.hwua.pojo.User> queryAllUser() throws Exception;
    //验证用户登录
    public User queryUserByUser(User user)throws  Exception;
    //注册用户
    public Integer registerUser(User user)throws  Exception;

    //根据用户名查找用户
    public User queryUserByName(String name)throws  Exception;

    //根据用户id查找用户
    public User queryUserById(String id)throws  Exception;

}
