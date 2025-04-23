package com.tarunkumar.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Setter
@Getter

public class UserDto {
	
	private int Id;
	private String Name;
	private String Email;
	private String Password;
	private String About;
	
	//Getter Method
	public int getId() {
		return Id;
	}
	public String getName() {
		return Name;
	}
	public String getEmail() {
		return Email;
	}
	public String getPassword() {
		return Password;
		
	}
	public String getAbout() {
		return About;
	}

	//Setter Method
	public void setId(int Id) {
		this.Id=Id;
	}
	public void setName(String Name) {
		this.Name=Name;
	}
	public void setEmail(String Email) {
		this.Email = Email;
		
	}
	public void setPassword(String Password) {
		this.Password = Password;

	}
	public void setAbout(String About) {
		this.About= About;
		
	}

}
