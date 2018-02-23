<%@ page import="controller.User"%>

<% 
	User user =new User();
	user.getUser(request,response);
%>
<%Object firstname = request.getAttribute("firstname");
Object lastname =request.getAttribute("lastname"); 
if (firstname==null && lastname==null){
	firstname= "Stranger";
	lastname="";
}
%>

<h3>Hello <%=firstname%> <%=lastname%>!</h3>