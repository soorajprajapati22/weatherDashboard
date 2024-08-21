package com.weather.weatherDashboard.Service;

import com.weather.weatherDashboard.Entity.User;
import com.weather.weatherDashboard.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class userServiceImpl implements userService{


    private final userRepository userRepo;

    private  final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public userServiceImpl(userRepository userRepo,BCryptPasswordEncoder passwordEncoder) {
        this.userRepo=userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); //if you want role base
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
