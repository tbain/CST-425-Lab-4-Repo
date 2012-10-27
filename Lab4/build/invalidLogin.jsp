<%@ page session = "true" %>
<html>
<head>
</head>
<h2>Invalid Login or Session</h2>
<%
	session.invalidate();
	String errMsg = null;
	try {
		errMsg = request.getParameter("errMsg");
		if (errMsg == null || errMsg.length() == 0) {
			errMsg = "";
		}
%>
			<p><em><%= errMsg %></em><br/>Please attempt login again.<br/><br/></p>
<%			
	} catch (NullPointerException nfe) {
		errMsg = null;
	}
%>
<%@ include file="./index.html" %>
</html>