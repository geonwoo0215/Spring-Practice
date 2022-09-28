package com.forwardingredirect.forwardingredirect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("/hello")
    @ResponseBody
    public String home() {
        return "hello";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/hello";
    }

    @GetMapping("/forwarding")
    public String forwarding() {
        return "forward:/hello";
    }
}
