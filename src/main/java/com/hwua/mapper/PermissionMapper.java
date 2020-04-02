package com.hwua.mapper;

import com.hwua.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
@MapperScan
public interface PermissionMapper {
//    查询所有权限
    public List<Permission> queryAllPermission() throws  Exception;
//    根据资源权限管理id删除对应资源权限
    public Integer deletePermissionById(String id)throws  Exception;
    //添加权限
    public Integer addPermission(Permission permission) throws  Exception;
}
