package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import properties.CONST;

@WebServlet(urlPatterns="/home")
public class Home extends Authentication{
	static final long serialVersionUID = CONST.serialVersionUID;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");  
        rd.forward(request,response);  
	}
}
