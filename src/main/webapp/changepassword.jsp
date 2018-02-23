<%@ include file="account.jsp" %>
<h4>Change Password Form</h4>
<form action="/jsplogin/account/edit/password" method="post">  
Old Password:<input type="password" name="old"/><br/><br/>  
New Password:<input type="password" name="new"/><br/><br/>
Confirm New Password:<input type="password" name="confirm"/><br/><br/>

<%Object authError = request.getAttribute("authError"); %>
<%if(authError!=null){ %>
<p><%=authError%></p><br/><br/>
<%} %>
<input type="submit" value="Change Password"/>
</form> 