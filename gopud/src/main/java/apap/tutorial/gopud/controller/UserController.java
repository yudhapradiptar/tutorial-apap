package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user){
        if(userService.cekValidation(user.getPassword())){
            userService.addUser(user);
            return "home";
        }
        return "passwordFailed";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    private String updatePassword(@RequestParam("password") String password,
                                  @RequestParam("passwordLama") String passwordLama,
                                  @RequestParam("cekPassword") String cekPassword){
        String user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUsername();
        if(!userService.cekPasswordLama(user, passwordLama)){
            return "passwordFailed";
        }
        if(!userService.cekValidation(password)){
            return "passwordFailed";
        }
        userService.updatePassword(user, password);
        return "home";
    }
}
