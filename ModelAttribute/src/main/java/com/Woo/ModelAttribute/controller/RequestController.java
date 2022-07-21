package com.Woo.ModelAttribute.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RequestController {

    @ResponseBody
    @PostMapping ("/requestParam")
    public String requestParam(@RequestParam String username, @RequestParam int age) {
        log.info("username = {}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/modelAttribute")
    public String modelAttribute(@ModelAttribute HelloData data) {
        log.info("username = {},age = {}", data.getUsername(), data.getAge());
        return "ok";
    }

    @Getter
    static class HelloData {
        private String username;
        private int age;
    }


}
