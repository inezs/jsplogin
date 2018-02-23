package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import properties.CONST;

//@WebServlet(urlPatterns="/home")
public class User extends Authentication {
	static final long serialVersionUID = CONST.serialVersionUID;
	public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(authCookie(request,response)) {
			request.setAttribute("username", user.getUsername());
			request.setAttribute("firstname", user.getFirstname());
			request.setAttribute("lastname", user.getLastname());
		}
		
	}
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException{
//
//		if(authCookie(request, response)) {
//			response.setContentType("text/html");  
//			
//			String name=user.getFirstname()+" "+user.getLastname(); 
//	        request.setAttribute("name",name);
//	        
//		}
//		RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");  
//	    rd.forward(request,response);
//	}
}
