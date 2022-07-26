package com.woo.filterinterceptor.controller;

import com.woo.filterinterceptor.loginForm.LoginForm;
import com.woo.filterinterceptor.memberForm.Member;
import com.woo.filterinterceptor.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/")
    @ResponseBody
    public String home(){
        return "ok";
    }

    @GetMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(){
        return "ok";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL) {
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        HttpSession session = request.getSession();

        session.setAttribute("memberId",loginMember);

        return "redirect:" + redirectURL;
    }

    @ResponseBody
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "ok";
    }
}
