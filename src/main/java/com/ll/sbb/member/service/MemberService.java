package com.ll.sbb.member.service;

import com.ll.sbb.member.model.Member;
import com.ll.sbb.member.repository.MemberList;
import com.ll.sbb.rspData.model.RspData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MemberService {

    private final MemberList memberList;

    public RspData loginValid(String username, String password) {
        RspData ret;

        Member fnd = memberList.getByUsername(username);
        if (fnd == null) {
            ret = RspData.builder()
                    .resultCode("F-2")
                    .msg(username + "(은)는 존재하지 않는 회원입니다.").build();
        } else {
            if (fnd.getPassword().equals(password)) {
                ret = RspData.builder()
                        .resultCode("S-1")
                        .msg(username + " 님 환영합니다.").build();
            } else {
                ret = RspData.builder()
                        .resultCode("F-1")
                        .msg("비밀번호가 일치하지 않습니다.").build();
            }
        }

        return ret;
    }

    public RspData register(String name, String password) {
        return RspData.builder().resultCode("S-1").msg(memberList.add(name, password) + "가 생성되었습니다.").build();
    }
}

