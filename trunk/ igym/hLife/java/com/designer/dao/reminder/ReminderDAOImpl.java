package com.designer.dao.reminder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.DesignerUtils;
import com.designer.common.framework.UserMap;
import com.designer.common.util.ReminderDateCheck;
import com.designer.common.util.ReminderInstance;
import com.designer.model.reminder.Module;
import com.designer.model.reminder.Reminder;

public class ReminderDAOImpl implements ReminderDAO{
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public List<ReminderInstance> getReminders(String moduleKey) throws ApplicationException
	{
		List<ReminderInstance> extExpList= new ArrayList<ReminderInstance>();
		List<ReminderInstance> dateCheckList= new ArrayList<ReminderInstance>();
		List<ReminderInstance> resultList= new ArrayList<ReminderInstance>();
		
		Connection con = null;
		PreparedStatement expPstmt = null;
		PreparedStatement datePstmt = null;
		PreparedStatement reminderPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet expRs = null;
		ResultSet dateRs = null;
		ResultSet reminderRs = null;
		ResultSet resultRs = null;
		try
		{
			con = appDataSource.getConnection();
			
			reminderPstmt = con.prepareStatement("select MODULES.SOURCE_QUERY, REMINDER.* from MODULES, REMINDER where MODULE_TAG = ? and MODULES.ID = REMINDER.MODULE_ID and MODULES.STATUS_FLAG = 'ON' and REMINDER.STATUS_FLAG = 'ON'");
			expPstmt = con.prepareStatement("select * from REMINDER_EXT_EXPRESSION where REMINDER_ID = ?");
			datePstmt = con.prepareStatement("select * from REMINDER_DATE_CHECK where REMINDER_ID = ?");
			
			reminderPstmt.setString(1, moduleKey);
			
			reminderRs = reminderPstmt.executeQuery();
			String sourceQuery = null;
			if(reminderRs.next())
			{
				sourceQuery = reminderRs.getString("SOURCE_QUERY").trim();
				if(sourceQuery == null || sourceQuery.equals(""))
					throw new ApplicationException("Source query empty for module:" + moduleKey);
			}
			else
				return resultList;
			
			do {
				// External expression part
				expPstmt.setInt(1, reminderRs.getInt("ID"));
				expRs = expPstmt.executeQuery();
				if(expRs.next())
					resultPstmt = con.prepareStatement(sourceQuery + " where " + expRs.getString("EXPRESSION"));
				else
					resultPstmt = con.prepareStatement(sourceQuery);
				
				resultRs = resultPstmt.executeQuery();
				while(resultRs.next())
				{
					ReminderInstance rem = new ReminderInstance();
					rem.setBody(formatBody(reminderRs, resultRs, con));
					rem.setModulePrimaryKeyId(resultRs.getInt(reminderRs.getString("MODULE_PRIMARY_KEY")));
					String str = reminderRs.getString("RECEIVER_PARAM");
					str = str.replace("{customer}", resultRs.getString("USER_ID"));
					rem.setRecipient(str);
					
					extExpList.add(rem);					
				}
				
				if(expRs != null)
					expRs.close();
				if(resultPstmt != null)
					resultPstmt.close();
				if(resultRs != null)
					resultRs.close();
				

				// Date check part
				datePstmt.setInt(1, reminderRs.getInt("ID"));
				dateRs = datePstmt.executeQuery();
				if(dateRs.next())
				{
					resultPstmt = con.prepareStatement(sourceQuery);
					resultRs = resultPstmt.executeQuery();
					long timeMilis = System.currentTimeMillis();
					while(resultRs.next())
					{
						if(timeMilis - resultRs.getTimestamp(dateRs.getString("COL_SQL_NAME")).getTime() > dateRs.getLong("OLDER_THAN") * 24 * 60 * 60 * 1000)
						{
							ReminderInstance rem = new ReminderInstance();
							rem.setBody(formatBody(reminderRs, resultRs, con));
							rem.setModulePrimaryKeyId(resultRs.getInt(reminderRs.getString("MODULE_PRIMARY_KEY")));
							String str = reminderRs.getString("RECEIVER_PARAM");
							str = str.replace("{customer}", resultRs.getString("USER_ID"));
							rem.setRecipient(str);
							
							dateCheckList.add(rem);
						}
					}
					if(resultPstmt != null)
						resultPstmt.close();
					if(resultRs != null)
						resultRs.close();
				}
				else
					dateCheckList.addAll(extExpList);
				
				if(dateRs != null)
					dateRs.close();
			
			}while(reminderRs.next());
			
			
			// By default merging the two result Sets for AND condition
			for(ReminderInstance rem : extExpList)
			{
				if(dateCheckList.contains(rem))
					resultList.add(rem);
			}
			
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally
		{
			try {
				if(expPstmt != null)
					expPstmt.close();
				if(datePstmt != null)
					datePstmt.close();
				if(reminderPstmt != null)
					reminderPstmt.close();
				if(reminderRs != null)
					reminderRs.close();
				if(con != null)
					con.close();
				
			}catch(SQLException ex){}
			
		}
		
		return resultList;
	}
	
	public List<Reminder> getRemindersForModule(String moduleKey) throws ApplicationException {
		return getModule(moduleKey).getReminders();
	}

	public Module getModule(String moduleKey) throws ApplicationException {

		Connection con = null;
		PreparedStatement modPstmt = null;
		ResultSet modRs = null;
		PreparedStatement remPstmt = null;
		ResultSet remRs = null;
		Module module = new Module();
		try
		{
			con = appDataSource.getConnection();
			modPstmt = con.prepareStatement("select * from MODULES where MODULE_TAG = ?");
			modPstmt.setString(1, moduleKey);
			modRs = modPstmt.executeQuery();
			modRs.next();
			
			populateModule(module, modRs);
			
			remPstmt = con.prepareStatement("select * from REMINDER where REMINDER.MODULE_ID = ?");
			remPstmt.setInt(1, module.getId());
			remRs = remPstmt.executeQuery();
			while(remRs.next())
			{
				Reminder rem = new Reminder();
				rem.setDesc(remRs.getString("REMINDER_DESC"));
				rem.setName(remRs.getString("REMINDER_NAME"));
				rem.setStatusFlag(remRs.getString("STATUS_FLAG"));
				rem.setMessageBody(remRs.getString("REMINDER_MESSAGE_BODY"));
				rem.setId(remRs.getInt("ID"));
				
				loadDateChecks(rem, con);
				
				module.addReminder(rem);
			}
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
		}
		finally
		{
			try
			{
				if(remRs != null)
					remRs.close();
				if(remPstmt != null)
					remPstmt.close();
				if(modRs != null)
					modRs.close();
				if(modPstmt != null)
					modPstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException ex){}
		}

		return module;
	}

	public List<String> getModuleKeys() throws ApplicationException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> keys = new ArrayList<String>();
		try
		{
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("select MODULE_TAG from MODULES");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				keys.add(rs.getString(1));
			}
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
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
			catch(SQLException ex){}
		}

