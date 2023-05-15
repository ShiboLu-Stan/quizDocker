package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.QuestionDao;
import com.personalproject1.quiz.dao.UsedQuestionDao;
import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public Question getQuestionbyID(int id) {
        return questionDao.getQuestionbyID(id);
    }

    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    public void updateQuestion(Question question){
        questionDao.updateQuestion(question);
    }

    public void updateQuestionStatus(int id) {

        String status = getQuestionbyID(id).getStatus().equals(Status.Active.toString())?Status.Suspend.toString():Status.Active.toString();
        questionDao.updateQuestionStatus(id, status);
    }

    public void createNewQuestion(String question, String answer, String option1, String option2, String option3, String option4,Integer authorID, Integer categoryID){
        List<String> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        questionDao.createNewQuestion(new Question(question, answer, options, authorID, categoryID));
    }



}
