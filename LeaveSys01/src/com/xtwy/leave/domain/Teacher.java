package com.xtwy.leave.domain;

public class Teacher {

	public Teacher() {
		super();

	}

	public Teacher(long tid, String taccount, String tpassword, String tname,
			String tsex) {
		super();
		this.tid = tid;
		this.taccount = taccount;
		this.tpassword = tpassword;
		this.tname = tname;
		this.tsex = tsex;
	}

	private long tid;
	private String taccount;
	private String tpassword;
	private String tname;
	private String tsex;

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getTaccount() {
		return taccount;
	}

	public void setTaccount(String taccount) {
		this.taccount = taccount;
	}

	public String getTpassword() {
		return tpassword;
	}

	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", taccount=" + taccount
				+ ", tpassword=" + tpassword + ", tname=" + tname + ", tsex="
				+ tsex + "]";
	}

}
