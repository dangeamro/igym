package com.designer.model.customer;

import java.util.Date;
import java.sql.Timestamp;


public class Customer {

	private String userId = null;
	private String password = null;
	private String firstName = null;
	private String lastName = null;
	private String gender = null;
	private Date doj = null;
	private Date dob = null;
	private Date dos = null;
	private Integer durationInWeeks = null;
	private String phoneNumber = null;
	private String mobileNumber = null;
	private String emailAddress = null;
	private String courses = null;
	private String address = null;
	private Date creationDate = null;
	private Date lastModifiedDate = null;
	private String role = null;
	private String locked = null;
	private String hintQuestion = null;
	private String answer = null;
	private Timestamp lastAccessDateTime = null;
	 
	private Integer protienPercentage = null;
	private Integer carbohydratesPercentage = null;
	private Integer fatPercentage = null;
	private Integer otherPercentage = null;
	private Double suggestedCalorieConsumption = null;

	
	//~-----	Constructors
	
	public Customer(){};
	
	//~----		Getters & Setters
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}

	public boolean isAdmin() {
		return role.equalsIgnoreCase("admin");
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDos() {
		return dos;
	}

	public void setDos(Date dos) {
		this.dos = dos;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getHintQuestion() {
		return hintQuestion;
	}
	
	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Timestamp getLastAccessDateTime() {
		return lastAccessDateTime;
	}

	public void setLastAccessDateTime(Timestamp lastAccessDateTime) {
		this.lastAccessDateTime = lastAccessDateTime;
	}

	public Integer getCarbohydratesPercentage() {
		return carbohydratesPercentage;
	}

	public void setCarbohydratesPercentage(Integer carbohydratesPercentage) {
		this.carbohydratesPercentage = carbohydratesPercentage;
	}

	public Integer getFatPercentage() {
		return fatPercentage;
	}

	public void setFatPercentage(Integer fatPercentage) {
		this.fatPercentage = fatPercentage;
	}

	public Integer getOtherPercentage() {
		return otherPercentage;
	}

	public void setOtherPercentage(Integer otherPercentage) {
		this.otherPercentage = otherPercentage;
	}

	public Integer getProtienPercentage() {
		return protienPercentage;
	}

	public void setProtienPercentage(Integer protienPercentage) {
		this.protienPercentage = protienPercentage;
	}

	public Double getSuggestedCalorieConsumption() {
		return suggestedCalorieConsumption;
	}

	public void setSuggestedCalorieConsumption(Double suggestedCalorieConsumption) {
		this.suggestedCalorieConsumption = suggestedCalorieConsumption;
	}

	public Integer getDurationInWeeks() {
		return durationInWeeks;
	}

	public void setDurationInWeeks(Integer durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}


	
}
