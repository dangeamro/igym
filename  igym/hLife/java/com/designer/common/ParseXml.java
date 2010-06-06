package com.designer.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParseXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection conDB = null;
		PreparedStatement pstmtDB = null;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String myDB ="jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=C:/Food+Items+Database.xls";
			Connection conXl = DriverManager.getConnection(myDB);
			Statement stmtXl = conXl.createStatement();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
    		String connectionUrl = "jdbc:sqlserver://COMP1\\SQLEXPRESS;databaseName=DesignerBodies;" +
			"user=NeelKamal; password=neel";
			conDB = DriverManager.getConnection(connectionUrl);
			String sql = new String();
			ResultSet rsXl = stmtXl.executeQuery( "SELECT * FROM [database2$]");
			Double num;
			String str;
			int j=1;
			while(rsXl.next()){
				sql ="insert into FOOD_MASTER (FOOD_NAME,CALORIES, FAT_IN_GRAMS, CARBOHYDRATES_IN_GRAMS, DIETARY_FIBRE, SUGAR, PROTIEN_IN_GRAMS, SERVINGS_PER_DISH) values (?,?,?,?,?,?,?,?)";
				pstmtDB=conDB.prepareStatement(sql.toString());
				pstmtDB.setString(1,rsXl.getString(1));
				
				str = rsXl.getString(2);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(2,num);
				
				str = rsXl.getString(3);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(3,num);
				
				str = rsXl.getString(4);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(4,num);
				
				str = rsXl.getString(5);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(5,num);
				
				str = rsXl.getString(6);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(6,num);
				
				str = rsXl.getString(7);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(7,num);

				str = rsXl.getString(8);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(8,num);
				
				pstmtDB.executeUpdate();
				
				System.out.println("Food  Row Inserted :: "+j++);
				
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

}
