package com.ll.sbb.rspData.model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RspData {
    private final String resultCode;
    private final String msg;
}
