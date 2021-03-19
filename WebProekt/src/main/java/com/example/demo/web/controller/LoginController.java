package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.InvalidUserCredentialsException;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    //Service for logging in has been created
    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try {
            user=this.authService.login(request.getParameter("username"), request.getParameter("password"));
            //TODO: Edit code here.
            request.getSession().setAttribute("user",user);
            return "redirect:/home";
        } catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent", "login");
            return "master-template";
        }
    }

}
