package com.tarunkumar.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

    private Integer id;
    @NotEmpty
    @Size(min=4 , message="username must be min of 4 character !!")
    private String name;
    @Email(message="email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min=4,max=10,message="Password must be min of 3 chars and max of 10 chars !!")
    private String password;
    @NotEmpty
    private String about;

    // Default constructor
    public UserDto() {}

    // Full constructor
    public UserDto(Integer id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAbout() { return about; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setAbout(String about) { this.about = about; }

    // toString() - exclude password
    @Override
    public String toString() {
        return "UserDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", about='" + about + '\'' +
               '}';
    }
}
