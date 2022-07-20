package com.Woo.MessageBody.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyJsonController {

    @ResponseBody
    @PostMapping("/requestBodyString")
    public String requestBodyString (@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/requestBodyJson")
    public HelloData requestBodyJson(@RequestBody HelloData data){
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }

    @Getter
    static class HelloData {
        private String username;
        private int age;
    }

}

