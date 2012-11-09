package edu.asupoly.cst425.lab4.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.asupoly.cst425.lab4.dao.Lab4DAO;


public final class SubscriberBeanFactory {

	private Lab4DAO dao;
	private static Map<String, SubscriberBean> subscribers = new HashMap<String, SubscriberBean>();
	
	public SubscriberBeanFactory(Lab4DAO dao) {
		this.dao = dao;
	}
	
	public static SubscriberBean getSubscriber(String subscriberId, String passwd) {
		SubscriberBean sval = subscribers.get(subscriberId);
		
		if (sval != null && sval.getPasswd().equals(passwd)) {
			return sval;
		} else if (subscriberId.equals(passwd)) {
			// Obviously we wouldn't do this, but we are stubbing out real auth behavior for now
			sval = new SubscriberBean(subscriberId, passwd);
			subscribers.put(subscriberId, sval);
		}
		return sval;
	}
	
	public List<NewsItemBean> getFavorites(String subscriberID) {		
		return dao.getFavoritesForSubscribeID(subscriberID);
	}
	public boolean setFavorites(String subscriberID, List<NewsItemBean> favorites) {
		return dao.overWriteAllFavoritesForSubscribeID(subscriberID, favorites);
	}
	
	public boolean addFavorite(String subscriberID, NewsItemBean newsItem) {
		return dao.addFavoriteForSubscribeID(subscriberID, newsItem);
	}	
	
	public boolean removeFavorite(String subscriberID, NewsItemBean newsItem) {
		return dao.removeFavoritesForSubscribeID(false, subscriberID, newsItem);
	}	
	
	public boolean removeAllFavorites(String subscriberID, NewsItemBean newsItem) {
		return dao.removeFavoritesForSubscribeID(true, subscriberID, newsItem);
	}

}
