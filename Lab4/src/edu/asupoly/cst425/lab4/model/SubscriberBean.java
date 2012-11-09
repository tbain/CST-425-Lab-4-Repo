package edu.asupoly.cst425.lab4.model;

public class SubscriberBean {
	String subscriberId;
	String passwd;
			
	public SubscriberBean(String subscriberId, String passwd) {
		this.subscriberId = subscriberId;
		this.passwd = passwd;	
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
}
