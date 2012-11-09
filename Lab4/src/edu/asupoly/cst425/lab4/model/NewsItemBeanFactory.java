/**
 * NewsItemBeanFactory.java
 */
package edu.asupoly.cst425.lab4.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.asupoly.cst425.lab4.dao.Lab4DAO;

/**
 * Factory class provides static accessor for getting reporters.
 * 
 * @author kgary
 *
 */
public final class NewsItemBeanFactory {
	private Lab4DAO dao;
	
	public NewsItemBeanFactory(Lab4DAO dao) {
		this.dao = dao;
	}
	
	public NewsItemBean getNewsItem(int itemId, String rid) {
		return dao.getNewsItemForId(itemId);
	}
	
	public NewsItemBean getNewsItem(int itemId) {
		return getNewsItem(itemId, null);
	}
	
	public List<NewsItemBean> getAllItems() {
		return dao.getAllNewsItems();
	}
	
	public List<NewsItemBean> getOneReportersNewsItems(String reporterId) {
		return dao.getNewsItemsForUsername(reporterId);
	}
	
	public boolean addNewsItem(String title, String story, String reporterId) {
		NewsItemBean newsItem = new NewsItemBean(title, story, reporterId);
		return dao.insertNewsItem(newsItem);
	}
	
	public boolean editNewsItem(int itemId, String title, String story, String reporterId) {
		NewsItemBean newsItem = getNewsItem(itemId, reporterId);
		newsItem.setItemTitle(title, reporterId);
		newsItem.setItemStory(story, reporterId);
		return dao.updateNewsItem(newsItem);
	}
	
	public boolean removeNewsItem(int itemId) {
		return dao.removeNewsItem(itemId);		
	}	
}
