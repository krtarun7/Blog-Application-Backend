package com.tarunkumar.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tarunkumar.blog.exception.ResourceNotFoundException;
import com.tarunkumar.blog.entities.User;
import com.tarunkumar.blog.payloads.UserDto;
import com.tarunkumar.blog.repository.UserRepo;
import com.tarunkumar.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
    	
    	System.out.println(userDto.toString());
    	System.out.println("entered in creaat user function");
    	
        Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email is already registered."); // Replace with CustomException if defined
        }
        
//        userDto.setId(null);

        User user = this.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Saving new user...");

        User savedUser = this.userRepo.save(user);
        logger.info("User saved with ID: {}", savedUser.getId());
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        logger.info("User updated with ID: {}", updatedUser.getId());
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        logger.info("Fetched user by ID: {}", userId);
        return this.userToDtoWithoutPassword(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::userToDtoWithoutPassword)
                .collect(Collectors.toList());
        logger.info("Fetched all users, count: {}", userDtos.size());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
        logger.info("Deleted user with ID: {}", userId);
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

    private UserDto userToDtoWithoutPassword(User user) {
        UserDto userDto = userToDto(user);
//        userDto.setPassword(null); // Hide password
        return userDto;
    }
}
