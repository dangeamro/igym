package com.designer.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.designer.model.customer.ChangePassword;

@SuppressWarnings("serial")
public class ChangePasswordForm extends ActionForm {
		
	private String userId = null;
	private String oldPassword = null;
	private String newPassword = null;
	private String confirmPassword = null;
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		userId = null;
		oldPassword = null;
		newPassword = null;
		confirmPassword = null;
	}
	
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		
		ActionErrors errors = new ActionErrors();
		
		if(oldPassword == null || oldPassword.length() == 0){
			errors.add("oldPassword", new ActionMessage(
					"common.error.field.required"));
		}

		if(newPassword == null || newPassword.length() == 0){
			errors.add("newPassword", new ActionMessage(
					"common.error.field.required"));
		}
		else if(newPassword.length() < 6){
			errors.add("newPassword", new ActionMessage(
					"common.error.field.invalid.format"));
		}

		if (!confirmPassword.equals(newPassword)) {
			errors.add("confirmPassword", new ActionMessage(
					"user.error.password.mismatch"));
		}

		if(errors.size() > 0)
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.message.header"));
		
		return errors;
	}
	
	public ChangePassword populateToModel(){
		ChangePassword cp = new ChangePassword();
		cp.setUserId(this.userId);
		cp.setNewPassword(this.newPassword);
		
		return cp;
	}
	
}
