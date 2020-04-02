package com.hwua.services;

import com.hwua.pojo.Role;

import java.util.List;

public interface RoleServices {
    //查询所有角色
    List<Role> queryAllRole()throws  Exception;
    // 根据用户id查找用户所具有的所有角色
    List<Role> queryRoleByUserId(String userid)throws  Exception;
    //删除角色
    Integer deleteRoleById(String id)throws  Exception;
    //添加角色
    Integer addRole(Role role)throws  Exception;


}
