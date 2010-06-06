package com.designer.dao.goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.model.goal.Goal;

public class GoalDAOImpl implements GoalDAO {

	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public Goal getGoalForUser(String UserId) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goal goal = null;
		
		try
		{
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM USER_GOALS WHERE USER_ID = ? ");
			pstmt.setString(1, UserId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				goal = new Goal();
				
				goal.setGoalId(rs.getInt("GOAL_ID"));
				goal.setUserId(rs.getString("USER_ID"));
				goal.setGoalName(rs.getString("GOAL_NAME"));
				goal.setStartReading(rs.getDouble("START_READING"));
				goal.setEndReading(rs.getDouble("END_READING"));
				goal.setReadingUnit(rs.getString("READING_UNIT"));
				goal.setStartDate(rs.getDate("START_DATE"));
				goal.setEndDate(rs.getDate("END_DATE"));
				goal.setCalorieRestrictionPerDay(rs.getInt("CALORIE_RESTRICTION_PER_DAY"));
				goal.setCaloriesBurntPerDay(rs.getInt("CALORIE_BURNT_PER_DAY"));
				goal.setProtienPercentage(rs.getInt("PROTIEN_PERCENTAGE"));
				goal.setFatPercentage(rs.getInt("FAT_PERCENTAGE"));
				goal.setCarbohydratesPercentage(rs.getInt("CARBO_PERCENTAGE"));
				goal.setOtherNutrientPercentage(rs.getInt("OTHER_PERCENTAGE"));
				goal.setCreatedOn(rs.getTimestamp("CREATED_ON"));
				goal.setLastModifiedOn(rs.getTimestamp("LAST_MODIFIED_ON"));
			}
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se){}
		}
	
		return goal;
	}
	
	public void addGoal(Goal goal) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO USER_GOALS ")
			.append("(USER_ID,GOAL_NAME,START_READING,END_READING,READING_UNIT,START_DATE, ")
			.append("END_DATE,CALORIE_RESTRICTION_PER_DAY,CALORIE_BURNT_PER_DAY,PROTIEN_PERCENTAGE, ")
			.append("FAT_PERCENTAGE,CARBO_PERCENTAGE,OTHER_PERCENTAGE,CREATED_ON,LAST_MODIFIED_ON) ")
			.append("VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
		
		try
		{
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, goal.getUserId());
			pstmt.setString(2, goal.getGoalName());
			pstmt.setDouble(3, goal.getStartReading());
			pstmt.setDouble(4, goal.getEndReading());
			pstmt.setString(5, goal.getReadingUnit());
			pstmt.setDate(6, goal.getStartDate());
			pstmt.setDate(7, goal.getEndDate());
			pstmt.setDouble(8, goal.getCalorieRestrictionPerDay());
			pstmt.setDouble(9, goal.getCaloriesBurntPerDay());
			pstmt.setInt(10, goal.getProtienPercentage());
			pstmt.setInt(11, goal.getFatPercentage());
			pstmt.setInt(12, goal.getCarbohydratesPercentage());
			pstmt.setInt(13, goal.getOtherNutrientPercentage());
			pstmt.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(15, new Timestamp(System.currentTimeMillis()));
			
			pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try
			{
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se){}
		}
	
	}
	
	public void updateGoal(Goal goal) throws ApplicationException
	{
		// TODO
	}
	
}
