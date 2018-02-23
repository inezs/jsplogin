package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class RoleDAO extends Database{
	protected static Map <Integer, String> allRole=new HashMap<Integer,String>();
	public static void collectRoles(){
		try {
			openConnection();
			PreparedStatement ps=conn.prepareStatement(  
					"select * from role");     
	      
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				allRole.put(rs.getInt(1),rs.getString(2));
			}
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
}
