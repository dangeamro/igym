package com.designer.dao.statusAndMeasurement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.model.statusAndMeasurements.AutomatedMeasurements;
import com.designer.model.statusAndMeasurements.ReportCardMeasurements;
import com.designer.model.statusAndMeasurements.StatusAndMeasurement;

public class StatusAndMeasurementDAOImpl implements StatusAndMeasurementDAO{
	
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public void addStatusAndMeasurement(StatusAndMeasurement sm) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = appDataSource.getConnectionFactory().getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO STAT_MEASURE_DETAIL(USER_ID,STATUS_DATE,NECK,WEIGHT,UPPER_CHEST, ");
			sql.append(" U_ARMS_RIGHT,U_ARMS_LEFT,WAIST,HIPS,U_THIGHS_RIGHT,U_THIGHS_LEFT, ");
			sql.append(" L_THIGHS_RIGHT,L_THIGHS_LEFT,CALVES_RIGHT,CALVES_LEFT,ANKLES_RIGHT,ANKLES_LEFT,STATUS)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'A')");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1,sm.getUserId());
			pstmt.setTimestamp(2,sm.getDate());
			pstmt.setDouble(3,sm.getNeck());
			pstmt.setDouble(4,sm.getWeight());
			pstmt.setDouble(5,sm.getUpperChest());
			pstmt.setDouble(6,sm.getUppArmRight());
			pstmt.setDouble(7,sm.getUppArmLeft());
			pstmt.setDouble(8,sm.getWaist());
			pstmt.setDouble(9,sm.getHips());
			pstmt.setDouble(10,sm.getUppThighsRight());
			pstmt.setDouble(11,sm.getUppThighsLeft());
			pstmt.setDouble(12,sm.getLowThighsRight());
			pstmt.setDouble(13,sm.getLowThighsLeft());
			pstmt.setDouble(14,sm.getCalvesRight());
			pstmt.setDouble(15,sm.getCalvesLeft());
			pstmt.setDouble(16,sm.getAnklesRight());
			pstmt.setDouble(17,sm.getAnklesLeft());
			
			pstmt.executeUpdate();
			
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(pstmt != null)
					pstmt.close();

