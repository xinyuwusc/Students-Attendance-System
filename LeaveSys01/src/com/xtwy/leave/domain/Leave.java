package com.xtwy.leave.domain;

import java.util.Date;

public class Leave {
	private long lid;
	private long sid;
	private long tid;
	private Date lapplytime;
	private Date lbegintime;
	private Date lendtime;
	private String lreason;
	private String lapprovestatus;
	private String lfeedback;

	public Leave() {
		super();
	}

	public Leave(long lid, long sid, long tid, Date lapplytime,
			Date lbegintime, Date lendtime, String lreason,
			String lapprovestatus, String lfeedback) {
		super();
		this.lid = lid;
		this.sid = sid;
		this.tid = tid;
		this.lapplytime = lapplytime;
		this.lbegintime = lbegintime;
		this.lendtime = lendtime;
		this.lreason = lreason;
		this.lapprovestatus = lapprovestatus;
		this.lfeedback = lfeedback;
	}

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
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

	public String getLfeedback() {
		return lfeedback;
	}

	public void setLfeedback(String lfeedback) {
		this.lfeedback = lfeedback;
	}

	@Override
	public String toString() {
		return "Leave [lid=" + lid + ", sid=" + sid + ", tid=" + tid
				+ ", lapplytime=" + lapplytime + ", lbegintime=" + lbegintime
				+ ", lendtime=" + lendtime + ", lreason=" + lreason
				+ ", lapprovestatus=" + lapprovestatus + ", lfeedback="
				+ lfeedback + "]";
	}

}
