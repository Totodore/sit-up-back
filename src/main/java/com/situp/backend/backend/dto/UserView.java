package com.situp.backend.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserView {
    private long id;
    private String name;
    private String firstname;
    private String email;
    private Date birthdate;
    private boolean admin;
}
