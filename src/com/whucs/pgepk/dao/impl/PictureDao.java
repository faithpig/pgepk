package com.whucs.pgepk.dao.impl;

import java.util.List;
import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.Picture;
import com.whucs.pgepk.util.YeekuHibernateDaoSupport;

public class PictureDao extends YeekuHibernateDaoSupport<Picture> implements IBaseDao<Picture>{

	@Override
	public String add(Picture entity) {
		String rt = (String)getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(Picture entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(Picture entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public Picture find(String id) {
		return getHibernateTemplate().get(Picture.class, id);
	}

}
