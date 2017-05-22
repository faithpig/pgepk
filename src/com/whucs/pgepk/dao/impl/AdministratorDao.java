package com.whucs.pgepk.dao.impl;

import java.util.List;

import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.Administrator;
import com.whucs.pgepk.util.YeekuHibernateDaoSupport;

public class AdministratorDao extends YeekuHibernateDaoSupport<Administrator> implements IBaseDao<Administrator>{

	@Override
	public String add(Administrator entity) {
		String rt=(String) getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(Administrator entity) {
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(Administrator entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrator> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Administrator find(String id) {
		return getHibernateTemplate().get(Administrator.class,id);
	}
	
	//记录的总数
	public int Records(String hql){	
		return getHibernateTemplate().find(hql).size();
	}

}
