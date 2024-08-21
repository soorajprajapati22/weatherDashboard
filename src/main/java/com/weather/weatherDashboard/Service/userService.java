package com.weather.weatherDashboard.Service;

import com.weather.weatherDashboard.Entity.User;

import java.util.List;

public interface userService {

    public User createUser(User user);

    public boolean checkEmail(String email);


    User findByEmail(String email);
    User updateUser(User user);
    void deleteUser(int id);

    String encodePassword(String password);  // Add this method


}
