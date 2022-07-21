package com.Woo.ApiException.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/apiException/{id}")
    public HelloData save(@PathVariable String id) {
        log.info("id = {}",id);

        if(id.equals("ex")){
            throw new RuntimeException("런타임");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력");
        }

        return new HelloData("hello"+id,id);
    }

    @Data
    @AllArgsConstructor
    static class HelloData{
        private String username;
        private String id;
    }

}