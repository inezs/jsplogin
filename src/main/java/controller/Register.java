package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet(urlPatterns="/register")
public class Register extends HttpServlet{
	static final long serialVersionUID = CONST.serialVersionUID;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		RequestDispatcher rd=request.getRequestDispatcher("/register.jsp");  
        rd.forward(request,response);  
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		Credential credential =new Credential();
		credential.setUsername(request.getParameter("username"));
		credential.setPassword(request.getParameter("password"));
		
		Employee employee= new Employee();
		employee.setFirstname(request.getParameter("firstname"));
		employee.setLastname(request.getParameter("lastname"));
		employee.setUsername(request.getParameter("username"));
		
		if(credential.getUsername().equals("")|| credential.getPassword().equals("")||employee.getFirstname().equals("")||employee.getLastname().equals("")) {
			String authError="Username, Password, First Name, Last Name can not be blank";
			request.setAttribute("authError",authError);
			
			RequestDispatcher rd=request.getRequestDispatcher("./register.jsp");  
	        rd.forward(request,response); 
		}else {
			if(CredentialDAO.usernameExist(credential)) {
				String authError="Sorry username has been used";
				request.setAttribute("authError",authError);
				
				RequestDispatcher rd=request.getRequestDispatcher("./register.jsp");  
		        rd.forward(request,response); 
			}else {
				EmployeeDAO.addEmployee(employee);
				CredentialDAO.addCredential(credential);
				
				Log log =new Log(employee.getUsername(), "Register");
				LogDAO.insertLog(log);
				
				
				response.sendRedirect("/jsplogin/auth");
			}
		}
		
	}
}
