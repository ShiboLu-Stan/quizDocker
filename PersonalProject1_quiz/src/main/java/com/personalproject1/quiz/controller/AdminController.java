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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class AdminController {

    private CategoryService categoryService;
    private UsedQuestionService usedQuestionService;
    private UserService userService;

    private QuizService quizService;

    private FeedbackService feedbackService;
    private ContactService contactService;

    @Autowired
    public AdminController(QuizService quizService, CategoryService categoryService, UserService userService
            , UsedQuestionService usedQuestionService, FeedbackService feedbackService, ContactService contactService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.usedQuestionService = usedQuestionService;
        this.feedbackService = feedbackService;
        this.contactService = contactService;
        int i = 0; //test jenkins
    }


    @GetMapping("/admin")
    public String getAdminPage(Model model, HttpSession session) {

        User user = (User)session.getAttribute("user"); // admin user

        List<Quiz> quizzes = quizService.getAllQuizzes();
        quizzes.sort((o1, o2) -> o2.getDateTaken().compareTo(o1.getDateTaken()));

        List<Category> quizCategories = categoryService.getListByQuiz(quizzes);
        List<User> quizUsers = userService.getListByQuiz(quizzes);
        List<int[]> quizQuestions = usedQuestionService.getListByQuiz(quizzes);
        List<User> allUsers = userService.getAllUsers();
        List<Feedback> allFeedbacks = feedbackService.getAllFeedback();
        List<Contact> allContacts = contactService.getAllContact();

        session.setAttribute("allFeedbacks", allFeedbacks);
        session.setAttribute("allContacts", allContacts);
        session.setAttribute("user", user);
        session.setAttribute("allQuizzes", quizzes);
        session.setAttribute("quizCategories", quizCategories);
        session.setAttribute("quizUsers", quizUsers);
        session.setAttribute("quizQuestions", quizQuestions);
        session.setAttribute("allUsers", allUsers);

        return "admin";
    }

    @PostMapping("/adminCategoryID")
    public String getAdminPageByCategoryID(Model model,
                               @RequestParam(name = "categoryID") Integer categoryID,
                               HttpSession session) {

        User user = (User)session.getAttribute("user"); // admin user

        List<Quiz> quizzes = quizService.getAllQuizzesByCategoryID(categoryID);
        quizzes.sort((o1, o2) -> o2.getDateTaken().compareTo(o1.getDateTaken()));

        List<Category> quizCategories = categoryService.getListByQuiz(quizzes);
        List<User> quizUsers = userService.getListByQuiz(quizzes);
        List<int[]> quizQuestions = usedQuestionService.getListByQuiz(quizzes);
        List<User> allUsers = userService.getAllUsers();
        List<Feedback> allFeedbacks = feedbackService.getAllFeedback();
        List<Contact> allContacts = contactService.getAllContact();

        session.setAttribute("allFeedbacks", allFeedbacks);
        session.setAttribute("allContacts", allContacts);

        session.setAttribute("user", user);
        session.setAttribute("allQuizzes", quizzes);
        session.setAttribute("quizCategories", quizCategories);
        session.setAttribute("quizUsers", quizUsers);
        session.setAttribute("quizQuestions", quizQuestions);
        session.setAttribute("allUsers", allUsers);

        return "admin";
    }

    @PostMapping("/adminUserID")
    public String getAdminPageByUserID(Model model,
                               @RequestParam(name = "userID") Integer userID,
                               HttpSession session) {

        User user = (User)session.getAttribute("user"); // admin user

        List<Quiz> quizzes = quizService.getAllQuizzesByUserID(userID);
        quizzes.sort((o1, o2) -> o2.getDateTaken().compareTo(o1.getDateTaken()));

        List<Category> quizCategories = categoryService.getListByQuiz(quizzes);
        List<User> quizUsers = userService.getListByQuiz(quizzes);
        List<int[]> quizQuestions = usedQuestionService.getListByQuiz(quizzes);

        List<User> allUsers = userService.getAllUsers();
        List<Feedback> allFeedbacks = feedbackService.getAllFeedback();
        List<Contact> allContacts = contactService.getAllContact();

        session.setAttribute("allFeedbacks", allFeedbacks);
        session.setAttribute("allContacts", allContacts);

        session.setAttribute("user", user);
        session.setAttribute("allQuizzes", quizzes);
        session.setAttribute("quizCategories", quizCategories);
        session.setAttribute("quizUsers", quizUsers);
        session.setAttribute("quizQuestions", quizQuestions);
        session.setAttribute("allUsers", allUsers);

        return "admin";
    }

    @PostMapping("/userProfile")
    public String getUserProfileByUserID(Model model,
                                       @RequestParam(name = "userID") Integer userID,
                                       HttpSession session) {

        User userProfile = userService.getUserById(userID);

        session.setAttribute("certainUserProfile", userProfile);

        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile")
    public String getUserProfile(Model model,HttpSession session) {
        User user = (User)session.getAttribute("certainUserProfile");
        System.out.println(user);
        model.addAttribute("certainUserProfile", user);

        return "userProfile";
    }

    @PostMapping("/changeUserStatus")
    public String changeUserStatus(Model model,
                                         @RequestParam(name = "userID") Integer userID,
                                         HttpSession session) {

        userService.changeUserStatus(userID);

        return "redirect:/admin";
    }



}
