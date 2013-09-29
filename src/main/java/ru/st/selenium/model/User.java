package ru.st.selenium.model;

public class User {

	private String id;
	private String username;
	private String email;
	private String password;
	private String role;
	private String firstname;
	private String lastname;
	private String gender;
	private String birthday;
	private String country;
	private String city;
	private boolean isUsedAsMerchantManager;
	
	
	public String getId() {
		return id;
	}
	public User setId(String id) {
		this.id = id;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getRole() {
		return role;
	}
	public User setRole(String role) {
		this.role = role;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}
	public User setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}


	public String getLastname() {
		return lastname;
	}
	public User setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}



	public String getGender() {
		return gender;
	}
	public User setGender(String gender) {
		this.gender = gender;
		return this;
	}


	public String getBirthday() {
		return birthday;
	}
	public User setBirthday(String birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getCountry() {
		return country;
	}
	
	public User setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getCity() {
		return city;
	}
	public User setCity(String city) {
		this.city = city;
		return this;
	}

	public boolean getIsUsedAsMerchantManager() {
		return isUsedAsMerchantManager;
	}
	public User setIsUsedAsMerchantManager(boolean isUsedAsMerchantManager) {
		this.isUsedAsMerchantManager = isUsedAsMerchantManager;
		return this;
	}
}
