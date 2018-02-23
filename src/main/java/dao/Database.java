package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static String DB_URL = "jdbc:mysql://localhost/company";
	protected static Connection conn = null;
	
	public static void openConnection() {
		try{ 
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection(DB_URL,"root","");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void closeConnection() {
		try {
			if(conn!=null)
	            conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
