package com.designer.model.goal;

import java.sql.Date;
import java.sql.Timestamp;


public class Goal {
	private String UserId = null;
	private Integer goalId = null;
	private String goalName = null;
	private Double startReading = null;
	private Double endReading = null;
	private String readingUnit = null;
	private Date startDate = null;
	private Date endDate = null;
	private Integer calorieRestrictionPerDay = null;
	private Integer caloriesBurntPerDay = null;
	private Integer protienPercentage = null;
	private Integer fatPercentage = null;
	private Integer carbohydratesPercentage = null;
	private Integer otherNutrientPercentage = null;
	private Timestamp createdOn = null;
	private Timestamp lastModifiedOn = null;
	
	
	
	public Integer getGoalId() {
		return goalId;
	}
	public void setGoalId(Integer goalId) {
		this.goalId = goalId;
	}
	public Integer getCalorieRestrictionPerDay() {
		return calorieRestrictionPerDay;
	}
	public void setCalorieRestrictionPerDay(Integer calorieRestrictionPerDay) {
		this.calorieRestrictionPerDay = calorieRestrictionPerDay;
	}
	public Integer getCaloriesBurntPerDay() {
		return caloriesBurntPerDay;
	}
	public void setCaloriesBurntPerDay(Integer caloriesBurntPerDay) {
		this.caloriesBurntPerDay = caloriesBurntPerDay;
	}
	public Integer getCarbohydratesPercentage() {
		return carbohydratesPercentage;
	}
	public void setCarbohydratesPercentage(Integer carbohydratesPercentage) {
		this.carbohydratesPercentage = carbohydratesPercentage;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getEndReading() {
		return endReading;
	}
	public void setEndReading(Double endReading) {
		this.endReading = endReading;
	}
	public Integer getFatPercentage() {
		return fatPercentage;
	}
	public void setFatPercentage(Integer fatPercentage) {
		this.fatPercentage = fatPercentage;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public Integer getOtherNutrientPercentage() {
		return otherNutrientPercentage;
	}
	public void setOtherNutrientPercentage(Integer otherNutrientPercentage) {
		this.otherNutrientPercentage = otherNutrientPercentage;
	}
	public Integer getProtienPercentage() {
		return protienPercentage;
	}
	public void setProtienPercentage(Integer protienPercentage) {
		this.protienPercentage = protienPercentage;
	}
	public String getReadingUnit() {
		return readingUnit;
	}
	public void setReadingUnit(String readingUnit) {
		this.readingUnit = readingUnit;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Double getStartReading() {
		return startReading;
	}
	public void setStartReading(Double startReading) {
		this.startReading = startReading;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Timestamp lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	
}
