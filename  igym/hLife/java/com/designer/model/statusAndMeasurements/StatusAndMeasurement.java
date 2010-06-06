package com.designer.model.statusAndMeasurements;


import java.sql.Timestamp;

public class StatusAndMeasurement{
	
	private String userId;
	
	private Integer statusId;
	
	private Timestamp date;
	
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}	
}
