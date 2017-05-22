package com.whucs.pgepk.dao.impl;

import java.util.List;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.Message;
import com.whucs.pgepk.util.YeekuHibernateDaoSupport;

public class MessageDao extends YeekuHibernateDaoSupport<Message> implements IBaseDao<Message>{

	@Override
	public String add(Message entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(Message entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(Message entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Message find(String id) {
		return getHibernateTemplate().get(Message.class, id);
	}

}
