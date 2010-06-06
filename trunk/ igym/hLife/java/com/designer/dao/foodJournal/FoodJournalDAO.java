package com.designer.dao.foodJournal;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.designer.common.framework.ApplicationException;
import com.designer.controller.foodJournal.FoodJournalForm;
import com.designer.model.foodJournal.FoodItemModel;
import com.designer.model.foodJournal.MealNutrientSummaryModel;
import com.designer.model.foodJournal.ViewMealModel;
import com.designer.model.foodJournal.SuggestedNutrientSummary;

public interface FoodJournalDAO {
	
	
	public void addMeal(FoodItemModel foodItemModel,String mealIdList, FoodJournalForm foodJournalForm) throws ApplicationException;
	
	public void updateMeal(FoodJournalForm foodJournalForm) throws ApplicationException;
	
	public ArrayList<ViewMealModel> getMeals(String userId,int pageNum) throws ApplicationException;
		
	public void deleteMeal(int mealId) throws ApplicationException;
	
	public int getPageCount(String userId) throws ApplicationException;
	
	public ArrayList<FoodItemModel> getMasterFoodList(String userId) throws ApplicationException;
	
	public MealNutrientSummaryModel getNutrientSummaryForDay(String userId, Timestamp date) throws ApplicationException;
	
	public SuggestedNutrientSummary getSuggestedNutritionForUser(String userId) throws ApplicationException;
	
	public String getAssignFood(String userId) throws ApplicationException;
	
	public void saveAssignMeal(String mealIdList,String userId) throws ApplicationException;
}
