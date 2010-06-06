package com.designer.model.supplimentTracker;


import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")

public class SupplimentTracker extends ActionForm{
	
	private Integer supplimentId;
	
	private String userId;
	
	private Timestamp date;
	
	private String isMonthly; 
	
	private Integer n_s_Complex;
	
	private Integer glutamine;
	
	private Integer m_s_oil;
	
	private Integer oxyOptimizer;
	
	private Integer m_s_complex;
	
	private Integer healthSupport;
	
	private Integer efaSupport;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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
}