				if(con != null)
					con.close();
			}
			catch (SQLException se) {}
		} 
	}
	
	public int getPageCount(String userId) throws ApplicationException {
		
		int pageCount=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement("SELECT COUNT (STATUS_DATE) FROM STAT_MEASURE_DETAIL WHERE USER_ID = ?");
	 
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery(); 
		
			if(rs.next()) 
				pageCount= rs.getInt(1);
		}
		catch (SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se){}
 		}  

		return pageCount;
}
	
	public int getPageAutomatedCount(String userId) throws ApplicationException {
		
		int pageCount=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnection();

		//	pstmt = con.prepareStatement("SELECT COUNT (STATUS_DATE) FROM STAT_MEASURE_DETAIL WHERE USER_ID = ?");
			
			pstmt = con.prepareStatement("SELECT COUNT(1) FROM AUTOMATED_MEASUREMENTS WHERE ID_2=? ");
			
			System.out.println("userId == "+userId);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery(); 
		
			if(rs.next()) 
				pageCount= rs.getInt(1);
		}
		catch (SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se){}
 		}  

		return pageCount;
}
	public StatusAndMeasurement getStatusAndMeasurements(String userId, Integer pageNum) throws ApplicationException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StatusAndMeasurement sm = null;
		try
		{
			con = appDataSource.getConnectionFactory().getConnection();
			StringBuffer sb = new StringBuffer();
			
			sb.append("SELECT * FROM STAT_MEASURE_DETAIL WHERE STATUS='A' AND" );
			sb.append(" STATUS_DATE IN ( SELECT MIN (DT_INNER_QRY.STATUS_DATE) AS  STATUS_DATE");
			sb.append(" FROM (SELECT DISTINCT TOP ");
			sb.append( pageNum );
			sb.append(" STATUS_DATE FROM  STAT_MEASURE_DETAIL");
			sb.append(" WHERE USER_ID = ?");
			sb.append(" ORDER BY STATUS_DATE DESC) DT_INNER_QRY) AND");
			sb.append(" USER_ID = ?");

			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				sm = new StatusAndMeasurement();
				
				sm.setUserId(userId);
				sm.setStatusId(rs.getInt("STATUS_ID"));
				sm.setDate(rs.getTimestamp("STATUS_DATE"));
				sm.setNeck(rs.getDouble("NECK"));
				sm.setUpperChest(rs.getDouble("UPPER_CHEST"));
				sm.setWeight(rs.getDouble("WEIGHT"));
				sm.setUppArmRight(rs.getDouble("U_ARMS_RIGHT"));
				sm.setUppArmLeft(rs.getDouble("U_ARMS_LEFT"));
				sm.setWaist(rs.getDouble("WAIST"));
				sm.setHips(rs.getDouble("HIPS"));
				sm.setUppThighsRight(rs.getDouble("U_THIGHS_RIGHT"));
				sm.setUppThighsLeft(rs.getDouble("U_THIGHS_LEFT"));
				sm.setLowThighsRight(rs.getDouble("L_THIGHS_RIGHT"));
				sm.setLowThighsLeft(rs.getDouble("L_THIGHS_LEFT"));
				sm.setCalvesRight(rs.getDouble("CALVES_RIGHT"));
				sm.setCalvesLeft(rs.getDouble("CALVES_LEFT"));
				sm.setAnklesLeft(rs.getDouble("ANKLES_LEFT"));
				sm.setAnklesRight(rs.getDouble("ANKLES_RIGHT"));
			}
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se) {}
		}
		
		return sm;
	}
	
	public void deleteStatusAndMeasurement(Integer smId) throws ApplicationException {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement("DELETE FROM STAT_MEASURE_DETAIL WHERE STATUS_ID=?");
			
			pstmt.setInt(1, smId);
			pstmt.executeUpdate();
			
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se){}
		} 
	}
	
	public void addNewAutomatedMeasurements(String dirFileName) throws ApplicationException {
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection conDB = null;
		PreparedStatement pstmtDB = null;
		try{			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String myDB ="jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+dirFileName;
			Connection conXl = DriverManager.getConnection(myDB);
			Statement stmtXl = conXl.createStatement();
			conDB = appDataSource.getConnectionFactory().getConnection();
			ResultSet rsDB;
			StringBuffer sql = new StringBuffer();
			StringBuffer sql1 = new StringBuffer();
			ResultSet rsXl = stmtXl.executeQuery( "SELECT * FROM [db_backup$]");

			//ResultSetMetaData rsmd =rsXl.getMetaData();
			//int noOfCols=rsmd.getColumnCount();
			while(rsXl.next()){
					sql.setLength(0);
					String date_Time = rsXl.getString(1);
					DateFormat myDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					Timestamp timestamp = new Timestamp(myDateFormat.parse(date_Time).getTime());
					String id = rsXl.getString(2);
					sql.append(" SELECT * FROM AUTOMATED_MEASUREMENTS WHERE DATE_TIMES_1 = ? AND ID_2 = ? ");
					pstmtDB = conDB.prepareStatement(sql.toString());
					pstmtDB.setTimestamp(1, timestamp);
					pstmtDB.setString(2, id);
					rsDB = pstmtDB.executeQuery();					
					if(!rsDB.next()){					
						pstmtDB=null;
						sql1.setLength(0);
						sql1.append("INSERT INTO AUTOMATED_MEASUREMENTS VALUES ");
						sql1.append("(?,?,?,?,?,?,?,?,?,?,");
						sql1.append("?,?,?,?,?,?,?,?,?,?,");
						sql1.append("?,?,?,?,?,?,?,?,?,?,");
						sql1.append("?,?,?,?,?,?,?,?,?,?,");
						sql1.append("?,?,?,?,?,?,?,?,?,?,");
						sql1.append("?,?,?,?,?,?,?,?,?,?,");
						sql1.append("?,?,?,?,?)");
						pstmtDB=conDB.prepareStatement(sql1.toString());
						pstmtDB.setTimestamp(1, timestamp);
						pstmtDB.setString(2, id);
						Double num;
						int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,22,23,24,25,26,27,29,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,125,126,127,132,133,135,138,139,141,142,143,144,145,146,147,148,149,150,154,165,166,167,171};
						for(int i=3;i<=63;i++){
							String str = rsXl.getString(arr[i-1]);
							num = 0.0;
							//System.out.println("-- "+arr[i]+"-- Value -- "+ str);
							if(!str.equals("(null)")){
								num = Double.parseDouble(str);
							}
							pstmtDB.setDouble(i, num);						
						}
						
						pstmtDB.setString(64, rsXl.getString(167));
						pstmtDB.setDouble(65, Double.parseDouble(rsXl.getString(171)));
						pstmtDB.executeUpdate();
						
						System.out.println("Automated Status Row Inserted!!");
					}
			}
			
			if(stmtXl != null){
			stmtXl.close();
			}
			//	rsXl.close();
			
		}catch(Exception se){
			System.out.println("Error while opening connection");
			se.printStackTrace();
		}finally{
			try {
				if(pstmtDB != null){
					pstmtDB.close();
				}

				if(conDB != null){
					conDB.close();
				}
			} catch (SQLException se) {
				System.out.println("Error while closing connection");
				se.printStackTrace();
			}
		} 
	}
	
