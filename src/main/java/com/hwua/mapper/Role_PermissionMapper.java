package com.hwua.mapper;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface Role_PermissionMapper {
    //添加
    public Integer addRole_Permission(String roleid,String [] permissionid)throws  Exception;
}
