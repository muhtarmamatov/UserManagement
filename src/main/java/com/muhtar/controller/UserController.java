package com.muhtar.controller;

import com.muhtar.domain.RoleName;
import com.muhtar.domain.User;
import com.muhtar.service.UserService;
import com.muhtar.service.register.UserRegistrationService;
import com.muhtar.utils.ResultInfo;
import com.muhtar.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private UserRegistrationService registrationService;
    @Autowired
    private UserValidator userValidator;

    @Autowired
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

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute(name = "user")
                                         User user,
                             BindingResult result,
                             RedirectAttributes attributes,Model model){
        ResultInfo<User> isUserCreated = registrationService.registerUser(user);
        userValidator.validate(user,result);

        if(result.hasErrors()){
            return "Registration";
        }
        if(isUserCreated.getStatus() == ResultInfo.Status.ERROR){
            model.addAttribute("message",isUserCreated.getMessage());
            return "Registration";
        }
        else {
            attributes.addFlashAttribute("msgSuccess", isUserCreated.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id){
        this.userService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/updateUser/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public Optional<User> updateUser(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(path = "/roles",produces = "application/json; charset=UTF-8",method = RequestMethod.GET)
    @ResponseBody
    public List<RoleName> roles(){
        return Arrays.asList(RoleName.values());
    }
}
