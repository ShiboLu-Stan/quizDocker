package com.personalproject1.quiz.controller;

import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.service.CategoryService;
import com.personalproject1.quiz.service.FeedbackService;
import com.personalproject1.quiz.service.HomeService;
import com.personalproject1.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback")
    public String getFeedbackPage(Model model, HttpSession session) {

        return "feedback";
    }

    @PostMapping("/feedback")
    public String getFeedbackPage(Model model,
                                  @RequestParam(name = "curRate") String curRate,
                                  @RequestParam(name = "feedbackText") String feedbackText,
                                  HttpSession session) {
        feedbackService.newFeedback(curRate, feedbackText);
        return "redirect:/home";
    }

    @GetMapping("/allFeedback")
    public String getAllFeedback(Model model, HttpSession session) {

        model.addAttribute("feedbackList", feedbackService.getAllFeedback());

        return "allFeedback";
    }



}
