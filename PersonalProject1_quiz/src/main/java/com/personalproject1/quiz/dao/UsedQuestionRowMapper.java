package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.UsedQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UsedQuestionRowMapper implements RowMapper<UsedQuestion> {

    @Override
    public UsedQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsedQuestion usedQuestion = new UsedQuestion();

        usedQuestion.setId(rs.getInt("id"));
        usedQuestion.setQuestion(rs.getString("question"));
        usedQuestion.setAnswer(rs.getString("answer"));
        usedQuestion.setUserAnswer(rs.getString("userAnswer"));
        List<String> list = new ArrayList<>();
        list.add(rs.getString("option1"));
        list.add(rs.getString("option2"));
        list.add(rs.getString("option3"));
        list.add(rs.getString("option4"));
        usedQuestion.setOptions(list);
        usedQuestion.setCreateTime(new Date(rs.getTimestamp("createTime").getTime()));
        usedQuestion.setLastUpdateTime(new Date(rs.getTimestamp("lastUpdateTime").getTime()));
        usedQuestion.setAuthorID(rs.getInt("authorID"));
        usedQuestion.setCategoryID(rs.getInt("categoryID"));
        usedQuestion.setQuestionID(rs.getInt("questionID"));
        return usedQuestion;
    }
}
