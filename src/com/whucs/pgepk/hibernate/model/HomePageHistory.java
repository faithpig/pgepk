package com.whucs.pgepk.hibernate.model;

import java.util.Date;

//��ҳ������ʷ��¼��
public class HomePageHistory {
	private String id;		//id,����
	private Date time;		//���ڣ����嵽�죬����
	private byte[] pic;		//����ͼ

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

}
