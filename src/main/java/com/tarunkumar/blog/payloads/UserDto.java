package com.tarunkumar.blog.payloads;

public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String password;
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
