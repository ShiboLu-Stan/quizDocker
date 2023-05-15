package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.UsedQuestion;
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
public class UsedQuestionDao {
    JdbcTemplate jdbcTemplate;
    UsedQuestionRowMapper usedQuestionRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UsedQuestionDao(JdbcTemplate jdbcTemplate, UsedQuestionRowMapper usedQuestionRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.usedQuestionRowMapper = usedQuestionRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }



    public int createNewUsedQuestion(UsedQuestion usedQuestion) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO UsedQuestion (question, answer, userAnswer, option1, option2, option3, option4, createTime, lastUpdateTime, authorID, categoryID, questionID) VALUES " +
                "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//        jdbcTemplate.update(query, usedQuestion.getQuestion(), usedQuestion.getAnswer(), usedQuestion.getUserAnswer(), usedQuestion.getOptions().get(0),
//                usedQuestion.getOptions().get(1), usedQuestion.getOptions().get(2), usedQuestion.getOptions().get(3),
//                usedQuestion.getCreateTime(), usedQuestion.getLastUpdateTime(), usedQuestion.getAuthorID(), usedQuestion.getCategoryID(),
//                usedQuestion.getQuestionID(), keyHolder);
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, usedQuestion.getQuestion());
            pst.setString(2, usedQuestion.getAnswer());
            pst.setString(3, usedQuestion.getUserAnswer());
            pst.setString(4, usedQuestion.getOptions().get(0));
            pst.setString(5, usedQuestion.getOptions().get(1));
            pst.setString(6, usedQuestion.getOptions().get(2));
            pst.setString(7, usedQuestion.getOptions().get(3));
            pst.setTimestamp(8, new Timestamp(usedQuestion.getCreateTime().getTime()));
            pst.setTimestamp(9, new Timestamp(usedQuestion.getLastUpdateTime().getTime()));
            pst.setInt(10, usedQuestion.getAuthorID());
            pst.setInt(11, usedQuestion.getCategoryID());
            pst.setInt(12, usedQuestion.getQuestionID());
            return pst;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        return generatedId;
    }

    public List<UsedQuestion> getAllUsedQuestions() {
        String query = "SELECT * FROM UsedQuestion";

        List<UsedQuestion> usedQuestions = jdbcTemplate.query(query, usedQuestionRowMapper);

        return usedQuestions;
    }

    public UsedQuestion getUsedQuestionbyID(int id){
        String query = "select * from UsedQuestion where id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        List<UsedQuestion> usedQuestions = namedParameterJdbcTemplate.query(query, parameterSource,usedQuestionRowMapper);

        return usedQuestions.size() == 0 ? null : usedQuestions.get(0);
    }

    public String getAndUpdateUsedQuestion(int id, String userAnswer) {

        String query = "select * from UsedQuestion where id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        List<UsedQuestion> usedQuestions = namedParameterJdbcTemplate.query(query, parameterSource,usedQuestionRowMapper);

        String answer = usedQuestions.get(0).getAnswer();

        query = "UPDATE UsedQuestion " +
                "SET userAnswer = :userAnswer " +
                "WHERE id=:identity";
        parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        parameterSource.addValue("userAnswer", userAnswer);
        namedParameterJdbcTemplate.update(query, parameterSource);

        return answer;
    }
}
