package com.designer.dao.foodJournal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.controller.foodJournal.FoodJournalForm;
import com.designer.model.foodJournal.FoodItemModel;
import com.designer.model.foodJournal.ManualFoodModel;
import com.designer.model.foodJournal.MealNutrientSummaryModel;
import com.designer.model.foodJournal.NonFoodModel;
import com.designer.model.foodJournal.SuggestedNutrientSummary;
import com.designer.model.foodJournal.ViewMealModel;

public class FoodJournalDAOImpl implements FoodJournalDAO{
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public ArrayList<ViewMealModel> getMeals(String userId,int pageNum) throws ApplicationException {

		ArrayList<ViewMealModel> mealList = new ArrayList<ViewMealModel>();
		Connection con = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnection();
			PreparedStatement pstmt; 
			StringBuffer sb = new StringBuffer();
	
			sb.append(" Select * FROM USER_MEAL_ENTRIES WHERE MEAL_DATE IN " );
			sb.append(" (SELECT MIN(MEAL_DATE) FROM (SELECT DISTINCT TOP  " );
			sb.append( pageNum );
			sb.append(" MEAL_DATE FROM USER_MEAL_ENTRIES " );
			sb.append(" WHERE USER_ID=? ORDER BY MEAL_DATE DESC ) INNERQRY )AND ");
			sb.append(" USER_MEAL_ENTRIES.USER_ID= ? ");
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);

			rs = pstmt.executeQuery(); 
			while ( rs.next() ) { 
				ViewMealModel viewMealModel = new ViewMealModel();
				viewMealModel.setMealId(rs.getInt("MEAL_ENTRY_ID"));
				viewMealModel.setMealType(rs.getInt("MEAL_TYPE"));
				viewMealModel.setTime(rs.getString("MEALTIME"));
				viewMealModel.setDate(rs.getTimestamp("MEAL_DATE"));
				
				viewMealModel.setEnergyLevel(rs.getString("ENERGY_LEVEL"));
				viewMealModel.setAppetite(rs.getString("APPETITE"));
				viewMealModel.setMood(rs.getString("MOOD"));
				viewMealModel.setDigestiveState(rs.getString("DIGESTIVE_STATE"));
				viewMealModel.setBowelMovement(rs.getString("BOWEL_MOVEMENT"));
				int mealType = rs.getInt("MEAL_TYPE");
				if(mealType==2){
					viewMealModel.setNonFoodMeal(null);
					viewMealModel.setManualFoodModel(null);
					viewMealModel.setFoodMealList(getFoodListForMealid(viewMealModel.getMealId(), userId));
				}
				else if(mealType==1){
					viewMealModel.setFoodMealList(null);
					viewMealModel.setManualFoodModel(null);
					viewMealModel.setNonFoodMeal(getSupplimentFoodForMealid(viewMealModel.getMealId()));
				}
				else if(mealType==3){
					viewMealModel.setFoodMealList(null);
					viewMealModel.setNonFoodMeal(null);
					viewMealModel.setManualFoodModel(getManualFoodForMealid(viewMealModel.getMealId()));
					
				}
				
				mealList.add(viewMealModel);
				
		     } 
	 	           
		} catch (Exception e) {
		    System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException se) {
				System.err.println("Error while closing connection");
				se.printStackTrace();
			}
 		}  

		return mealList;
	}
	

