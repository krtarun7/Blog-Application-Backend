package com.tarunkumar.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarunkumar.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
