package com.colson.dal.excel.bean;

public class Prop{
	private int idx;
	private String pname;
	private String handers;
	
	
	
	public Prop(int idx, String pname, String handers) {
		super();
		this.idx = idx;
		this.pname = pname;
		this.handers = handers;
	}
	
	
	public Prop(int idx, String pname) {
		super();
		this.idx = idx;
		this.pname = pname;
	}


	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getHanders() {
		return handers;
	}
	public void setHanders(String handers) {
		this.handers = handers;
	}
	
	
}