public int getPageCount(String userId) throws ApplicationException{
		
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		int pageCount=0;
		Connection con = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			//con = appDataSource.getConnection();
			Statement stmt 		= con.createStatement(); 
			StringBuffer sb= new StringBuffer();
			//Select * from USER_MEAL_ENTRIES WHERE USER_ID='DWAIN'
			sb.append("SELECT COUNT (*) FROM ( ");
			sb.append(" SELECT  DISTINCT MEAL_DATE AS DATE_COUNT FROM  USER_MEAL_ENTRIES "); 
			sb.append(" WHERE USER_ID = '");
			sb.append( userId  );
			sb.append("' ) DATE_COUNT ");
	 
			rs = stmt.executeQuery(sb.toString()); 
		
			while ( rs.next() ) { 
				pageCount= rs.getInt(1);
		     } 
			System.out.println("Page COunt -- "+pageCount);
		} catch (Exception se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
 		}  

		return pageCount;
}


	
	


	public void addMeal(FoodItemModel foodItemModel, String mealIdList,
			FoodJournalForm foodJournalForm) throws ApplicationException {

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMaxId = null;
		PreparedStatement pstmtMealLink = null;
		PreparedStatement pstmtNonFood = null;
		PreparedStatement pstmtManualFood = null;

		ResultSet rs = null;
		ResultSet rstmtMaxId = null;

		int inc = 0;
		try {
			con = appDataSource.getConnection();
			con.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			StringBuffer sqlML = new StringBuffer();
			StringBuffer sqlNonFood = new StringBuffer();
			StringBuffer sqlManualFood = new StringBuffer();
			// Query 1: Geting max seq number which will be inserted into the
			// USER_MEAL_ENTRIES id's column
			String getMaxMEId = "SELECT MAX(MEAL_ENTRY_ID) ID FROM USER_MEAL_ENTRIES ";

			pstmtMaxId = con.prepareStatement(getMaxMEId);
			rstmtMaxId = pstmtMaxId.executeQuery();

			// pstmtMaxId.close();
			// pstmtMaxId = null;
			//		    
			int mealId = 1;

			if (rstmtMaxId.next()) {
				mealId = rstmtMaxId.getInt(1);
				System.err.println("Inside If mealId " + mealId);
				if (mealId < 1) {
					mealId = 1;
				} else {
					mealId += 1;
				}
			}
			System.err.println("DAO mealId 1 ");
			// Query 2: Insert data into USER_MEAL_ENTRIES
			sql.append(" INSERT INTO USER_MEAL_ENTRIES(MEAL_ENTRY_ID,MEAL_DATE,MEALTIME,USER_ID,MEAL_TYPE,ENERGY_LEVEL,APPETITE,MOOD,DIGESTIVE_STATE,BOWEL_MOVEMENT) ");
			sql.append(" VALUES (?,CAST(? AS DATETIME ),?,?,?,?,?,?,?,?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mealId);
			pstmt.setDate(2, new Date(System.currentTimeMillis()));
			pstmt.setString(3, foodItemModel.getMealTime());
			pstmt.setString(4, foodItemModel.getUserId());
			pstmt.setInt(5, foodItemModel.getMealType());
			pstmt.setString(6, foodJournalForm.getEnergyLevelName());
			pstmt.setString(7, foodJournalForm.getAppetiteName());
			pstmt.setString(8, foodJournalForm.getMoodName());
			pstmt.setString(9, foodJournalForm.getDigestiveStateName());
			String bowelMovement = "No";
			if (foodJournalForm.getBowelMovementName().equalsIgnoreCase("yes")) {
				bowelMovement = foodJournalForm.getMotion();
			}
			pstmt.setString(10, bowelMovement);
			pstmt.executeUpdate();
			System.err.println("DAO mealId 2 " + mealId);

			if (foodItemModel.getMealType() == 1) { // NonFood Meals
				sqlNonFood.append(" INSERT INTO MEAL_NONFOOD_ENTRIES (MEAL_ENTRY_ID,MRP_SCOOPS,MRP_SCOOPS_QTY,MSOIL,WATERAMT ) ");
				sqlNonFood.append(" VALUES (?,?,?,?,?) ");
				pstmtNonFood = con.prepareStatement(sqlNonFood.toString());

				pstmtNonFood.setInt(1, mealId);
				pstmtNonFood.setString(2, foodJournalForm.getMrpScoops());
				pstmtNonFood.setInt(3, foodJournalForm.getMrpScoopsQty());
				pstmtNonFood.setString(4, foodJournalForm.getMsOil());
				pstmtNonFood.setDouble(5, foodJournalForm.getWaterAmt());
				pstmtNonFood.executeUpdate();

			} else if (foodItemModel.getMealType() == 2){ // Food Meals
				// Query 3: Insert data into USER_MEAL_ENTRIES
				sqlML
						.append(" INSERT INTO MEAL_FOOD_LINK(MEAL_ENTRY_ID,FOOD_ID ) ");
				sqlML.append(" VALUES (?,?) ");

				pstmtMealLink = con.prepareStatement(sqlML.toString());
				String mealIds[] = null;

				System.err.println("DAO mealId foodItemModel.getMealIdList()  "
						+ foodItemModel.getMealIdList());
				if (mealIdList != null && mealIdList != "") {
					// mealIdList=request.getParameter("mealIdList");
					mealIds = foodItemModel.getMealIdList().split("\t");
				}

				inc = 0;
				if (mealIds != null) {
					while (inc < mealIds.length) {
						pstmtMealLink.setInt(1, mealId);
						pstmtMealLink.setInt(2, Integer.parseInt(mealIds[inc]));
						System.out.println("Value in List  " + mealIds[inc]);
						inc++;
						pstmtMealLink.executeUpdate();
					}
				}

			}
			else if(foodItemModel.getMealType() == 3){
				System.out.println("add Meal DAO_IMPL mealType :: 3");
				pstmtManualFood = null;				
				sqlManualFood.append(" INSERT INTO MEAL_MANUALFOOD_ENTRIES (MEAL_ENTRY_ID,PROTEIN_NAME,PROTEIN_AMT,COMPLEX_CARBS_NAME,COMPLEX_CARBS_AMT,FIBROUS_CARBS_NAME ,FIBROUS_CARBS_AMT,MS_OIL,ESSENTIAL_FATS,CONDIMENTS,CONDIMENTS_AMT,SAUCE,SAUCE_AMT  ) ");
				sqlManualFood.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				pstmtManualFood = con.prepareStatement(sqlManualFood.toString());

				pstmtManualFood.setInt(1, mealId);
				pstmtManualFood.setString(2, foodJournalForm.getProteinName());
				pstmtManualFood.setDouble(3, foodJournalForm.getProteinAmt());
				pstmtManualFood.setString(4, foodJournalForm.getComplexCarbsName());
				pstmtManualFood.setDouble(5, foodJournalForm.getComplexCarbsAmt());
				pstmtManualFood.setString(6, foodJournalForm.getFibrousCarbsName());
				pstmtManualFood.setDouble(7, foodJournalForm.getFibrousCarbsAmt());
				pstmtManualFood.setDouble(8, foodJournalForm.getMetabolicSuppOil());
				pstmtManualFood.setDouble(9, foodJournalForm.getEsentialFats());
				pstmtManualFood.setString(10, foodJournalForm.getCondimentsName());
				pstmtManualFood.setDouble(11, foodJournalForm.getCondimentsAmt());
				pstmtManualFood.setString(12, foodJournalForm.getSauceName());
				pstmtManualFood.setDouble(13, foodJournalForm.getSauceAmt());
				pstmtManualFood.executeUpdate();
			}
			
			
			
			System.err.println("DAO mealId " + mealId);
			con.commit();
		} catch (SQLException se) {
			System.err.println("DAO EXcception " + se.getMessage());
			throw new ApplicationException(se.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (pstmtMealLink != null) {
					pstmtMealLink.close();
				}

				if (pstmtMaxId != null) {
					pstmtMaxId.close();
				}

				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}

		}
	}
	
	public void updateMeal(FoodJournalForm foodJournalForm) throws ApplicationException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE USER_MEAL_ENTRIES SET ENERGY_LEVEL = ?, APPETITE = ?, MOOD = ?,DIGESTIVE_STATE = ?, BOWEL_MOVEMENT = ? WHERE MEAL_ENTRY_ID=? ");//MEAL_DATE,
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, foodJournalForm.getEnergyLevelName());
			pstmt.setString(2, foodJournalForm.getAppetiteName());
			pstmt.setString(3, foodJournalForm.getMoodName());
			pstmt.setString(4, foodJournalForm.getDigestiveStateName());
			String bowelMovement = "No";
			if (foodJournalForm.getBowelMovementName().equalsIgnoreCase("yes")) {
				bowelMovement = foodJournalForm.getMotion();
			}
			pstmt.setString(5, bowelMovement);			
	        pstmt.setInt(6, foodJournalForm.getMealId());
	     
	        pstmt.executeUpdate();

		}catch(SQLException se){
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}

				if(pstmt != null){
					pstmt.close();
				}

				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}


		} 
	}
	
	
	public void deleteMeal(int mealId) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement("DELETE FROM USER_MEAL_ENTRIES WHERE MEAL_ENTRY_ID=? ");
   
	        pstmt.setInt(1,mealId);
     
	        pstmt.executeUpdate();

	        System.out.println("St Inserted");
		}catch(SQLException se){
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}

				if(pstmt != null){
					pstmt.close();
				}

				if(con != null && ! con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				//do nothing
			}
		} 
	}
	
