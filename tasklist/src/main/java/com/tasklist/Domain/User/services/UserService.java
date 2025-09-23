package com.tasklist.Domain.User.services;

import com.tasklist.Domain.User.DTOs.UserRegisterDTO;
import com.tasklist.Domain.User.User;
import com.tasklist.Domain.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO data){
        String encodedPassword = passwordEncoder.encode(data.password());
        UserRegisterDTO preparedUser = new UserRegisterDTO(data.username(), encodedPassword );
        User userEntity = new User(preparedUser);
        return userRepository.save(userEntity);

    }

}
