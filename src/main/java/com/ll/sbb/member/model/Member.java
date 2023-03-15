package com.ll.sbb.member.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {
    private final long id;
    private final String username;
    private final String password;
}