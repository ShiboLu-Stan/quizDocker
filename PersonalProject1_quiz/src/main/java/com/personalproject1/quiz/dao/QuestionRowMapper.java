package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();

        question.setId(rs.getInt("id"));
        question.setQuestion(rs.getString("question"));
        question.setAnswer(rs.getString("answer"));
        List<String> list = new ArrayList<>();
        list.add(rs.getString("option1"));
        list.add(rs.getString("option2"));
        list.add(rs.getString("option3"));
        list.add(rs.getString("option4"));
        question.setOptions(list);
        question.setCreateTime(new Date(rs.getTimestamp("createTime").getTime()));
        question.setLastUpdateTime(new Date(rs.getTimestamp("lastUpdateTime").getTime()));
        question.setAuthorID(rs.getInt("authorID"));
        question.setCategoryID(rs.getInt("categoryID"));
        question.setStatus(rs.getString("status"));
        return question;
    }
}
