package com.whucs.pgepk.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.Contact;


public class ContactDao extends HibernateDaoSupport implements IBaseDao<Contact>{

	@Override
	public String add(Contact entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(Contact entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(Contact entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Contact find(String id) {
		return getHibernateTemplate().get(Contact.class, id);
	}

}
