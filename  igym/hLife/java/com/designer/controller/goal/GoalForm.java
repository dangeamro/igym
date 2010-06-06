package com.designer.controller.goal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.designer.model.goal.Goal;

@SuppressWarnings("serial")
public class GoalForm extends ActionForm {
	
	private long miliPerDay = 1000*60*60*24; 
	private long miliPerWeek = 1000*60*60*24*7;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	private String userId = null;
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
	private Date createdOn = null;
	private Date lastModifiedOn = null;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getGoalId() {
		return goalId;
	}
	public void setGoalId(Integer goalId) {
		this.goalId = goalId;
	}
	public String getEndDate() {
		if(startDate != null)
			return dateFormat.format(endDate);
		
		return "";
	}
	public void setEndDate(String endDate) {
		try
		{
			this.endDate = dateFormat.parse(endDate);
		}
		catch(ParseException pe)
		{
			this.endDate = null;
		}
	}
	public Double getEndReading() {
		return endReading;
	}
	public void setEndReading(Double endReading) {
		this.endReading = endReading;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public String getStartDate() {
		
		if(startDate != null)
			return dateFormat.format(startDate);

		return ""; 
	}
	public void setStartDate(String startDate) {
		try
		{
			this.startDate = dateFormat.parse(startDate);
		}
		catch(ParseException pe)
		{
			this.startDate = null;
		}
	}
	public Double getStartReading() {
		return startReading;
	}
	public void setStartReading(Double startReading) {
		this.startReading = startReading;
	}
	public String getReadingUnit() {
		return readingUnit;
	}
	public void setReadingUnit(String readingUnit) {
		this.readingUnit = readingUnit;
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
	public Integer getFatPercentage() {
		return fatPercentage;
	}
	public void setFatPercentage(Integer fatPercentage) {
		this.fatPercentage = fatPercentage;
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
	public Integer getCalorieCunsumptionPerDay(){
		return caloriesBurntPerDay + calorieRestrictionPerDay;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public String getNumberOfDays(){
		
		return String.valueOf((endDate.getTime() - startDate.getTime())/miliPerDay) + " days";
	}
	
	public String getCompletedDays(){
		java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
		
		if(endDate.compareTo(today)<0)
			return "Full Duration";
		
		return String.valueOf((today.getTime() - startDate.getTime())/miliPerDay) + " days";
	}

	public String getRemainingDays(){
		java.sql.Date today = new java.sql.Date(System.currentTimeMillis());		

		if(endDate.compareTo(today)<0)
			return "NA";

		return String.valueOf((endDate.getTime() - today.getTime())/miliPerDay) + " days";
	}
	
	public String getTotalChange() {
		if(startReading > endReading)
			return  (startReading-endReading) + " " + readingUnit + " loss";
		else
			return  (endReading - startReading) + " " + readingUnit + " gain";
	}

	public String getAvgDailyChange() {
		Long avgDailyChange = Math.round((endReading - startReading)/((endDate.getTime() - startDate.getTime())/miliPerDay));
		
		return String.valueOf(avgDailyChange);
		
	}

	public String getAvgWeeklyChange() {
		Long avgWeeklyChange = Math.round((startReading-endReading)/((endDate.getTime() - startDate.getTime())/miliPerWeek));
		
		return String.valueOf(avgWeeklyChange);
	}

	public void populateFromModel(Goal goal) {
		this.userId = goal.getUserId();
		this.goalId = goal.getGoalId();
		this.goalName = goal.getGoalName();
		this.startReading = goal.getStartReading();
		this.endReading = goal.getEndReading();
		this.readingUnit = goal.getReadingUnit();
		this.startDate = goal.getStartDate();
		this.endDate =  goal.getEndDate();
		this.calorieRestrictionPerDay = goal.getCalorieRestrictionPerDay();
		this.caloriesBurntPerDay = goal.getCaloriesBurntPerDay();
		this.protienPercentage = goal.getProtienPercentage();
		this.fatPercentage = goal.getFatPercentage();
		this.carbohydratesPercentage = goal.getCarbohydratesPercentage();
		this.otherNutrientPercentage = goal.getOtherNutrientPercentage();
		this.createdOn =  goal.getCreatedOn();
		this.lastModifiedOn = goal.getLastModifiedOn();
	}

	public Goal populateToModel() {
		Goal goal = new Goal();
		
		goal.setGoalId(goalId);
		goal.setUserId(userId);
		goal.setGoalName(goalName);
		goal.setStartReading(startReading);
		goal.setEndReading(endReading);
		goal.setReadingUnit(readingUnit);
		goal.setStartDate(new java.sql.Date(startDate.getTime()));
		goal.setEndDate(new java.sql.Date(endDate.getTime()));
		goal.setCalorieRestrictionPerDay(calorieRestrictionPerDay);
		goal.setCaloriesBurntPerDay(caloriesBurntPerDay);
		goal.setProtienPercentage(protienPercentage);
		goal.setFatPercentage(fatPercentage);
		goal.setCarbohydratesPercentage(carbohydratesPercentage);
		goal.setOtherNutrientPercentage(otherNutrientPercentage);
		return goal;
	}
}
