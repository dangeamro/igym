package com.designer.model.foodJournal;

public class MealNutrientSummaryModel {	
	
	public Double totalCalories;
	public Double totalFats;
	public Double totalProtien;
	public Double totalCarbohydrates;
	public Double totalDietoryFibres;
	public Double totalSugar;
	
	public Double totalCaloriesPcnt;
	public Double totalFatsPcnt;
	public Double totalProtienPcnt;
	public Double totalCarbohydratesPcnt;
	public Double othersPcnt;
	
	
	
	/**
	 * @return the totalCaloriesPcnt
	 */
	public Double getTotalCaloriesPcnt() {
		return totalCaloriesPcnt;
	}
	/**
	 * @param totalCaloriesPcnt the totalCaloriesPcnt to set
	 */
	public void setTotalCaloriesPcnt(Double totalCaloriesPcnt) {
		this.totalCaloriesPcnt = totalCaloriesPcnt;
	}
	/**
	 * @return the totalCarbohydratesPcnt
	 */
	public Double getTotalCarbohydratesPcnt() {
		return totalCarbohydratesPcnt;
	}
	/**
	 * @param totalCarbohydratesPcnt the totalCarbohydratesPcnt to set
	 */
	public void setTotalCarbohydratesPcnt(Double totalCarbohydratesPcnt) {
		this.totalCarbohydratesPcnt = totalCarbohydratesPcnt;
	}
	/**
	 * @return the totalFatsPcnt
	 */
	public Double getTotalFatsPcnt() {
		return totalFatsPcnt;
	}
	/**
	 * @param totalFatsPcnt the totalFatsPcnt to set
	 */
	public void setTotalFatsPcnt(Double totalFatsPcnt) {
		this.totalFatsPcnt = totalFatsPcnt;
	}
	/**
	 * @return the totalProtienPcnt
	 */
	public Double getTotalProtienPcnt() {
		return totalProtienPcnt;
	}
	/**
	 * @param totalProtienPcnt the totalProtienPcnt to set
	 */
	public void setTotalProtienPcnt(Double totalProtienPcnt) {
		this.totalProtienPcnt = totalProtienPcnt;
	}
	/**
	 * @return the totalCalories
	 */
	public Double getTotalCalories() {
		return totalCalories;
	}
	/**
	 * @param totalCalories the totalCalories to set
	 */
	public void setTotalCalories(Double totalCalories) {
		this.totalCalories = totalCalories;
	}
	/**
	 * @return the totalCarbohydrates
	 */
	public Double getTotalCarbohydrates() {
		return totalCarbohydrates;
	}
	/**
	 * @param totalCarbohydrates the totalCarbohydrates to set
	 */
	public void setTotalCarbohydrates(Double totalCarbohydrates) {
		this.totalCarbohydrates = totalCarbohydrates;
	}
	/**
	 * @return the totalDietoryFibres
	 */
	public Double getTotalDietoryFibres() {
		return totalDietoryFibres;
	}
	/**
	 * @param totalDietoryFibres the totalDietoryFibres to set
	 */
	public void setTotalDietoryFibres(Double totalDietoryFibres) {
		this.totalDietoryFibres = totalDietoryFibres;
	}
	/**
	 * @return the totalFats
	 */
	public Double getTotalFats() {
		return totalFats;
	}
	/**
	 * @param totalFats the totalFats to set
	 */
	public void setTotalFats(Double totalFats) {
		this.totalFats = totalFats;
	}
	/**
	 * @return the totalProtien
	 */
	public Double getTotalProtien() {
		return totalProtien;
	}
	/**
	 * @param totalProtien the totalProtien to set
	 */
	public void setTotalProtien(Double totalProtien) {
		this.totalProtien = totalProtien;
	}
	/**
	 * @return the totalSugar
	 */
	public Double getTotalSugar() {
		return totalSugar;
	}
	/**
	 * @param totalSugar the totalSugar to set
	 */
	public void setTotalSugar(Double totalSugar) {
		this.totalSugar = totalSugar;
	}
	
	public void populateSummaryPcnt(){
		Double temp = this.totalFats*9;
		setTotalFatsPcnt((temp/this.totalCalories)*100);
		temp = this.totalCarbohydrates*4;
		setTotalCarbohydratesPcnt((temp/this.totalCalories)*100);
		temp = this.totalProtien*4;
		setTotalProtienPcnt((temp/this.totalCalories)*100);
		setOthersPcnt(100-(this.totalCarbohydratesPcnt+this.totalFatsPcnt+this.totalProtienPcnt));		
	}
	
	public void calcTotalcalories(){
		this.totalCalories=(this.totalFats*9)+(this.totalCarbohydrates*4)+(this.totalProtien*4);
	}
	/**
	 * @return the othersPcnt
	 */
	public Double getOthersPcnt() {
		return othersPcnt;
	}
	/**
	 * @param othersPcnt the othersPcnt to set
	 */
	public void setOthersPcnt(Double othersPcnt) {
		this.othersPcnt = othersPcnt;
	}
	
	
	
	
	
}
