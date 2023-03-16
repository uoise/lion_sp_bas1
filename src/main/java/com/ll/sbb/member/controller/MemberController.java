package com.ll.sbb.member.controller;

import com.ll.sbb.member.model.Member;
import com.ll.sbb.member.service.MemberService;
import com.ll.sbb.rq.model.Rq;
import com.ll.sbb.rspData.model.RspData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;
    // 싱글톤
    private final Rq rq;
    // request 요청시 사라지는 객체

    public MemberController(MemberService memberService, Rq rq) {
        this.memberService = memberService;
        this.rq = rq;

    }

    @GetMapping("member/register")
    @ResponseBody
    public RspData addPerson(@RequestParam String name, String age) {
        return memberService.register(name, age);
    }

    @GetMapping("member/login")
    public String loginForm() {
//        if (rq.isLogined()) return "usr/member/logined";
        return "usr/member/login";
    }

    @PostMapping("member/login")
    @ResponseBody
    public RspData login(String username, String password) {
        RspData ret = memberService.loginValid(username, password);
        if (ret.isSuccess()) rq.setSession("userId", (long) ret.getData());
        return RspData.of(ret.getResultCode(), ret.getMsg());
    }

    @GetMapping("member/me")
    public String me(Model model) {
        long userid = rq.getSession("userId", -1L);
        Member me = (Member) memberService.me(userid).getData();
        model.addAttribute("me", me);
        return "usr/member/showme";
    }

    @GetMapping("member/logout")
    @ResponseBody
    public RspData logout() {
        rq.removeSession("userId");
        return RspData.builder().resultCode("S-1").msg("로그아웃되었습니다.").build();
    }
}