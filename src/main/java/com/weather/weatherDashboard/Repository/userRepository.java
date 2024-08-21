package com.weather.weatherDashboard.Repository;

import com.weather.weatherDashboard.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User,Integer> {

    public  boolean existsByEmail(String email);

    public User findByEmail(String email);
}
