package com.designer.controller.customer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.designer.common.framework.DesignerUtils;
import com.designer.model.customer.SearchCustomer;

@SuppressWarnings("serial")
public class SearchCustomerForm extends ActionForm {
	
	private String firstName = null;
	private String lastName = null;
	private Date doj = null;
	private Date dos = null;
	private String emailAddress = null;
	private String courses = null;
	
	public SearchCustomerForm(){}
	
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
	public String getDos() {
		return DesignerUtils.formatDate(this.dos);
	}
	public void setDos(String dos) {
		this.dos = DesignerUtils.parseDate(dos, DesignerUtils.DATE_DEFAULT);
	}

	public String getDoj() {
		return DesignerUtils.formatDate(doj);
	}
	public void setDoj(String doj) {
		this.doj = DesignerUtils.parseDate(doj, DesignerUtils.DATE_DEFAULT);
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
	
	public SearchCustomer populateToModel(){
		SearchCustomer searchCustomer = new SearchCustomer();
				
		searchCustomer.setFirstName(this.firstName);
		searchCustomer.setLastName(this.lastName);
		searchCustomer.setDoj(DesignerUtils.toSqlDate(this.doj));
		searchCustomer.setDos(DesignerUtils.toSqlDate(this.dos));
		searchCustomer.setEmailAddress(this.emailAddress);
		searchCustomer.setCourses(this.courses);
		
		return searchCustomer;
	}
}
