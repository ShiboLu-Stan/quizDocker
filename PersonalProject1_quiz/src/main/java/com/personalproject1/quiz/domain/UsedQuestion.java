package com.personalproject1.quiz.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsedQuestion {
    private int id;
    private String question;
    private String answer;
    private String userAnswer;
    private List<String> options;
    private Date createTime;
    private Date lastUpdateTime;
    private int authorID;
    private int categoryID;
    private int questionID;

    public UsedQuestion(String question, String answer, String userAnswer, List<String> options, int authorID, int categoryID, int questionID) {
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.options = options;
        this.createTime = new Date();
        this.lastUpdateTime = new Date();
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.questionID = questionID;
    }
}
