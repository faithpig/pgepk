package com.whucs.pgepk.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.HomePageVisitor;

public class HomePageVisitorDao extends HibernateDaoSupport implements IBaseDao<HomePageVisitor>{

	@Override
	public String add(HomePageVisitor entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(HomePageVisitor entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(HomePageVisitor entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HomePageVisitor> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public HomePageVisitor find(String id) {
		return getHibernateTemplate().get(HomePageVisitor.class, id);
	}

}
