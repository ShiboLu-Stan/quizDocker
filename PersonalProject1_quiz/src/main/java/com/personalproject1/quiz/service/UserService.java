package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.UserDao;
import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) { this.userDao = userDao; }

    public void createNewUser(User user) {
        userDao.createNewUser(user);
    }

    public void createNewUser(String firstName, String lastName, String address, String email, String phoneNumber, String status, String role, String username, String password) {
        userDao.createNewUser(new User(firstName, lastName, address, email, phoneNumber, status, role, username, password));
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(new User(-1, "invalid username", "invalid password"));
    }

    public Optional<User> validateLogin(String username, String password) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username)
                        && a.getPassword().equals(password))
                .findAny();
    }

    public Optional<User> validateUserName(String username) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username))
                .findAny();
    }
    public Optional<User> validateEmail(String email) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getEmail().equals(email))
                .findAny();
    }

    public List<User> getListByQuiz(List<Quiz> quizzes){
        List<User> userList = new ArrayList<>();

        quizzes.stream().forEach(quiz -> userList.add(userDao.getUserByID(quiz.getUserID())));

        return userList;
    }

    public void changeUserStatus(int userID){

        userDao.changeUserStatus(userID);
    }

}
