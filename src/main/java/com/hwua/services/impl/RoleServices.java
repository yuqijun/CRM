package com.hwua.services.impl;

import com.hwua.mapper.RoleMapper;
import com.hwua.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServices  implements com.hwua.services.RoleServices {
    @Autowired
    private RoleMapper  roleMapper ;
    @Override
    public List<Role> queryAllRole() throws Exception {
        return roleMapper.queryAllRole();
    }

    @Override
    public List<Role> queryRoleByUserId(String userid) throws Exception {
        return roleMapper.queryRoleByUserId(userid);
    }

    @Override
    public Integer deleteRoleById(String id) throws Exception {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public Integer addRole(Role role) throws Exception {
        return roleMapper.addRole(role);
    }
}
