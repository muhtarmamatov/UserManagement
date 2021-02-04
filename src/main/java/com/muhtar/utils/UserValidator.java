package com.muhtar.utils;

import com.muhtar.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","firstName.NotEmpty");
        if(user.getFirstName().length() < 2){
            errors.rejectValue("firstName", "name.size");
        }
        if(!matches(user.getFirstName())){
            errors.rejectValue("firstName","regex.letters");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.NotEmpty");
        if (user.getLastName().length() < 2){
            errors.rejectValue("lastName","name.size");
        }
        if(!matches(user.getLastName())){
            errors.rejectValue("lastName","regex.letters");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password.NotEmpty");
        if(user.getPassword().length() < 6 || user.getPassword().length() > 32){
            errors.rejectValue("password","password.size");
        }
        if(!user.getPassword().equals(user.getPassword2())){
            errors.rejectValue("password2","password.NotMatch");
        }
        if(!isEmailValid(user.getEmail())){
            errors.rejectValue("email","email.NotValid");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"roleName", "roleName.NotEmpty");

        if(user.getRoleName() == null || user.getRoleName().isEmpty()){
            errors.rejectValue("roleName","roleName.select");
        }
    }

    private boolean isEmailValid(String email) {
        String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
        return email.matches(pattern);
    }

    private boolean matches(String regex) {
        String pattern = "[a-zA-Z]+";
        return regex.matches(pattern);
    }
}
