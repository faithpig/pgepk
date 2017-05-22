package com.whucs.pgepk.hibernate.model;

//����Ա��
public class Administrator {
	private String id;			//����Աid������
	private String userId;		//����Ա�û���
	private int isSuper;		//�Ƿ񳬼�����Ա��0��1
	private String password;	//����Ա����
	private String name;		//����Ա��ʵ����
	private String number;		//����Ա���֤��
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
