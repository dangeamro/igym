package com.designer.controller.supplimentTracker;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts.action.ActionForm;
import com.designer.common.framework.ApplicationConfig;
import com.designer.model.supplimentTracker.SupplimentTracker;

@SuppressWarnings("serial")

public class SupplimentTrackerForm extends ActionForm{
	
	private SimpleDateFormat dtformat = null;

	private Integer supplimentId;
	
	private String userId;
	
	private Date date;
	
	private String isMonthly; 
	
	private Integer n_s_Complex;
	
	private Integer glutamine;
	
	private Integer m_s_oil;
	
	private Integer oxyOptimizer;
	
	private Integer m_s_complex;
	
	private Integer healthSupport;
	
	private Integer efaSupport;

	public SupplimentTrackerForm(){
		dtformat = new SimpleDateFormat(ApplicationConfig.getInstance()
				.getConfigValue("datePattern.long"));
	}
	
	public String getDate() {
		return dtformat.format(date);
	}

	public void setDate(String date) {
		try{
			dtformat.applyPattern("MM/dd/yyyy");
			this.date = dtformat.parse(date) ;
		}
		catch(ParseException pe)
		{
			// do nothing
		}
	}

	public Integer getEfaSupport() {
		return efaSupport;
	}

	public void setEfaSupport(Integer efaSupport) {
		this.efaSupport = efaSupport;
	}

	public Integer getGlutamine() {
		return glutamine;
	}

	public void setGlutamine(Integer glutamine) {
		this.glutamine = glutamine;
	}

	public Integer getHealthSupport() {
		return healthSupport;
	}

	public void setHealthSupport(Integer healthSupport) {
		this.healthSupport = healthSupport;
	}

	public Integer getM_s_complex() {
		return m_s_complex;
	}

	public void setM_s_complex(Integer m_s_complex) {
		this.m_s_complex = m_s_complex;
	}

	public Integer getM_s_oil() {
		return m_s_oil;
	}

	public void setM_s_oil(Integer m_s_oil) {
		this.m_s_oil = m_s_oil;
	}

	public Integer getN_s_Complex() {
		return n_s_Complex;
	}

	public void setN_s_Complex(Integer complex) {
		n_s_Complex = complex;
	}

	public Integer getOxyOptimizer() {
		return oxyOptimizer;
	}

	public void setOxyOptimizer(Integer oxyOptimizer) {
		this.oxyOptimizer = oxyOptimizer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsMonthly() {
		return isMonthly;
	}

	public void setIsMonthly(String isMonthly) {
		this.isMonthly = isMonthly;
	}

	public Integer getSupplimentId() {
		return supplimentId;
	}

	public void setSupplimentId(Integer supplimentId) {
		this.supplimentId = supplimentId;
	}
	

	public SupplimentTracker populateToModel()
	{
		SupplimentTracker tracker = new SupplimentTracker();
		if(date!=null){
		tracker.setDate(new Timestamp(date.getTime()));
		}
		tracker.setEfaSupport(efaSupport);
		tracker.setGlutamine(glutamine);
		tracker.setHealthSupport(healthSupport);
		tracker.setIsMonthly(isMonthly);
		tracker.setM_s_complex(m_s_complex);
		tracker.setM_s_oil(m_s_oil);
		tracker.setN_s_Complex(n_s_Complex);
		tracker.setOxyOptimizer(oxyOptimizer);
		tracker.setSupplimentId(supplimentId);
		tracker.setUserId(userId);
		
		return tracker;
	}
	
	public void populateFromModel(SupplimentTracker tracker)
	{
		date = tracker.getDate();
		efaSupport = tracker.getEfaSupport();
		glutamine = tracker.getGlutamine();
		healthSupport = tracker.getHealthSupport();
		isMonthly = tracker.getIsMonthly();
		m_s_complex = tracker.getM_s_complex();
		m_s_oil = tracker.getM_s_oil();
		n_s_Complex = tracker.getN_s_Complex();
		oxyOptimizer = tracker.getOxyOptimizer();
		supplimentId = tracker.getSupplimentId();
		userId = tracker.getUserId();
	}
	
}
