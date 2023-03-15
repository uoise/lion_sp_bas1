package com.ll.sbb.rspData.model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RspData {
    private final String resultCode;
    private final String msg;
    private final Object data;


    public boolean isSuccess() {
        return resultCode.startsWith("S");
    }

    public static RspData of(String resultCode, String msg, Object data) {
        return RspData.builder().resultCode(resultCode).msg(msg).data(data).build();
    }

    public static RspData of(String resultCode, String msg) {
        return of(resultCode, msg, null);
    }
}
