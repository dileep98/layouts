package com.terralogic.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class LoginUser {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
