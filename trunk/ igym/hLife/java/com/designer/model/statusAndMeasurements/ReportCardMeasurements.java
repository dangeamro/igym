package com.designer.model.statusAndMeasurements;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.designer.common.framework.DesignerUtils;

public class ReportCardMeasurements {
	
	private String usrId;
	private Date dateTime;
	private Double LeanBodyMass;
	private Double weight;
	private Double pbf;
	private Double basalMetabolicRate;
	private Double bmi;
	private Double height;
	private Double tbw;
	private Double fat;
	
	
	public Double getBasalMetabolicRate() {
		return basalMetabolicRate;
	}
	public void setBasalMetabolicRate(Double basalMetabolicRate) {
		this.basalMetabolicRate = basalMetabolicRate;
	}
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	public String getStrDateTime() {
		if(dateTime == null)
			return "NA";
		return new SimpleDateFormat("MM/dd/yyyy").format(dateTime);
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getLeanBodyMass() {
		return LeanBodyMass;
	}
	public void setLeanBodyMass(Double leanBodyMass) {
		LeanBodyMass = leanBodyMass;
	}
	public Double getPbf() {
		return pbf;
	}
	public void setPbf(Double pbf) {
		this.pbf = pbf;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getTbw() {
		return tbw;
	}
	public void setTbw(Double tbw) {
		this.tbw = tbw;
	}
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public Double getdryLeanMass() {
		return DesignerUtils.formatDouble(LeanBodyMass - tbw);
	}

}
