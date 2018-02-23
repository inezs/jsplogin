<%@ include file="index.jsp" %> 
<h4>Login Form</h4>
<form action="auth" method="post">  
Username:<input type="text" name="username"/><br/><br/>  
Password:<input type="password" name="password"/><br/><br/>

<%Object authError = request.getAttribute("authError"); %>
<%if(authError!=null){ %>
<p><%=authError%></p><br/><br/>
<%} %>
<input type="submit" value="Login"/>
</form> 
