package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LogDAO;
import model.Log;
import properties.CONST;

@WebServlet(urlPatterns="/logout")
public class Logout extends Authentication{
	static final long serialVersionUID = CONST.serialVersionUID;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
//		if(authCookie(request,response)) {
			clearCredentialCookie(request, response);
			Log log=new Log(user.getUsername(),"Logout");
	    	LogDAO.insertLog(log);
			response.sendRedirect("/jsplogin/auth");
//		}
		
	}
}
