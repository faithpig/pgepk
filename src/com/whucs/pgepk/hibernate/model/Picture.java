package com.whucs.pgepk.hibernate.model;

//ͼƬ��
public class Picture {
	private String id;				//ͼƬid������
	private String extension;		//��չ��
	private String type;			//ͼƬ���ࣨ�������š�����רҵ֪ʶ���������߷��棩
	private String wzid;				//����id
	private String path;			//ͼƬ����·��
	private String name;			//ͼƬ��

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWzid() {
		return wzid;
	}

	public void setWzid(String wzid) {
		this.wzid = wzid;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
