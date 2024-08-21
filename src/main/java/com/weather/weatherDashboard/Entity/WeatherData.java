package com.weather.weatherDashboard.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherData {

    private Main main;
    private Wind wind;
    private int visibility;
    private Weather[] weather;
    private String name; // City name

    // Getters and setters

    public static class Main {
        private double temp;
        private int humidity;

        // Getters and setters
        public double getTemp() {
            return temp;
        }
        public void setTemp(double temp) {
            this.temp = temp;
        }
        public int getHumidity() {
            return humidity;
        }
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    public static class Wind {
        private double speed;

        // Getters and setters
        public double getSpeed() {
            return speed;
        }
        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }

    public static class Weather {
        private String description;

        // Getters and setters
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }

    // Getters and setters
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public Wind getWind() {
        return wind;
    }
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    public int getVisibility() {
        return visibility;
    }
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    public Weather[] getWeather() {
        return weather;
    }
    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}