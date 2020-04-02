package com.hwua.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface User_roleMapper {

    public Integer insertUser_RoleByUserId(@Param(value ="userid" ) String userid, @Param(value="roleid") Integer [] roleid)throws  Exception;
}
