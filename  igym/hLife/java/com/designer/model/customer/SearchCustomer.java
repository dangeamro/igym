package com.designer.model.customer;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class SearchCustomer {
	private String firstName = null;
	private String lastName = null;
	private Date doj = null;
	private Date dos = null;
	private String emailAddress = null;
	private String courses = null;
	
	public SearchCustomer(){}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.firstName = null;
		this.lastName = null;
		this.dos = null;
		this.doj = null;
		this.courses = null;
		this.emailAddress = null;
	}
	
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	public Date getDos() {
		return dos;
	}
	public void setDos(Date dos) {
		this.dos = dos;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
