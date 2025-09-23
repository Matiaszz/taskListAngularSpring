package com.tasklist.Domain.User.repository;

import com.tasklist.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User getUserByUsername(String username);
}
