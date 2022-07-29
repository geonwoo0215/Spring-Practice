package com.woo.mockmvc.controller;

import com.woo.mockmvc.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MvcController {

    @ResponseBody
    @PostMapping("/param")
    public String modelAttribute(@ModelAttribute HelloData helloData) {
        return "hello! "+ helloData.getName();
    }

    @ResponseBody
    @PostMapping("content")
    public String requestBody(@RequestBody HelloData helloData){
        return "hello! "+ helloData.getName();
    }


}
