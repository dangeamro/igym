package com.designer.dao.supplimentTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.designer.common.framework.ApplicationDataSource;
import com.designer.common.framework.ApplicationException;
import com.designer.model.supplimentTracker.SupplimentTracker;
public class SupplimentTrackerDAOImpl implements SupplimentTrackerDAO{
	
	private static ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
	
	public void addSuppliment(SupplimentTracker supplimentTracker) throws ApplicationException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = appDataSource.getConnectionFactory().getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" INSERT INTO SUPPLEMENT_TRACKER(USER_ID,PURCHASE_DATE,IS_MONTHLY,N_S_COMPLEX,GLUTAMINE,M_S_OIL, ");
			sql.append(" OXY_OPTIMIZER,M_S_COMPLEX,HEALTH_SUPPORT,EFA_SUPPORT,STATUS)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,'A')");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1,supplimentTracker.getUserId());
			pstmt.setTimestamp(2,supplimentTracker.getDate());
			pstmt.setString(3,supplimentTracker.getIsMonthly());			
			pstmt.setInt(4,supplimentTracker.getN_s_Complex());
			pstmt.setInt(5,supplimentTracker.getGlutamine());
			pstmt.setInt(6,supplimentTracker.getM_s_oil());
			pstmt.setInt(7,supplimentTracker.getOxyOptimizer());
			pstmt.setInt(8,supplimentTracker.getM_s_complex());
			pstmt.setInt(9,supplimentTracker.getHealthSupport());
			pstmt.setInt(10,supplimentTracker.getEfaSupport());
			
			pstmt.executeUpdate();
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
			catch (SQLException se) {}
		} 
	}
	
public void changeDefaults(SupplimentTracker supplimentTracker) throws ApplicationException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = appDataSource.getConnectionFactory().getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" UPDATE MONTHLY_PACKAGE_SUPPLEMENT SET N_S_COMPLEX = ?,GLUTAMINE = ?,M_S_OIL = ?, ");
			sql.append(" OXY_OPTIMIZER = ?,M_S_COMPLEX = ?,HEALTH_SUPPORT = ?,EFA_SUPPORT = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1,supplimentTracker.getN_s_Complex());
			pstmt.setInt(2,supplimentTracker.getGlutamine());
			pstmt.setInt(3,supplimentTracker.getM_s_oil());
			pstmt.setInt(4,supplimentTracker.getOxyOptimizer());
			pstmt.setInt(5,supplimentTracker.getM_s_complex());
			pstmt.setInt(6,supplimentTracker.getHealthSupport());
			pstmt.setInt(7,supplimentTracker.getEfaSupport());
			
			pstmt.executeUpdate();
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
			catch (SQLException se) {}
		} 
	}
	
	
	public SupplimentTracker getSupplimentTracker(String userId, int pageNum ) throws ApplicationException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SupplimentTracker tracker = null;
		try{
			con = appDataSource.getConnectionFactory().getConnection();

			StringBuffer sb = new StringBuffer();			
			
			sb.append("SELECT SUPPLEMENT_ID, PURCHASE_DATE, IS_MONTHLY, N_S_COMPLEX, GLUTAMINE, M_S_OIL, OXY_OPTIMIZER, M_S_COMPLEX, HEALTH_SUPPORT, EFA_SUPPORT");
			sb.append(" FROM SUPPLEMENT_TRACKER WHERE STATUS='A' AND");
			sb.append(" PURCHASE_DATE IN ( SELECT MIN (DT_INNER_QRY.PURCHASE_DATE) AS PURCHASE_DATE");
			sb.append(" FROM (SELECT DISTINCT TOP ");
			sb.append(pageNum);
			sb.append(" PURCHASE_DATE  FROM  SUPPLEMENT_TRACKER");
			sb.append(" WHERE USER_ID = ?");
			sb.append(" ORDER BY PURCHASE_DATE DESC) DT_INNER_QRY) AND");
			sb.append(" USER_ID = ?");
			
			pstmt = con.prepareStatement(sb.toString());
			//pstmt.setInt(1, pageNum);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				tracker = new SupplimentTracker();
				
				tracker.setDate(rs.getTimestamp("PURCHASE_DATE"));
				tracker.setSupplimentId(rs.getInt("SUPPLEMENT_ID"));
				tracker.setIsMonthly(rs.getString("IS_MONTHLY").trim());
				tracker.setN_s_Complex(rs.getInt("N_S_COMPLEX"));
				tracker.setGlutamine(rs.getInt("GLUTAMINE"));
				tracker.setM_s_oil(rs.getInt("M_S_OIL"));
				tracker.setOxyOptimizer(rs.getInt("OXY_OPTIMIZER"));
				tracker.setM_s_complex(rs.getInt("M_S_COMPLEX"));
				tracker.setHealthSupport(rs.getInt("HEALTH_SUPPORT"));
				tracker.setEfaSupport(rs.getInt("EFA_SUPPORT"));
				tracker.setUserId(userId);
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
			} catch (SQLException se) {}


		} 
		return tracker;
	}
	
	public int getPageCount(String userId) throws ApplicationException{
		
		int pageCount=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = appDataSource.getConnection();
			pstmt = con.prepareStatement("SELECT COUNT (PURCHASE_DATE) FROM SUPPLEMENT_TRACKER WHERE USER_ID = ?");
			
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery(); 
		
			if( rs.next() ){ 
				pageCount = rs.getInt(1);
		     }
		}
		catch(SQLException e)
		{
			throw new ApplicationException(e.getMessage());
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
			} catch (SQLException se) {}
 		}  

		return pageCount;
}
	public void deleteSupplimentTracker(Integer supplimentTrackerId) throws ApplicationException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append(" DELETE FROM SUPPLEMENT_TRACKER WHERE SUPPLEMENT_ID=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, supplimentTrackerId);
			
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
			} catch (SQLException se) {}
		} 
	}
	
	public SupplimentTracker getMonthlyPackValues() throws ApplicationException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SupplimentTracker tracker = new SupplimentTracker();
		
		try{
			con = appDataSource.getConnectionFactory().getConnection();
			pstmt = con.prepareStatement("SELECT * FROM MONTHLY_PACKAGE_SUPPLEMENT");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				tracker.setN_s_Complex(rs.getInt("N_S_COMPLEX"));
				tracker.setGlutamine(rs.getInt("GLUTAMINE"));
				tracker.setM_s_oil(rs.getInt("M_S_OIL"));
				tracker.setOxyOptimizer(rs.getInt("OXY_OPTIMIZER"));
				tracker.setM_s_complex(rs.getInt("M_S_COMPLEX"));
				tracker.setEfaSupport(rs.getInt("EFA_SUPPORT"));
				tracker.setHealthSupport(rs.getInt("HEALTH_SUPPORT"));
			}
			
		}catch(SQLException se)
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
			} catch (SQLException se) {}
		}
		
		return tracker;
	}

}