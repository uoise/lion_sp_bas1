package com.ll.sbb.member.repository;


import com.ll.sbb.member.model.Member;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Getter
@Component
public class MemberList {
    private long idGen;
    private final List<Member> list;

    public MemberList() {
        list = new ArrayList<>();
        add(Member.builder().id(++idGen).username("user1").password("1234").build());
        add(Member.builder().id(++idGen).username("abc").password("12345").build());
        add(Member.builder().id(++idGen).username("test").password("12346").build());
        add(Member.builder().id(++idGen).username("love").password("12347").build());
        add(Member.builder().id(++idGen).username("like").password("12348").build());
        add(Member.builder().id(++idGen).username("giving").password("12349").build());
        add(Member.builder().id(++idGen).username("thanks").password("123410").build());
        add(Member.builder().id(++idGen).username("hello").password("123411").build());
        add(Member.builder().id(++idGen).username("good").password("123412").build());
        add(Member.builder().id(++idGen).username("peace").password("123413").build());
    }

    public boolean remove(long id) {
        return list.removeIf(m -> m.getId() == id);
    }

    public long add(String username, String password) {
        Member member = Member.builder().id(++idGen).username(username).password(password).build();
        return this.add(member);
    }

    public long add(Member member) {
        list.add(member);
        return member.getId();
    }

    public long update(Member modMember) {
        return list.stream()
                .filter(m -> m.getId() == m.getId())
                .map(m -> m = modMember)
                .findFirst()
                .map(Member::getId)
                .orElse(-1L);
    }

    public Member getByID(long id) {
        return list.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Member getByUsername(String username) {
        return list.stream()
                .filter(m -> m.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}