package com.whucs.pgepk.dao.impl;

import java.util.List;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.EPPolicy;
import com.whucs.pgepk.util.YeekuHibernateDaoSupport;

public class EPPolicyDao extends YeekuHibernateDaoSupport<EPPolicy> implements IBaseDao<EPPolicy>{

	@Override
	public String add(EPPolicy entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(EPPolicy entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(EPPolicy entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EPPolicy> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public EPPolicy find(String id) {
		return getHibernateTemplate().get(EPPolicy.class, id);
	}

}