public ArrayList<FoodItemModel> getMasterFoodList(String userId) throws ApplicationException{
		
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		//int pageCount=0;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
		
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			//con = appDataSource.getConnection();
			
			StringBuffer sb= new StringBuffer();
		 
			sb.append("select FOOD_MASTER.*, COLOUR = CASE WHEN ASSIGN.FOOD_ID IS NULL THEN 'red' ELSE 'black' END ");  
			sb.append("FROM FOOD_MASTER left  outer join ");
			sb.append("(select * from USER_FOOD_ASSIGNMENT where USER_FOOD_ASSIGNMENT.USER_ID = ?) ASSIGN ");
			sb.append("on FOOD_MASTER.FOOD_ID =  ASSIGN.FOOD_ID order by COLOUR, FOOD_MASTER.FOOD_NAME");
			
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				FoodItemModel foodItem = new FoodItemModel();
				foodItem.setItemId(rs.getInt("FOOD_ID"));
				foodItem.setItemName(rs.getString("FOOD_NAME"));
				foodItem.setCalories(rs.getDouble("CALORIES"));
				foodItem.setFats(rs.getDouble("FAT_IN_GRAMS"));
				foodItem.setProtiens(rs.getDouble("PROTIEN_IN_GRAMS"));
				foodItem.setCarbohydrates(rs.getDouble("CARBOHYDRATES_IN_GRAMS"));
				foodItem.setDietryFibre(rs.getDouble("DIETARY_FIBRE"));
				foodItem.setSugar(rs.getDouble("SUGAR"));
				foodItem.setServings(rs.getDouble("SERVINGS_PER_DISH"));
				foodItem.setColour(rs.getString("COLOUR"));
				foodList.add(foodItem);
		     } 
		} catch (Exception se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
 		}  

		return foodList;
}

