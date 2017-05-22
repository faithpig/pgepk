package com.whucs.pgepk.service.impl;

import java.util.List;

import com.whucs.pgepk.dao.impl.EPPolicyDao;
import com.whucs.pgepk.hibernate.model.EPPolicy;
import com.whucs.pgepk.service.inter.IEPPolicyService;

public class EPPolicyService implements IEPPolicyService{

	private EPPolicyDao epPolDao;
	
	public void setEpPolDao(EPPolicyDao epPolDao) {
		this.epPolDao = epPolDao;
	}

	@Override
	public List<EPPolicy> list(int offset, int pageSize) {
		String hql="from EPPolicy order by addTime Desc,id desc";
		List<EPPolicy> polList=epPolDao.findByPage(hql, offset, pageSize);
		return polList;
	}
	
	//获取总记录数
	public int getAllCount(){
		String hql="from EPPolicy order by id Desc";
		int max=epPolDao.list(hql).size();
		return max;
	}

	@Override
	public EPPolicy detail(String id) {
		EPPolicy entity=epPolDao.find(id);
		return entity;
	}

	@Override
	public List<EPPolicy> search(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from EPPolicy where title like '%"+key+"%' order by addTime Desc,id desc";
			List<EPPolicy> polList=epPolDao.findByPage(hql, offset, pageSize);
			return polList;
		}
		String hql="from EPPolicy where title like '%"+key+"%' and column='"+column+"' order by addTime Desc,id desc";
		List<EPPolicy> polList=epPolDao.findByPage(hql, offset, pageSize);
		return polList;
	}
	
	//获取搜索总记录数
	public int getSearchCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from EPPolicy where title like '%"+key+"%' order by id Desc";
			int max=epPolDao.list(hql).size();
			return max;
		}
		String hql="from EPPolicy where title like '%"+key+"%' and column='"+column+"' order by id Desc";
		int max=epPolDao.list(hql).size();
		return max;
	}

	@Override
	public List<EPPolicy> listVerify(int offset, int pageSize) {
		String hql="from EPPolicy where sname is not null order by addTime Desc,id desc";
		List<EPPolicy> polList=epPolDao.findByPage(hql, offset, pageSize);
		return polList;
	}

	public int getListVerifyCount(){
		String hql="from EPPolicy where sname is not null order by addTime Desc";
		int max=epPolDao.list(hql).size();
		return max;
	}
	
	@Override
	public List<EPPolicy> searchVerify(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from EPPolicy where title like '%"+key+"%' and sname is not null order by addTime Desc,id desc";
			List<EPPolicy> polList=epPolDao.findByPage(hql, offset, pageSize);
			return polList;
		}
		String hql="from EPPolicy where title like '%"+key+"%' and column='"+column+"' and sname is not null order by addTime Desc,id desc";
		List<EPPolicy> polList=epPolDao.findByPage(hql, offset, pageSize);
		return polList;
	}
	
	public int getSearchVerifyCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from EPPolicy where title like '%"+key+"%' and sname is not null order by id Desc";
			int max=epPolDao.list(hql).size();
			return max;
		}
		String hql="from EPPolicy where title like '%"+key+"%' and column='"+column+"' and sname is not null order by id Desc";
		int max=epPolDao.list(hql).size();
		return max;
	}
	
	//BY JIANGYI 
	public EPPolicy findFromId(String id){
		String hql="from EPPolicy where FG_ID='"+id+"'";
		EPPolicy epPol=null;
		try{
			epPol=epPolDao.list(hql).get(0);
		}catch(Exception e){
			System.out.println("查询出错");
			epPol=null;
		}		
		return epPol;
	}
	
	public void update(EPPolicy epPol) {
		// TODO Auto-generated method stub
		epPolDao.update(epPol);
	}
	public void delete(EPPolicy epPol) {
		// TODO Auto-generated method stub
		epPolDao.delete(epPol);
	}
	public void add(EPPolicy epPol) {
		// TODO Auto-generated method stub
		epPolDao.add(epPol);
	}
	public int getListNotVerifyCount(){
		String hql="from EPPolicy where sname is null order by id Desc";
		int max=epPolDao.list(hql).size();
		return max;
	}
	public List<EPPolicy> listNotVerify(int offset, int pageSize) {
		String hql="from EPPolicy where sname is null order by addTime Desc,id desc";
		List<EPPolicy> newsList=epPolDao.findByPage(hql, offset, pageSize);
		return newsList;
	}

}
