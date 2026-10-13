package com.example.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final String greeting;

    public HelloController(@Value("${greeting.message}") String greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/hello")
    public String hello() { return greeting; }
}
