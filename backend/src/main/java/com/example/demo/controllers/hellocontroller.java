package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class hellocontroller {
    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }
}