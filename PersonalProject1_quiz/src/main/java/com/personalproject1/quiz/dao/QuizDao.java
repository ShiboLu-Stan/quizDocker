package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class QuizDao {
    JdbcTemplate jdbcTemplate;
    QuizRowMapper quizRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.quizRowMapper = quizRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public int createNewQuiz(Quiz quiz) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO Quiz (UsedQuestion1, UsedQuestion2, UsedQuestion3, UsedQuestion4, UsedQuestion5, name, dateTaken, dateFinish, userID, score, categoryID) VALUES " +
                "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        jdbcTemplate.update(query, quiz.getUsedQuestions().get(0), quiz.getUsedQuestions().get(1), quiz.getUsedQuestions().get(2), quiz.getUsedQuestions().get(3),
//                quiz.getUsedQuestions().get(4), quiz.getQuizName(), formatter.format(quiz.getDateTaken()),
//                formatter.format(quiz.getDateTaken()), quiz.getUserID(), quiz.getScore(), quiz.getCategoryID());

        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, quiz.getUsedQuestions().get(0));
            pst.setInt(2, quiz.getUsedQuestions().get(1));
            pst.setInt(3, quiz.getUsedQuestions().get(2));
            pst.setInt(4, quiz.getUsedQuestions().get(3));
            pst.setInt(5, quiz.getUsedQuestions().get(4));
            pst.setString(6, quiz.getQuizName());
            pst.setTimestamp(7, new Timestamp(quiz.getDateTaken().getTime()));
            pst.setTimestamp(8, new Timestamp(quiz.getDateFinish().getTime()));
            pst.setInt(9, quiz.getUserID());
            pst.setFloat(10, quiz.getScore());
            pst.setInt(11, quiz.getCategoryID());
            return pst;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        return generatedId;
    }

    public List<Quiz> getAllQuizzes() {
        String query = "SELECT * FROM Quiz";

        List<Quiz> quizs = jdbcTemplate.query(query, quizRowMapper);

        return quizs;
    }

    public void submitUpdateQuiz(int id, int score) {

        String query = "UPDATE Quiz " +
                "SET dateFinish = :dateFinish, score = :score " +
                "WHERE id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        parameterSource.addValue("dateFinish", new Timestamp(new Date().getTime()));
        parameterSource.addValue("score", score);
        namedParameterJdbcTemplate.update(query, parameterSource);
    }

}
