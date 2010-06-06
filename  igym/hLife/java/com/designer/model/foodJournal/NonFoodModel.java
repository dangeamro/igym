package com.designer.model.foodJournal;

public class NonFoodModel {
	
	public int meal_id;
	public String mrpScoops;
	public int mrpScoopsQty;
	public String msOil;
	public Double waterAmt;
	
	
	public int getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(int meal_id) {
		this.meal_id = meal_id;
	}
	public String getMrpScoops() {
		return mrpScoops;
	}
	
	public int getMrpScoopsQty() {
		return mrpScoopsQty;
	}
	
	public String getMsOil() {
		return msOil;
	}
	
	public Double getWaterAmt() {
		return waterAmt;
	}
	public void setWaterAmt(Double waterAmt) {
		this.waterAmt = waterAmt;
	}
	public void setMrpScoops(String mrpScoops) {
		this.mrpScoops = mrpScoops;
	}
	public void setMrpScoopsQty(int mrpScoopsQty) {
		this.mrpScoopsQty = mrpScoopsQty;
	}
	public void setMsOil(String msOil) {
		this.msOil = msOil;
	}
	
	
	
}
