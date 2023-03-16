package com.ll.sbb.member.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Member {
    private final long id;
    private final String username;
    private final String password;
}