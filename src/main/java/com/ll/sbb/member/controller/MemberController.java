package com.ll.sbb.member.controller;

import com.ll.sbb.member.service.MemberService;
import com.ll.sbb.rspData.model.RspData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public RspData login(@RequestParam String username, String password) {
        return memberService.loginValid(username, password);
    }
}