package com.whucs.pgepk.service.impl;

import java.util.List;

import com.whucs.pgepk.dao.impl.LinkDao;
import com.whucs.pgepk.hibernate.model.Link;
import com.whucs.pgepk.service.inter.ILinkService;

public class LinkService implements ILinkService{

	private LinkDao linDao;
	
	public void setLinDao(LinkDao linDao) {
		this.linDao = linDao;
	}

	@Override
	public List<Link> list() {
		String hql="from Link order by id desc";
		List<Link> linList=linDao.list(hql);
		return linList;
	}
	
	
	//BY JIANGYI 
	public Link findFromId(String id){
		String hql="from Link where LJ_ID='"+id+"'";
		Link lin=null;
		try{
			lin=linDao.list(hql).get(0);
		}catch(Exception e){
			System.out.println("查询出错");
			lin=null;
		}		
		return lin;
	}
	
	public void update(Link lin) {
		// TODO Auto-generated method stub
		linDao.update(lin);
	}
	public void delete(Link lin) {
		// TODO Auto-generated method stub
		linDao.delete(lin);
	}
	public void add(Link lin) {
		// TODO Auto-generated method stub
		linDao.add(lin);
	}

}
