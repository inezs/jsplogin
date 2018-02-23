package dao;

import java.sql.Statement;

import model.Log;

public class LogDAO extends Database{
	public static void insertLog(Log log) {
		try{  
			openConnection();
			Statement st= conn.createStatement();
	        st.executeUpdate("INSERT INTO transaction_log (username,log) VALUES("+
			"'"+log.getUsername()+"',"+
	        		"'"+log.getLog()+"');");
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
}
