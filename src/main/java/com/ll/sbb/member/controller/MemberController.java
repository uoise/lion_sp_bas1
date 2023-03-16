package com.ll.sbb.member.controller;

import com.ll.sbb.member.service.MemberService;
import com.ll.sbb.rq.model.Rq;
import com.ll.sbb.rspData.model.RspData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("member/loginForm")
    @ResponseBody
    public String loginForm() {
        if (rq.isLogined()) return """
                <h1>이미 로그인 되었습니다.</h1>
                """.stripIndent();
        return """
                <h1>login</h1>
                <form method="get" action="/member/login">
                  <input type="text"      name="username" placeholder="username">
                    <input type="text"      name="password" placeholder="password">
                  <button type="submit">로그인</button>
                  </form>
                """.stripIndent();
    }

    @GetMapping("member/login")
    @ResponseBody
    public RspData login(@RequestParam String username, String password) {
        RspData ret = memberService.loginValid(username, password);
        if (ret.isSuccess()) rq.setSession("userId", (long) ret.getData());
        return RspData.of(ret.getResultCode(), ret.getMsg());
    }

    @GetMapping("member/me")
    @ResponseBody
    public RspData me() {
        long userid = rq.getSession("userId", -1L);
        return memberService.me(userid);
    }

    @GetMapping("member/logout")
    @ResponseBody
    public RspData logout() {
        rq.removeSession("userId");
        return RspData.builder().resultCode("S-1").msg("로그아웃되었습니다.").build();
    }
}