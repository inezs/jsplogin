package model;

import java.io.Serializable;
import java.util.List;

import properties.CONST;

public class Employee implements Serializable{
	static final long serialVersionUID = CONST.serialVersionUID;
	
	private String username;
	private String firstname;
	private String lastname;
	private List<String> roles;
	
	public Employee() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		String res=username+" "+firstname+" "+lastname;
		for(int i=0; i<roles.size();i++) {
			res+=" "+roles.get(i);
		}
		return res;
	}
}
