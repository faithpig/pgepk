package com.whucs.pgepk.hibernate.model;

import java.util.Date;

//��ҳ���ʼ�¼��
public class HomePageVisitor {
	private String id;			//id,����
	private Date enterTime;		//�οͽ���ʱ��
	private String ip;			//�ο�ip

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
