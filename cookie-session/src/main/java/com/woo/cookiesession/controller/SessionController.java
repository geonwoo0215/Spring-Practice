package com.woo.cookiesession.controller;

import com.woo.cookiesession.loginForm.LoginForm;
import com.woo.cookiesession.memberForm.Member;
import com.woo.cookiesession.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SessionController {

    private final LoginService loginService;

    @ResponseBody
    @PostMapping("/sessionLogin")
    public String login(@ModelAttribute LoginForm form, HttpServletRequest request){

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login={}", loginMember);

        HttpSession session = request.getSession();
        session.setAttribute("memberId",loginMember);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/sessionCheck")
    public String sessionCheck(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session == null) {
            return "not login";
        }

        Member loginMember = (Member) session.getAttribute("memberId");

        if(loginMember == null){
            return "not member";
        }

        return "ok";

    }

    @ResponseBody
    @GetMapping("/sessionCheckV2")
    public String sessionCheckV2(@SessionAttribute(name = "memberId", required = false) Member loginMember){

        if(loginMember ==null){
            return "not member";
        }

        return "ok";
    }


    @ResponseBody
    @PostMapping("/sessionLogout")
    public String sessionLogout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "ok";
    }

}
