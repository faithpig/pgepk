package com.whucs.pgepk.dao.impl;

import java.util.List;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.EPTerm;
import com.whucs.pgepk.util.YeekuHibernateDaoSupport;

public class EPTermDao extends YeekuHibernateDaoSupport<EPTerm> implements IBaseDao<EPTerm>{

	@Override
	public String add(EPTerm entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(EPTerm entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(EPTerm entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EPTerm> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public EPTerm find(String id) {
		return getHibernateTemplate().get(EPTerm.class, id);
	}

}
