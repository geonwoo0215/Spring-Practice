package com.Woo.BeanValidation.controller;

import com.Woo.BeanValidation.form.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
public class ValidationController {

    @PostMapping("/userJson")
    @ResponseBody
    public String saveUserModel(@Valid @RequestBody UserForm userForm) {
        log.info("userDto = {}",userForm.toString());
        return "ok";
    }

    @PostMapping("/userModel")
    @ResponseBody
    public String saveUserJson(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult){
        log.info("userDto = {}",userForm.toString());
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult.getFieldError().getDefaultMessage());
        }
        return "ok";
    }


}
