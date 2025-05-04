package com.tarunkumar.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tarunkumar.blog.payloads.UserDto;
import com.tarunkumar.blog.services.UserService;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // POST - Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Creating user with email: {}", userDto.getEmail());
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT - Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        logger.info("Updating user with ID: {}", userId);
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE - Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        logger.info("Deleting user with ID: {}", userId);
        this.userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    // GET - Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        logger.info("Fetching user with ID: {}", userId);
        UserDto userDto = this.userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    // GET - Get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Fetching all users");
        List<UserDto> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
