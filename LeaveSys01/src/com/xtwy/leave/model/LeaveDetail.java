package com.xtwy.leave.model;

import java.util.Date;

public class LeaveDetail {
	private long lid;
	private String sno;
	private String sname;
	private Date lapplytime;
	private Date lbegintime;
	private Date lendtime;
	private String lreason;
	private String lapprovestatus;
	private String lfeekback;

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getLapplytime() {
		return lapplytime;
	}

	public void setLapplytime(Date lapplytime) {
		this.lapplytime = lapplytime;
	}

	public Date getLbegintime() {
		return lbegintime;
	}

	public void setLbegintime(Date lbegintime) {
		this.lbegintime = lbegintime;
	}

	public Date getLendtime() {
		return lendtime;
	}

	public void setLendtime(Date lendtime) {
		this.lendtime = lendtime;
	}

	public String getLreason() {
		return lreason;
	}

	public void setLreason(String lreason) {
		this.lreason = lreason;
	}

	public String getLapprovestatus() {
		return lapprovestatus;
	}

	public void setLapprovestatus(String lapprovestatus) {
		this.lapprovestatus = lapprovestatus;
	}

	public String getLfeekback() {
		return lfeekback;
	}

	public void setLfeekback(String lfeekback) {
		this.lfeekback = lfeekback;
	}

	@Override
	public String toString() {
		return "LeaveDetail [lid=" + lid + ", sno=" + sno + ", sname=" + sname
				+ ", lapplytime=" + lapplytime + ", lbegintime=" + lbegintime
				+ ", lendtime=" + lendtime + ", lreason=" + lreason
				+ ", lapprovestatus=" + lapprovestatus + ", lfeekback="
				+ lfeekback + "]";
	}

}
