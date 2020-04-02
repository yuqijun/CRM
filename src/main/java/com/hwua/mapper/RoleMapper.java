package com.hwua.mapper;

import com.hwua.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@MapperScan
public interface RoleMapper {
//    查询所有角色
    List<Role> queryAllRole()throws  Exception;
// 根据用户id查找用户所具有的所有角色
    List<Role> queryRoleByUserId(String userid)throws  Exception;

    //删除角色
    Integer deleteRoleById(String id)throws  Exception;

    //新建角色
    Integer  addRole(Role role)throws  Exception;
}
