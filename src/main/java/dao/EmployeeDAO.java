package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO extends RoleDAO{
	public static List<String> getEmployeeRoles(String username){
		collectRoles();
		List<String> res=new ArrayList<String>();
		try {
			openConnection();
			PreparedStatement ps=conn.prepareStatement(  
					"select role_id from employee_role where username=?");     
			ps.setString(1,username);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				res.add(allRole.get(rs.getInt(1)));
			}
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
		return res;
	}
	public static Employee getEmployee(String username) {
		Employee res = new Employee();
		try{  
			openConnection();
			PreparedStatement ps=conn.prepareStatement(  
					"select * from employee_data where username=?");  
			ps.setString(1,username);    
	      
			ResultSet rs=ps.executeQuery();
			rs.next();

			res.setUsername(rs.getString(1)); 
			res.setFirstname(rs.getString(2));
			res.setLastname(rs.getString(3));
			res.setRoles(getEmployeeRoles(username));
	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
		return res;
	}
	
	public static void addEmployee(Employee employee) {
		try{  
			openConnection();
			Statement st= conn.createStatement();
	        st.executeUpdate("INSERT INTO employee_data (username,firstname,lastname) VALUES("+
	        	"'"+employee.getUsername()+"',"+
				"'"+employee.getFirstname()+"',"+
	        	"'"+employee.getLastname()+"');");  
	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
	
	public static void deleteEmployee(String username) {
		try{  
			openConnection();
			Statement st= conn.createStatement();
			st = conn.createStatement();
		    
			String sql = "DELETE FROM employee_role WHERE (username ='"+username+"')";
		    st.executeUpdate(sql);
		    
			sql = "DELETE FROM employee_data WHERE (username ='"+username+"')";
		    st.executeUpdate(sql);
	          
		}catch(Exception e){
			System.out.println(e);
		}finally {
			closeConnection();
		}
	}
}
