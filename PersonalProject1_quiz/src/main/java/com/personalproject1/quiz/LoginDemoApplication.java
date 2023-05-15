package com.personalproject1.quiz;

import com.personalproject1.quiz.dao.*;
import com.personalproject1.quiz.domain.Category;
import com.personalproject1.quiz.domain.enums.Role;
import com.personalproject1.quiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LoginDemoApplication {
    private static UserDao userDao;
    private static CategoryDao categoryDao;
    private static ContactDao contactDao;
    private static FeedBackDao feedBackDao;
    private static QuestionDao questionDao;
    private static UsedQuestionDao usedQuestionDao;

    public LoginDemoApplication() {
    }

    @Autowired
    public LoginDemoApplication(UserDao userDao,
                                CategoryDao categoryDao,
                                ContactDao contactDao,
                                FeedBackDao feedBackDao,
                                QuestionDao questionDao,
                                UsedQuestionDao usedQuestionDao){
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.contactDao = contactDao;
        this.feedBackDao = feedBackDao;
        this.questionDao = questionDao;
        this.usedQuestionDao = usedQuestionDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginDemoApplication.class, args);

//        System.out.println(userDao.getAllUsers());
//        System.out.println(categoryDao.getAllCategorys());
//        System.out.println(contactDao.getAllContacts());
//        System.out.println(feedBackDao.getAllFeedbacks());
//        System.out.println(questionDao.getAllQuestions());
//        System.out.println(usedQuestionDao.getAllUsedQuestions());
    }

}
