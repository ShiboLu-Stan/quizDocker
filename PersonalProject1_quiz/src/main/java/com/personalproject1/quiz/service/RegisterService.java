package com.personalproject1.quiz.service;

import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.domain.enums.Role;
import com.personalproject1.quiz.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final UserService userService;

    @Autowired
    public RegisterService(UserService userService) {this.userService = userService; }

    public Optional<User> validateRegister(String firstName, String lastName, String address, String email, String phoneNumber, String username, String password) {
        Optional<User> possibleUser = userService.validateUserName(username);
        if (possibleUser.isPresent()){
            return Optional.empty();
        }
        possibleUser = userService.validateEmail(email);
        if (possibleUser.isPresent()){
            return Optional.empty();
        }

        userService.createNewUser(firstName, lastName, address, email, phoneNumber, Status.Active.toString(), Role.user.toString(), username, password);
        return userService.validateLogin(username, password);
    }

}
