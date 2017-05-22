package com.whucs.pgepk.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.HomePageHistory;

public class HomePageHistoryDao extends HibernateDaoSupport implements IBaseDao<HomePageHistory>{

	@Override
	public String add(HomePageHistory entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(HomePageHistory entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(HomePageHistory entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HomePageHistory> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public HomePageHistory find(String id) {
		return getHibernateTemplate().get(HomePageHistory.class, id);
	}

}
