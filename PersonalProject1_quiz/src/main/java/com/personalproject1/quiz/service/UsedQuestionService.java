package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.QuestionDao;
import com.personalproject1.quiz.dao.UsedQuestionDao;
import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.UsedQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsedQuestionService {
    private final UsedQuestionDao usedQuestionDao;

    @Autowired
    public UsedQuestionService(UsedQuestionDao usedQuestionDao) {
        this.usedQuestionDao = usedQuestionDao;
    }

    public UsedQuestion getUsedQuestionbyID(int id) {
        return usedQuestionDao.getUsedQuestionbyID(id);
    }

    public List<UsedQuestion> getListByQuiz(Quiz quiz){
        List<UsedQuestion> list = new ArrayList<>();
        for (int i : quiz.getUsedQuestions()){
            list.add(getUsedQuestionbyID(i));
        }

        return list;
    }

    public List<int[]> getListByQuiz(List<Quiz> quizzes){
        List<int[]> questionsList = new ArrayList<>();

        quizzes.forEach(quiz->{
            int[] questions = new int[5];

            for (int i = 0; i < questions.length; i++){
                questions[i] = usedQuestionDao.getUsedQuestionbyID(quiz.getUsedQuestions().get(i)).getQuestionID();
            }

            questionsList.add(questions);
        });

        return questionsList;
    }

}
