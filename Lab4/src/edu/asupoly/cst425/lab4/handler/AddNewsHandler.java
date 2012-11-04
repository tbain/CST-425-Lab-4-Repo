package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.model.NewsItemBeanFactory;
import edu.asupoly.cst425.lab4.model.ReporterBean;

public class AddNewsHandler implements ActionHandler {

	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) 
	{
		 ReporterBean rBean = (ReporterBean)session.getAttribute("reporterBean");
		  
		 if (params != null && rBean != null)
		 { 
			  String title = HandlerUtilities.getParameterValue("title", params);
			  String story = HandlerUtilities.getParameterValue("story", params);
			  String itemId = HandlerUtilities.getParameterValue("item", params);			
			  String msg = "";
			  int id = 0;
			  
			  if (title != null && title.length() > 0 && story != null && story.length() > 0) 
			  { 
				  if (itemId != null && itemId.length() > 0) 
				  {
					  try 
					  {
						  id = Integer.parseInt(itemId);
					  } catch (Exception exc) {
							msg = "Invalid format for news item ID";		
					  }
					  
					  if (msg.equals("") && NewsItemBeanFactory.editNewsItem(id, title, story, rBean.getReporterId())) 
					  {
						  msg = "News item " + id + " successfully edited!";
					  } else 
					  {
						  msg = "News item " + id + " could not be edited!";
					  }
				  } else
				  {
					  NewsItemBeanFactory.addNewsItem(title, story, rBean.getReporterId());
					  msg = "News item successfully added!";
				  }				 
			  }		
			  session.setAttribute("msg", msg);
			  return "index";
		 }
		 else
		 {					
			 return null;
		 }
	}

}
