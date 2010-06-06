package com.designer.controller.foodJournal;

import javax.servlet.http.HttpServletRequest;

import com.designer.model.foodJournal.FoodItemModel;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

@SuppressWarnings("serial")

public class FoodJournalForm extends ActionForm {
	
	private String mealTime	 = null;
	
	private String mealDate =  null;
	
	private int mealId;
	
	private int mealType;
	
	private byte mealNum;
	
	private String mrpScoops = null;
	
	private Integer mrpScoopsQty = null;
	
	private String msOil = null;
	
	private Double metabolicSuppOil = null;

	private Double waterAmt = null;
	 
	private String proteinName=null;
	
	private String complexCarbsName=null;
	
	private String fibrousCarbsName=null;
	
	private Double proteinAmt;
	
	private Double complexCarbsAmt;
	
	private Double fibrousCarbsAmt;
	
	private String efaSupportName;
	
	private Double esentialFats;
	
	private String condimentsName;
	
	private Double condimentsAmt;
	
	private String sauceName;
	
	private Double sauceAmt;
	
	private int efaSupportId;
	
	private int energyLevel;
	
	private int appetite;
	
	private int mood;
	
	private int digestiveState;
	
	private int bowelMovement;
	
	private int constipated;
	
	private String energyLevelName;
	
	private String appetiteName;
	
	private String moodName;
	
	private String digestiveStateName;
	
	private String bowelMovementName;
	
	private String motion;

	private String userId = null;
	
