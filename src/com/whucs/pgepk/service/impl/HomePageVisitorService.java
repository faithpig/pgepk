package com.whucs.pgepk.service.impl;

import java.util.Date;

import com.whucs.pgepk.dao.impl.HomePageVisitorDao;
import com.whucs.pgepk.hibernate.model.HomePageVisitor;
import com.whucs.pgepk.service.inter.IHomePageVisitorService;

public class HomePageVisitorService implements IHomePageVisitorService{

	private HomePageVisitorDao homPagVisDao;
	
	public void setHomPagVisDao(HomePageVisitorDao homPagVisDao) {
		this.homPagVisDao = homPagVisDao;
	}

	@Override
	public String add(Date enterTime, String ip) {
		HomePageVisitor entity=new HomePageVisitor();
		entity.setEnterTime(enterTime);
		entity.setIp(ip);
		String rtnId=homPagVisDao.add(entity);
		return rtnId;
	}

}
