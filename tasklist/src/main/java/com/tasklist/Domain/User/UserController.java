package com.tasklist.Domain.User;

import com.tasklist.Domain.User.DTOs.CompleteUserDTO;
import com.tasklist.Domain.User.DTOs.UserRegisterDTO;
import com.tasklist.Domain.User.services.CustomUserService;
import com.tasklist.Domain.User.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CompleteUserDTO> register(@RequestBody UserRegisterDTO data){
        if (data.username() == null || data.username().isEmpty() || data.password() == null || data.password().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body must contain `username` and `password`");
        }

        User user = userService.register(data);
        CompleteUserDTO dto = new CompleteUserDTO(user);
        return ResponseEntity.ok(dto);
    }

}
