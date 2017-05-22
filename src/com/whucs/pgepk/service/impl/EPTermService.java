package com.whucs.pgepk.service.impl;

import java.util.List;

import com.whucs.pgepk.dao.impl.EPTermDao;
import com.whucs.pgepk.hibernate.model.EPTerm;
import com.whucs.pgepk.service.inter.IEPTermService;

public class EPTermService implements IEPTermService{

	private EPTermDao epTerDao;
	
	public void setEpTerDao(EPTermDao epTerDao) {
		this.epTerDao = epTerDao;
	}

	@Override
	public List<EPTerm> list(int offset, int pageSize) {
		String hql="from EPTerm order by ZS_ADDTIME Desc,id desc";
		List<EPTerm> terList=epTerDao.findByPage(hql, offset, pageSize);
		return terList;
	}
	
	//获取总记录数
	public int getAllCount(){
		String hql="from EPTerm order by id Desc";
		int max=epTerDao.list(hql).size();
		return max;
	}

	@Override
	public EPTerm detail(String id) {
		EPTerm entity=epTerDao.find(id);
		return entity;
	}

	@Override
	public List<EPTerm> search(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from EPTerm where title like '%"+key+"%' order by addTime Desc,id desc";
			List<EPTerm> terList=epTerDao.findByPage(hql, offset, pageSize);
			return terList;
		}
		String hql="from EPTerm where title like '%"+key+"%' and column='"+column+"' order by addTime Desc,id desc";
		List<EPTerm> terList=epTerDao.findByPage(hql, offset, pageSize);
		return terList;
	}
	
	//获取搜索总记录数
	public int getSearchCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from EPTerm where title like '%"+key+"%' order by id Desc";
			int max=epTerDao.list(hql).size();
			return max;
		}
		String hql="from EPTerm where title like '%"+key+"%' and column='"+column+"' order by id Desc";
		int max=epTerDao.list(hql).size();
		return max;
	}

	@Override
	public List<EPTerm> listVerify(int offset, int pageSize) {
		String hql="from EPTerm where sname is not null order by ZS_ADDTIME Desc,id desc";
		List<EPTerm> terList=epTerDao.findByPage(hql, offset, pageSize);
		return terList;
	}
	
	public int getListVerifyCount(){
		String hql="from EPTerm where sname is not null order by id Desc";
		int max=epTerDao.list(hql).size();
		return max;
	}

	@Override
	public List<EPTerm> searchVerify(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from EPTerm where title like '%"+key+"%' and sname is not null order by ZS_ADDTIME Desc,id desc";
			List<EPTerm> terList=epTerDao.findByPage(hql, offset, pageSize);
			return terList;
		}
		String hql="from EPTerm where title like '%"+key+"%' and column='"+column+"' and sname is not null order by ZS_ADDTIME Desc,id desc";
		List<EPTerm> terList=epTerDao.findByPage(hql, offset, pageSize);
		return terList;
	}
	
	public int getSearchVerifyCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from EPTerm where title like '%"+key+"%' and sname is not null order by id Desc";
			int max=epTerDao.list(hql).size();
			return max;
		}
		String hql="from EPTerm where title like '%"+key+"%' and column='"+column+"' and sname is not null order by id Desc";
		int max=epTerDao.list(hql).size();
		return max;
	}
	
	
	//BY JIANGYI
	public EPTerm findFromId(String id){
		String hql="from EPTerm where ZS_ID='"+id+"'";
		EPTerm epTerm=null;
		try{
			epTerm=epTerDao.list(hql).get(0);
		}catch(Exception e){
			System.out.println("查询出错");
			epTerm=null;
		}		
		return epTerm;
	}
	
	public void update(EPTerm epTerm) {
		// TODO Auto-generated method stub
		epTerDao.update(epTerm);
	}
	public void delete(EPTerm epTerm) {
		// TODO Auto-generated method stub
		epTerDao.delete(epTerm);
	}
	public void add(EPTerm epTerm) {
		// TODO Auto-generated method stub
		epTerDao.add(epTerm);
	}
	public int getListNotVerifyCount(){
		String hql="from EPTerm where sname is null order by id Desc";
		int max=epTerDao.list(hql).size();
		return max;
	}
	public List<EPTerm> listNotVerify(int offset, int pageSize) {
		String hql="from EPTerm where sname is null order by ZS_ADDTIME Desc,id desc";
		List<EPTerm> newsList=epTerDao.findByPage(hql, offset, pageSize);
		return newsList;
	}

}
