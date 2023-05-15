package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.QuizDao;
import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
    private final QuizDao quizDao;

    @Autowired
    public HomeService(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public List<Quiz> getAllQuizByUser(User user) {
        List<Quiz> list = quizDao.getAllQuizzes().stream()
                .filter(q -> q.getUserID() == user.getId()).collect(Collectors.toList());
        System.out.println("Quiz for user No." + user.getId()+list);
        return list;
    }

}
