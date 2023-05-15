package com.personalproject1.quiz.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private int id;
    private Date contactDate;
    private int userID;
    private String text;

}
