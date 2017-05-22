package com.whucs.pgepk.dao.impl;

import java.util.List;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.EPNews;
import com.whucs.pgepk.util.YeekuHibernateDaoSupport;

public class EPNewsDao extends YeekuHibernateDaoSupport<EPNews> implements IBaseDao<EPNews>{

	@Override
	public String add(EPNews entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(EPNews entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(EPNews entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EPNews> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public EPNews find(String id) {
		return getHibernateTemplate().get(EPNews.class, id);
	}

}
