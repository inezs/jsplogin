<%@ page import="controller.Authentication"%>

<% Authentication au=new Authentication();
	boolean isAuth = au.authCookie(request, response);
%>
<% if(!isAuth){
	response.sendRedirect("/jsplogin/logout");
} %>