public  NonFoodModel getSupplimentFoodForMealid(int mealId) throws ApplicationException{
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	Connection con = null;
	ResultSet rs = null;
	NonFoodModel nonFoodEntry = new NonFoodModel();
	try{
		con = appDataSource.getConnectionFactory().getConnection();
		String sb = "SELECT * FROM MEAL_NONFOOD_ENTRIES WHERE MEAL_ENTRY_ID = ?";
		
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString()); 
		pstmt.setInt(1, mealId);
		
		rs = pstmt.executeQuery();
		
		if ( rs.next() ) {
			nonFoodEntry.setMeal_id(rs.getInt("MEAL_ENTRY_ID"));
			nonFoodEntry.setMrpScoops(rs.getString("MRP_SCOOPS"));
			nonFoodEntry.setMrpScoopsQty(rs.getInt("MRP_SCOOPS_QTY"));
			nonFoodEntry.setMsOil(rs.getString("MSOIL"));
			nonFoodEntry.setWaterAmt(rs.getDouble("WATERAMT"));
	     } 
	} catch (Exception se) {
		throw new ApplicationException(se.getMessage());
	}finally{
		try {
			if(rs != null){
				rs.close();
			}
			if(con != null && !con.isClosed()){
				con.close();
			}
		} catch (SQLException se) {
			// do nothing
		}
		}  

	return nonFoodEntry;
}

