package com.ll.sbb.member.service;

import com.ll.sbb.member.model.Member;
import com.ll.sbb.member.repository.MemberRepository;
import com.ll.sbb.rspData.model.RspData;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public RspData loginValid(String username, String password) {
        RspData ret;
        Member fnd = memberRepository.findByUsername(username).orElse(null);
        if (fnd == null) {
            ret = RspData.of("F-2", username + "(은)는 존재하지 않는 회원입니다.");
        } else {
            if (fnd.getPassword().equals(password)) {
                ret = RspData.of("S-1", username + " 님 환영합니다.", fnd.getId());
            } else {
                ret = RspData.of("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }
        return ret;
    }

    @Transactional
    public RspData register(String name, String password) {
        long id = memberRepository.save(Member.builder().username(name).password(password).build()).getId();
        return RspData.of("S-1", id + "번 계정 생성에 성공했습니다.", id);
    }

    @Transactional
    public RspData me(long userId) {
        Member fnd = memberRepository.findById(userId).orElse(null);
        if (fnd == null) return RspData.of("F-1", "로그인 후 이용해주세요.");
        return RspData.of("S-1", fnd.getUsername() + " 님 환영합니다", fnd);
    }
}

