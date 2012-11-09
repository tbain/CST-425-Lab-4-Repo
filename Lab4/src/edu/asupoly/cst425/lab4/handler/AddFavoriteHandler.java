package edu.asupoly.cst425.lab4.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.controller.ControllerServlet;
import edu.asupoly.cst425.lab4.model.NewsItemBean;
import edu.asupoly.cst425.lab4.model.NewsItemBeanFactory;
import edu.asupoly.cst425.lab4.model.SubscriberBean;
import edu.asupoly.cst425.lab4.model.SubscriberBeanFactory;

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
			
			NewsItemBeanFactory nFactory = (NewsItemBeanFactory) session.getServletContext().getAttribute(ControllerServlet.NEWS_ITEM_FACTORY);
			SubscriberBeanFactory sFactory = (SubscriberBeanFactory) session.getServletContext().getAttribute(ControllerServlet.SUBSCRIBER_ITEM_FACTORY);
			NewsItemBean newsItem = nFactory.getNewsItem(itemId);
			
			List<NewsItemBean> subscribersFavorites = sFactory.getFavorites(sBean.getSubscriberId());
			boolean result = true;
						
			for (NewsItemBean index : subscribersFavorites)
			{
				if (index.getItemId() == newsItem.getItemId())
				{
					result = false;
				}
			}
			
			if (result == true)
			{
				sFactory.addFavorite(sBean.getSubscriberId(), newsItem);
			}
			else
			{
				 String msg = "That favorite is already in your favorites!";
				 session.setAttribute("msg", msg);
			}
		}
		
		return "index";
	}

}
