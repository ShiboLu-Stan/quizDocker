package com.personalproject1.quiz.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
    private int id;
    private Date feedBackDate;
    private int Star;
    private String text;
}
