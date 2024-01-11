package com.example.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    private String id;
    private String password1;
    private String password2;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String birth;
    private LocalDate createDay;
    private LocalDate startDay;
    private String isActive;

}
