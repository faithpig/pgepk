package com.whucs.pgepk.service.impl;

import java.util.List;

import com.whucs.pgepk.util.*;
import com.whucs.pgepk.dao.impl.AdministratorDao;
import com.whucs.pgepk.hibernate.model.Administrator;
import com.whucs.pgepk.service.inter.IAdministratorService;

public class AdministratorService implements IAdministratorService{

	private AdministratorDao admDao;

	public void setAdmDao(AdministratorDao admDao) {
		this.admDao = admDao;
	}

	public Administrator findFromName(String a_username) {
		String hql="from Administrator where GLY_USERID='"+a_username+"'";
		Administrator adm=null;
		try{
		adm=admDao.list(hql).get(0);
		}catch(Exception e){
			System.out.println("查询出错");
			adm=null;
		}		
		return adm;
	}
	
	public Administrator findFromId(String id) {
		String hql="from Administrator where GLY_ID='"+id+"'";
		Administrator adm=null;
		try{
		adm=admDao.list(hql).get(0);
		}catch(Exception e){
			System.out.println("查询出错");
			adm=null;
		}		
		return adm;
	}

	@Override
	public void update(Administrator adm) {
		// TODO Auto-generated method stub
		admDao.update(adm);
	}
	public void delete(Administrator adm) {
		// TODO Auto-generated method stub
		admDao.delete(adm);
	}

	public void add(Administrator adm) {
		// TODO Auto-generated method stub
		admDao.add(adm);
	}
	//查询所有记录
	public int getRows(){
		String hql="from Administrator";
		return admDao.Records(hql);
	}
	//分页初始化
	public Pager pageInitial(Pager pager,String method){
		pager.newPager(pager.getCurrentPage(),method,pager.getMaxRecords(),pager.getPageSize());
		return pager;
	}
	//按页显示
	public List<Administrator> viewAdminByList(int offset,int pageSize){
		String hql="from Administrator order by GLY_ID Desc";
		return admDao.findByPage(hql, offset, pageSize);
	}
	public int searchAdminMax(String key, String column) {
		String hql="from Administrator where GLY_USERID like '%"+key+"%'";
		if(column.equals("userId")){
			hql="from Administrator where GLY_USERID like '%"+key+"%'";
		}else if(column.equals("a_name")){
			hql="from Administrator where GLY_NAME like '%"+key+"%'";
		}
		return admDao.Records(hql);
	}
	@Override
	public List<Administrator> searchAdmin(String key, String column,
			int offset, int pageSize) {
		// TODO Auto-generated method stub
		List<Administrator> entity=null;
		String hql="from Administrator where GLY_USERID like '%"+key+"%'";
		if(column.equals("userId")){
			hql="from Administrator where GLY_USERID like '%"+key+"%'";
		}else if(column.equals("a_name")){
			hql="from Administrator where GLY_NAME like '%"+key+"%'";
		}
		entity=admDao.findByPage(hql, offset, pageSize);
		return entity;
	}
}
