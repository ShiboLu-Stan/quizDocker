package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {

    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();

        quiz.setId(rs.getInt("id"));
        List<Integer> list = new ArrayList<>();
        list.add(rs.getInt("UsedQuestion1"));
        list.add(rs.getInt("UsedQuestion2"));
        list.add(rs.getInt("UsedQuestion3"));
        list.add(rs.getInt("UsedQuestion4"));
        list.add(rs.getInt("UsedQuestion5"));
        quiz.setUsedQuestions(list);
        quiz.setQuizName(rs.getString("name"));

        quiz.setDateTaken(new Date(rs.getTimestamp("dateTaken").getTime()));
        quiz.setDateFinish(new Date(rs.getTimestamp("dateFinish").getTime()));

        quiz.setUserID(rs.getInt("userID"));
        quiz.setScore(rs.getFloat("score"));
        quiz.setCategoryID(rs.getInt("categoryID"));

        return quiz;
    }
}
