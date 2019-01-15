package com.xtwy.leave.domain;

import java.util.Date;

public class Student {
	private long sid;
	private String sno;
	private String spassword;
	private String sname;
	private String ssex;
	private Date sbirth;
	private String stelephone;
	private String sdeletestatus;
	private long tid;

	public Student() {
		super();
	}

	public Student(long sid, String sno, String spassword, String sname,
			String ssex, Date sbirth, String stelephone, String sdeletestatus,
			long tid) {
		super();
		this.sid = sid;
		this.sno = sno;
		this.spassword = spassword;
		this.sname = sname;
		this.ssex = ssex;
		this.sbirth = sbirth;
		this.stelephone = stelephone;
		this.sdeletestatus = sdeletestatus;
		this.tid = tid;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public Date getSbirth() {
		return sbirth;
	}

	public void setSbirth(Date sbirth) {
		this.sbirth = sbirth;
	}

	public String getStelephone() {
		return stelephone;
	}

	public void setStelephone(String stelephone) {
		this.stelephone = stelephone;
	}

	public String getSdeletestatus() {
		return sdeletestatus;
	}

	public void setSdeletestatus(String sdeletestatus) {
		this.sdeletestatus = sdeletestatus;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sno=" + sno + ", spassword="
				+ spassword + ", sname=" + sname + ", ssex=" + ssex
				+ ", sbirth=" + sbirth + ", stelephone=" + stelephone
				+ ", sdeletestatus=" + sdeletestatus + ", tid=" + tid + "]";
	}

}
