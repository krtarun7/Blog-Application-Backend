package com.tarunkumar.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
    private String name;
    private String email;
    private String password;
    private String about;
    
    
  //Getter Method
  	public int getId() {
  		return id;
  	}
  	public String getName() {
  		return name;
  	}
  	public String getEmail() {
  		return email;
  	}
  	public String getPassword() {
  		return password;
  		
  	}
  	public String getAbout() {
  		return about;
  	}

  	//Setter Method
  	public void setId(int Id) {
  		this.id=Id;
  	}
  	public void setName(String Name) {
  		this.name=Name;
  	}
  	public void setEmail(String Email) {
  		this.email = Email;
  		
  	}
  	public void setPassword(String Password) {
  		this.password = Password;

  	}
  	public void setAbout(String About) {
  		this.about= About;
  		
  	}
    
    
    
    
    
	

}
