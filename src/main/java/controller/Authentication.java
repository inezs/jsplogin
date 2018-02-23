package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CredentialDAO;
import dao.EmployeeDAO;
import dao.LogDAO;
import model.Credential;
import model.Employee;
import model.Log;
import properties.CONST;

@WebServlet(urlPatterns="/auth")
public class Authentication extends HttpServlet{
	static final long serialVersionUID = CONST.serialVersionUID;
	protected Employee user = new Employee();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");  
        rd.forward(request,response);  
	}
	public boolean authCookie( HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Credential cred=getCredentialCookie(request);
		
		if(CredentialDAO.validate(cred)) {
			user= EmployeeDAO.getEmployee(cred.getUsername());
			return true;
		}else {
			return false;
			
		}
	}
	
	public boolean authorizeRole(HttpServletRequest request, HttpServletResponse response, String role) throws ServletException, IOException{
		boolean status=false;
		if(authCookie(request, response)) {
			for(int i=0; i<user.getRoles().size();i++) {
				if(user.getRoles().get(i).equalsIgnoreCase(role)) {
					status=true;
				}
			}
		}
		return status;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Credential cred= new Credential();
		cred.setUsername(request.getParameter("username"));  
		cred.setPassword(request.getParameter("password")); 
	         
	    if(CredentialDAO.validate(cred)){  
	    	Log log=new Log(request.getParameter("username"),"Succesfully Login");
	    	LogDAO.insertLog(log);
	    	
	    	setCredentialCookie(request, response, request.getParameter("username"), request.getParameter("password"),CONST.cookieMaxAge);		    
		    response.sendRedirect("/jsplogin/home");
	    }  
	    else{  
	        String authError="Sorry username or password error"; 
	        request.setAttribute("authError",authError);
	        RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");  
	        rd.forward(request,response);  
	    }   
	    }
	
	protected Credential getCredentialCookie(HttpServletRequest request) {
		String cookieUsername="", cookiePassword="";
		Credential cred=new Credential();
		javax.servlet.http.Cookie[] cookies= request.getCookies();
		if(cookies!=null) {
			for(int i=0; i <cookies.length; i++) {
				if(cookies[i].getName().equals("username")) {
					cookieUsername= cookies[i].getValue();
					cred.setUsername(cookieUsername);
				}
				if(cookies[i].getName().equals("password")) {
					cookiePassword= cookies[i].getValue();
					cred.setPassword(cookiePassword);
				}
			}
		}
		
		return cred;
	}
	
	protected void setCredentialCookie(HttpServletRequest request, HttpServletResponse response, String username, String password, Integer maxAge) {
		Cookie userName,passWord;

		userName = new Cookie("username", username);
    	passWord = new Cookie("password", password);
    	
    	userName.setPath("/jsplogin");
    	passWord.setPath("/jsplogin");
    	
    	userName.setMaxAge(maxAge);
	    passWord.setMaxAge(maxAge);
	    
	    response.addCookie(userName);
	    response.addCookie(passWord);
//		}    
	    
	}
	
	protected void clearCredentialCookie(HttpServletRequest request,HttpServletResponse response) {
		setCredentialCookie(request,response,"","",0);
}
}
