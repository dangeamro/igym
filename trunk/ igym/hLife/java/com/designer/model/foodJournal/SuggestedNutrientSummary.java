package com.designer.model.foodJournal;

import com.designer.common.framework.DesignerUtils;

public class SuggestedNutrientSummary {
	
	private Integer protienPercentage = null;
	private Integer carbohydratesPercentage = null;
	private Integer fatPercentage = null;
	private Integer otherPercentage = null;
	private Double protienGms = null;
	private Double carbohydratesGms = null;
	private Double fatGms = null;
	private Double otherGms = null;
	private Double suggestedCalorieConsumption = null;
	
	public void populateToGms(){
		Double temp=(this.carbohydratesPercentage*this.suggestedCalorieConsumption)/100;
		setCarbohydratesGms(temp/4);
		temp=(this.protienPercentage*this.suggestedCalorieConsumption)/100;
		setProtienGms(temp/4);
		temp=(this.fatPercentage*this.suggestedCalorieConsumption)/100;
		setFatGms(temp/9);		
	}
	
	
	/**
	 * @return the carbohydratesGms
	 */
	public Double getCarbohydratesGms() {
		
		return DesignerUtils.formatDouble(carbohydratesGms,"###0.#");
	}
	/**
	 * @param carbohydratesGms the carbohydratesGms to set
	 */
	public void setCarbohydratesGms(Double carbohydratesGms) {
		this.carbohydratesGms = carbohydratesGms;
	}
	/**
	 * @return the fatGms
	 */
	public Double getFatGms() {
		
		return DesignerUtils.formatDouble(fatGms,"###0.#");
	}
	/**
	 * @param fatGms the fatGms to set
	 */
	public void setFatGms(Double fatGms) {
		this.fatGms = fatGms;
	}
	/**
	 * @return the otherGms
	 */
	public Double getOtherGms() {
		return otherGms;
	}
	/**
	 * @param otherGms the otherGms to set
	 */
	public void setOtherGms(Double otherGms) {
		this.otherGms = otherGms;
	}
	/**
	 * @return the protienGms
	 */
	public Double getProtienGms() {
		
		return DesignerUtils.formatDouble(protienGms,"###0.#");
	}
	/**
	 * @param protienGms the protienGms to set
	 */
	public void setProtienGms(Double protienGms) {
		this.protienGms = protienGms;
	}
	/**
	 * @return the carbohydratesPercentage
	 */
	public Integer getCarbohydratesPercentage() {
		return carbohydratesPercentage;
	}
	/**
	 * @param carbohydratesPercentage the carbohydratesPercentage to set
	 */
	public void setCarbohydratesPercentage(Integer carbohydratesPercentage) {
		this.carbohydratesPercentage = carbohydratesPercentage;
	}
	/**
	 * @return the fatPercentage
	 */
	public Integer getFatPercentage() {
		return fatPercentage;
	}
	/**
	 * @param fatPercentage the fatPercentage to set
	 */
	public void setFatPercentage(Integer fatPercentage) {
		this.fatPercentage = fatPercentage;
	}
	/**
	 * @return the otherPercentage
	 */
	public Integer getOtherPercentage() {
		return otherPercentage;
	}
	/**
	 * @param otherPercentage the otherPercentage to set
	 */
	public void setOtherPercentage(Integer otherPercentage) {
		this.otherPercentage = otherPercentage;
	}
	/**
	 * @return the protienPercentage
	 */
	public Integer getProtienPercentage() {
		return protienPercentage;
	}
	/**
	 * @param protienPercentage the protienPercentage to set
	 */
	public void setProtienPercentage(Integer protienPercentage) {
		this.protienPercentage = protienPercentage;
	}
	/**
	 * @return the suggestedCalorieConsumption
	 */
	public Double getSuggestedCalorieConsumption() {
		return suggestedCalorieConsumption;
	}
	/**
	 * @param suggestedCalorieConsumption the suggestedCalorieConsumption to set
	 */
	public void setSuggestedCalorieConsumption(Double suggestedCalorieConsumption) {
		this.suggestedCalorieConsumption = suggestedCalorieConsumption;
	}
	
	
	
	

}
