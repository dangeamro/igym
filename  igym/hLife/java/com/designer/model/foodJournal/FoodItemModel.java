package com.designer.model.foodJournal;

public class FoodItemModel {
	int itemId;
	String itemName =null;
	Double calories;
	Double fats;
	Double protiens;
	Double carbohydrates;
	Double dietryFibre;
	Double sugar;
	Double servings;
	int mealType;
	String colour;
	
	private String userId = null;
	private String mealTime	 = null;
	private String mealDate =  null;
	private String mealIdList;
	
	
	 
	
	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getMealIdList() {
		return mealIdList;
	}
	public void setMealIdList(String mealIdList) {
		this.mealIdList = mealIdList;
	}
	/**
	 * @return the calories
	 */
	public Double getCalories() {
		return calories;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(Double calories) {
		this.calories = calories;
	}
	/**
	 * @return the carbohydrates
	 */
	public Double getCarbohydrates() {
		return carbohydrates;
	}
	/**
	 * @param carbohydrates the carbohydrates to set
	 */
	public void setCarbohydrates(Double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	/**
	 * @return the dietryFibre
	 */
	public Double getDietryFibre() {
		return dietryFibre;
	}
	/**
	 * @param dietryFibre the dietryFibre to set
	 */
	public void setDietryFibre(Double dietryFibre) {
		this.dietryFibre = dietryFibre;
	}
	/**
	 * @return the fats
	 */
	public Double getFats() {
		return fats;
	}
	/**
	 * @param fats the fats to set
	 */
	public void setFats(Double fats) {
		this.fats = fats;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the protiens
	 */
	public Double getProtiens() {
		return protiens;
	}
	/**
	 * @param protiens the protiens to set
	 */
	public void setProtiens(Double protiens) {
		this.protiens = protiens;
	}
	/**
	 * @return the servings
	 */
	public Double getServings() {
		return servings;
	}
	/**
	 * @param servings the servings to set
	 */
	public void setServings(Double servings) {
		this.servings = servings;
	}
	/**
	 * @return the sugar
	 */
	public Double getSugar() {
		return sugar;
	}
	/**
	 * @param sugar the sugar to set
	 */
	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getMealDate() {
		return mealDate;
	}
	public void setMealDate(String mealDate) {
		this.mealDate = mealDate;
	}
	public String getMealTime() {
		return mealTime;
	}
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	public int getMealType() {
		return mealType;
	}
	public void setMealType(int mealType) {
		this.mealType = mealType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
