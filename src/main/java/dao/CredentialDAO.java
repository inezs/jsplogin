package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Credential;

public class CredentialDAO extends Database{
	public static boolean validate(Credential cred){  
		boolean status=false; 
		try{  
			openConnection();
			PreparedStatement ps=conn.prepareStatement(  
					"select * from login_credentials where username=? and password=?");  
			ps.setString(1,cred.getUsername());  
			ps.setString(2,cred.getPassword());  
			
			ResultSet rs=ps.executeQuery();  
			status=rs.next();  
	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
		return status;  
	}
	public static boolean usernameExist(Credential cred){  
		boolean status=false; 
		try{  
			openConnection();
			PreparedStatement ps=conn.prepareStatement(  
					"select * from login_credentials where username=?");  
			ps.setString(1,cred.getUsername());   
			
			ResultSet rs=ps.executeQuery();  
			status=rs.next();  
	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
		return status;  
	}
	
	public static void addCredential(Credential cred) {
		try{  
			openConnection();
			Statement st= conn.createStatement();
	        st.executeUpdate("INSERT INTO login_credentials (username,password) VALUES("+
			"'"+cred.getUsername()+"',"+
	        		"'"+cred.getPassword()+"');");  
	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
	
	public static void deleteCredential(String username) {
		try{  
			openConnection();
			Statement st= conn.createStatement();
			st = conn.createStatement();
		    
			String sql = "DELETE FROM login_credentials WHERE (username ='"+username+"')";
		    st.executeUpdate(sql);

	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
	
	public static void updateCredential(Credential credential) {
		try{  
			openConnection();
			Statement st= conn.createStatement();
			st = conn.createStatement();
		    
			String sql = "UPDATE login_credentials SET "
					+ "password='"+credential.getPassword()+"'" 
					+ " WHERE (username ='"+credential.getUsername()+"')";
		    st.executeUpdate(sql);

	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
}
