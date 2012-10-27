package edu.asupoly.cst425.lab4;

public class ReporterBean {
	String reporterId;
	String passwd;
	
	ReporterBean(String rid, String passwd) {
		this.reporterId = rid;
		this.passwd = passwd;
	}

	public String getReporterId() {
		return reporterId;
	}

	public String getPasswd() {
		return passwd;
	}
}
