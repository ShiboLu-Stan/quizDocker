package com.personalproject1.quiz.controller;

import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.service.LoginService;
import com.personalproject1.quiz.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, Model model) {
        // if attribute doesn't exist, Create a new one or not.
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "register";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/register")
    public String getRegister(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String address,
                              @RequestParam String email,
                              @RequestParam String phoneNumber,
                              @RequestParam String username,
                              @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = registerService.validateRegister(firstName, lastName, address, email, phoneNumber, username, password);

        if(possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", possibleUser.get());

            return "redirect:/home";
        } else { // if user details are invalid

            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("register", "username");
            return "register";
        }

    }

}