	private String mealIdList = null;
	

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		
		if(mealType==2){
			if(mealIdList.length()<=0){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.empty.foodList"));			
			}
			if(mealIdList.length()>700){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("common.foodList.maxSizeReached"));			
			}	
		}
		
	
		return errors;
		
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getComplexCarbsAmt() {
		return complexCarbsAmt;
	}

	public void setComplexCarbsAmt(Double complexCarbsAmt) {
		this.complexCarbsAmt = complexCarbsAmt;
	}

	public String getComplexCarbsName() {
		return complexCarbsName;
	}

	public void setComplexCarbsName(String complexCarbsName) {
		this.complexCarbsName = complexCarbsName;
	}

	
	public Double getFibrousCarbsAmt() {
		return fibrousCarbsAmt;
	}

	public void setFibrousCarbsAmt(Double fibrousCarbsAmt) {
		this.fibrousCarbsAmt = fibrousCarbsAmt;
	}

	public String getFibrousCarbsName() {
		return fibrousCarbsName;
	}

	public void setFibrousCarbsName(String fibrousCarbsName) {
		this.fibrousCarbsName = fibrousCarbsName;
	}

	public int getMealType() {
		return mealType;
	}

	public void setMealType(int mealType) {
		this.mealType = mealType;
	}

	

	public String getMrpScoops() {
		return mrpScoops;
	}

	public void setMrpScoops(String mrpScoops) {
		this.mrpScoops = mrpScoops;
	}

	public String getMsOil() {
		return msOil;
	}

	public void setMsOil(String msOil) {
		this.msOil = msOil;
	}

	public Double getProteinAmt() {
		return proteinAmt;
	}

	public void setProteinAmt(Double proteinAmt) {
		this.proteinAmt = proteinAmt;
	}

	public String getProteinName() {
		return proteinName;
	}

	public void setProteinName(String proteinName) {
		this.proteinName = proteinName;
	}

	public byte getMealNum() {
		return mealNum;
	}

	public void setMealNum(byte mealNum) {
		this.mealNum = mealNum;
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

	public Double getWaterAmt() {
		return waterAmt;
	}

	public void setWaterAmt(Double waterAmt) {
		this.waterAmt = waterAmt;
	}

	public int getEfaSupportId() {
		return efaSupportId;
	}

	public void setEfaSupportId(int efaSupportId) {
		this.efaSupportId = efaSupportId;
	}

	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	public String getAppetiteName() {
		return appetiteName;
	}

	public void setAppetiteName(String appetiteName) {
		this.appetiteName = appetiteName;
	}

	public String getBowelMovementName() {
		return bowelMovementName;
	}

	public void setBowelMovementName(String bowelMovementName) {
		this.bowelMovementName = bowelMovementName;
	}

	

	/**
	 * @return the motion
	 */
	public String getMotion() {
		return motion;
	}

	/**
	 * @param motion the motion to set
	 */
	public void setMotion(String motion) {
		this.motion = motion;
	}

	public String getDigestiveStateName() {
		return digestiveStateName;
	}

	public void setDigestiveStateName(String digestiveStateName) {
		this.digestiveStateName = digestiveStateName;
	}

	public String getEnergyLevelName() {
		return energyLevelName;
	}

	public void setEnergyLevelName(String energyLevelName) {
		this.energyLevelName = energyLevelName;
	}

	public String getMoodName() {
		return moodName;
	}

	public void setMoodName(String moodName) {
		this.moodName = moodName;
	}

	public int getAppetite() {
		return appetite;
	}

	public void setAppetite(int appetite) {
		this.appetite = appetite;
	}

	public int getBowelMovement() {
		return bowelMovement;
	}

	public void setBowelMovement(int bowelMovement) {
		this.bowelMovement = bowelMovement;
	}

	public int getConstipated() {
		return constipated;
	}

	public void setConstipated(int constipated) {
		this.constipated = constipated;
	}

	public int getDigestiveState() {
		return digestiveState;
	}

	public void setDigestiveState(int digestiveState) {
		this.digestiveState = digestiveState;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getMrpScoopsQty() {
		return (Integer)mrpScoopsQty;
	}

	public void setMrpScoopsQty(int mrpScoopsQty) {
		this.mrpScoopsQty = (Integer)mrpScoopsQty;
	}
	public String getMealIdList() {
		return mealIdList;
	}

	public void setMealIdList(String mealIdList) {
		this.mealIdList = mealIdList;
	}
	
	

	public FoodItemModel populateToModel(){
		FoodItemModel foodItemModel = new FoodItemModel();
		
		foodItemModel.setUserId(userId);
		foodItemModel.setMealDate(mealDate);
		foodItemModel.setMealTime(mealTime);
		foodItemModel.setMealType(mealType);
		foodItemModel.setMealIdList(mealIdList);
		return foodItemModel;
	}

	/**
	 * @return the efaSupportName
	 */
	public String getEfaSupportName() {
		return efaSupportName;
	}

	/**
	 * @param efaSupportName the efaSupportName to set
	 */
	public void setEfaSupportName(String efaSupportName) {
		this.efaSupportName = efaSupportName;
	}

	/**
	 * @return the condimentsAmt
	 */
	public Double getCondimentsAmt() {
		return condimentsAmt;
	}

	/**
	 * @param condimentsAmt the condimentsAmt to set
	 */
	public void setCondimentsAmt(Double condimentsAmt) {
		this.condimentsAmt = condimentsAmt;
	}

	/**
	 * @return the condimentsName
	 */
	public String getCondimentsName() {
		return condimentsName;
	}

	/**
	 * @param condimentsName the condimentsName to set
	 */
	public void setCondimentsName(String condimentsName) {
		this.condimentsName = condimentsName;
	}

	/**
	 * @return the esentialFats
	 */
	public Double getEsentialFats() {
		return esentialFats;
	}

	/**
	 * @param esentialFats the esentialFats to set
	 */
	public void setEsentialFats(Double esentialFats) {
		this.esentialFats = esentialFats;
	}

	/**
	 * @return the sauceAmt
	 */
	public Double getSauceAmt() {
		return sauceAmt;
	}

	/**
	 * @param sauceAmt the sauceAmt to set
	 */
	public void setSauceAmt(Double sauceAmt) {
		this.sauceAmt = sauceAmt;
	}

	/**
	 * @return the sauceName
	 */
	public String getSauceName() {
		return sauceName;
	}

	/**
	 * @param sauceName the sauceName to set
	 */
	public void setSauceName(String sauceName) {
		this.sauceName = sauceName;
	}

	/**
	 * @return the metabolicSuppOil
	 */
	public Double getMetabolicSuppOil() {
		return metabolicSuppOil;
	}

	/**
	 * @param metabolicSuppOil the metabolicSuppOil to set
	 */
	public void setMetabolicSuppOil(Double metabolicSuppOil) {
		this.metabolicSuppOil = metabolicSuppOil;
	}


}
