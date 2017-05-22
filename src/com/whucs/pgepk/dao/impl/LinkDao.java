package com.whucs.pgepk.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.Link;

public class LinkDao extends HibernateDaoSupport implements IBaseDao<Link>{

	@Override
	public String add(Link entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(Link entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(Link entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Link> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Link find(String id) {
		return getHibernateTemplate().get(Link.class, id);
	}

}
