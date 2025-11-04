package com.parkingon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test-text")
    @ResponseBody
    public String testText() {
        return "Spring MVC is working!";
    }

    @GetMapping("/test-view")
    public String testView() {
        return "index";
    }
}
