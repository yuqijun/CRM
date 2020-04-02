package com.hwua.services.impl;

import com.hwua.mapper.Role_PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Role_PermissionService implements com.hwua.services.Role_PermissionService {
    @Autowired
    private Role_PermissionMapper mapper;
    @Override
    public Integer addRole_Permission(String roleid, String[] permissionid) throws Exception {
        return mapper.addRole_Permission(roleid,permissionid);
    }
}