public  ManualFoodModel getManualFoodForMealid(int mealId) throws ApplicationException{
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	Connection con = null;
	ResultSet rs = null;
	ManualFoodModel manualFoodEntry = new ManualFoodModel();
	try{
		con = appDataSource.getConnectionFactory().getConnection();
		String sb = "SELECT * FROM MEAL_MANUALFOOD_ENTRIES WHERE MEAL_ENTRY_ID = ?";
		
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString()); 
		pstmt.setInt(1, mealId);
		
		rs = pstmt.executeQuery();
		
		if ( rs.next() ) {
			manualFoodEntry.setMeal_id(rs.getInt("MEAL_ENTRY_ID"));
			
			manualFoodEntry.setProteinName(rs.getString("PROTEIN_NAME"));
			manualFoodEntry.setComplexCarbsName(rs.getString("COMPLEX_CARBS_NAME"));
			manualFoodEntry.setFibrousCarbsName(rs.getString("FIBROUS_CARBS_NAME"));
			manualFoodEntry.setProteinAmt(rs.getDouble("PROTEIN_AMT"));
			manualFoodEntry.setComplexCarbsAmt(rs.getDouble("COMPLEX_CARBS_AMT"));
			manualFoodEntry.setFibrousCarbsAmt(rs.getDouble("FIBROUS_CARBS_AMT"));
			manualFoodEntry.setMsOil(rs.getDouble("MS_OIL"));
			manualFoodEntry.setEsentialFats(rs.getDouble("ESSENTIAL_FATS"));
			manualFoodEntry.setCondimentsName(rs.getString("CONDIMENTS"));
			manualFoodEntry.setCondimentsAmt(rs.getDouble("CONDIMENTS_AMT"));
			manualFoodEntry.setSauceName(rs.getString("SAUCE"));
			manualFoodEntry.setSauceAmt(rs.getDouble("SAUCE_AMT"));
		}
		
		
		
	} catch (Exception se) {
		throw new ApplicationException(se.getMessage());
	}finally{
		try {
			if(rs != null){
				rs.close();
			}
			if(con != null && !con.isClosed()){
				con.close();
			}
		} catch (SQLException se) {
			// do nothing
		}
		}  

	return manualFoodEntry;
}
public ArrayList<FoodItemModel> getFoodListForMealid(int mealId, String userId) throws ApplicationException{
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	Connection con = null;
	ResultSet rs = null;
	ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
	
	try{
		con = appDataSource.getConnectionFactory().getConnection();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select food_list.*, COLOUR = CASE WHEN ASSIGN.FOOD_ID IS NULL THEN 'red' ELSE 'black' END ");
		sb.append("FROM (SELECT FOOD_MASTER.* from USER_MEAL_ENTRIES, MEAL_FOOD_LINK, FOOD_MASTER WHERE ");
		sb.append("USER_MEAL_ENTRIES.MEAL_ENTRY_ID = ? AND ");
		sb.append("USER_MEAL_ENTRIES.USER_ID = ? AND ");
		sb.append("MEAL_FOOD_LINK.MEAL_ENTRY_ID = USER_MEAL_ENTRIES.MEAL_ENTRY_ID AND ");
		sb.append("MEAL_FOOD_LINK.FOOD_ID = FOOD_MASTER.FOOD_ID) food_list left  outer join");
		sb.append("(select * from USER_FOOD_ASSIGNMENT where USER_FOOD_ASSIGNMENT.USER_ID = ?) ASSIGN ");
		sb.append("on food_list.FOOD_ID =  ASSIGN.FOOD_ID");
		PreparedStatement pstmt = con.prepareStatement(sb.toString()); 
		pstmt.setInt(1, mealId);
		pstmt.setString(2, userId);
		pstmt.setString(3, userId);
		rs = pstmt.executeQuery();
		
		while ( rs.next() ) {
			FoodItemModel foodItem = new FoodItemModel();
			foodItem.setItemId(rs.getInt("FOOD_ID"));
			foodItem.setItemName(rs.getString("FOOD_NAME"));
			foodItem.setCalories(rs.getDouble("CALORIES"));
			foodItem.setFats(rs.getDouble("FAT_IN_GRAMS"));
			foodItem.setProtiens(rs.getDouble("PROTIEN_IN_GRAMS"));
			foodItem.setCarbohydrates(rs.getDouble("CARBOHYDRATES_IN_GRAMS"));
			foodItem.setDietryFibre(rs.getDouble("DIETARY_FIBRE"));
			foodItem.setSugar(rs.getDouble("SUGAR"));
			foodItem.setColour(rs.getString("COLOUR"));
			foodItem.setServings(rs.getDouble("SERVINGS_PER_DISH"));				
			foodList.add(foodItem);
	     } 
	} catch (Exception se) {
		throw new ApplicationException(se.getMessage());
	}finally{
		try {
			if(rs != null){
				rs.close();
			}
			if(con != null && !con.isClosed()){
				con.close();
			}
		} catch (SQLException se) {
			// do nothing
		}
		}  

	return foodList;
}

	public ArrayList<FoodItemModel> getAssignedToUserFoodList(String userId) throws ApplicationException{
		
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection con = null;
		ResultSet rs = null;
		ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
		
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			//con = appDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT FOOD_MASTER.* FROM USER_FOOD_ASSIGNMENT, FOOD_MASTER WHERE USER_ID = ? AND USER_FOOD_ASSIGNMENT.FOOD_ID = FOOD_MASTER.FOOD_ID "); 
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				FoodItemModel foodItem = new FoodItemModel();
				foodItem.setItemId(rs.getInt("FOOD_ID"));
				foodItem.setItemName(rs.getString("FOOD_NAME"));
				foodItem.setCalories(rs.getDouble("CALORIES"));
				foodItem.setFats(rs.getDouble("FAT_IN_GRAMS"));
				foodItem.setProtiens(rs.getDouble("PROTIEN_IN_GRAMS"));
				foodItem.setCarbohydrates(rs.getDouble("CARBOHYDRATES_IN_GRAMS"));
				foodItem.setDietryFibre(rs.getDouble("DIETARY_FIBRE"));
				foodItem.setSugar(rs.getDouble("SUGAR"));
				foodItem.setServings(rs.getDouble("SERVINGS_PER_DISH"));				
				foodList.add(foodItem);
		     } 
		} catch (Exception se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
			}  
	
		return foodList;
	}
