package com.designer.dao.customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.DesignerUtils;
import com.designer.common.framework.UserMap;
import com.designer.model.customer.ChangePassword;
import com.designer.model.customer.Customer;
import com.designer.model.customer.SearchCustomer;

public class CustomerDAOImpl implements CustomerDAO {
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	
	
	public Customer getCustomerByUserId(String userId) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer customer = null;
		
		try
		{
			con = appDataSource.getConnection();
			StringBuffer query = new StringBuffer("SELECT ")
				.append(" USER_DETAIL.USER_ID, PASSWORD, ROLE_TAG, FIRST_NAME, LAST_NAME, LOCKED, LAST_ACCESS_DATE_TIME,")
				.append(" gender, DATE_OF_BIRTH, DATE_OF_JOINING, DATE_OF_START, DURATION_IN_WEEKS, PHONE_NUMBER,")
				.append(" MOBILE_NUMBER, EMAIL_ADDRESS, HINT_QUESTION, ANSWER, ADDRESS, SUGGESTED_CALORIE_PER_DAY,")
				.append(" PROTIEN_PERCENTAGE, FAT_PERCENTAGE, CARBO_PERCENTAGE, OTHER_PERCENTAGE")
				.append(" FROM USER_ROLE, USER_DETAIL LEFT OUTER JOIN NUTRITION_CONTROL ON ")
				.append(" USER_DETAIL.USER_ID = NUTRITION_CONTROL.USER_ID ")
				.append(" WHERE")
				.append(" USER_DETAIL.ROLE_ID = USER_ROLE.ROLE_ID AND")
				.append(" USER_DETAIL.USER_ID = ?");
				
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, userId.toLowerCase());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				customer = new Customer();
				
				customer.setUserId(rs.getString("USER_ID"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setRole(rs.getString("ROLE_TAG"));
				customer.setFirstName(rs.getString("FIRST_NAME"));
				customer.setLastName(rs.getString("LAST_NAME"));
				customer.setLocked(rs.getString("LOCKED"));
				customer.setGender(rs.getString("gender"));
				customer.setDob(rs.getDate("DATE_OF_BIRTH"));
				customer.setDoj(rs.getDate("DATE_OF_JOINING"));
				customer.setDos(rs.getDate("DATE_OF_START")); 
				customer.setDurationInWeeks(rs.getInt("DURATION_IN_WEEKS"));
				customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				customer.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				customer.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				customer.setHintQuestion(rs.getString("HINT_QUESTION"));
				customer.setAnswer(rs.getString("ANSWER"));
				customer.setAddress(rs.getString("ADDRESS"));
				
				customer.setCarbohydratesPercentage(rs.getInt("CARBO_PERCENTAGE"));
				customer.setProtienPercentage(rs.getInt("PROTIEN_PERCENTAGE"));
				customer.setFatPercentage(rs.getInt("FAT_PERCENTAGE"));
				customer.setOtherPercentage(rs.getInt("OTHER_PERCENTAGE"));
				customer.setSuggestedCalorieConsumption(rs.getDouble("SUGGESTED_CALORIE_PER_DAY"));
				
				customer.setLastAccessDateTime(rs.getTimestamp("LAST_ACCESS_DATE_TIME"));
			}
			
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
				
		}finally{
				try{
					if(rs != null)
						rs.close();
					if(pstmt != null)
						pstmt.close();
					if(con != null && !con.isClosed())
						con.close();
				}catch(SQLException se){
					//do nothing
				}
				
		}	
		
