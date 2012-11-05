package edu.asupoly.cst425.lab4.handler;

import java.util.Map;
import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.controller.ControllerServlet;
import edu.asupoly.cst425.lab4.model.NewsItemBeanFactory;
import edu.asupoly.cst425.lab4.model.ReporterBean;

public class RemoveHandler implements ActionHandler
{
	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) 
	{	
		ReporterBean rBean = (ReporterBean)session.getAttribute("reporterBean");
		NewsItemBeanFactory factory = (NewsItemBeanFactory) session.getServletContext().getAttribute(ControllerServlet.NEWS_ITEM_FACTORY); 
			  
		if (rBean != null && params != null)
		{
		  String itemId = HandlerUtilities.getParameterValue("item", params);
		  String title = HandlerUtilities.getParameterValue("title", params);		
		 		  String msg = "";
		  int id = 0;
		  
		  if (title != null && title.length() > 0 )
		  { 
			  if (itemId != null && itemId.length() > 0) 
			  {
				  try {
					  id = Integer.parseInt(itemId);
				  } catch (Exception exc) {
						msg = "Invalid format for news item ID";		
				  }
				  if ( msg.equals("") && factory.removeNewsItem(id) )
				  {
					  msg = "News item " + id + " successfully removed!";
				  }
				  else 
				  {
					  msg = "News item " + id + " could not be removed!";
				  }
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
