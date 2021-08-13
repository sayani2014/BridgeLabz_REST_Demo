package com.example.demo.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class UserDTO {

    @NotEmpty(message = "Please enter name")
    @Pattern(regexp = "[a-zA-Z]{3,}")
    private String name;

    @NotNull(message = "Please enter age")
    private int age;

    @Email
    private String email;

    @Size(max = 10, min = 10, message = "Please enter phone number")
    private String phone;
}
