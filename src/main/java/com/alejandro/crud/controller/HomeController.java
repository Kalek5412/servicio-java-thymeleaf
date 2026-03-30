package com.alejandro.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping(value = {"/","/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login-basico";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }
}
