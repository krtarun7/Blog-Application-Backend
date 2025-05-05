package com.tarunkumar.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Column(name = "user_name", nullable = false, length = 100)
   // @NotEmpty
   // @Size(min = 4, message = "Username must be at least 4 characters")
    private String name;

    @Column(unique = true, nullable = false)
    @Email(message = "Email address is not valid")
    private String email;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 6, max = 100, message = "Password must be between 6-100 characters")
    private String password;

    @Column(length = 500)
    private String about;

    // Default constructor
    public User() {}

    // Constructor without ID
    public User(String name, String email, String password, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    // Full constructor
    public User(Integer id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    // toString() - exclude password
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", about='" + about + '\'' +
               '}';
    }
}