		return customer;			
	}

		
	public void addCustomer(Customer customer) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement check1 = null;
		PreparedStatement check2 = null;
		PreparedStatement check3 = null;

		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO USER_DETAIL")
			.append(" (USER_ID, PASSWORD, FIRST_NAME, LAST_NAME, GENDER, DATE_OF_BIRTH,")
			.append(" DATE_OF_JOINING, DATE_OF_START, DURATION_IN_WEEKS, PHONE_NUMBER, MOBILE_NUMBER,")
			.append(" EMAIL_ADDRESS, ROLE_ID, HINT_QUESTION, ANSWER, ADDRESS,")
			.append("  CREATION_DATE, LAST_MODIFICATION_DATE)")
			.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		try
		{
			con = appDataSource.getConnection();

			check1 = con.prepareStatement("select * from USER_DETAIL where USER_ID = ?");
			check1.setString(1, customer.getUserId());
			check2 = con.prepareStatement("select * from USER_ROLE where ROLE_TAG = ?");
			check2.setString(1, customer.getUserId());
			check3 = con.prepareStatement("select * from USER_GROUP where GROUP_TAG = ?");
			check3.setString(1, customer.getUserId());
			
			if(check1.executeQuery().next() || check2.executeQuery().next() || check3.executeQuery().next())
				throw new SQLException("error.dao.duplicate.User.Id");
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, customer.getUserId().toLowerCase());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, customer.getFirstName());
			pstmt.setString(4, customer.getLastName());
			pstmt.setString(5, customer.getGender());
			pstmt.setDate(6, DesignerUtils.toSqlDate(customer.getDob()));
			pstmt.setDate(7, DesignerUtils.toSqlDate(customer.getDoj()));
			pstmt.setDate(8, DesignerUtils.toSqlDate(customer.getDos()));
			if(customer.getDurationInWeeks() == null)
				pstmt.setNull(9, Types.INTEGER);
			else
				pstmt.setInt(9, customer.getDurationInWeeks());
			pstmt.setString(10, customer.getPhoneNumber());
			pstmt.setString(11, customer.getMobileNumber());
			pstmt.setString(12, customer.getEmailAddress());
			pstmt.setInt(13, customer.isAdmin()?1:2);
			pstmt.setString(14, customer.getHintQuestion());
			pstmt.setString(15, customer.getAnswer());
			pstmt.setString(16, customer.getAddress());
			pstmt.setDate(17, new Date(System.currentTimeMillis()));
			pstmt.setDate(18, new Date(System.currentTimeMillis()));
			
			try{
				pstmt.execute();
				addNutritionControl(con, customer); //Creating nutrition entries for a customer  
				con.commit();
			}catch(SQLException se){
				con.rollback();
				throw new ApplicationException(se.getMessage());
			}
			
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
			
		}finally{
			
			try{
				if(check1 != null)
					check1.close();
				if(check2 != null)
					check2.close();
				if(check3 != null)
					check3.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
		
	}
	
	
	public void addNutritionControl(Connection con, Customer customer) throws SQLException{
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null;
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO NUTRITION_CONTROL")
			.append(" (USER_ID, SUGGESTED_CALORIE_PER_DAY, PROTIEN_PERCENTAGE,")
			.append(" FAT_PERCENTAGE, CARBO_PERCENTAGE, OTHER_PERCENTAGE, CREATED_ON, LAST_MODIFIED_ON)")
			.append(" VALUES (?,?,?,?,?,?,?,?) ");
			
		try
		{
			pstmtDelete = con.prepareStatement("DELETE FROM NUTRITION_CONTROL WHERE USER_ID = ?");
			pstmtDelete.setString(1, customer.getUserId());
			pstmtDelete.executeUpdate();
			
			if(customer.isAdmin())
				return;
				
			pstmtInsert = con.prepareStatement(query.toString());
			
			pstmtInsert.setString(1, customer.getUserId());
			pstmtInsert.setDouble(2, customer.getSuggestedCalorieConsumption());
			pstmtInsert.setInt(3, customer.getProtienPercentage());
			pstmtInsert.setInt(4, customer.getFatPercentage());
			pstmtInsert.setInt(5, customer.getCarbohydratesPercentage());
			pstmtInsert.setInt(6, customer.getOtherPercentage());
			pstmtInsert.setDate(7, new Date(System.currentTimeMillis()));
			pstmtInsert.setDate(8, new Date(System.currentTimeMillis()));
			
			pstmtInsert.execute();
		}
		finally
		{
			if(pstmtDelete != null)
				pstmtDelete.close();
			if(pstmtInsert != null)
				pstmtInsert.close();
		}
		
	}

	public void deleteUser(String userId) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer qry = new StringBuffer("DELETE FROM USER_DETAIL WHERE USER_ID  = ?"); 
		
		try
		{
			con = appDataSource.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(qry.toString());
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			con.commit();
			UserMap.getInstance().removeUser(userId);
		}catch(SQLException se){
			if(con != null)
				try{
					con.rollback();
				}catch(SQLException innerSe){
					//do nothing;
				}
				
			throw new ApplicationException(se.getMessage());
			
		}finally{
			
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
		
	}
	
	public void lockUser(String userId) throws ApplicationException 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE USER_DETAIL SET LOCKED='Y' WHERE USER_ID = ?");
			pstmt.setString(1, userId);
			
			pstmt.executeUpdate();
			con.commit();
		}catch(SQLException se){
			if(con != null)
				try{
					con.rollback();
				}catch(SQLException innerSe){
					//do nothing;
				}
				
			throw new ApplicationException(se.getMessage());
			
		}finally{
			
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
		
	}
	
	public void unlockCustomers(String[] customerIds) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer qry = new StringBuffer("UPDATE USER_DETAIL SET LOCKED='N' WHERE USER_ID IN ("); 
		
		for(int i=0; i< customerIds.length; i++)
		{
			qry.append("'").append(customerIds[i]).append("',");
		}
		qry.setCharAt(qry.length() - 1, ')');
				
		try
		{
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(qry.toString());
			pstmt.executeUpdate();
			con.commit();
		}catch(SQLException se){
			if(con != null)
				try{
					con.rollback();
				}catch(SQLException innerSe){
					//do nothing;
				}
				
			throw new ApplicationException(se.getMessage());
			
		}finally{
			
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
		
	}
	
			
	public List<Customer> searchUser(SearchCustomer searchCustomer) throws ApplicationException{
		return searchUser(searchCustomer, USER_ALL);
	}
				
	public List<Customer> searchUser(SearchCustomer searchCustomer, byte searchFilter) throws ApplicationException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Customer> customerList = new ArrayList<Customer>();
		try
		{
			con = appDataSource.getConnection();

			StringBuffer qry = new StringBuffer("SELECT USER_DETAIL.* FROM USER_DETAIL, USER_ROLE");
			qry.append(" WHERE USER_DETAIL.ROLE_ID = USER_ROLE.ROLE_ID");

			if(searchCustomer.getFirstName() != null && searchCustomer.getFirstName().length() > 0){
				qry.append(" AND FIRST_NAME LIKE '").append(searchCustomer.getFirstName()).append("%'");
			}
			if(searchCustomer.getLastName() != null && searchCustomer.getLastName().length() > 0){
				qry.append(" AND LAST_NAME LIKE '").append(searchCustomer.getLastName()).append("%'");
			}
			if(searchCustomer.getEmailAddress() != null && searchCustomer.getEmailAddress().length() > 0){
				qry.append(" AND EMAIL_ADDRESS LIKE '").append(searchCustomer.getEmailAddress()).append("%'");
			}
			if(searchCustomer.getCourses() != null && searchCustomer.getCourses().length() > 0){
				qry.append(" AND COURSES LIKE '%").append(searchCustomer.getCourses()).append("%'");
			}
			if(searchCustomer.getDoj() != null){
				qry.append(" AND DATE_OF_JOINING >=? ");
			}
			if(searchCustomer.getDos() != null){
				qry.append(" AND DATE_OF_START >=? ");
			}
			
			switch(searchFilter)
			{
			case USER_ADMIN:
				qry.append(" AND USER_ROLE.ROLE_TAG = 'admin'");
				break;

			case USER_CUSTOMER:
				qry.append(" AND USER_ROLE.ROLE_TAG = 'customer'");
				break;
			}
			qry.append(" ORDER BY FIRST_NAME, LAST_NAME, DATE_OF_JOINING");
/*			if(searchCustomer.getDob() != null) {
				qry.append("")
			}
*/			
			pstmt = con.prepareStatement(qry.toString());
			
			int i=1;
			if(searchCustomer.getDoj() != null)
				pstmt.setDate(i++, searchCustomer.getDoj());
			if(searchCustomer.getDos() != null)
				pstmt.setDate(i++, searchCustomer.getDos());
				
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Customer customer = new Customer();
				
				customer.setUserId(rs.getString("USER_ID"));
				customer.setFirstName(rs.getString("FIRST_NAME"));
				customer.setLastName(rs.getString("LAST_NAME"));
				customer.setDoj(rs.getDate("DATE_OF_JOINING"));
				customer.setDob(rs.getDate("DATE_OF_START"));
				customer.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				customer.setCourses((rs.getString("COURSES") == null || rs.getString("COURSES").length() == 0) ? "Not Planned":rs.getString("COURSES"));
				
				customerList.add(customer);			}
		
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
			
		}finally{
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
			
		}	
		
		return customerList;
	}


	public List<Customer> listCustomers(String sortOn) throws ApplicationException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Customer> customerList = new ArrayList<Customer>();
		try
		{
			con = appDataSource.getConnection();

			StringBuffer qry = new StringBuffer("SELECT * FROM USER_DETAIL WHERE ROLE_ID NOT IN(SELECT ROLE_ID FROM USER_ROLE where ROLE_TAG = 'system') ORDER BY ROLE_ID DESC, USER_ID");
			
			if(sortOn != null && sortOn.length() != 0)
				qry.append("ORDER BY " + sortOn);
			pstmt = con.prepareStatement(qry.toString());
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Customer customer = new Customer();
				
				customer.setUserId(rs.getString("USER_ID"));
				customer.setFirstName(rs.getString("FIRST_NAME"));
				customer.setLastName(rs.getString("LAST_NAME"));
				customer.setRole(rs.getInt("ROLE_ID") == 1 ? "Admin":"Customer");
				customer.setDos(rs.getDate("DATE_OF_START"));
				customer.setDurationInWeeks(rs.getInt("DURATION_IN_WEEKS"));
				customer.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				customer.setCourses((rs.getString("COURSES") == null || rs.getString("COURSES").length() == 0) ? "Not Planned":rs.getString("COURSES"));
				customer.setLocked(rs.getString("LOCKED"));
				customerList.add(customer);			
				}
		
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
			
		}finally{
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
			
		}	
		
		return customerList;
	}


	public void updateCustomer(Customer customer) throws ApplicationException {

		Connection con = null;
		PreparedStatement pstmt = null;

		StringBuffer query = new StringBuffer();
		
		query.append("UPDATE USER_DETAIL SET ")
			.append((customer.getPassword() == null ? "" : " PASSWORD = '" + customer.getPassword() + "', "))
			.append(" FIRST_NAME = ?, LAST_NAME = ? , GENDER = ? , DATE_OF_BIRTH = ? ,")
			.append(" DATE_OF_JOINING = ? , DATE_OF_START = ? , DURATION_IN_WEEKS = ? , PHONE_NUMBER = ? , MOBILE_NUMBER = ? ,")
			.append(" EMAIL_ADDRESS = ? , ROLE_ID = ? , HINT_QUESTION = ? , ANSWER = ? , ADDRESS = ? ,")
			.append(" LAST_MODIFICATION_DATE  = ? WHERE USER_ID = ?");

		
		try
		{
			con = appDataSource.getConnection();

			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getGender());
			pstmt.setDate(4, DesignerUtils.toSqlDate(customer.getDob()));
			pstmt.setDate(5, DesignerUtils.toSqlDate(customer.getDoj()));
			pstmt.setDate(6, DesignerUtils.toSqlDate(customer.getDos()));			
			if(customer.getDurationInWeeks() == null)
				pstmt.setNull(7, Types.INTEGER);
			else
				pstmt.setInt(7, customer.getDurationInWeeks());
			pstmt.setString(8, customer.getPhoneNumber());
			pstmt.setString(9, customer.getMobileNumber());
			pstmt.setString(10, customer.getEmailAddress());
			pstmt.setInt(11, customer.isAdmin()?1:2);
			pstmt.setString(12, customer.getHintQuestion());
			pstmt.setString(13, customer.getAnswer());
			pstmt.setString(14, customer.getAddress());
			pstmt.setDate(15, new Date(System.currentTimeMillis()));
			pstmt.setString(16, customer.getUserId());
			
			try{
				pstmt.executeUpdate();
				addNutritionControl(con, customer); //Creating nutrition entries for a customer  
				con.commit();
			}catch(SQLException se){
				con.rollback();
				throw new ApplicationException(se.getMessage());
			}
			
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
			
		}finally{
			
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
		
	}

	public void changePassword(ChangePassword cp) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement("UPDATE USER_DETAIL SET PASSWORD = ? WHERE USER_ID = ?");
			pstmt.setString(1, cp.getNewPassword());
			pstmt.setString(2, cp.getUserId());

			try{
				pstmt.executeUpdate();
				con.commit();
			}catch(SQLException se){
				con.rollback();
				throw new ApplicationException(se.getMessage());
			}
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
			
		}finally{
			
			try{
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
	}
}
