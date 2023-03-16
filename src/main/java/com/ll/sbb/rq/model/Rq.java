package com.ll.sbb.rq.model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;


@AllArgsConstructor
public class Rq {
    private HttpServletRequest req;
    private HttpServletResponse rsp;

    public String getCookie(String name, String defaultValue) {
        if (req.getCookies() == null) return defaultValue;
        return Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(defaultValue);
    }

    public long getCookieAsLong(String name, long defaultValue) {
        if (req.getCookies() == null) return defaultValue;
        try {
            return Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(name))
                    .map(Cookie::getValue)
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(defaultValue);
        } catch (Exception e) {
            throw new RuntimeException(name + " Long::parseLong exception");
        }
    }

    public <T> void setCookie(String name, T data) {
        rsp.addCookie(new Cookie(name, data.toString()));
    }

    public boolean removeCookie(String name) {
        Cookie cookie = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (cookie == null) return false;
        cookie.setMaxAge(0);
        rsp.addCookie(cookie);
        return true;
    }
}
