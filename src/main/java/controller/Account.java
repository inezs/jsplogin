package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CredentialDAO;
import dao.EmployeeDAO;
import dao.LogDAO;
import model.Credential;
import model.Log;
import properties.CONST;

@WebServlet(urlPatterns= {"/account","/account/edit/password","/account/delete"})
public class Account extends Authentication {
	static final long serialVersionUID = CONST.serialVersionUID;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(authCookie(request,response)) {
			if(request.getRequestURI().equals("/jsplogin/account")) {
				doGetAccount(request,response);
			}else if(request.getRequestURI().equals("/jsplogin/account/edit/password")){
				doGetChangePassword(request,response);
			}else if(request.getRequestURI().equals("/jsplogin/account/delete")) {
				doGetDeleteAccount(request,response);
			}
		}else {
			response.sendRedirect("/jsplogin/logout");
		}
			
	}
	private void doGetAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/account.jsp");  
	    rd.forward(request,response);
	}
	
	private void doGetChangePassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/changepassword.jsp");  
	    rd.forward(request,response);
	}
	
	private void doGetDeleteAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/deleteaccount.jsp");  
	    rd.forward(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		if(authCookie(request,response)) {
			if(request.getRequestURI().equals("/jsplogin/account")) {
				doPostAccount(request,response);
			}else if(request.getRequestURI().equals("/jsplogin/account/edit/password")){
				doPostChangePassword(request,response);
			}else if(request.getRequestURI().equals("/jsplogin/account/delete")) {
				doPostDeleteAccount(request,response);
			}
		}else {
			response.sendRedirect("/jsplogin/logout");
		}
			
	}
	private void doPostAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
	
	private void doPostChangePassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String authError=""; 
		
		if(request.getParameter("new").equals(request.getParameter("confirm"))&&request.getParameter("new")!="") {
				Credential oldCredential =new Credential();
				oldCredential.setUsername(user.getUsername());
				oldCredential.setPassword(request.getParameter("old"));
				
				if(CredentialDAO.validate(oldCredential)) {
					Credential newCredential = new Credential();
					newCredential.setUsername(user.getUsername());
					newCredential.setPassword(request.getParameter("new"));
					
					CredentialDAO.updateCredential(newCredential);
					
					clearCredentialCookie(request,response);
					setCredentialCookie(request,response,newCredential.getUsername(),newCredential.getPassword(),CONST.cookieMaxAge);
					
					response.sendRedirect("/jsplogin/account");
					
				}else {
					authError="Wrong password"; 
			        request.setAttribute("authError",authError);
			        
			        RequestDispatcher rd=request.getRequestDispatcher("/changepassword.jsp");  
			        rd.forward(request,response);
				}
			
		}else {
			authError+="Your new password doesn't match or blank";
			request.setAttribute("authError",authError);
	        RequestDispatcher rd=request.getRequestDispatcher("/changepassword.jsp");  
	        rd.forward(request,response);
		}		
	}
	
	private void doPostDeleteAccount(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Credential credential =new Credential();
		credential.setUsername(user.getUsername());
		credential.setPassword(request.getParameter("confirm"));
		
		if(CredentialDAO.validate(credential)) {
			CredentialDAO.deleteCredential(credential.getUsername());
			EmployeeDAO.deleteEmployee(credential.getUsername());
			
			Log log =new Log(credential.getUsername(), "Delete Account");
			LogDAO.insertLog(log);
			
			clearCredentialCookie(request,response);
			response.sendRedirect("/jsplogin/home.jsp");
		}else {
			String authError="Sorry wrong password"; 
	        request.setAttribute("authError",authError);
	        RequestDispatcher rd=request.getRequestDispatcher("/deleteaccount.jsp");  
	        rd.forward(request,response);  
		}
	}
}
