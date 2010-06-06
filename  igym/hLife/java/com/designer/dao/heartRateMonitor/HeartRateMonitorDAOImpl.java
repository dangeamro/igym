package com.designer.dao.heartRateMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.controller.heartRateMonitor.HeartRateMonitorInfo;
import com.designer.model.heartRateMonitor.HeartRateMonitor;

public class HeartRateMonitorDAOImpl implements HeartRateMonitorDAO {

	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public void addHeartRateMonitor(HeartRateMonitor heartRateMonitor) throws ApplicationException{
	
		Connection con = null;
		PreparedStatement pstmtMonitor = null;
		PreparedStatement pstmtMonitorId = null;
		PreparedStatement pstmtReading = null;
		ResultSet monitorIdResultSet = null;
		
		StringBuffer monitorIdQry = new StringBuffer("SELECT MAX(ID) ID FROM HEART_RATE_MONITOR ")
					.append(" WHERE USER_ID = ? ");
		
		if(heartRateMonitor.getReadings() == null || heartRateMonitor.getReadings().size() == 0)
		{
			System.err.println("No reading sent from UI.");
			return;
		}
		try
		{
			con = appDataSource.getConnection();
			con.setAutoCommit(false);
			pstmtMonitor = con.prepareStatement("INSERT INTO HEART_RATE_MONITOR VALUES (?,?,?,?,?)");
			pstmtMonitorId = con.prepareStatement(monitorIdQry.toString());
			pstmtReading = con.prepareStatement("INSERT INTO HEART_RATE_READINGS VALUES (?,?,?)");
			
			pstmtMonitor.setString(1, heartRateMonitor.getUserId());
			pstmtMonitor.setTimestamp(2, new Timestamp(heartRateMonitor.getDateTime().getTime()));
			pstmtMonitor.setInt(3, heartRateMonitor.getInterval());
			pstmtMonitor.setInt(4, heartRateMonitor.getStartGradation());
			pstmtMonitor.setInt(5, heartRateMonitor.getEndGradation());
			
			pstmtMonitorId.setString(1, heartRateMonitor.getUserId());
			//pstmtMonitorId.setTimestamp(2, new Timestamp(heartRateMonitor.getDateTime().getTime()));
			
			try{
				pstmtMonitor.executeUpdate();
				
				monitorIdResultSet = pstmtMonitorId.executeQuery();
				
				if(monitorIdResultSet.next()){
					int heartRateMonitorId = monitorIdResultSet.getInt("ID");
					int cnt = 0;					
					for(Integer reading:heartRateMonitor.getReadings()){
						pstmtReading.setInt(1, heartRateMonitorId);
						pstmtReading.setInt(2, cnt++);
						pstmtReading.setInt(3, reading);
						pstmtReading.executeUpdate();
					}
					
				}
				
				con.commit();
			}catch(SQLException se){
				con.rollback();
				throw new ApplicationException(se.getMessage());
			}		
		}catch (SQLException se){
			throw new ApplicationException(se.getMessage());
		}finally{
			
			try{
				if(monitorIdResultSet != null)
					monitorIdResultSet.close();

				if(pstmtReading != null)
					pstmtReading.close();

				if(pstmtMonitorId != null)
					pstmtMonitorId.close();

				if(pstmtMonitor != null)
					pstmtMonitor.close();

				if(con != null && !con.isClosed())
					con.close();
			}catch(SQLException se){
				//do nothing
			}
		}
	}

	
	/**
	 * Returns the user's heart rate monitor entry for a given row number.
	 */
	public HeartRateMonitor getHeartRateMonitor(String userId, int pageNum) throws ApplicationException{
		
		HeartRateMonitor heartRateMonitor = null;
		
		Connection con = null;
		PreparedStatement monitorPstmt = null;
		PreparedStatement readingPstmt = null;
		ResultSet monitorRs = null;
		ResultSet readingRs = null;
		StringBuffer monitorQuery = new StringBuffer();
		
		monitorQuery.append("SELECT * FROM HEART_RATE_MONITOR WHERE DATE_TIME IN ")
			.append(" (select min(date_time) from ")
			.append(" (select distinct top " + pageNum + " date_time  ")
			.append(" from heart_rate_monitor ")
			.append(" where user_id = ? ")
			.append(" order by date_time desc) inner_qry) ");
		
		try{
			con = appDataSource.getConnection();
			monitorPstmt = con.prepareStatement(monitorQuery.toString());
			
			monitorPstmt.setString(1, userId);
			
			monitorRs = monitorPstmt.executeQuery();
			if(monitorRs.next()){
				heartRateMonitor = new HeartRateMonitor();
				heartRateMonitor.setId(monitorRs.getInt("ID"));
				heartRateMonitor.setUserId(monitorRs.getString("USER_ID"));
				heartRateMonitor.setDateTime(monitorRs.getTimestamp("DATE_TIME"));
				heartRateMonitor.setInterval(monitorRs.getInt("INTERVAL"));
				heartRateMonitor.setStartGradation(monitorRs.getInt("START_GRADATION"));
				heartRateMonitor.setEndGradation(monitorRs.getInt("END_GRADATION"));
			}
			
			if(heartRateMonitor != null){
				readingPstmt = con.prepareStatement("SELECT READING FROM HEART_RATE_READINGS WHERE MONITOR_ID = ? ORDER BY SEQUENCE");
				readingPstmt.setInt(1, heartRateMonitor.getId());
				
				readingRs = readingPstmt.executeQuery();
				
				List<Integer> readings = new ArrayList<Integer>();
				while(readingRs.next()){
					readings.add(readingRs.getInt("READING"));
				}
				
				heartRateMonitor.setReadings(readings);
			}
			
		}catch(SQLException se){
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(monitorRs != null){
					monitorRs.close();
				}
				
				if(readingRs != null){
					readingRs.close();
				}
				if(monitorPstmt != null){
					monitorPstmt.close();
				}
				if(readingPstmt != null){
					readingPstmt.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
 		}  
			
		return heartRateMonitor;
	}
	
	/**
	 * Returns the user's heart rate monitor entry for a given date.
	 */
	public HeartRateMonitor getHeartRateMonitor(String userId, Date date) throws ApplicationException{
		
		HeartRateMonitor heartRateMonitor = null;
		
		Connection con = null;
		PreparedStatement monitorPstmt = null;
		PreparedStatement readingPstmt = null;
		ResultSet monitorRs = null;
		ResultSet readingRs = null;
		
		try{
			con = appDataSource.getConnection();
			monitorPstmt = con.prepareStatement("SELECT * FROM HEART_RATE_MONITOR WHERE DATE_TIME = ? AND USER_ID = ? ");
			
			monitorPstmt.setTimestamp(1, new Timestamp(date.getTime()));
			monitorPstmt.setString(2, userId);
			
			monitorRs = monitorPstmt.executeQuery();
			if(monitorRs.next()){
				heartRateMonitor = new HeartRateMonitor();
				heartRateMonitor.setId(monitorRs.getInt("ID"));
				heartRateMonitor.setUserId(monitorRs.getString("USER_ID"));
				heartRateMonitor.setDateTime(monitorRs.getTimestamp("DATE_TIME"));
				heartRateMonitor.setInterval(monitorRs.getInt("INTERVAL"));
				heartRateMonitor.setStartGradation(monitorRs.getInt("START_GRADATION"));
				heartRateMonitor.setEndGradation(monitorRs.getInt("END_GRADATION"));
			}
			
			if(heartRateMonitor != null){
				readingPstmt = con.prepareStatement("SELECT READING FROM HEART_RATE_READINGS WHERE MONITOR_ID = ? ORDER BY SEQUENCE");
				readingPstmt.setInt(1, heartRateMonitor.getId());
				
				readingRs = readingPstmt.executeQuery();
				
				List<Integer> readings = new ArrayList<Integer>();
				while(readingRs.next()){
					readings.add(readingRs.getInt("READING"));
				}
				
				heartRateMonitor.setReadings(readings);
			}
			
		}catch(SQLException se){
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(monitorRs != null){
					monitorRs.close();
				}
				
				if(readingRs != null){
					readingRs.close();
				}
				if(monitorPstmt != null){
					monitorPstmt.close();
				}
				if(readingPstmt != null){
					readingPstmt.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
 		}
			
		return heartRateMonitor;
	}
	
	public List<HeartRateMonitor> getMonitorsBetweenDates(String userId, Date fromDate, Date toDate) throws ApplicationException{
		
		Connection con = null;
		PreparedStatement monitorPstmt = null;
		PreparedStatement readingPstmt = null;
		ResultSet monitorRs = null;
		ResultSet readingRs = null;

		List<HeartRateMonitor> heartRateMonitors = new ArrayList<HeartRateMonitor>();
		
		try{
			con = appDataSource.getConnection();
			monitorPstmt = con.prepareStatement("SELECT * FROM  HEART_RATE_MONITOR WHERE USER_ID = ? AND DATE_TIME >= ? AND DATE_TIME <= ? ");
			readingPstmt = con.prepareStatement("SELECT READING FROM HEART_RATE_READINGS WHERE MONITOR_ID = ? ORDER BY SEQUENCE");
			
			monitorPstmt.setString(1, userId);
			monitorPstmt.setTimestamp(2,new Timestamp(fromDate.getTime()));
			monitorPstmt.setTimestamp(3,new Timestamp(toDate.getTime()));
			
			monitorRs = monitorPstmt.executeQuery();

			while(monitorRs.next()){
				HeartRateMonitor heartRateMonitor = new HeartRateMonitor();
				heartRateMonitor = new HeartRateMonitor();
				heartRateMonitor.setId(monitorRs.getInt("ID"));
				heartRateMonitor.setUserId(monitorRs.getString("USER_ID"));
				heartRateMonitor.setDateTime(monitorRs.getTimestamp("DATE_TIME"));
				heartRateMonitor.setInterval(monitorRs.getInt("INTERVAL"));
				heartRateMonitor.setStartGradation(monitorRs.getInt("START_GRADATION"));
				heartRateMonitor.setEndGradation(monitorRs.getInt("END_GRADATION"));

				if(heartRateMonitor != null){
					readingPstmt.setInt(1, heartRateMonitor.getId());
					readingRs = readingPstmt.executeQuery();
					
					List<Integer> readings = new ArrayList<Integer>();
					while(readingRs.next()){
						readings.add(readingRs.getInt("READING"));
					}
					
					heartRateMonitor.setReadings(readings);
				}
				
				heartRateMonitors.add(heartRateMonitor);
			}
		}
		catch (SQLException se) 
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(monitorRs != null){
					monitorRs.close();
				}
				
				if(readingRs != null){
					readingRs.close();
				}
				if(monitorPstmt != null){
					monitorPstmt.close();
				}
				if(readingPstmt != null){
					readingPstmt.close();
				}
				if(con != null && !con.isClosed()){
					con.close();
				}
			} catch (SQLException se) {
				// do nothing
			}
 		}
		return heartRateMonitors;

	}

	public List<HeartRateMonitorInfo> getAllMonitors(String userId) throws ApplicationException{
		
		//int pageCount=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HeartRateMonitorInfo> heartRateMonitors = new ArrayList<HeartRateMonitorInfo>();
		try{
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM  HEART_RATE_MONITOR WHERE USER_ID = ?");
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
		
			while( rs.next() ) {
				HeartRateMonitorInfo heartRateMonitorInfo = new HeartRateMonitorInfo();
				
				heartRateMonitorInfo.setDateTime(rs.getTimestamp("DATE_TIME"));
				heartRateMonitorInfo.setInterval(rs.getInt("INTERVAL"));
				heartRateMonitorInfo.setStartGradation(rs.getInt("START_GRADATION"));
				heartRateMonitorInfo.setEndGradation(rs.getInt("END_GRADATION"));
				heartRateMonitors.add(heartRateMonitorInfo);
		    } 
		} catch (SQLException se) {
			throw new ApplicationException(se.getMessage());
		}finally{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null && !con.isClosed())
					con.close();
			} catch (SQLException se) {
				// do nothing
			}
 		}  

		return heartRateMonitors;
	}

}
