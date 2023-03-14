package com.ll.sbb.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Person {
    private long id;
    private String name;
    private int age;
}
