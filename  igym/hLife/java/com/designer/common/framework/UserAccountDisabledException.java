package com.designer.common.framework;

@SuppressWarnings("serial")
public class UserAccountDisabledException extends ApplicationException {

	public UserAccountDisabledException(){
		super("User account is disabled. Please contact the Administrator");
	}

}
