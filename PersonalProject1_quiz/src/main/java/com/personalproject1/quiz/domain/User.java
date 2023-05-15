package com.personalproject1.quiz.domain;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private Date createDate;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Email;
    private String PhoneNumber;
    private String Status;
    private String Role;
    private String username;
    private String password;

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String address, String email, String phoneNumber, String status, String role, String username, String password) {

        this.createDate = new Date();
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Email = email;
        PhoneNumber = phoneNumber;
        Status = status;
        Role = role;
        this.username = username;
        this.password = password;
    }
}
