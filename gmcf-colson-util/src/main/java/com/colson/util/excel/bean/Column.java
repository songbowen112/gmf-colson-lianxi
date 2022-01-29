package com.colson.util.excel.bean;

public class Column {
	private int cidx;
	private String cname;
	private String pname;
	private String type;
	private String cformat;
	private String handler;
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCformat() {
		return cformat;
	}
	public void setCformat(String cformat) {
		this.cformat = cformat;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
}
