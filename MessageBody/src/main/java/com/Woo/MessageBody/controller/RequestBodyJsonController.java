package com.Woo.MessageBody.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyJsonController {


    @ResponseBody
    @PostMapping("/httpEntityString")
    public HttpEntity<String> requestBodyString (HttpEntity<String> messageBody) {
        log.info("messageBody={}", messageBody);
        String body = messageBody.getBody();
        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/httpEntityJson")
    public HttpEntity<HelloData> requestBodyJson(HttpEntity<HelloData> httpEntity){
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", httpEntity.getBody().getUsername(), httpEntity.getBody().getAge());
        return new HttpEntity<>(helloData);
    }


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

