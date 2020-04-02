package com.hwua.services.impl;

import com.hwua.mapper.MemberMapper;
import com.hwua.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServices implements com.hwua.services.MemberServices {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Member queryMemberById(String id) throws Exception {
        return memberMapper.queryMemberById(id);
    }
}
