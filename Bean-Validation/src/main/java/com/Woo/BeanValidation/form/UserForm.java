package com.Woo.BeanValidation.form;

import lombok.Builder;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@ToString
public class UserForm {

    @NotBlank
    private String id;

    @Size(min = 8, max = 20)
    private String password;


}
