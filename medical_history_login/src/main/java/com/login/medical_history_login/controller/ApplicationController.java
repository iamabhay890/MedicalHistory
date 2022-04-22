package com.login.medical_history_login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/login")
public class ApplicationController {

    @GetMapping("/app")
    public String process() {
        return "Welcome";
    }

    @GetMapping("/apps")
    public String processes() {
        return "Hello";
    }

    @GetMapping("/appss")
    public String processeses() {
        return "Good Evening";
    }
}