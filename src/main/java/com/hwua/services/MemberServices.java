package com.hwua.services;

import com.hwua.pojo.Member;

public interface MemberServices {
    //根据id查找对应的Member
    public Member queryMemberById(String id)throws  Exception;
}