public MealNutrientSummaryModel getNutrientSummaryForDay(String userId, Timestamp date) throws ApplicationException{
		
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection con = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		MealNutrientSummaryModel nutrientSummaryModel = new MealNutrientSummaryModel();
		
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT Sum(FOOD_MASTER.calories) CALORIES,");
				sb.append("Sum(FOOD_MASTER.FAT_IN_GRAMS) FATS, Sum(FOOD_MASTER.PROTIEN_IN_GRAMS) PROTIEN,");
				sb.append("Sum(FOOD_MASTER.CARBOHYDRATES_IN_GRAMS) CARBOHYDRATES, Sum(FOOD_MASTER.DIETARY_FIBRE) DIETARY_FIBRE, ");
				sb.append("Sum(FOOD_MASTER.SUGAR) SUGAR from USER_MEAL_ENTRIES, MEAL_FOOD_LINK, FOOD_MASTER ");
			sb.append("WHERE USER_MEAL_ENTRIES.USER_ID = ? AND ");
			sb.append("USER_MEAL_ENTRIES.MEAL_DATE = ? AND ");
			sb.append("MEAL_FOOD_LINK.MEAL_ENTRY_ID = USER_MEAL_ENTRIES.MEAL_ENTRY_ID and ");
			sb.append("MEAL_FOOD_LINK.FOOD_ID = FOOD_MASTER.FOOD_ID ");
			
			
			PreparedStatement pstmt = con.prepareStatement(sb.toString()); 
			pstmt.setString(1, userId);
			pstmt.setTimestamp(2, date);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				nutrientSummaryModel.setTotalCalories(rs.getDouble("CALORIES"));
				nutrientSummaryModel.setTotalFats(rs.getDouble("FATS"));
				nutrientSummaryModel.setTotalProtien(rs.getDouble("PROTIEN"));
				nutrientSummaryModel.setTotalCarbohydrates(rs.getDouble("CARBOHYDRATES"));
				nutrientSummaryModel.setTotalDietoryFibres(rs.getDouble("DIETARY_FIBRE"));
				nutrientSummaryModel.setTotalSugar(rs.getDouble("SUGAR"));
				
				
		     }
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("select	sum(Meal_manualfood_entries.PROTEIN_AMT) as PROTIEN,");
			sb1.append("sum(Meal_manualfood_entries.ESSENTIAL_FATS)as FATS,");
			sb1.append("sum(Meal_manualfood_entries.COMPLEX_CARBS_AMT+Meal_manualfood_entries.FIBROUS_CARBS_AMT )as CARBOHYDRATES ");
			sb1.append("from user_meal_entries,Meal_manualfood_entries ");
			sb1.append("WHERE USER_MEAL_ENTRIES.USER_ID = ? AND USER_MEAL_ENTRIES.MEAL_DATE = ? AND ");
			sb1.append("user_meal_entries.Meal_entry_id=Meal_manualfood_entries.Meal_entry_id ");
			PreparedStatement pstmt1 = con.prepareStatement(sb1.toString());
			pstmt1.setString(1, userId);
			pstmt1.setTimestamp(2, date);
			rs1 = pstmt1.executeQuery();
			MealNutrientSummaryModel manualFoodModel = new MealNutrientSummaryModel();
			if( rs1.next() ) {
				manualFoodModel.setTotalFats(rs1.getDouble("FATS"));
				manualFoodModel.setTotalProtien(rs1.getDouble("PROTIEN"));
				manualFoodModel.setTotalCarbohydrates(rs1.getDouble("CARBOHYDRATES"));
				manualFoodModel.calcTotalcalories();
				nutrientSummaryModel.setTotalCalories(manualFoodModel.getTotalCalories()+nutrientSummaryModel.getTotalCalories());
				nutrientSummaryModel.setTotalFats(manualFoodModel.getTotalFats()+nutrientSummaryModel.getTotalFats());
				nutrientSummaryModel.setTotalProtien(manualFoodModel.getTotalProtien()+nutrientSummaryModel.getTotalProtien());
				nutrientSummaryModel.setTotalCarbohydrates(manualFoodModel.getTotalCarbohydrates()+nutrientSummaryModel.getTotalCarbohydrates());
				
				
			}
			nutrientSummaryModel.populateSummaryPcnt();
			
			
		} catch (Exception se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(rs1 != null){
					rs.close();
				}
				
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
			}  
	
		return nutrientSummaryModel;
	}

