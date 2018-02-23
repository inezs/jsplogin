<%@ include file="account.jsp" %>
<h4>To delete the account, please confirm your password:</h4>
<form action="/jsplogin/account/delete" method="post">
Confirm Password:<input type="password" name="confirm"/><br/><br/>

<%Object authError = request.getAttribute("authError"); %>
<%if(authError!=null){ %>
<p><%=authError%></p><br/><br/>
<%} %>
<input type="submit" value="Delete Account"/>
</form>