package com.tarunkumar.blog.repository;

import com.tarunkumar.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    // Custom query method to find user by email
    Optional<User> findByEmail(String email);
}
