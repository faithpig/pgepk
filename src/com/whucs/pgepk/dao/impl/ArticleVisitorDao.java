package com.whucs.pgepk.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whucs.pgepk.dao.inter.IBaseDao;
import com.whucs.pgepk.hibernate.model.ArticleVisitor;

public class ArticleVisitorDao extends HibernateDaoSupport implements IBaseDao<ArticleVisitor>{

	@Override
	public String add(ArticleVisitor entity) {
		String rt = (String) getHibernateTemplate().save(entity);
		return rt;
	}

	@Override
	public void delete(ArticleVisitor entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(ArticleVisitor entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleVisitor> list(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public ArticleVisitor find(String id) {
		return getHibernateTemplate().get(ArticleVisitor.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	public List listTime(String sql){
		try{
		List list=getHibernateTemplate().find(sql);
		return list;
		}catch(Exception e){
			return null;
		}
	}
	
}
