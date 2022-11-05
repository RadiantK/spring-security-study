package com.spring.security.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @ResponseBody
    @GetMapping("/greeting")
    public String greeting() {
        return "hello";
    }

    @ResponseBody
    @PostMapping("/greeting")
    public String postGreeting(@RequestBody String name) {
        return "hello " + name;
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/auth")
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "loginForm";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "AccessDenied";
    }

}
