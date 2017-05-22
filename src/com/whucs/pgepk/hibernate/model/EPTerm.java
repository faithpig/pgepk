package com.whucs.pgepk.hibernate.model;

import java.util.Date;

//����רҵ֪ʶ��
public class EPTerm {
	private String id;			//id������
	private String title;		//����
	private String detail;		//����
	private Date addTime;		//���ʱ��
	private String gid;			//����Աid
	private String gname;		//����Ա��ʵ����
	private String sname;		//���������
	private long count;			//���ʼ�¼
	private String column;		//�����Ķ�����Ŀ��
	private String video;		//��Ƶ��flash���ļ���ַ����û����Ϊnull
	private int haspic;			//�Ƿ���ͼƬ��0��1

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getHaspic() {
		return haspic;
	}

	public void setHaspic(int haspic) {
		this.haspic = haspic;
	}

}
