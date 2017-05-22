package com.whucs.pgepk.service.impl;

import com.whucs.pgepk.dao.impl.HomePageHistoryDao;
import com.whucs.pgepk.service.inter.IHomePageHistoryService;

public class HomePageHistoryService implements IHomePageHistoryService{

	@SuppressWarnings("unused")
	private HomePageHistoryDao homPagHisDao;

	public void setHomPagHisDao(HomePageHistoryDao homPagHisDao) {
		this.homPagHisDao = homPagHisDao;
	}
	
}
