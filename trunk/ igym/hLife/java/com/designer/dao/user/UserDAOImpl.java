package com.designer.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.DesignerUtils;
import com.designer.controller.user.UserInfo;
import com.designer.model.message.Recipient;

public class UserDAOImpl implements UserDAO {
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public List<UserInfo> getUsers() throws ApplicationException
	{
		List<UserInfo> userList = new ArrayList<UserInfo>();
		
		Connection con = null;
		PreparedStatement userPstmt = null;
		ResultSet userRs = null;
		PreparedStatement rolePstmt = null;
		ResultSet roleRs = null;
		
		try {
			con = appDataSource.getConnection();
			userPstmt = con.prepareStatement("select USER_ID, FIRST_NAME, LAST_NAME, GENDER, " +
					"DATE_OF_BIRTH, DATE_OF_JOINING, DATE_OF_START, DURATION_IN_WEEKS, EMAIL_ADDRESS from USER_DETAIL");
			rolePstmt = con.prepareStatement("select * from USER_ROLE");
			
			UserInfo userInfo = null;

			// Loading users
			userRs = userPstmt.executeQuery();
			while(userRs.next())
			{
				userInfo = new UserInfo(Recipient.PERSON);
				userInfo.setUserId(userRs.getString("USER_ID"));
				userInfo.setFirstName(userRs.getString("FIRST_NAME"));
				userInfo.setLastName(userRs.getString("LAST_NAME"));
				userInfo.setGender(userRs.getString("GENDER").equalsIgnoreCase("M")?"Male":"Female");
				userInfo.setDateOfBirth(DesignerUtils.formatDate(userRs.getDate("DATE_OF_BIRTH")));
				userInfo.setDateOfJoining(DesignerUtils.formatDate(userRs.getDate("DATE_OF_JOINING")));
				userInfo.setDateOfStart(DesignerUtils.formatDate(userRs.getDate("DATE_OF_START")));
				userInfo.setDurationInWeeks(userRs.getInt("DURATION_IN_WEEKS"));
				userInfo.setEmailAddress(userRs.getString("EMAIL_ADDRESS"));
				userInfo.setEntryType(Recipient.PERSON);
				
				
				loadRolesForUser(userInfo, con);
				loadGroupsForUser(userInfo, con);
				
				userList.add(userInfo);
			}
			
			//loading user Roles
			roleRs = rolePstmt.executeQuery();
			while(roleRs.next())
			{
				userInfo = new UserInfo(Recipient.ROLE);
				userInfo.setUserId(roleRs.getString("ROLE_TAG"));
				userInfo.setFirstName(roleRs.getString("DESCRIPTION"));
				userInfo.setEntryType(Recipient.ROLE);
				userList.add(userInfo);
			}
		
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally {
			try {
				if(userRs != null)
					userRs.close();
				if(roleRs != null)
					roleRs.close();
				if(userPstmt != null)
					userPstmt.close();
				if(rolePstmt != null)
					rolePstmt.close();
				if(con != null || !con.isClosed())
					con.close();
			}
			catch(SQLException e) {}
		}
		
		return userList;
	}
	
	private void loadRolesForUser(UserInfo userInfo, Connection con) throws SQLException
	{
		PreparedStatement pstmt = con.prepareStatement("select ROLE_TAG from USER_ROLE, USER_DETAIL where USER_ROLE.ROLE_ID = USER_DETAIL.ROLE_ID and USER_DETAIL.USER_ID = ?");
		pstmt.setString(1, userInfo.getUserId());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			userInfo.addRole(rs.getString(1));
		}
			
		
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
	}
	private void loadGroupsForUser(UserInfo userInfo, Connection con) throws SQLException
	{
		PreparedStatement pstmt = con.prepareStatement("select GROUP_TAG from USER_GROUP, USER_DETAIL, USER_GROUP_LINK where USER_GROUP.GROUP_ID = USER_GROUP_LINK.GROUP_ID and USER_GROUP_LINK.USER_ID = USER_DETAIL.ROLE_ID and USER_DETAIL.USER_ID = ?");
		pstmt.setString(1, userInfo.getUserId());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
			userInfo.addGroup(rs.getString(1));
		
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
	}
}
