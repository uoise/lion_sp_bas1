package com.ll.sbb.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

}
