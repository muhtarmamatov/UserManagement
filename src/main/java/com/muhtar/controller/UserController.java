package com.muhtar.controller;

import com.muhtar.domain.User;
import com.muhtar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping(value = "/signup")
    public String signUp(Model model){
        model.addAttribute("user", new User());
        return "Registration";
    }
    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "Login";
    }

    @PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute("user")User user){
        Boolean saveResult = userService.saveUser(user);
        if (saveResult.equals(true)){
            return "redirect:/login";
        }
        else {
            return "/signup";
        }
    }
}
