package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class QuestionDao {
    JdbcTemplate jdbcTemplate;
    QuestionRowMapper questionRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public QuestionDao (JdbcTemplate jdbcTemplate, QuestionRowMapper questionRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.questionRowMapper = questionRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }



    public void createNewQuestion(Question question) {
        String query = "INSERT INTO Question (question, answer, option1, option2, option3, option4, createTime, lastUpdateTime, authorID, categoryID, status) VALUES " +
                "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, question.getQuestion(), question.getAnswer(), question.getOptions().get(0),
                question.getOptions().get(1), question.getOptions().get(2), question.getOptions().get(3),
                formatter.format(new Timestamp(question.getCreateTime().getTime())),
                formatter.format(new Timestamp(question.getLastUpdateTime().getTime())) , question.getAuthorID(), question.getCategoryID(), Status.Active.toString());
    }

    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM Question";

        List<Question> questions = jdbcTemplate.query(query, questionRowMapper);

        return questions;
    }

    public Question getQuestionbyID(int id){
        String query = "select * from Question where id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        List<Question> questions = namedParameterJdbcTemplate.query(query, parameterSource,questionRowMapper);

        return questions.size() == 0 ? null : questions.get(0);

    }

    public List<Question> getQuestionbyCategory(int CategoryID){
        String query = "select * from Question where categoryID=:CategoryID and status=:status";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("CategoryID", CategoryID);
        parameterSource.addValue("status", Status.Active.toString());
        List<Question> questions = namedParameterJdbcTemplate.query(query, parameterSource,questionRowMapper);
        int num = questions.size();
        if(num < 5){
            return new ArrayList<>();
        }
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size()<5){
            int cur;
            do{
                cur = random.nextInt(num);
            }while (set.contains(cur));
            set.add(cur);
        }

        List<Question> result = new ArrayList<>();
        for (int i : set){
            result.add(questions.get(i));
        }
        return result;

    }

    public void updateQuestion(Question question) {

        String query = "UPDATE Question " +
                "SET question = :question, answer = :answer, option1 = :option1, option2 = :option2, option3 = :option3" +
                ", option4 = :option4, lastUpdateTime = :lastUpdateTime " +
                "WHERE id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", question.getId());
        parameterSource.addValue("question", question.getQuestion());
        parameterSource.addValue("answer", question.getAnswer());
        parameterSource.addValue("option1", question.getOptions().get(0));
        parameterSource.addValue("option2", question.getOptions().get(1));
        parameterSource.addValue("option3", question.getOptions().get(2));
        parameterSource.addValue("option4", question.getOptions().get(3));
        parameterSource.addValue("lastUpdateTime", formatter.format(new Timestamp(question.getLastUpdateTime().getTime())));
        namedParameterJdbcTemplate.update(query, parameterSource);
    }

    public void updateQuestionStatus(int id, String status) {

        String query = "UPDATE Question " +
                "SET status = :status " +
                "WHERE id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        parameterSource.addValue("status", status);
        namedParameterJdbcTemplate.update(query, parameterSource);
    }
}
