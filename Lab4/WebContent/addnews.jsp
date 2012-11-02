<jsp:include page="header.jsp"/>
<%@ page session = "true" 
import="edu.asupoly.cst425.lab4.model.*" %>

<%
  String title = request.getParameter("title");
  String story = request.getParameter("story");
  String itemId = request.getParameter("item");
  ReporterBean rBean = (ReporterBean)session.getAttribute("reporterBean");
  
  if (title == null) { title = ""; }
  if (story == null) { story = ""; }	
%>


<h3>Add News</h3>
<p>Fill in all fields to add your news to NEW news.</p>
<form action="<%= request.getContextPath() %>/controller?action=addNews" method="post">
 Title: <input type="text" size="50" name="title" value="<%= title %>" /><br/>
 Story: <textarea cols="50" rows="15" name="story" /><%= story %></textarea>
 <br/>
 <input type="submit" value="Add News" />
<%
if (itemId != null && itemId.length() > 0) {
%>
	<input type="hidden" name="item" value="<%= itemId %>" />
</form>
<% } %>
<jsp:include page="footer.jsp"/>
