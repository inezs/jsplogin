<%@ include file="index.jsp" %>
<h4>Registration Form</h4>
<form action="register" method="post">  
Username:<input type="text" name="username"/><br/><br/>
First Name:<input type="text" name="firstname"/><br/><br/>
Last Name:<input type="text" name="lastname"/><br/><br/> 
Password:<input type="password" name="password"/><br/><br/>

<%Object authError = request.getAttribute("authError"); %>
<%if(authError!=null){ %>
<p><%=authError%></p><br/><br/>
<%} %>
<input type="submit" value="Register"/>
</form> 