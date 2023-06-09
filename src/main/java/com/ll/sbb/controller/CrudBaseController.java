package com.ll.sbb.controller;

import com.ll.sbb.model.Person;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
public class CrudBaseController {
    static int id = 3;
    List<Person> list = new ArrayList<>() {{
        add(Person.builder().id(1)
                .name("홍").age(1)
                .build());
        add(Person.builder().id(2)
                .name("홍").age(2)
                .build());
        add(Person.builder().id(3)
                .name("홍").age(3)
                .build());
    }};


    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, int age) {
        list.add(Person.builder().id(++id).name(name).age(age).build());
        return id + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String deletePerson(@RequestParam int id) {
        String res;
        if (list.removeIf(p -> p.getId() == id)) res = id + "번 사람이 삭제되었습니다.";
        else res = id + "번 사람이 존재하지 않습니다.";
        return res;
    }

    @GetMapping("/home/updatePerson")
    @ResponseBody
    public String updatePerson(@RequestParam int id, String name, int age) {
        String res = "";
        Person fnd = list.stream().filter(p -> p.getId() == id).findFirst().map(p -> {
                    p.setName(name);
                    p.setAge(age);
                    return p;
                }).
                orElse(null);
        if (fnd == null) {
            res = id + "번 사람이 존재하지 않습니다.";
        } else {
            res = id + "번 사람이 수정되었습니다.";
        }

        return res;
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> getPeople() {
        return list;
    }


    @GetMapping("/cookies")
    @ResponseBody
    public int gsCookie(HttpServletRequest req, HttpServletResponse rsp) {
        int cookieCount;
        if (req.getCookies() != null) {
            cookieCount = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst().orElse(0);
        } else {
            cookieCount = 0;
        }
        ++cookieCount;
        rsp.addCookie(new Cookie("count", String.valueOf(cookieCount)));
        System.out.println(cookieCount);
        return cookieCount;
    }
}
