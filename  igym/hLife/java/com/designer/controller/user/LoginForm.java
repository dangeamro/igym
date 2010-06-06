package com.designer.controller.user;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class LoginForm extends ActionForm {
	
	private String loginId = null;
	
	private String password = null;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