/*
 * author: Antony
 * 
 * 
 */

	public String getAssignFood(String userId) throws ApplicationException{
		
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection con = null;
		ResultSet rs = null;
		boolean flag=false;
		//ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
		StringBuffer sb=new StringBuffer();
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			//con = appDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT FOOD_ID FROM USER_FOOD_ASSIGNMENT WHERE USER_ID = ?"); 
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				if(flag == true){
					sb.append("\t");
				}
				sb.append(rs.getInt(1));
				flag=true;
		     } 
		} catch (Exception se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
			}  
	
		return sb.toString();
	}
	
	/*
	 * 
	 * author : Antony
	 * storing food assign
	 * @see com.designer.dao.foodJournal.FoodJournalDAO#saveAssignMeal(java.lang.String, java.lang.String)
	 */
	public void saveAssignMeal(String mealIdList,String userId) throws ApplicationException{
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlDel = new StringBuffer();
		Connection con = null;
		PreparedStatement pstmt =null;
		PreparedStatement pstmtDel =null;
		System.out.println("MealList ==> "+mealIdList);
	//	System.out.println("MealList indexof ==> "+mealIdList.indexOf("\t"));
		System.out.println("userId ==> "+userId);
		
		
		try{
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			String mealIds[]=null;
			int inc=0;
			if(mealIdList !=null && mealIdList!="" ){
				//	mealIdList=request.getParameter("mealIdList");
				mealIds=mealIdList.split("\t");
			}
			
//		//	System.out.println("mealIds.length ==> "+mealIds.length);
//		    while (inc < mealIds.length){
//		    	if (mealIds[inc]==null || mealIds[inc]==""){
//		    		System.out.println("Value in List in if  Null ");
//		    	}else{
//		    		System.out.println("Value in List in if  "+mealIds[inc]);
//		    	}
//	       	inc++;
//		  }
			//System.out.println("mealIds ==> "+mealIds);
			 	
			sqlDel.append(" DELETE FROM USER_FOOD_ASSIGNMENT WHERE USER_ID = ? ");
			pstmtDel  = con.prepareStatement(sqlDel.toString());
			pstmtDel.setString(1, userId);
			pstmtDel.executeUpdate(); 
			
			sql.append(" INSERT INTO USER_FOOD_ASSIGNMENT (USER_ID,FOOD_ID) ");
			sql.append(" VALUES (?,?) ");
				
			pstmt  = con.prepareStatement(sql.toString());
			inc=0;
			if(mealIds != null){
			    while (inc < mealIds.length){
			    //	System.out.println("Value in List  "+mealIds[inc]);
		        	pstmt.setString(1, userId);
					pstmt.setInt(2, Integer.parseInt(mealIds[inc]));
					pstmt.executeUpdate();
					inc++;
			  }
			}
		con.commit();
	    }catch(SQLException se){
	    	 try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 throw new ApplicationException(se.getMessage());
		}finally{
			try { 
				if(pstmt != null){
					pstmt.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			}catch (SQLException se) {
				// do nothing
			}
		}
	}
	
	public SuggestedNutrientSummary getSuggestedNutritionForUser(String userId) throws ApplicationException{
		SuggestedNutrientSummary nutritionSummary = new SuggestedNutrientSummary();
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection con = null;
		ResultSet rs = null;
		//ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
		StringBuffer sb=new StringBuffer();
		try{
			con = appDataSource.getConnection();			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM NUTRITION_CONTROL WHERE USER_ID = ?");			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();			
			if( rs.next() ) {
				nutritionSummary.setSuggestedCalorieConsumption(rs.getDouble("SUGGESTED_CALORIE_PER_DAY"));
				nutritionSummary.setCarbohydratesPercentage(rs.getInt("CARBO_PERCENTAGE"));
				nutritionSummary.setProtienPercentage(rs.getInt("PROTIEN_PERCENTAGE"));
				nutritionSummary.setFatPercentage(rs.getInt("FAT_PERCENTAGE"));
				nutritionSummary.setOtherPercentage(rs.getInt("OTHER_PERCENTAGE"));
				nutritionSummary.populateToGms();
			}
			
		
		}catch (Exception se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
			}
		
		
		return nutritionSummary;
	}
}
	

