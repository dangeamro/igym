package com.designer.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.ApplicationDataSource;

public class StoreNutrientFoodIntoDatabase {
	private static String formHtmlText(String str)
	{
		if(str == null || str.trim().equals(""))
			return "";
		return str.replace("&", "&amp;").replace("<", "&lt;").replace(">",
				"&gt;").replace("\r\n", "<br>").replace("\n\r", "<br>").replace("\"", "\\\"");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationConfig.init("ApplicationConfig.properties");
		ApplicationDataSource appDataSource = ApplicationDataSource.getInstance();
		Connection conDB = null;
		PreparedStatement pstmtDB = null;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String myDB ="jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ApplicationConfig.getInstance().getConfigValue("nutrient.food.items.file");
			Connection conXl = DriverManager.getConnection(myDB);
			Statement stmtXl = conXl.createStatement();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			conDB = appDataSource.getConnection();
			pstmtDB=conDB.prepareStatement("insert into FOOD_MASTER (FOOD_NAME,CALORIES, CARBOHYDRATES_IN_GRAMS, FAT_IN_GRAMS, PROTIEN_IN_GRAMS) values (?,?,?,?,?)");

			ResultSet rsXl = stmtXl.executeQuery( "SELECT * FROM [SelectNutrients-Step3-final$]");
			Double num;
			String str;
			int j=1;
			while(rsXl.next()){
				System.out.println("dshb");
				String foodName = rsXl.getString(2)+" -- "+rsXl.getString(3);
				
				
				pstmtDB.setString(1,formHtmlText(foodName.trim()));
				
				str = rsXl.getString(4);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(2,num);
				
				str = rsXl.getString(5);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(3,num);
				
				str = rsXl.getString(6);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(4,num);
				
				str = rsXl.getString(7);
				num = Double.parseDouble(str);				
				pstmtDB.setDouble(5,num);
				
				pstmtDB.executeUpdate();
				
				System.out.println("Food  Row Inserted :: "+j++ +" ------ "+ foodName);
				
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
