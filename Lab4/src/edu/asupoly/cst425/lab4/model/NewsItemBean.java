package edu.asupoly.cst425.lab4.model;

import java.util.Date;

public class NewsItemBean {
	
	private int itemId;
	private String itemTitle;
	private String itemStory;
	private Date itemDate;
	private String reporterId;
	
	NewsItemBean(String title, String story, String rid) {
		itemTitle = title;
		itemStory = story;
		reporterId = rid;
		itemDate = new Date();
	}
	
	NewsItemBean(String title, String story, String rid, Date date) {
		this.itemTitle = title;
		this.itemStory = story;
		this.reporterId = rid;
		this.itemDate = date;
	}
	
	public NewsItemBean(String title, String story, String rid, Date date, int itemId) {
		this.itemTitle = title;
		this.itemStory = story;
		this.reporterId = rid;
		this.itemDate = date;
		this.itemId = itemId;
	}
	
	public int getItemId() {
		return itemId;
	}
	public String getReporterId() {
		return reporterId;
	}

	public void setItemTitle(String itemTitle, String rid) {
		if (rid.equals(reporterId)) {
			this.itemTitle = itemTitle;
		}
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemStory(String itemStory, String rid) {
		if (rid.equals(reporterId)) {
			this.itemStory = itemStory;
			setItemDate(new Date());
		}
	}
	
	public void setItemStory(String itemStory) {
		this.itemStory = itemStory;
	}

	public String getItemStory() {
		return itemStory;
	}

	private void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

	public Date getItemDate() {
		return itemDate;
	}
	
}
