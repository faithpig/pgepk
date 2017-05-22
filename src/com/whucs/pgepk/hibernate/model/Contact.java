package com.whucs.pgepk.hibernate.model;

//����ϵ���ǡ���
public class Contact {
	private String id;			//id,����
	private String gid;			//����Աid
	private String gname;		//����Ա��ʵ����
	private String e_mail;		//��ϵ��ʽ��email
	private String linkman;		//��ϵ��
	private String phone;		//��ϵ��ʽ���绰����
	private String postcode;		//�ʱ�
	private String address;		//ͨѶ��ַ

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
