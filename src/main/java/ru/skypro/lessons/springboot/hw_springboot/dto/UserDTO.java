package ru.skypro.lessons.springboot.hw_springboot.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String login;
    private String password;
    private String role;
}
