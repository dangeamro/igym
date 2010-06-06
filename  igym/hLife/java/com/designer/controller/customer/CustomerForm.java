package com.designer.controller.customer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.designer.common.framework.DesignerUtils;
import com.designer.common.framework.UserMap;
import com.designer.model.customer.Customer;

@SuppressWarnings("serial")
public class CustomerForm extends ActionForm {
	
	private String userId = null;
	private String password = null;
	private String confirmPassword = null;	
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
	private boolean locked = false;
	private String hintQuestion = null;
	private String answer = null;
	private Timestamp lastAccessDateTime = null;
	
	private Integer protienPercentage = null;
	private Integer carbohydratesPercentage = null;
	private Integer fatPercentage = null;
	private Integer otherPercentage = null;
	private Double suggestedCalorieConsumption = null;
	
	private boolean updatePage = false;
	private boolean passwordSet = false;
	
	
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

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getDob() {
		return DesignerUtils.formatDate(dob);
	}
	
	public void setDob(String strDate) {
		this.dob = DesignerUtils.parseDate(strDate, DesignerUtils.DATE_DEFAULT);
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
	
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	public String getPassword() {
		return null;
	}
	public String getPasswordForDatabase() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		passwordSet = true;
	}
	
	public String getConfirmPassword() {
		return null;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String contactNumber) {
		this.phoneNumber = contactNumber;
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
	public String getCreationDate() {
		return DesignerUtils.formatDate(creationDate);
	}
	
	public void setCreationDate(String strCreationDate) {
		this.creationDate = DesignerUtils.parseDate(strCreationDate, DesignerUtils.DATE_DEFAULT);
	}
	
	public String getLastModifiedDate() {
		return DesignerUtils.formatDate(lastModifiedDate);
	}

	public void setLastModifiedDate(String strLastModifiedDate) {
		this.lastModifiedDate = DesignerUtils.parseDate(strLastModifiedDate, DesignerUtils.DATE_DEFAULT);
	}
	
	public String getDoj() {
		return DesignerUtils.formatDate(doj);
	}
	public void setDoj(String strDate) {
		this.doj = DesignerUtils.parseDate(strDate, DesignerUtils.DATE_DEFAULT);
	}
	
	public String getDos() {
		return DesignerUtils.formatDate(dos);
	}
	public void setDos(String strDos) {
		this.dos = DesignerUtils.parseDate(strDos, DesignerUtils.DATE_DEFAULT);
	}

	public String getLastAccessDateTime() {
		return DesignerUtils.formatDate(lastAccessDateTime,DesignerUtils.DATE_TIME);
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
	public void setSuggestedCalorieConsumption(Double calorieConsumption) {
		this.suggestedCalorieConsumption = calorieConsumption;
	}
	public Integer getDurationInWeeks() {
		return durationInWeeks;
	}
	public void setDurationInWeeks(Integer durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}
	public boolean isUpdatePage() {
		return updatePage;
	}
	public void setUpdatePage(boolean updatePage) {
		this.updatePage = updatePage;
	}
	public boolean isPasswordSet() {
		return passwordSet;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.userId = null;
		this.password = null;
		this.firstName = null;
		this.lastName = null;
		this.dob = null;
		this.doj = null;
		this.dos = null;
		this.gender = null;
		this.courses = null;
		this.phoneNumber = null;
		this.mobileNumber = null;
		this.emailAddress = null;
		this.role = null;
		this.locked = false;
		this.hintQuestion = null;
		this.answer = null;
		this.lastAccessDateTime = null;
		this.protienPercentage = null;
		this.fatPercentage = null;
		this.carbohydratesPercentage = null;
		this.otherPercentage = null;
		this.suggestedCalorieConsumption = null;
		super.reset(mapping, request);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if (userId == null || userId.length() == 0) {
			errors.add("userId", new ActionMessage(
					"common.error.field.required"));
		}
		else if(userId.length() > 20){
			errors.add("userId", new ActionMessage(
					"common.error.unacceptable.size"));
		}
		else if(!updatePage && UserMap.getInstance().contains(userId)){
			errors.add("userId", new ActionMessage(
					"error.duplicate.User.Id"));
		}

		if(!updatePage || passwordSet)
		{
			if(password == null || password.length() == 0){
				errors.add("password", new ActionMessage(
						"common.error.field.required"));
			}
			else if(!isValidPassword(password)){
				errors.add("password", new ActionMessage(
						"common.error.field.invalid.format"));
			}
	
			if (!confirmPassword.equals(password)) {
				errors.add("confirmPassword", new ActionMessage(
						"user.error.password.mismatch"));
			}
		}

		if (firstName == null || firstName.length() == 0) {
			errors.add("firstName", new ActionMessage(
					"common.error.field.required"));
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("lastName", new ActionMessage(
					"common.error.field.required"));
		}
		if(emailAddress == null || emailAddress.length() == 0 ){
			errors.add("emailAddress", new ActionMessage(
					"common.error.field.required"));
		}else if(!isValidEmail(emailAddress)){
			errors.add("emailAddress", new ActionMessage(
					"common.error.field.invalid.format"));
		}
		Boolean hintQuestionValid = new Boolean(true);
		
		if(hintQuestion == null || hintQuestion.length() == 0 ){
			hintQuestionValid = false;
			errors.add("hintQuestion", new ActionMessage(
					"common.error.field.required"));
		}
		
		if(hintQuestionValid){
			if(answer == null || answer.length() == 0){
				errors.add("answer", new ActionMessage(
						"common.error.field.required"));
			}else if(answer.length() > 50){
				errors.add("answer", new ActionMessage(
						"common.error.unacceptable.size"));
			}
			
		}
		
		
		//The following validations apply only to customer users
		if(!role.equalsIgnoreCase("admin"))
		{
			if (dob == null) {
				errors.add("dob", new ActionMessage(
						"common.error.field.required"));
			}else if(dob.compareTo(new Date()) >= 0)  
				errors.add("dob", new ActionMessage(
					"common.error.future.date"));
	
			if (doj == null) {
				errors.add("doj", new ActionMessage(
						"common.error.field.required"));
			}
	
			if (dos == null) {
				errors.add("dos", new ActionMessage(
						"common.error.field.required"));
			}
			
			if (durationInWeeks == null || durationInWeeks == 0) {
				errors.add("durationInWeeks", new ActionMessage(
						"common.error.field.required"));
			}else if(durationInWeeks > 999){
				errors.add("durationInWeeks", new ActionMessage(
				"common.error.unacceptable.size"));
			}
			
			Integer aggrPercentage = 0;
			
			if(protienPercentage != null)
				aggrPercentage = protienPercentage + fatPercentage + carbohydratesPercentage + otherPercentage;
			
			if(aggrPercentage != 0 && aggrPercentage != 100)
				errors.add("aggrPercentage", new ActionMessage(
								"error.message.invalid.percentage.aggregate"));
		}
		
		if(errors.size() > 0)
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.message.header"));
		
		return errors;
	}
	
	
	/**
	 * Checks a emailId for general email validations.
	 * 
	 * @param emailAddress the email string to be validated.
	 */
	private boolean isValidEmail(String emailAddress){
		//the email pattern string
		String emailPattern = "[a-zA-Z0-9]([_/./-]?[a-zA-Z0-9])*[@][a-zA-Z0-9]([-/_]?[a-zA-Z0-9])*[.][a-zA-Z]([.]?[a-zA-Z])*";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(emailAddress);
		
		return matcher.matches();		
	}

	/**
	 * Tests validity of the password string. Performs following checks:<ol>
	 * <li>Password length between {8,40} (both inclusive).</li>
	 * <li>Password is alphanumeric(special characters & white spaces also allowed).</li>
	 * <li>Password contains at least one digit.</li>
	 * <li>Password doesn't starts or ends with white spaces.</li>
	 * <li>No character in the password occurs more than 2 times.</li></ol>
	 * 
	 * @param password The password string to be validated.
	 */
	private boolean isValidPassword(String password){
		
		int minAllowablePwdLength = 6;
		int maxAllowablePwdLength = 20;
		if(password.length() < minAllowablePwdLength || 
				password.length() >maxAllowablePwdLength)
			return false;	//password length not proper.
		
		// The following section is commented because Dwain doesn't wants strict checking on password.
		/* 
		String passwordPattern = "\\S(.)*\\d+(.)*\\S";
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(password);
		
		if(!matcher.matches()){
			return false; //password pattern not proper.
		}else{
			char[] pwdToCharArray = password.toCharArray();

			for (int i = 0; i<password.length();i++){
				int occurrence = 0;
				occurrence = password.length() - password.replaceAll(""+ pwdToCharArray[i],"").length();
				if(occurrence > 2)
					return false; //Any character in password occurs more than 2 times.
				
			}
		}*/

		return true;
	}

	public Customer populateToModel(){
		Customer customer = new Customer();
		
		customer.setUserId(this.userId);
		customer.setPassword(this.password);
		customer.setFirstName(this.firstName);
		customer.setLastName(this.lastName);
		customer.setPhoneNumber(this.phoneNumber);
		customer.setMobileNumber(this.mobileNumber);
		customer.setEmailAddress(this.emailAddress);
		customer.setGender(this.gender);
		customer.setCourses(this.courses);
		customer.setAddress(this.address);
		customer.setRole(this.role);
		customer.setLocked((this.locked)?"Y":"N");
		customer.setHintQuestion(this.hintQuestion);
		customer.setAnswer(this.answer);
		customer.setDoj(this.doj);
		customer.setDob(this.dob);
		customer.setDos(this.dos);
		customer.setDurationInWeeks(durationInWeeks);
		customer.setProtienPercentage(protienPercentage);
		customer.setFatPercentage(fatPercentage);
		customer.setCarbohydratesPercentage(carbohydratesPercentage);
		customer.setOtherPercentage(otherPercentage);
		customer.setSuggestedCalorieConsumption(suggestedCalorieConsumption);
		
		return customer;
	}

	public void populateFromModel(Customer customer){
		
		this.userId = customer.getUserId();
		this.password = customer.getPassword();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.mobileNumber = customer.getMobileNumber();
		this.phoneNumber = customer.getPhoneNumber();
		this.emailAddress = customer.getEmailAddress();
		this.gender = customer.getGender();
		this.courses = customer.getCourses();
		this.address = customer.getAddress();
		this.role = customer.getRole();
		this.locked = (customer.getLocked().equalsIgnoreCase("Y"))? true : false;
		this.hintQuestion = customer.getHintQuestion();
		this.answer = customer.getAnswer();
		this.dob = customer.getDob();
		this.doj = customer.getDoj();
		this.dos = customer.getDos();
		this.durationInWeeks = customer.getDurationInWeeks();
		this.creationDate = customer.getCreationDate();
		this.lastModifiedDate = customer.getLastModifiedDate();
		this.lastAccessDateTime = customer.getLastAccessDateTime();
		this.protienPercentage = customer.getProtienPercentage();
		this.fatPercentage = customer.getFatPercentage();
		this.carbohydratesPercentage = customer.getCarbohydratesPercentage();
		this.otherPercentage = customer.getOtherPercentage();
		this.suggestedCalorieConsumption = customer.getSuggestedCalorieConsumption();
	}
}
