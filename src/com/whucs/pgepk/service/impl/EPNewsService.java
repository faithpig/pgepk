package com.whucs.pgepk.service.impl;

import java.util.List;

import com.whucs.pgepk.dao.impl.EPNewsDao;
import com.whucs.pgepk.hibernate.model.EPNews;
import com.whucs.pgepk.service.inter.IEPNewsService;

public class EPNewsService implements IEPNewsService{

	private EPNewsDao epNewDao;
	
	public void setEpNewDao(EPNewsDao epNewDao) {
		this.epNewDao = epNewDao;
	}

	@Override
	public List<EPNews> list(int offset, int pageSize) {
		String hql="from EPNews order by addTime Desc,id desc";
		List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
		return newsList;
	}
	
	//获取总记录数
	public int getAllCount(){
		String hql="from EPNews order by id Desc";
		int max=epNewDao.list(hql).size();
		return max;
	}

	@Override
	public EPNews detail(String id) {
		EPNews entity=epNewDao.find(id);
		return entity;
	}

	@Override
	public List<EPNews> search(String key, String column, int offset,int pageSize) {
		String hql;
		if(column.trim().equals("")){
			if(null==key){
				 hql="from EPNews order by addTime Desc,id desc";
			}else{
				hql="from EPNews where title like '%"+key+"%' order by addTime Desc,id desc";
			}
			List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
			return newsList;
		}
		if(null==key){
			hql="from EPNews where column='"+column+"' order by addTime Desc,id desc";
		}else{
		hql="from EPNews where title like '%"+key+"%' and column='"+column+"' order by addTime Desc,id desc";
		}List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
		return newsList;
	}
	
	//获取搜索总记录数
	public int getSearchCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from EPNews where title like '%"+key+"%' order by id Desc";
			int max=epNewDao.list(hql).size();
			return max;
		}
		String hql="from EPNews where title like '%"+key+"%' and column='"+column+"' order by id Desc";
			int max=epNewDao.list(hql).size();
		return max;
	}

	@Override
	public List<EPNews> listVerify(int offset, int pageSize) {
		String hql="from EPNews where sname is not null order by addTime Desc,id desc";
		List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
		return newsList;
	}
	
	public int getListVerifyCount(){
		String hql="from EPNews where sname is not null order by addTime Desc,id desc";
		int max=epNewDao.list(hql).size();
		return max;
	}

	@Override
	public List<EPNews> searchVerify(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from EPNews where title like '%"+key+"%' and sname is not null order by addTime Desc,id desc";
			List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
			return newsList;
		}
		String hql="from EPNews where title like '%"+key+"%' and column='"+column+"' and sname is not null order by addTime Desc,id desc";
		List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
		return newsList;
	}
	
	public int getSearchVerifyCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from EPNews where title like '%"+key+"%' and sname is not null order by id Desc";
			int max=epNewDao.list(hql).size();
			return max;
		}
		String hql="from EPNews where title like '%"+key+"%' and column='"+column+"' and sname is not null order by id Desc";
			int max=epNewDao.list(hql).size();
		return max;
	}
	
	//BY JIANGYI 
	public EPNews findFromId(String id){
		String hql="from EPNews where XW_ID='"+id+"'";
		EPNews epNew=null;
		try{
			epNew=epNewDao.list(hql).get(0);
		}catch(Exception e){
			System.out.println("查询出错");
			epNew=null;
		}		
		return epNew;
	}
	
	public void update(EPNews epNew) {
		// TODO Auto-generated method stub
		epNewDao.update(epNew);
	}
	public void delete(EPNews epNew) {
		// TODO Auto-generated method stub
		epNewDao.delete(epNew);
	}
	public void add(EPNews epNew) {
		// TODO Auto-generated method stub
		epNewDao.add(epNew);
	}
	public int getListNotVerifyCount(){
		String hql="from EPNews where sname is null order by id Desc";
		int max=epNewDao.list(hql).size();
		return max;
	}
	public List<EPNews> listNotVerify(int offset, int pageSize) {
		String hql="from EPNews where sname is null order by addTime Desc,id desc";
		List<EPNews> newsList=epNewDao.findByPage(hql, offset, pageSize);
		return newsList;
	}
}
