package edu.asupoly.cst425.lab4.model;

import java.util.ArrayList;
import java.util.List;

public class SubscriberBean {
	String subscriberId;
	String passwd;
	List<NewsItemBean> favorites;
	
	public SubscriberBean(String subscriberId, String passwd) {
		this(subscriberId, passwd, new ArrayList<NewsItemBean>());
	}
	
	public SubscriberBean(String subscriberId, String passwd, List<NewsItemBean> favorites) {
		this.subscriberId = subscriberId;
		this.passwd = passwd;
		this.favorites = favorites;
	}
	
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public List<NewsItemBean> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<NewsItemBean> favorites) {
		this.favorites = favorites;
	}
	
	public void addFavorite(NewsItemBean newsItem) {
		favorites.add(newsItem);
	}
}
