package edu.asupoly.cst425.lab4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import edu.asupoly.cst425.lab4.model.NewsItemBean;

public class Lab4DAO {
	private final static Logger log = Logger.getLogger(Lab4DAO.class.getName());
	
	Connection conn;
	final String dbUrl = "jdbc:mysql://localhost:3306/cst425lab4";
	final String dbUser = "root";
	final String dbPassword = "admin";
	
	public Lab4DAO(String dbUrl) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT VERSION()");
			
			if(rs.next()) {
				log.info("SQL RESULT: " + rs.getString(1));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean insertNewsItem(NewsItemBean newsItem) {
		
		String insertStatement = "INSERT INTO newsitems (title, story, create_date, reporter) " +
		"VALUES ('%s', '%s', NOW(), '%s');";	
		String title = newsItem.getItemTitle();
		String story = newsItem.getItemStory();		
		String reporterId = newsItem.getReporterId();		
		String insertSQL = String.format(insertStatement, title, story, reporterId);
		int result = 0;
		
		try {
			Statement st = conn.createStatement();		
			result = st.executeUpdate(insertSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		if (result == 0)
		{
			return false;
		}
		else
		{
			return true;
		}		
	}
	
	public boolean updateNewsItem(NewsItemBean newsItem) {
		
		String updateStatement = "UPDATE newsItems SET title = '%s', story = '%s', create_date = NOW() WHERE id = %d;";	
		String title = newsItem.getItemTitle();
		String story = newsItem.getItemStory();
		int id = newsItem.getItemId();
			
		String updateSQL = String.format(updateStatement, title, story, id);
		log.info("SQL Update statment: " + updateSQL);
		int result = 0;
		
		try {	
			Statement st = conn.createStatement();		
			result = st.executeUpdate(updateSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (result == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public List<NewsItemBean> getNewsItemsForUsername(String username) {
		return getNewsItemsWhere("WHERE reporter = '" + username + "'");			
	}	
	
	public NewsItemBean getNewsItemForId(int id) {
		List<NewsItemBean> results = getNewsItemsWhere("WHERE id = " + id);
		if(results.size() == 1) {
			return results.get(0);
		}
		 return null;
	}
	
	public List<NewsItemBean> getAllNewsItems() {
		return getNewsItemsWhere("");
	}
	
	public List<NewsItemBean> getNewsItemsWhere(String sqlWhereStatement) { 
		List<NewsItemBean> results = new ArrayList<NewsItemBean>();
		
		String getSQL = String.format("SELECT id, title, story, create_date, reporter from newsitems "+sqlWhereStatement+";");
		try {
			Statement st = conn.createStatement();		
			ResultSet rs = st.executeQuery(getSQL);
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String story = rs.getString("story");
				Date date = rs.getDate("create_date");
				String reporter = rs.getString("reporter");
				
				NewsItemBean newsItem = new NewsItemBean(title, story, reporter, date, id);
				results.add(newsItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return results;		
	}
	
	public boolean removeNewsItem(int itemId) {
		String deleteSQL = String.format("DELETE FROM newsitems WHERE id = %d;", itemId);
		int result = 0;
		
		try {
			Statement st = conn.createStatement();		
			result = st.executeUpdate(deleteSQL);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		if (result == 0)
		{
			return false;
		}
		else
		{
			this.removeAllFavoritesForNewsID(itemId);
			return true;
		}
	}

	public List<NewsItemBean> getFavoritesForSubscribeID(String subscriberID) 
	{	
		List<NewsItemBean> results = new ArrayList<NewsItemBean>();
				
		String getSQL = String.format("SELECT item_id FROM favorites WHERE username = '%s'", subscriberID);
		
		try {
			Statement st = conn.createStatement();		
			ResultSet rs = st.executeQuery(getSQL);
			while(rs.next()) 
			{
				int id = rs.getInt("item_id");
				NewsItemBean newsItem = this.getNewsItemForId(id);				
				results.add(newsItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return results;		
	}

	public boolean overWriteAllFavoritesForSubscribeID(String subscriberID,
			List<NewsItemBean> favorites) 
	{
		boolean result = removeFavoritesForSubscribeID(true, subscriberID, null);
				
		for (NewsItemBean index : favorites)
		{
			result = addFavoriteForSubscribeID(subscriberID, index);
		}
		
		return result;
	}

	public boolean addFavoriteForSubscribeID(String subscriberID,
			NewsItemBean newsItem) 
	{		
		String insertFavorite = String.format("INSERT INTO favorites (username, item_id) " +
				"VALUES ('%s', '%d');", subscriberID, newsItem.getItemId());	
		int result = 0;
		
		try {
			Statement st = conn.createStatement();		
			result = st.executeUpdate(insertFavorite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		if (result == 0)
		{
			return false;
		}
		else
		{
			return true;
		}	
	}

	public boolean removeFavoritesForSubscribeID(boolean removeAllFavorites,
			String subscriberID, NewsItemBean newsItem)
	{
		int result = 0;
		
		if (removeAllFavorites == false)
		{
			String deleteOneSQL = String.format("DELETE FROM favorites WHERE username = '%s' AND item_id = %d;", 
					subscriberID, newsItem.getItemId());
					
			try {
				Statement st = conn.createStatement();		
				result = st.executeUpdate(deleteOneSQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else if (newsItem == null)
		{
			String deleteAllSQL = String.format("DELETE FROM favorites WHERE username = '%s;'", subscriberID);
			try {
				Statement st = conn.createStatement();		
				result = st.executeUpdate(deleteAllSQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
		
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public boolean removeAllFavoritesForNewsID(int itemId) 
	{
		int result = 0;
		String deleteAllSQL = String.format("DELETE FROM favorites WHERE item_id = %d;", itemId);
		try {
			Statement st = conn.createStatement();		
			result = st.executeUpdate(deleteAllSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
