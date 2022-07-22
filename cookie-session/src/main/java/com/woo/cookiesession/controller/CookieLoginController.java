package com.woo.cookiesession.controller;

import com.woo.cookiesession.loginForm.LoginForm;
import com.woo.cookiesession.memberForm.Member;
import com.woo.cookiesession.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CookieLoginController {

    private final LoginService loginService;

    @ResponseBody
    @PostMapping("/cookieLogin")
    public String login(@ModelAttribute LoginForm form, HttpServletResponse response) {
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login {}", loginMember);

        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));

        response.addCookie(idCookie);

        return "ok";
    }

    @ResponseBody
    @GetMapping("/cookieCheck")
    public String cookieCheck(@CookieValue(name = "memberId",required = false) Long memberId){
        if(memberId==null){
            return "not login";
        }
        return "ok";
    }

    @ResponseBody
    @PostMapping("/cookieLogout")
    public String cookieLogout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "ok";
    }


}
