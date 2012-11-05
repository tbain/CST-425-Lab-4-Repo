<jsp:include page="header.jsp"/>
<%@ page session = "true"
import="edu.asupoly.cst425.lab4.model.*, java.util.*, edu.asupoly.cst425.lab4.controller.*" %>

<%
ReporterBean rBean = (ReporterBean)session.getAttribute("reporterBean");
NewsItemBeanFactory factory = (NewsItemBeanFactory) session.getServletContext().getAttribute(ControllerServlet.NEWS_ITEM_FACTORY);
List<NewsItemBean> newsItems = factory.getAllItems();
String msg = (String)session.getAttribute("msg");

if (msg != null && msg.length() > 0) {
%>
	<em><%= msg %></em>
<%
	session.removeAttribute("msg");
}

for (NewsItemBean item : newsItems) {
%>
    <hr>
    <h3><%= item.getItemTitle() %></h3>
<%
	if (rBean != null && rBean.getReporterId().equals(item.getReporterId()))	{
%>
		<small><a href='<%=response.encodeURL(request.getContextPath() +"/controller") +"?action=remove&item="+item.getItemId()+"&title="+item.getItemTitle() %>'>remove</a> 
		<a href='<%= response.encodeURL("addnews.jsp?item="+item.getItemId()+"&title="+item.getItemTitle()+"&story="+item.getItemStory()) %>'>edit</a></small>
<%
	}
%>
	<p>
    	<%= item.getItemStory() %>
	</p>
<%
}
%>
<jsp:include page="footer.jsp"/>
