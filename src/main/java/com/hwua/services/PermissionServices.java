package com.hwua.services;

import com.hwua.pojo.Permission;

import java.util.List;

public interface PermissionServices {
    public List<Permission> queryAllPermission() throws  Exception;
    //    根据资源权限管理id删除对应资源权限
    public Integer deletePermissionById(String id)throws  Exception;
    //添加权限
    public Integer addPermission(Permission permission) throws  Exception;

}
