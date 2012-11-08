package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.controller.ControllerServlet;
import edu.asupoly.cst425.lab4.model.NewsItemBean;
import edu.asupoly.cst425.lab4.model.NewsItemBeanFactory;
import edu.asupoly.cst425.lab4.model.SubscriberBean;

public class AddFavoriteHandler implements ActionHandler {

	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) {
		
		SubscriberBean sBean = (SubscriberBean) session.getAttribute("subscriberBean");	
		if(params != null && sBean != null) {
			String item_s = HandlerUtilities.getParameterValue("item", params);
			int itemId = -1;
			try {
				itemId = Integer.parseInt(item_s);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				return "error";
			}
			
			NewsItemBeanFactory factory = (NewsItemBeanFactory) session.getServletContext().getAttribute(ControllerServlet.NEWS_ITEM_FACTORY);
			NewsItemBean newsItem = factory.getNewsItem(itemId);
			sBean.addFavorite(newsItem);			
		}
		
		return "index";
	}

}
