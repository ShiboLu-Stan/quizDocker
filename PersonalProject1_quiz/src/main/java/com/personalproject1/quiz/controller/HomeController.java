package com.personalproject1.quiz.controller;

import com.personalproject1.quiz.domain.Quiz;
import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.service.CategoryService;
import com.personalproject1.quiz.service.HomeService;
import com.personalproject1.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private QuestionService questionService;
    private HomeService homeService;

    private CategoryService categoryService;

    @Autowired
    public HomeController(QuestionService questionService, HomeService homeService, CategoryService categoryService) {
        this.questionService = questionService;
        this.homeService = homeService;
        this.categoryService = categoryService;
    }


    @GetMapping("/home")
    public String getMainPage(Model model, HttpSession session) {

        User user = (User)session.getAttribute("user");

        System.out.println("!!!getCategorys" + categoryService.getCategorys());

        model.addAttribute("user", user);
        model.addAttribute("quiz", homeService.getAllQuizByUser(user));
        model.addAttribute("category", categoryService.getCategorys());

        return "home";
    }



}