public AutomatedMeasurements getAutomatedMeasurements(String userId,int pageNum) throws ApplicationException {
		
		Connection con = null;
		//PreparedStatement pstmt = null;
		Statement stmt = null; 
		ResultSet rs = null;
		AutomatedMeasurements asm = null;
		StringBuffer sb = new StringBuffer();
		try
		{
			con = appDataSource.getConnection();
		//	System.out.println("I am in Action and Ant Test== DAOIPM");
			sb.append(" SELECT * FROM AUTOMATED_MEASUREMENTS WHERE ID_2='");
			sb.append(userId );
			sb.append("' AND DATE_TIMES_1 IN " );
			sb.append(" ( SELECT MIN (DT_INNER_QRY.DATE_TIMES_1) AS  DATE_TIMES_1 " );
			sb.append(" FROM (SELECT DISTINCT TOP  " );
			sb.append(pageNum);
			sb.append(" DATE_TIMES_1 FROM  AUTOMATED_MEASUREMENTS" );
			sb.append(" WHERE ID_2 = '" );
			sb.append(userId );
			sb.append("' ORDER BY DATE_TIMES_1 DESC)  DT_INNER_QRY )" );
					
//			SELECT * FROM AUTOMATED_MEASUREMENTS WHERE ID_2='DWAIN'
//				AND DATE_TIMES_1 IN
//				( SELECT MIN (DT_INNER_QRY.DATE_TIMES_1) AS  DATE_TIMES_1
//				 FROM (SELECT DISTINCT TOP 3
//				 DATE_TIMES_1 FROM  AUTOMATED_MEASUREMENTS
//				 WHERE ID_2 = 'DWAIN'
//				 ORDER BY DATE_TIMES_1 DESC)  DT_INNER_QRY )
			
//			pstmt = con.prepareStatement("SELECT * FROM AUTOMATED_MEASUREMENTS WHERE ID_2=?");
		//	pstmt = con.prepareStatement(sb.toString());
			
			stmt = con.createStatement(); 
//			pstmt.setString(1, userId);			
//			pstmt.setInt(2, pageNum);
//			pstmt.setString(3, userId);
		//	System.out.println("I am in Action and Ant Test== 3 b4 rs "+sb.toString());
		//	rs= pstmt.executeQuery();
			rs = stmt.executeQuery(sb.toString()); 
		//	System.out.println("I am in Action and Ant Test== b4 rs.next ");
			if(rs.next()){
				asm = new AutomatedMeasurements();
				
				asm.setDate_Time(rs.getTimestamp(1));
				asm.setId(rs.getString(2));
				asm.setIcw(rs.getDouble(3));
				asm.setIcw_Min(rs.getDouble(4));
				asm.setIcw_max(rs.getDouble(5));
				asm.setEcw(rs.getDouble(6));
				asm.setEcw_min(rs.getDouble(7));
				asm.setEcw_max(rs.getDouble(8));
				asm.setProtein(rs.getDouble(9));
				asm.setProtein_Min(rs.getDouble(10));
				
				asm.setProtein_Max(rs.getDouble(11));
				asm.setMineral(rs.getDouble(12));
				asm.setMineral_Min(rs.getDouble(13));
				asm.setMineral_Max(rs.getDouble(14));
				asm.setFat(rs.getDouble(15));
				asm.setFat_Min(rs.getDouble(16));
				asm.setFat_Max(rs.getDouble(17));
				asm.setTbw(rs.getDouble(18));
				asm.setLeanBodyMass(rs.getDouble(19));
				asm.setWeight(rs.getDouble(20));
				
				asm.setIdeal_Icw(rs.getDouble(21));
				asm.setIdeal_Ecw(rs.getDouble(22));
				asm.setIdeal_Protein(rs.getDouble(23));
				asm.setIdeal_mineral(rs.getDouble(24));
				asm.setIdeal_Fat(rs.getDouble(25));
				asm.setIdeal_Tbw(rs.getDouble(26));
				asm.setEcwTbw(rs.getDouble(27));
				asm.setLbmRightArm(rs.getDouble(28));
				asm.setPcntLbmRightArm(rs.getDouble(29));
				asm.setSecondGraphRightArm(rs.getDouble(30));
								
				asm.setLbmLeftArm(rs.getDouble(31));
				asm.setPcntLbmLeftArm(rs.getDouble(32));
				asm.setSecondGraphLeftArm(rs.getDouble(33));				
				asm.setLbmTrunk(rs.getDouble(34));				
				asm.setPcntLbmTrunk(rs.getDouble(35));
				asm.setSecondGraphTrunk(rs.getDouble(36));				
				asm.setLbmRightLeg(rs.getDouble(37));
				asm.setPcntLbmRightLeg(rs.getDouble(38));
				asm.setSecondGraphRightLeg(rs.getDouble(39));
				asm.setLbmLeftLeg(rs.getDouble(40));
				
				asm.setPcntLbmLeftLeg(rs.getDouble(41));
				asm.setSecondGraphLeftLeg(rs.getDouble(42));
				asm.setPcntBodyWeight(rs.getDouble(43));
				asm.setWt_Min(rs.getDouble(44));
				asm.setWt_Max(rs.getDouble(45));
				asm.setPcntBodyFat(rs.getDouble(46));
				asm.setIdeal_Weight(rs.getDouble(47));
				asm.setPcntFat(rs.getDouble(48));
				asm.setPcntTbw(rs.getDouble(49));
				asm.setIdealPcntBodyFat(rs.getDouble(50));
				
				asm.setPcntLeanBodyMass(rs.getDouble(51));
				asm.setIdealLeanBodyMass(rs.getDouble(52));
				asm.setPbf_Min1(rs.getDouble(53));
				asm.setPbf_Max1(rs.getDouble(54));
				asm.setBmi(rs.getDouble(55));
				asm.setBmi_Min(rs.getDouble(56));
				asm.setBmi_Max(rs.getDouble(57));
				asm.setPbf(rs.getDouble(58));
				asm.setPbf_Min2(rs.getDouble(59));
				asm.setPbf_Max2(rs.getDouble(60));
				
				asm.setIdealBmi(rs.getDouble(61));
				asm.setHeight(rs.getDouble(62));
				asm.setAge(rs.getDouble(63));
				asm.setSex(rs.getString(64));
				asm.setBasalMetabolicRate(rs.getDouble(65));
								
			}
			
		}
		catch(SQLException se)
		{
			System.out.println("I am in Action and Ant Test== catch "+se.getMessage());
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se) {}
		} 
	
		return asm;
	}
	
