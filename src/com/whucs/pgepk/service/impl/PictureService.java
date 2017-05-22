package com.whucs.pgepk.service.impl;

import java.util.List;

import com.whucs.pgepk.dao.impl.PictureDao;
import com.whucs.pgepk.hibernate.model.Picture;
import com.whucs.pgepk.service.inter.IPictureService;

public class PictureService implements IPictureService{

	private PictureDao picDao;
	
	public void setPicDao(PictureDao picDao) {
		this.picDao = picDao;
	}

	@Override
	public List<Picture> get(String type, String wzid) {
		String hql="from Picture where type='"+type+"'and wzid='"+wzid+"'";
		List<Picture> picList=picDao.list(hql);
		return picList;
	}
	
	//获取首页的3个图片
	public List<Picture> getNewsList(){
		String hql="from Picture where type='环保新闻' order by id Desc";
		List<Picture> picList=picDao.list(hql);
		return picList;
	}
	
	
	//BY JIANGYI
	public void addPic(Picture entity){
		picDao.add(entity);
	}
	
	public List<Picture> findFromName(String name){
		String hql="from Picture where TP_NAME='"+name+"'";
		List<Picture> picList=picDao.list(hql);
		return picList;
	}

	public void delete(Picture entity){
		picDao.delete(entity);
	}
}
