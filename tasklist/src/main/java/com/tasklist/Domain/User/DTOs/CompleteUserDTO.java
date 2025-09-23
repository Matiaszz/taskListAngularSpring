package com.tasklist.Domain.User.DTOs;

import com.tasklist.Domain.User.User;

import java.util.UUID;

public record CompleteUserDTO(
        UUID id,
        String username
) {
    public CompleteUserDTO(User user) {
        this(user.getId(), user.getUsername());
    }
}
