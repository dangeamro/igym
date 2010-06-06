package com.designer.controller.statusAndMeasurement;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.designer.common.framework.ApplicationConfig;
import com.designer.model.statusAndMeasurements.StatusAndMeasurement;

@SuppressWarnings("serial")

public class StatusAndMeasurementForm extends ActionForm{
	
	private SimpleDateFormat dtformat = null;
	
	private String userId;
	
	private Integer statusId;
	
	private Date date;
	
	private Double weight;
	
	private Double neck;
	
	private Double upperChest;
	
	private Double uppArmRight;
	
	private Double uppArmLeft;
	
	private Double waist;
	
	private Double hips;
	
	private Double uppThighsRight;
	
	private Double uppThighsLeft;
	
	private Double lowThighsRight;
	
	private Double lowThighsLeft;
	
	private Double calvesRight;
	
	private Double calvesLeft;
	
	private Double anklesRight;
	
	private Double anklesLeft;

	public StatusAndMeasurementForm()
	{
		dtformat = new SimpleDateFormat(ApplicationConfig.getInstance()
				.getConfigValue("datePattern.long"));
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the anklesLeft
	 */
	public Double getAnklesLeft() {
		return anklesLeft;
	}

	/**
	 * @param anklesLeft the anklesLeft to set
	 */
	public void setAnklesLeft(Double anklesLeft) {
		this.anklesLeft = anklesLeft;
	}

	/**
	 * @return the anklesRight
	 */
	public Double getAnklesRight() {
		return anklesRight;
	}

	/**
	 * @param anklesRight the anklesRight to set
	 */
	public void setAnklesRight(Double anklesRight) {
		this.anklesRight = anklesRight;
	}

	/**
	 * @return the calvesLeft
	 */
	public Double getCalvesLeft() {
		return calvesLeft;
	}

	/**
	 * @param calvesLeft the calvesLeft to set
	 */
	public void setCalvesLeft(Double calvesLeft) {
		this.calvesLeft = calvesLeft;
	}

	/**
	 * @return the calvesRight
	 */
	public Double getCalvesRight() {
		return calvesRight;
	}

	/**
	 * @param calvesRight the calvesRight to set
	 */
	public void setCalvesRight(Double calvesRight) {
		this.calvesRight = calvesRight;
	}

	/**
	 * @return the hips
	 */
	public Double getHips() {
		return hips;
	}

	/**
	 * @param hips the hips to set
	 */
	public void setHips(Double hips) {
		this.hips = hips;
	}

	/**
	 * @return the lowThighsLeft
	 */
	public Double getLowThighsLeft() {
		return lowThighsLeft;
	}

	/**
	 * @param lowThighsLeft the lowThighsLeft to set
	 */
	public void setLowThighsLeft(Double lowThighsLeft) {
		this.lowThighsLeft = lowThighsLeft;
	}

	/**
	 * @return the lowThighsRight
	 */
	public Double getLowThighsRight() {
		return lowThighsRight;
	}

	/**
	 * @param lowThighsRight the lowThighsRight to set
	 */
	public void setLowThighsRight(Double lowThighsRight) {
		this.lowThighsRight = lowThighsRight;
	}

	/**
	 * @return the neck
	 */
	public Double getNeck() {
		return neck;
	}

	/**
	 * @param neck the neck to set
	 */
	public void setNeck(Double neck) {
		this.neck = neck;
	}

	/**
	 * @return the uppArmLeft
	 */
	public Double getUppArmLeft() {
		return uppArmLeft;
	}

	/**
	 * @param uppArmLeft the uppArmLeft to set
	 */
	public void setUppArmLeft(Double uppArmLeft) {
		this.uppArmLeft = uppArmLeft;
	}

	/**
	 * @return the uppArmRight
	 */
	public Double getUppArmRight() {
		return uppArmRight;
	}

	/**
	 * @param uppArmRight the uppArmRight to set
	 */
	public void setUppArmRight(Double uppArmRight) {
		this.uppArmRight = uppArmRight;
	}

	/**
	 * @return the upperChest
	 */
	public Double getUpperChest() {
		return upperChest;
	}

	/**
	 * @param upperChest the upperChest to set
	 */
	public void setUpperChest(Double upperChest) {
		this.upperChest = upperChest;
	}

	/**
	 * @return the uppThighsLeft
	 */
	public Double getUppThighsLeft() {
		return uppThighsLeft;
	}

	/**
	 * @param uppThighsLeft the uppThighsLeft to set
	 */
	public void setUppThighsLeft(Double uppThighsLeft) {
		this.uppThighsLeft = uppThighsLeft;
	}

	/**
	 * @return the uppThighsRight
	 */
	public Double getUppThighsRight() {
		return uppThighsRight;
	}

	/**
	 * @param uppThighsRight the uppThighsRight to set
	 */
	public void setUppThighsRight(Double uppThighsRight) {
		this.uppThighsRight = uppThighsRight;
	}

	/**
	 * @return the waist
	 */
	public Double getWaist() {
		return waist;
	}

	/**
	 * @param waist the waist to set
	 */
	public void setWaist(Double waist) {
		this.waist = waist;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
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
	
	public void populateFromModel(StatusAndMeasurement sm)
	{
		userId = sm.getUserId();
		statusId = sm.getStatusId();
		date = sm.getDate();
		weight = sm.getWeight();
		neck = sm.getNeck();
		upperChest = sm.getUpperChest();
		uppArmRight = sm.getUppArmRight();
		uppArmLeft = sm.getUppArmLeft();
		waist = sm.getWaist();
		hips = sm.getHips();
		uppThighsRight = sm.getUppThighsRight();
		uppThighsLeft = sm.getUppThighsLeft();
		lowThighsRight = sm.getLowThighsRight();
		lowThighsLeft = sm.getLowThighsLeft();
		calvesRight = sm.getCalvesRight();
		calvesLeft = sm.getCalvesLeft();
		anklesRight = sm.getAnklesRight();
		anklesLeft = sm.getAnklesLeft();
	}
	public StatusAndMeasurement populateToModel()
	{
		StatusAndMeasurement sm = new StatusAndMeasurement();
		sm.setUserId(userId);
		sm.setStatusId(statusId);
		sm.setDate(new Timestamp(date.getTime()));
		sm.setWeight(weight);
		sm.setNeck(neck);
		sm.setUpperChest(upperChest);
		sm.setUppArmRight(uppArmRight);
		sm.setUppArmLeft(uppArmLeft);
		sm.setWaist(waist);
		sm.setHips(hips);
		sm.setUppThighsRight(uppThighsRight);
		sm.setUppThighsLeft(uppThighsLeft);
		sm.setLowThighsRight(lowThighsRight);
		sm.setLowThighsLeft(lowThighsLeft);
		sm.setCalvesRight(calvesRight);
		sm.setCalvesLeft(calvesLeft);
		sm.setAnklesRight(anklesRight);
		sm.setAnklesLeft(anklesLeft);
		
		return sm;
	}
}
