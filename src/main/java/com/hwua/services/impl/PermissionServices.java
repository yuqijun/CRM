package com.hwua.services.impl;

import com.hwua.mapper.PermissionMapper;
import com.hwua.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServices implements com.hwua.services.PermissionServices {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> queryAllPermission() throws Exception {

        return permissionMapper.queryAllPermission();
    }

    @Override
    public Integer deletePermissionById(String id) throws Exception {
        return permissionMapper.deletePermissionById(id);
    }

    @Override
    public Integer addPermission(Permission permission) throws Exception {
        return permissionMapper.addPermission(permission);
    }
}
