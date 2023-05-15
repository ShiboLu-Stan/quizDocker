package com.personalproject1.quiz.domain;

import com.personalproject1.quiz.domain.enums.Status;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int id;
    private String question;
    private String answer;
    private List<String> options;
    private Date createTime;
    private Date lastUpdateTime;
    private int authorID;
    private int categoryID;
    private String status;

    public Question(String question, String answer, List<String> options, int authorID, int categoryID) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.createTime = new Date();
        this.lastUpdateTime = new Date();
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.status = Status.Active.toString();
    }
}
