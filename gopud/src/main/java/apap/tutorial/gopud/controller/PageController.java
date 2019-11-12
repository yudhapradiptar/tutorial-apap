package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.repository.RoleDB;
import apap.tutorial.gopud.service.RoleService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("listRole", roleService.getListRole());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
