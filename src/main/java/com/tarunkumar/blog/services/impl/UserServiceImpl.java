package com.tarunkumar.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tarunkumar.blog.entities.User;
import com.tarunkumar.blog.exception.ResourceNotFoundException;
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
    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("Creating user: {}", userDto.getEmail());

        Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email is already registered.");
        }
        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);

        logger.info("User created with ID: {}", savedUser.getId());
        return userToDto(savedUser);
    }

    // UPDATE
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);
        logger.info("User updated with ID: {}", updatedUser.getId());
        return userToDto(updatedUser);
    }

    // GET ONE
    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        logger.info("Fetched user by ID: {}", userId);
        return userToDtoWithoutPassword(user);
    }

    // GET ALL
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::userToDtoWithoutPassword)
                .collect(Collectors.toList());
        logger.info("Fetched all users, count: {}", userDtos.size());
        return userDtos;
    }

    // DELETE
    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
        logger.info("Deleted user with ID: {}", userId);
    }

    // DTO to Entity
    private User dtoToUser(UserDto userDto) {
        User user =this.modelMapper.map(userDto, User.class);
        
        
 /*     user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
*/
        return user;
    }

    // Entity to DTO (with password)
    private UserDto userToDto(User user) {
        UserDto userDto =this.modelMapper.map(user,  UserDto.class);
        
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

    // Entity to DTO (without password)
    private UserDto userToDtoWithoutPassword(User user) {
        UserDto dto = userToDto(user);
        dto.setPassword(null); // Hide password
        return dto;
    }
}