public ArrayList<AutomatedMeasurements> compareAutomatedMeasurements(String userId, Date fromDate, Date todate)throws ApplicationException{
	ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<AutomatedMeasurements> measurementList = new ArrayList<AutomatedMeasurements>();
	try{
		con = appDataSource.getConnectionFactory().getConnection();
		StringBuffer sql = new StringBuffer();
		
		
		
		
		sql.append(" SELECT * FROM AUTOMATED_MEASUREMENTS WHERE ID_2=? AND DATE_TIMES_1 >= ? AND DATE_TIMES_1 <= ? ");
		
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, userId);
		pstmt.setTimestamp(2, new Timestamp(fromDate.getTime()));
		pstmt.setTimestamp(3, new Timestamp(todate.getTime()));
		
		rs= pstmt.executeQuery();
		
		while(rs.next()){
			AutomatedMeasurements automatedMeasurements = new AutomatedMeasurements();
			automatedMeasurements.setDate_Time(rs.getTimestamp(1));
			automatedMeasurements.setId(rs.getString(2));
			automatedMeasurements.setIcw(rs.getDouble(3));
			automatedMeasurements.setIcw_Min(rs.getDouble(4));
			automatedMeasurements.setIcw_max(rs.getDouble(5));
			automatedMeasurements.setEcw(rs.getDouble(6));
			automatedMeasurements.setEcw_min(rs.getDouble(7));
			automatedMeasurements.setEcw_max(rs.getDouble(8));
			automatedMeasurements.setProtein(rs.getDouble(9));
			automatedMeasurements.setProtein_Min(rs.getDouble(10));
			
			automatedMeasurements.setProtein_Max(rs.getDouble(11));
			automatedMeasurements.setMineral(rs.getDouble(12));
			automatedMeasurements.setMineral_Min(rs.getDouble(13));
			automatedMeasurements.setMineral_Max(rs.getDouble(14));
			automatedMeasurements.setFat(rs.getDouble(15));
			automatedMeasurements.setFat_Min(rs.getDouble(16));
			automatedMeasurements.setFat_Max(rs.getDouble(17));
			automatedMeasurements.setTbw(rs.getDouble(18));
			automatedMeasurements.setLeanBodyMass(rs.getDouble(19));
			automatedMeasurements.setWeight(rs.getDouble(20));
			
			automatedMeasurements.setIdeal_Icw(rs.getDouble(21));
			automatedMeasurements.setIdeal_Ecw(rs.getDouble(22));
			automatedMeasurements.setIdeal_Protein(rs.getDouble(23));
			automatedMeasurements.setIdeal_mineral(rs.getDouble(24));
			automatedMeasurements.setIdeal_Fat(rs.getDouble(25));
			automatedMeasurements.setIdeal_Tbw(rs.getDouble(26));
			automatedMeasurements.setEcwTbw(rs.getDouble(27));
			automatedMeasurements.setLbmRightArm(rs.getDouble(28));
			automatedMeasurements.setPcntLbmRightArm(rs.getDouble(29));
			automatedMeasurements.setSecondGraphRightArm(rs.getDouble(30));
							
			automatedMeasurements.setLbmLeftArm(rs.getDouble(31));
			automatedMeasurements.setPcntLbmLeftArm(rs.getDouble(32));
			automatedMeasurements.setSecondGraphLeftArm(rs.getDouble(33));				
			automatedMeasurements.setLbmTrunk(rs.getDouble(34));				
			automatedMeasurements.setPcntLbmTrunk(rs.getDouble(35));
			automatedMeasurements.setSecondGraphTrunk(rs.getDouble(36));				
			automatedMeasurements.setLbmRightLeg(rs.getDouble(37));
			automatedMeasurements.setPcntLbmRightLeg(rs.getDouble(38));
			automatedMeasurements.setSecondGraphRightLeg(rs.getDouble(39));
			automatedMeasurements.setLbmLeftLeg(rs.getDouble(40));
			
			automatedMeasurements.setPcntLbmLeftLeg(rs.getDouble(41));
			automatedMeasurements.setSecondGraphLeftLeg(rs.getDouble(42));
			automatedMeasurements.setPcntBodyWeight(rs.getDouble(43));
			automatedMeasurements.setWt_Min(rs.getDouble(44));
			automatedMeasurements.setWt_Max(rs.getDouble(45));
			automatedMeasurements.setPcntBodyFat(rs.getDouble(46));
			automatedMeasurements.setIdeal_Weight(rs.getDouble(47));
			automatedMeasurements.setPcntFat(rs.getDouble(48));
			automatedMeasurements.setPcntTbw(rs.getDouble(49));
			automatedMeasurements.setIdealPcntBodyFat(rs.getDouble(50));
			
			automatedMeasurements.setPcntLeanBodyMass(rs.getDouble(51));
			automatedMeasurements.setIdealLeanBodyMass(rs.getDouble(52));
			automatedMeasurements.setPbf_Min1(rs.getDouble(53));
			automatedMeasurements.setPbf_Max1(rs.getDouble(54));
			automatedMeasurements.setBmi(rs.getDouble(55));
			automatedMeasurements.setBmi_Min(rs.getDouble(56));
			automatedMeasurements.setBmi_Max(rs.getDouble(57));
			automatedMeasurements.setPbf(rs.getDouble(58));
			automatedMeasurements.setPbf_Min2(rs.getDouble(59));
			automatedMeasurements.setPbf_Max2(rs.getDouble(60));
			
			automatedMeasurements.setIdealBmi(rs.getDouble(61));
			automatedMeasurements.setHeight(rs.getDouble(62));
			automatedMeasurements.setAge(rs.getDouble(63));
			automatedMeasurements.setSex(rs.getString(64));
			automatedMeasurements.setBasalMetabolicRate(rs.getDouble(65));
							
			System.out.println("Row added to list");
			measurementList.add(automatedMeasurements);
		}
		
		
		
				
		
	}catch(SQLException se){
		System.out.println("Error while opening connection");
		se.printStackTrace();
	}finally{
		try {
			if(pstmt != null){
				pstmt.close();
			}

			if(con != null){
				con.close();
			}
		} catch (SQLException se) {
			System.out.println("Error while closing connection");
			se.printStackTrace();
		}


	} 

	return measurementList;
}
	
	
	public ReportCardMeasurements getReportCardMeasurements(String userId, Date date) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportCardMeasurements rcm = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DATE_TIMES_1, ID_2, HEIGHT_165, LEAN_BODY_MASS_20,")
			.append(" WEIGHT_21, FAT_15, BMI_145 , PBF_148, BASAL_METABOLIC_RATE_171, TBW_18 ")
			.append(" FROM AUTOMATED_MEASUREMENTS") 
			.append(" WHERE ID_2=? AND DATE_TIMES_1 = ?");
		
		try
		{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, userId);
			pstmt.setTimestamp(2, new Timestamp(date.getTime()));
			
			rs= pstmt.executeQuery();
			if(rs.next()){
				
				rcm = new ReportCardMeasurements();
				rcm.setUsrId(rs.getString("ID_2"));
				rcm.setLeanBodyMass(rs.getDouble("LEAN_BODY_MASS_20"));
				rcm.setDateTime(rs.getDate("DATE_TIMES_1"));
				rcm.setHeight(rs.getDouble("HEIGHT_165"));
				rcm.setWeight(rs.getDouble("WEIGHT_21"));
				rcm.setFat(rs.getDouble("FAT_15"));
				rcm.setLeanBodyMass(rs.getDouble("LEAN_BODY_MASS_20"));
				rcm.setBmi(rs.getDouble("BMI_145"));
				rcm.setPbf(rs.getDouble("PBF_148"));
				rcm.setBasalMetabolicRate(rs.getDouble("BASAL_METABOLIC_RATE_171"));
				rcm.setTbw(rs.getDouble("TBW_18"));
			}
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se) {}
		} 

	return rcm;
	}
	
	public Timestamp getOldestEntryDateFor(String userId) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Timestamp date = null;
		try
		{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement("SELECT MIN(DATE_TIMES_1) AS DATE_TIME FROM AUTOMATED_MEASUREMENTS WHERE ID_2 LIKE ?");
			pstmt.setString(1, userId);			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				date = rs.getTimestamp("DATE_TIME");
			}
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se) {}
		} 

	return date;
	}

	
	public Date getPreviousEntryDateFor(String userId) throws ApplicationException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Timestamp date = null;
		try
		{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement("SELECT MAX(DATE_TIMES_1) AS DATE_TIME FROM AUTOMATED_MEASUREMENTS WHERE DATE_TIMES_1 NOT IN (SELECT MAX(DATE_TIMES_1) FROM AUTOMATED_MEASUREMENTS WHERE ID_2 LIKE ?)  AND ID_2 LIKE ?");
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				date = rs.getTimestamp("DATE_TIME");
			}
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se) {}
		} 

		return date;		
		
	}

	public Timestamp getLatestEntryDateFor(String userId) throws ApplicationException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Timestamp date = null;
		try
		{
			con = appDataSource.getConnection();

			pstmt = con.prepareStatement("SELECT MAX(DATE_TIMES_1) AS DATE_TIME FROM AUTOMATED_MEASUREMENTS WHERE ID_2 LIKE ?");
			pstmt.setString(1, userId);			
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				date = rs.getTimestamp("DATE_TIME");
			}
		}
		catch(SQLException se)
		{
			throw new ApplicationException(se.getMessage());
		}
		finally
		{
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}
			catch(SQLException se) {}
		} 

	return date;		
		
	}

}
