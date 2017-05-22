package com.whucs.pgepk.hibernate.model;

import java.util.Date;

//文章访问记录类
public class ArticleVisitor {
	private String id;			//id
	private Date enterTime;		//进入页面时刻
	private Date closeTime;		//退出页面时刻
	private String totalTime;	//总时间
	private String ip;			//浏览者ip
	private String type;		//文章种类：环保新闻、环保新闻、环保政策法规、
	private String aid;			//文章id

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
