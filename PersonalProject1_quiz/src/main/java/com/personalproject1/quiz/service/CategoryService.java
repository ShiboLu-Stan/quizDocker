package com.personalproject1.quiz.service;

import com.personalproject1.quiz.dao.CategoryDao;
import com.personalproject1.quiz.dao.QuestionDao;
import com.personalproject1.quiz.domain.Category;
import com.personalproject1.quiz.domain.Question;
import com.personalproject1.quiz.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getCategorys() {
        return categoryDao.getAllCategorys();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryDao.getAllCategorys().stream().filter(c-> c.getId() == id).findFirst();
    }

    public List<Category> getListByQuiz(List<Quiz> quizzes){
        List<Category> categorylist = new ArrayList<>();

        quizzes.stream().forEach(quiz -> categorylist.add(categoryDao.getCategoryID(quiz.getCategoryID())));

        return categorylist;
    }


}
