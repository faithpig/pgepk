package com.whucs.pgepk.service.impl;

import java.util.Date;
import java.util.List;

import com.whucs.pgepk.dao.impl.ArticleVisitorDao;
import com.whucs.pgepk.dao.impl.EPNewsDao;
import com.whucs.pgepk.dao.impl.EPPolicyDao;
import com.whucs.pgepk.dao.impl.EPTermDao;
import com.whucs.pgepk.hibernate.model.ArticleVisitor;
import com.whucs.pgepk.hibernate.model.EPNews;
import com.whucs.pgepk.hibernate.model.EPPolicy;
import com.whucs.pgepk.hibernate.model.EPTerm;
import com.whucs.pgepk.service.inter.IArticleVisitorService;

public class ArticleVisitorService implements IArticleVisitorService {

	private ArticleVisitorDao artVisDao;

	public void setArtVisDao(ArticleVisitorDao artVisDao) {
		this.artVisDao = artVisDao;
	}

	private EPNewsDao epNewDao;

	public void setEpNewDao(EPNewsDao epNewDao) {
		this.epNewDao = epNewDao;
	}

	private EPPolicyDao epPolDao;

	public void setEpPolDao(EPPolicyDao epPolDao) {
		this.epPolDao = epPolDao;
	}

	private EPTermDao epTerDao;

	public void setEpTerDao(EPTermDao epTerDao) {
		this.epTerDao = epTerDao;
	}


	public String add(Date enterTime, Date closeTime, String ip, String type,String aid) {
		ArticleVisitor entity = new ArticleVisitor();
		entity.setEnterTime(enterTime);
		entity.setCloseTime(closeTime);
		entity.setIp(ip);
		entity.setType(type);
		entity.setAid(aid);
		Long tempTotalTime = closeTime.getTime() - enterTime.getTime();
		entity.setTotalTime(tempTotalTime.toString());
		String rtnId = artVisDao.add(entity);
		return rtnId;
	}

	// BY JIANGYI
	public List<ArticleVisitor> findAid(String id){
		String hql = "from ArticleVisitor where WZJL_AID='"+id+"'";
		return artVisDao.list(hql);		
	}
	
	
	public List<EPNews> newsList() {
		String hql = "from EPNews order by XW_COUNT Desc";
		List<EPNews> newsList = epNewDao.findByPage(hql, 0, 10);
		return newsList;
	}

	public List<EPPolicy> policyList() {
		String hql = "from EPPolicy order by FG_COUNT Desc";
		List<EPPolicy> polList = epPolDao.findByPage(hql, 0, 10);
		return polList;
	}

	public List<EPTerm> termList() {
		String hql = "from EPTerm order by ZS_COUNT Desc";
		List<EPTerm> terList = epTerDao.findByPage(hql, 0, 10);
		return terList;
	}

	@SuppressWarnings("rawtypes")
	public List visitTimeAll() {
		//String hql="select h,count(*) from select to_char(ZYJL_ENTERTIME,'hh24') as h from ZYFWJL order by ZYJL_ID GROUP BY h";
		String hql="select date_format(ZYJL_ENTERTIME,'%H') from HomePageVisitor";
		try{
			List tmpTime=artVisDao.listTime(hql);
			return tmpTime;
			}catch(Exception e){
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVisitor> newsListDay(String day) {
		try{
		String hql="from ArticleVisitor where WZJL_TYPE='环保新闻' and date_format(WZJL_ENTERTIME,'%Y-%m-%d')='"+day+"'";
		List<ArticleVisitor> newsList = artVisDao.listTime(hql);
		return newsList;
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ArticleVisitor> policyListDay(String day) {
		try{
		String hql=" from ArticleVisitor where WZJL_TYPE='环保政策法规' and date_format(WZJL_ENTERTIME,'%Y-%m-%d')='"+day+"'";
		List<ArticleVisitor> polList = artVisDao.listTime(hql);
		return polList;
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ArticleVisitor> termListDay(String day) {
		try{
		String hql="from ArticleVisitor where WZJL_TYPE='环保专业知识' and date_format(WZJL_ENTERTIME,'%Y-%m-%d')='"+day+"'";
		List<ArticleVisitor> terList = artVisDao.listTime(hql);
		return terList;
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List visitTimeAllDay(String day) {
		try{
		String hql="select to_char(ZYJL_ENTERTIME,'hh24') from HomePageVisitor where date_format(ZYJL_ENTERTIME,'%Y-%m-%d')='"+day+"'";
		List tmpTime=artVisDao.listTime(hql);
		return tmpTime;
		}catch(Exception e){
			return null;
		}
	}


	public void delete(ArticleVisitor articleVisitor) {
		// TODO Auto-generated method stub
		artVisDao.delete(articleVisitor);
	}

}
