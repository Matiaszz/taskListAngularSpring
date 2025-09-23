package com.tasklist.Domain.User.DTOs;

import org.springframework.lang.NonNull;

public record UserRegisterDTO(
        String username,
        String password
) {

    public UserRegisterDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
