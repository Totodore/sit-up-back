package com.situp.backend.backend.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AuthLoginDto {

    @Email
    private String email;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String password;
}
