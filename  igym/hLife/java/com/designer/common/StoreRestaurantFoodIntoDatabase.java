package com.designer.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.designer.common.framework.ApplicationConfig;
import com.designer.common.framework.ApplicationDataSource;

public class StoreRestaurantFoodIntoDatabase {

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
			String myDB ="jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ApplicationConfig.getInstance().getConfigValue("restaurent.food.items.file");
			Connection conXl = DriverManager.getConnection(myDB);
			Statement stmtXl = conXl.createStatement();
			
			conDB = appDataSource.getConnection();
			pstmtDB=conDB.prepareStatement("insert into FOOD_MASTER (FOOD_NAME,CALORIES, FAT_IN_GRAMS, CARBOHYDRATES_IN_GRAMS, DIETARY_FIBRE, SUGAR, PROTIEN_IN_GRAMS, SERVINGS_PER_DISH) values (?,?,?,?,?,?,?,?)");

			ResultSet rsXl = stmtXl.executeQuery( "SELECT * FROM [database2$]");
			Double num;
			String str;
			int j=1;
			while(rsXl.next()){
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
