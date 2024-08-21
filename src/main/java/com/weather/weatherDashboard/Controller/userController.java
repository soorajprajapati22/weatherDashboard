package com.weather.weatherDashboard.Controller;

import com.weather.weatherDashboard.Entity.User;
import com.weather.weatherDashboard.Entity.WeatherData;
import com.weather.weatherDashboard.Repository.userRepository;
import com.weather.weatherDashboard.Service.WeatherService;
import com.weather.weatherDashboard.Service.userService;
import com.weather.weatherDashboard.config.customUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private userRepository userRepo;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private userService userSer;



    @ModelAttribute
    public void userDetails(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            User user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/")
    public String home(@RequestParam(name = "city", required = false, defaultValue = "London") String city, Model model) {
        User user = (User) model.getAttribute("user");

        // Fetch weather data
        WeatherData weatherData = weatherService.getWeatherByCity(city);
        model.addAttribute("weather", weatherData);

        return "user/home";  // Ensure this view exists
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal customUserDetails userDetails, Model model) {
        User user = userSer.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "user/profile";  // Profile management view
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, @AuthenticationPrincipal customUserDetails userDetails, RedirectAttributes redirectAttributes) {
        User existingUser = userSer.findByEmail(userDetails.getUsername());
        if (existingUser != null) {
            user.setId(existingUser.getId());
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                user.setPassword(userSer.encodePassword(user.getPassword()));
            }
            userSer.updateUser(user);
            redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "User not found.");
        }
        return "redirect:/user/profile";
    }

    @PostMapping("/delete")
    public String deleteUser(@AuthenticationPrincipal customUserDetails userDetails) {
        User existingUser = userSer.findByEmail(userDetails.getUsername());
        if (existingUser != null) {
            userSer.deleteUser(existingUser.getId());
            return "redirect:/logout"; // Redirect to logout after deletion
        } else {
            return "redirect:/user/profile";
        }
    }
}