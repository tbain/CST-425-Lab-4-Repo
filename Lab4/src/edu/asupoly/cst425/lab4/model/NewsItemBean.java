package edu.asupoly.cst425.lab4.model;

import java.util.Date;

public class NewsItemBean {
	private static int nextId = 1;
	
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
		itemId = nextId++;
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
