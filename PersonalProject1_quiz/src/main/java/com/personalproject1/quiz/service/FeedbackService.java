package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.FeedBackDao;
import com.personalproject1.quiz.dao.QuizDao;
import com.personalproject1.quiz.domain.Feedback;
import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final FeedBackDao feedBackDao;

    @Autowired
    public FeedbackService(FeedBackDao feedBackDao) {
        this.feedBackDao = feedBackDao;
    }

    public List<Feedback> getAllFeedback() {
        return feedBackDao.getAllFeedbacks();
    }

    public void newFeedback(String curRate, String feedbackText){
        feedBackDao.createNewFeedback(new Feedback(0, new Date(), Integer.parseInt(curRate),feedbackText));
    }

}
