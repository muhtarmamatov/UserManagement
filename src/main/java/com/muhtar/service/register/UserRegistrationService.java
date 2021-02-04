package com.muhtar.service.register;

import com.muhtar.domain.User;
import com.muhtar.service.UserService;
import com.muhtar.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserService userService;

    public ResultInfo<User> registerUser(User user){
        if(UserExist(user)){
            return  new ResultInfo<>(
                    String.format("User with email %s is already exist, please reset password",user.getEmail()),
                    ResultInfo.Status.ERROR);
        }
        if (isPasswordMatch(user.getPassword(),user.getPassword2())){
            userService.saveUser(user);
            return new ResultInfo<>("Successfully registered!", ResultInfo.Status.SUCCESS);
        }
        return new ResultInfo<>("Password do not match please retry", ResultInfo.Status.ERROR);
    }

    private boolean isPasswordMatch(String password, String password2) {
        return password.equals(password2);
    }

    private boolean UserExist(User user) {
        return userService.findUserByEmail(user.getEmail()) != null;
    }
}
