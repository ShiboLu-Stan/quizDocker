package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Feedback;
import com.personalproject1.quiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class FeedBackDao {
    JdbcTemplate jdbcTemplate;
    FeedBackRowMapper feedBackRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedBackDao(JdbcTemplate jdbcTemplate, FeedBackRowMapper feedBackRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.feedBackRowMapper = feedBackRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewFeedback(Feedback feedback) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO Feedback (feedBackDate, Star, text) VALUES " +
                "( ?, ?, ?)";

        jdbcTemplate.update(query, formatter.format(new Timestamp(feedback.getFeedBackDate().getTime())), feedback.getStar(), feedback.getText()) ;
    }

    public List<Feedback> getAllFeedbacks() {
        String query = "SELECT * FROM Feedback";

        List<Feedback> feedbacks = jdbcTemplate.query(query, feedBackRowMapper);

        return feedbacks;
    }

}
