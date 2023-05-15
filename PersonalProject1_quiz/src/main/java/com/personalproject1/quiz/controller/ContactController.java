package com.personalproject1.quiz.controller;

import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.service.ContactService;
import com.personalproject1.quiz.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String getContactPage(Model model, HttpSession session) {

        return "contact";
    }

    @PostMapping("/contact")
    public String newContact(Model model,
                                  @RequestParam(name = "contactText") String contactText,
                                  HttpSession session) {
        User user = (User)session.getAttribute("user");
        contactService.newContact(user.getId(), contactText);
        return "redirect:/home";
    }

    @GetMapping("/allContact")
    public String getAllContact(Model model, HttpSession session) {

        model.addAttribute("contactList", contactService.getAllContact());

        return "allContact";
    }



}
