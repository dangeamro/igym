package com.designer.model.foodJournal;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ViewMealModel {
	
	int mealId;
	int mealType;
	Timestamp date;
	String time;
	
	String energyLevel;
	String appetite;
	String mood;
	String digestiveState;
	String bowelMovement;
	
	ArrayList<FoodItemModel> foodMealList = null;
	
	NonFoodModel nonFoodMeal = null;
	
	ManualFoodModel manualFoodModel = null;

	/**
	 * @return the appetite
	 */
	public String getAppetite() {
		return appetite;
	}

	/**
	 * @param appetite the appetite to set
	 */
	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}

	/**
	 * @return the bowelMovement
	 */
	public String getBowelMovement() {
		return bowelMovement;
	}

	/**
	 * @param bowelMovement the bowelMovement to set
	 */
	public void setBowelMovement(String bowelMovement) {
		this.bowelMovement = bowelMovement;
	}



	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the digestiveState
	 */
	public String getDigestiveState() {
		return digestiveState;
	}

	/**
	 * @param digestiveState the digestiveState to set
	 */
	public void setDigestiveState(String digestiveState) {
		this.digestiveState = digestiveState;
	}

	/**
	 * @return the energyLevel
	 */
	public String getEnergyLevel() {
		return energyLevel;
	}

	/**
	 * @param energyLevel the energyLevel to set
	 */
	public void setEnergyLevel(String energyLevel) {
		this.energyLevel = energyLevel;
	}

	/**
	 * @return the foodMealList
	 */
	public ArrayList<FoodItemModel> getFoodMealList() {
		return foodMealList;
	}

	/**
	 * @param foodMealList the foodMealList to set
	 */
	public void setFoodMealList(ArrayList<FoodItemModel> foodMealList) {
		this.foodMealList = foodMealList;
	}

	/**
	 * @return the mealId
	 */
	public int getMealId() {
		return mealId;
	}

	/**
	 * @param mealId the mealId to set
	 */
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	/**
	 * @return the mealType
	 */
	public int getMealType() {
		return mealType;
	}

	/**
	 * @param mealType the mealType to set
	 */
	public void setMealType(int mealType) {
		this.mealType = mealType;
	}

	/**
	 * @return the mood
	 */
	public String getMood() {
		return mood;
	}

	/**
	 * @param mood the mood to set
	 */
	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	 * @return the nonFoodMeal
	 */
	public NonFoodModel getNonFoodMeal() {
		return nonFoodMeal;
	}

	/**
	 * @param nonFoodMeal the nonFoodMeal to set
	 */
	public void setNonFoodMeal(NonFoodModel nonFoodMeal) {
		this.nonFoodMeal = nonFoodMeal;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the manualFoodModel
	 */
	public ManualFoodModel getManualFoodModel() {
		return manualFoodModel;
	}

	/**
	 * @param manualFoodModel the manualFoodModel to set
	 */
	public void setManualFoodModel(ManualFoodModel manualFoodModel) {
		this.manualFoodModel = manualFoodModel;
	}
	
	
	

}
