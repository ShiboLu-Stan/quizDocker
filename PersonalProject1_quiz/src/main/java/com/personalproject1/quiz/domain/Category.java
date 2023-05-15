package com.personalproject1.quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private int id;
    private int timeNeed;
    private String description;
}
