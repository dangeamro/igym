package com.designer.model;

public class User {

	private String loginId = null;
	
	private String password = null;
	
	private String loginType = null;
	
	private String userFirstName = null;
	
	private String userLastName = null;

	// Constructors
	public User(){ 	}
	
	public User(String loginId, String password, String loginType,
			String userFirstName, String userLastName) {
		this.loginId = loginId;
		this.password = password;
		this.loginType = loginType;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
	}
	
	// Getters and setters
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	
	
}
