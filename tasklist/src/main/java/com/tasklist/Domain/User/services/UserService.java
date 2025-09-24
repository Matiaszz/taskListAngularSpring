package com.tasklist.Domain.User.services;

import com.tasklist.Domain.User.DTOs.UserRegisterDTO;
import com.tasklist.Domain.User.User;
import com.tasklist.Domain.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO data){
        String encodedPassword = passwordEncoder.encode(data.password());
        UserRegisterDTO preparedUser = new UserRegisterDTO(data.username(), encodedPassword );
        User userEntity = new User(preparedUser);
        return userRepository.save(userEntity);
    }

    public String login(UserRegisterDTO data) {
        User user = userRepository.findUserByUsername(data.username());
        if (user == null ) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid username");

        boolean passwordMatches = passwordEncoder.matches(data.password(), user.getPassword());
        if (!passwordMatches) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid password");

        return jwtService.generateToken(user);

    }

}
