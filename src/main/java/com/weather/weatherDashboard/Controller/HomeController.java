package com.weather.weatherDashboard.Controller;

import com.weather.weatherDashboard.Entity.User;
import com.weather.weatherDashboard.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private userService userSer;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }



    @GetMapping("/register")
    public String register(){
        return "register";
    }



    @PostMapping("/createuser")
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if (user.getEmail().isEmpty() || user.getUsername().isEmpty() || user.getFullName().isEmpty() || user.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "All fields are required!");
            return "redirect:/register";
        }

        if (userSer.checkEmail(user.getEmail())) {
            redirectAttributes.addFlashAttribute("message", "Email already registered!");
        } else {
            User u = userSer.createUser(user);
            if (u != null) {
                redirectAttributes.addFlashAttribute("message", "Data saved into Database!");
            } else {
                redirectAttributes.addFlashAttribute("message", "Something went wrong!");
            }
        }

        return "redirect:/register";
    }

}
