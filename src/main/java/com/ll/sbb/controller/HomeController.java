package com.ll.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    public static int cnt = 0;

    @GetMapping("/main2")
    @ResponseBody
    public String m2() {
        return "Hello";
    }

    @GetMapping("/main3")
    @ResponseBody
    public String m3() {
        return "hi";
    }

    @GetMapping("/inc")
    @ResponseBody
    public int m4() {
        return cnt++;
    }

    @GetMapping("/adder")
    @ResponseBody
    public int adder(@RequestParam(defaultValue = "0") int x, int y) {
        return x + y;
    }

    @GetMapping("/add")
    @ResponseBody
    public int ret(@RequestParam(defaultValue = "0") int x) {
        return x;
    }
}
