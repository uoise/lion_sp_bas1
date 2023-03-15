package com.ll.sbb.member.controller;

import com.ll.sbb.member.service.MemberService;
import com.ll.sbb.rspData.model.RspData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;


@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("member/register")
    @ResponseBody
    public RspData addPerson(@RequestParam String name, String age) {
        return memberService.register(name, age);
    }

    @GetMapping("member/login")
    @ResponseBody
    public RspData login(HttpServletResponse rsp, @RequestParam String username, String password) {
        RspData ret = memberService.loginValid(username, password);
        if (ret.getResultCode().startsWith("S")) rsp.addCookie(new Cookie("username", username));
        return ret;
    }

    @GetMapping("member/me")
    @ResponseBody
    public RspData me(HttpServletRequest req) {
        if (req.getCookies() == null) return memberService.me(null);
        return memberService.me(Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("username"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null));
    }

    @GetMapping("member/logout")
    @ResponseBody
    public RspData logout(HttpServletRequest req, HttpServletResponse rsp) {
        if (req.getCookies() != null) Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("username"))
                .forEach(c -> {
                    c.setMaxAge(0);
                    rsp.addCookie(c);
                });
        return RspData.builder().resultCode("S-1").msg("로그아웃되었습니다.").build();
    }
}