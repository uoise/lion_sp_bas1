package com.ll.sbb.rq.model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;


@Component
@RequestScope
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

    public void setSession(String name, long value) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
    }

    public <T> T getSession(String name, T defaultValue) {
        try {
            T value = (T) req.getSession().getAttribute(name);
            if (value == null) return defaultValue;
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public long getSessionAsLong(String name, long defaultValue) {
        try {
            if (req.getSession().getAttribute(name) == null) return defaultValue;
            return Long.parseLong(req.getSession().getAttribute(name).toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean removeSession(String name) {
        HttpSession session = req.getSession();
        if (session.getAttribute(name) == null) return false;
        session.removeAttribute(name);
        return true;
    }

    public boolean isLogined() {
        return getSession("userId", -1) > 0;
    }
}
