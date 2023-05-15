package com.personalproject1.quiz.controller;

import com.personalproject1.quiz.domain.*;
import com.personalproject1.quiz.service.CategoryService;
import com.personalproject1.quiz.service.QuestionService;
import com.personalproject1.quiz.service.QuizService;
import com.personalproject1.quiz.service.UsedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.SliderUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    private final QuizService quizService;
    private final UsedQuestionService usedQuestionService;
    private final CategoryService categoryService;

    @Autowired
    public QuizController(UsedQuestionService usedQuestionService, QuizService quizService, CategoryService categoryService) {
        this.usedQuestionService = usedQuestionService;
        this.quizService = quizService;
        this.categoryService = categoryService;
    }

    @PostMapping("/quiz")
    public String getQuiz(Model model,
                          @RequestParam(name = "categoryID") Integer categoryID,
                          HttpSession session) {
        // init questions
        User user = (User)session.getAttribute("user");
        Quiz quiz = quizService.createQuizByCategory(categoryID, user.getId());
        List<UsedQuestion> usedQuestionList = usedQuestionService.getListByQuiz(quiz);

        Optional<Category> category = categoryService.getCategoryById(categoryID);

        if(category.isPresent()){
            model.addAttribute("quizCategory", category.get());
        }
        System.out.println("Init a quiz for user:"+quiz);

        model.addAttribute("usedQuestionList", usedQuestionList);
        session.setAttribute("quiz", quiz);
        return "quiz";
    }


    @PostMapping("/quizSubmit")
    public String submitQuiz(
            @RequestParam(name = "selectedChoiceId0", required = false, defaultValue = "") Integer[] selectedChoiceId0,
            @RequestParam(name = "selectedChoiceId1", required = false, defaultValue = "") Integer[] selectedChoiceId1,
            @RequestParam(name = "selectedChoiceId2", required = false, defaultValue = "") Integer[] selectedChoiceId2,
            @RequestParam(name = "selectedChoiceId3", required = false, defaultValue = "") Integer[] selectedChoiceId3,
            @RequestParam(name = "selectedChoiceId4", required = false, defaultValue = "") Integer[] selectedChoiceId4,
            HttpSession session
    ) {
        // update Quiz in database

        Quiz quiz = (Quiz)session.getAttribute("quiz");
        System.out.println("update Quiz in database:"+quiz);

        List<Integer[]> listSelected = new ArrayList<>();
        listSelected.add(selectedChoiceId0);
        listSelected.add(selectedChoiceId1);
        listSelected.add(selectedChoiceId2);
        listSelected.add(selectedChoiceId3);
        listSelected.add(selectedChoiceId4);
        List<String> listResults = new ArrayList<>();
        for (int j = 0; j < listSelected.size(); j++){
            Integer[] integers = listSelected.get(j);
            StringBuilder stringBuilder = new StringBuilder("");
            for(int i = 0; i < integers.length; i++){
                stringBuilder.append(integers[i]);
            }
            listResults.add(stringBuilder.toString());
        }


        quizService.submitQuiz(quiz, listResults);

        System.out.println("selectedChoiceId0 +  + selectedChoiceId1 +  + selectedChoiceId2 +  + selectedChoiceId3 + + selectedChoiceId4");
        System.out.println(selectedChoiceId0 + " " + selectedChoiceId1 + " " + selectedChoiceId2 + " " + selectedChoiceId3 + " " + selectedChoiceId4);


        // selectedChoiceId is assumed to be non-null
        return "redirect:/home";
    }

    @PostMapping("/quiz-result")
    public String getQuizResult(Model model,
                                @RequestParam(name = "quizID") Integer quizID,
                                HttpSession session) {

        Optional<Quiz> selectedQuiz = quizService.getQuizById(quizID);

        if (selectedQuiz.isPresent()) {
            List<UsedQuestion> usedQuestionList = new ArrayList<>();

            for (int i : selectedQuiz.get().getUsedQuestions()){
                usedQuestionList.add(usedQuestionService.getUsedQuestionbyID(i));
            }
            model.addAttribute("usedQuestionList", usedQuestionList);
            model.addAttribute("result", selectedQuiz.get());
        } else {
            model.addAttribute("result", "Invalid choice");
        }
        return "quiz-result";
    }
}
