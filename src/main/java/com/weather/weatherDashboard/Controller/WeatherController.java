package com.weather.weatherDashboard.Controller;


import com.weather.weatherDashboard.Entity.WeatherData;
import com.weather.weatherDashboard.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam(name = "city", required = false, defaultValue = "London") String city, Model model) {
        WeatherData weatherData = weatherService.getWeatherByCity(city);
        model.addAttribute("weather", weatherData);
        return "weather";
    }

}