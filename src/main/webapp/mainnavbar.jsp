<style>
mark { 
    background-color: yellow;
    color: black;
}
</style>

<%@ page import="controller.Authentication"%>
<%
Authentication a=new Authentication();
%>

| <a href=/jsplogin/home>Home</a> |
<% if(!a.authCookie(request, response)){%>
	<a href=/jsplogin/register>Register</a> |
	<a href=/jsplogin/auth>Login</a> |
<%} else{%>	
	<a href=/jsplogin/account>Account</a> |
	<a href=/jsplogin/logout>Logout</a> |
<%}%>