package com.personalproject1.quiz.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int id;
    private List<Integer> usedQuestions;
    private String quizName;
    private Date dateTaken;
    private Date dateFinish;
    private int userID;
    private float score;
    private int categoryID;

}
