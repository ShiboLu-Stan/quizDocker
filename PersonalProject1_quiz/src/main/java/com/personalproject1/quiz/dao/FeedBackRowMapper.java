package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Feedback;
import com.personalproject1.quiz.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Component
public class FeedBackRowMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();

        feedback.setId(rs.getInt("id"));
        feedback.setFeedBackDate(new Date(rs.getTimestamp("feedBackDate").getTime()));
        feedback.setStar(rs.getInt("Star"));
        feedback.setText(rs.getString("text"));
        return feedback;
    }
}
