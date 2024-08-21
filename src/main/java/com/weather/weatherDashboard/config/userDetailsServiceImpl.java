package com.weather.weatherDashboard.config;

import com.weather.weatherDashboard.Entity.User;
import com.weather.weatherDashboard.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return new customUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }

}

