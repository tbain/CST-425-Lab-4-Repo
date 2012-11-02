/**
 * NewsItemBeanFactory.java
 */
package edu.asupoly.cst425.lab4.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory class provides static accessor for getting reporters.
 * 
 * @author kgary
 *
 */
public final class NewsItemBeanFactory {
	private static Map<Integer, NewsItemBean> newsitems = new HashMap<Integer, NewsItemBean>();
	
	public static NewsItemBean getNewsItem(int itemId, String rid) {
		NewsItemBean rval = newsitems.get(new Integer(itemId));

		return rval;
	}
	
	public static Collection<NewsItemBean> getAllItems() {
		return newsitems.values(); 
	}
	
	public static boolean addNewsItem(String title, String story, String reporterId) {
		NewsItemBean newsItem = new NewsItemBean(title, story, reporterId);
		newsitems.put(newsItem.getItemId(), newsItem);
		return true;
	}
	
	public static boolean editNewsItem(int itemId, String title, String story, String reporterId) {
		NewsItemBean newsItem = getNewsItem(itemId, reporterId);
		if (newsItem != null) {
			newsItem.setItemTitle(title, reporterId);
			newsItem.setItemStory(story, reporterId);
			return true;
		}
		return false;
	}
	
	public static boolean removeNewsItem(int itemId) {
		newsitems.remove(itemId);
		return true;
	}
	
	private NewsItemBeanFactory() {}
}