		return keys;
	}
	public List<Module> getModules() throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Module module = null;
		List<Module> modules = new ArrayList<Module>();
		try
		{
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("select * from MODULES");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				module = new Module();
				populateModule(module, rs);
				modules.add(module);
			}
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
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
			catch(SQLException ex){}
		}

		return modules;
	}
	
	public void updateChecks(List<ReminderDateCheck> checks) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("update REMINDER_DATE_CHECK set OLDER_THAN = ? where REMINDER_ID = ? and COL_SQL_NAME = ?");
			for(ReminderDateCheck check : checks)
			{
				pstmt.setInt(1, check.getOlderThan());
				pstmt.setInt(2, check.getReminderId());
				pstmt.setString(3, check.getColSqlName());
				pstmt.executeUpdate();
			}
			con.commit();
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
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
			catch(SQLException ex){}
		}
	}

	public void updateEnabled(List<Integer> enabledList) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement("update REMINDER set STATUS_FLAG = 'OFF'");
			pstmt.executeUpdate();
			
			sql.append("update REMINDER set STATUS_FLAG = 'ON' where ID in(");
			for(Integer id : enabledList)
			{
				sql.append(id.toString()).append(',');
			}
			sql.setLength(sql.length() - 1);
			sql.append(')');
			pstmt2 = con.prepareStatement(sql.toString());
			pstmt2.executeUpdate();
			con.commit();
		}
		catch(SQLException ex)
		{
			throw new ApplicationException(ex.getMessage());
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
			catch(SQLException ex){}
		}
		
	}
	
	// private methods begins here
	private String formatBody(ResultSet reminderRs, ResultSet targetRs, Connection con) throws SQLException
	{
		StringBuffer bodyTemplate = new StringBuffer(reminderRs.getString("REMINDER_MESSAGE_BODY"));
		PreparedStatement pstmt = con.prepareStatement("select REMINDER_MSG_PARAMS.*, MODULE_METADATA.IS_DATE from REMINDER_MSG_PARAMS, REMINDER, MODULE_METADATA where REMINDER_MSG_PARAMS.REMINDER_ID = REMINDER.ID and REMINDER.MODULE_ID = MODULE_METADATA.MODULE_ID and REMINDER_MSG_PARAMS.COL_SQL_NAME = MODULE_METADATA.COL_SQL_NAME and REMINDER_ID = ?");
		pstmt.setInt(1, reminderRs.getInt("ID"));
		ResultSet rs = pstmt.executeQuery();
		String pattern;
		while(rs.next())
		{
			pattern = "{" + rs.getInt("POSITION") + '}';
			int pos;
			String replacement;
			String colName;
			while((pos = bodyTemplate.indexOf(pattern)) != -1)
			{
				colName = rs.getString("COL_SQL_NAME");
				replacement = rs.getString("IS_DATE").equals("N")? targetRs.getString(colName):DesignerUtils.formatDate(targetRs.getDate(colName)); 
				bodyTemplate.replace(pos, pos + pattern.length(), colName.equalsIgnoreCase("USER_ID")? UserMap.getInstance().getUserInfo(replacement).getDisplayName():replacement);
			}
		}
		
		return bodyTemplate.toString();
	}
	
	private void populateModule(Module module, ResultSet rs) throws SQLException
	{
		module.setId(rs.getInt("ID"));
		module.setDesc(rs.getString("MODULE_DESC"));
		module.setManagerClass(rs.getString("MODULE_MANAGER_CLASS"));
		module.setName(rs.getString("MODULE_NAME"));
		module.setStatusFlag(rs.getString("STATUS_FLAG"));
		module.setTag(rs.getString("MODULE_TAG"));
	}
	
	private void loadDateChecks(Reminder rem, Connection con) throws SQLException
	{
		PreparedStatement pstmt = con.prepareStatement("select MODULE_METADATA.COL_DISP_NAME, REMINDER_DATE_CHECK.* from REMINDER, REMINDER_DATE_CHECK, MODULE_METADATA where MODULE_METADATA.COL_SQL_NAME = REMINDER_DATE_CHECK.COL_SQL_NAME and MODULE_METADATA.MODULE_ID = REMINDER.MODULE_ID and REMINDER.ID =  REMINDER_DATE_CHECK.REMINDER_ID and REMINDER_DATE_CHECK.REMINDER_ID = ?");
		pstmt.setInt(1, rem.getId());
		ResultSet rs = pstmt.executeQuery();
		ReminderDateCheck check = null;
		while(rs.next())
		{
			check = new ReminderDateCheck(rem.getId(), rs.getString("COL_SQL_NAME"), rs.getString("COL_DISP_NAME"), rs.getInt("OLDER_THAN"));
			rem.addDateCheck(check);
		}
	}

}
