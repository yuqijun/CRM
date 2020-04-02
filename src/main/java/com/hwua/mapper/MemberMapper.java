package com.hwua.mapper;

import com.hwua.pojo.Member;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface MemberMapper {
    //根据id查找对应的Member
    public Member queryMemberById(String id)throws  Exception;
}
