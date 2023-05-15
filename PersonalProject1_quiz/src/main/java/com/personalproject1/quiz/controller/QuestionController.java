package com.personalproject1.quiz.controller;

import com.personalproject1.quiz.domain.*;
import com.personalproject1.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class QuestionController {

    private QuestionService questionService;
    private CategoryService categoryService;

    @Autowired
    public QuestionController(QuestionService questionService, CategoryService categoryService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/editQuestions")
    public String getEditQuestionsPage(Model model,
                                   HttpSession session) {

        List<Question> allQuestions = questionService.getAllQuestions();

        System.out.println(allQuestions);
        session.setAttribute("allQuestions", allQuestions);
        model.addAttribute("allCategories", categoryService.getCategorys());
        if(session.getAttribute("newQuestionValid")==null){
            session.setAttribute("newQuestionValid", "valid");
        }

        return "editQuestions";
    }

    @PostMapping("/changeQuestionStatus")
    public String changeQuestionStatus(Model model,
                                   @RequestParam(name = "questionID") Integer questionID,
                                   HttpSession session) {

        questionService.updateQuestionStatus(questionID);

        return "redirect:/editQuestions";
    }

    @PostMapping("/editQuestion")
    public String editQuestion(Model model,
                                @RequestParam(name = "questionEditID") String questionEditID,
                                @RequestParam(name = "question") String question,
                                @RequestParam(name = "selectedAnswer", required = false, defaultValue = "") List<String> selectedAnswer,
                                @RequestParam(name = "option1") String option1,
                                @RequestParam(name = "option2") String option2,
                                @RequestParam(name = "option3") String option3,
                                @RequestParam(name = "option4") String option4,
                                       HttpSession session) {

        Question curQuestion = questionService.getQuestionbyID(Integer.parseInt(questionEditID));
        if (question.length()!=0){
            curQuestion.setQuestion(question);
        }
        if (selectedAnswer.size()!=0){

            StringBuilder stringBuilderAnswer = new StringBuilder("");
            for (String eachAnswer : selectedAnswer){
                stringBuilderAnswer.append(eachAnswer);
            }
            curQuestion.setAnswer(stringBuilderAnswer.toString());
        }


        List<String> options = new ArrayList<>();

        if (option1.length()!=0){
            options.add(option1);

        } else {
            options.add(curQuestion.getOptions().get(0));
        }
        if (option2.length()!=0){
            options.add(option2);

        } else {
            options.add(curQuestion.getOptions().get(1));
        }
        if (option3.length()!=0){
            options.add(option3);

        } else {
            options.add(curQuestion.getOptions().get(2));
        }
        if (option4.length()!=0){
            options.add(option4);

        } else {
            options.add(curQuestion.getOptions().get(3));
        }
        curQuestion.setOptions(options);
        curQuestion.setLastUpdateTime(new Date());

        questionService.updateQuestion(curQuestion);

        return "redirect:/editQuestions";
    }

    @PostMapping("/createNewQuestion")
    public String createNewQuestion(Model model,
                                @RequestParam(name = "question") String question,
                                @RequestParam(name = "selectedAnswer", required = false, defaultValue = "") List<String> selectedAnswer,
                                @RequestParam(name = "option1") String option1,
                                @RequestParam(name = "option2") String option2,
                                @RequestParam(name = "option3") String option3,
                                @RequestParam(name = "option4") String option4,
                                    @RequestParam(name = "categoryID") Integer categoryID,
                                HttpSession session) {

        System.out.println(selectedAnswer);

        User user = (User)session.getAttribute("user"); // admin user
        if (selectedAnswer == null || question.length() == 0 || selectedAnswer.size() == 0 || option1.length() == 0 || option2.length() == 0
                || option3.length() == 0 || option4.length() == 0 ||categoryID == null){

            session.setAttribute("newQuestionValid", "unvalid");
            return "redirect:/editQuestions";
        }

        StringBuilder stringBuilderAnswer = new StringBuilder("");
        for (String eachAnswer : selectedAnswer){
            stringBuilderAnswer.append(eachAnswer);
        }

        session.setAttribute("newQuestionValid", "valid");

        questionService.createNewQuestion(question,stringBuilderAnswer.toString(),option1, option2, option3, option4,user.getId(), categoryID);


        return "redirect:/editQuestions";
    }



}
