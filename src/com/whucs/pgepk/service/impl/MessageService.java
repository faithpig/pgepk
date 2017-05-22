package com.whucs.pgepk.service.impl;

import java.util.Date;
import java.util.List;

import com.whucs.pgepk.dao.impl.MessageDao;
import com.whucs.pgepk.hibernate.model.Message;
import com.whucs.pgepk.service.inter.IMessageService;

public class MessageService implements IMessageService{

	private MessageDao mesDao;
	
	public void setMesDao(MessageDao mesDao) {
		this.mesDao = mesDao;
	}

	@Override
	public List<Message> list(int offset, int pageSize) {
		String hql="from Message order by LYB_ADDTIME Desc";
		List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
		return mesList;
	}
	
	//获取总记录数
	public int getAllCount(){
		String hql="from Message order by id Desc";
		int max=mesDao.list(hql).size();
		return max;
	}

	@Override
	public Message detail(String id) {
		Message entity=mesDao.find(id);
		return entity;
	}

	@Override
	public List<Message> search(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from Message where detail like '%"+key+"%' order by LYB_ADDTIME Desc";
			List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
			return mesList;
		}
		String hql="from Message where detail like '%"+key+"%' and column='"+column+"' order by LYB_ADDTIME Desc";
		List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
		return mesList;
	}

	//获取搜索总记录数
	public int getSearchCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from Message where detail like '%"+key+"%' order by id Desc";
			int max=mesDao.list(hql).size();
			return max;
		}
		String hql="from Message where detail like '%"+key+"%' and column='"+column+"' order by id Desc";
		int max=mesDao.list(hql).size();
		return max;		
	}
	
	@Override
	public String add(String ip, String detail,String column) {
		Message entity = new Message();
		entity.setAddTime(new Date());
		entity.setColumn(column);
		entity.setDetail(detail);
		entity.setIp(ip);
		String rtnId=mesDao.add(entity);
		return rtnId;
	}

	@Override
	public List<Message> listVerify(int offset, int pageSize) {
		String hql="from Message where sname is not null order by LYB_ADDTIME Desc";
		List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
		return mesList;
	}
	
	public int getListVerifyCount(){
		String hql="from Message where sname is not null order by id Desc";
		int max=mesDao.list(hql).size();
		return max;
	}

	@Override
	public List<Message> searchVerify(String key, String column, int offset,
			int pageSize) {
		if(column.trim().equals("")){
			String hql="from Message where detail like '%"+key+"%' and sname is not null order by LYB_ADDTIME Desc";
			List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
			return mesList;
		}
		String hql="from Message where detail like '%"+key+"%' and column='"+column+"' and sname is not null order by LYB_ADDTIME Desc";
		List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
		return mesList;
	}
	
	public int getSearchVerifyCount(String key, String column){
		if(column.trim().equals("")){
			String hql="from Message where detail like '%"+key+"%' and sname is not null order by id Desc";
			int max=mesDao.list(hql).size();
			return max;
		}
		String hql="from Message where detail like '%"+key+"%' and column='"+column+"' and sname is not null order by id Desc";
		int max=mesDao.list(hql).size();
		return max;	
	}

	//BY JIANGYI 
		public Message findFromId(String id){
			String hql="from Message where LYB_ID='"+id+"'";
			Message mes=null;
			try{
				mes=mesDao.list(hql).get(0);
			}catch(Exception e){
				System.out.println("查询出错");
				mes=null;
			}		
			return mes;
		}
		
		public void update(Message mes) {
			// TODO Auto-generated method stub
			mesDao.update(mes);
		}
		public void delete(Message mes) {
			// TODO Auto-generated method stub
			mesDao.delete(mes);
		}
		public void add(Message mes) {
			// TODO Auto-generated method stub
			mesDao.add(mes);
		}
		public int getListNotVerifyCount(){
			String hql="from Message where sname is null order by id Desc";
			int max=mesDao.list(hql).size();
			return max;
		}
		public List<Message> listNotVerify(int offset, int pageSize) {
			String hql="from Message where sname is null order by LYB_ADDTIME Desc";
			List<Message> newsList=mesDao.findByPage(hql, offset, pageSize);
			return newsList;
		}
		public List<Message> searchColumn(String column, int offset,
				int pageSize) {
			if(column.trim().equals("")){
				//String hql="from Message where sname is not null order by id Desc";
				String hql="from Message order by LYB_ADDTIME Desc";
				List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
				return mesList;
			}
			//String hql="from Message where column='"+column+"' and sname is not null order by id Desc";
			String hql="from Message where column='"+column+"' order by LYB_ADDTIME Desc";
			List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
			return mesList;
		}
		public int getColumnCount(String column){
			if(column.trim().equals("")){
				//String hql="from Message where sname is not null order by id Desc";
				String hql="from Message order by id Desc";
				int max=mesDao.list(hql).size();
				return max;
			}
			//String hql="from Message where column='"+column+"' and sname is not null order by id Desc";
			String hql="from Message where column='"+column+"' order by id Desc";
			int max=mesDao.list(hql).size();
			return max;	
		}
		
		public List<Message> searchReply(String key, String column, int offset,
				int pageSize) {
			if(column.trim().equals("")){
				String hql="from Message where detail like '%"+key+"%' and gname is not null order by LYB_ADDTIME Desc";
				List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
				return mesList;
			}
			String hql="from Message where detail like '%"+key+"%' and column='"+column+"' and gname is not null order by LYB_ADDTIME Desc";
			List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
			return mesList;
		}
		
		public int getSearchReplyCount(String key, String column){
			if(column.trim().equals("")){
				String hql="from Message where detail like '%"+key+"%' and gname is not null order by id Desc";
				int max=mesDao.list(hql).size();
				return max;
			}
			String hql="from Message where detail like '%"+key+"%' and column='"+column+"' and gname is not null order by id Desc";
			int max=mesDao.list(hql).size();
			return max;	
		}
		public List<Message> listReply(int offset, int pageSize) {
			String hql="from Message where gname is not null order by LYB_ADDTIME Desc";
			List<Message> mesList=mesDao.findByPage(hql, offset, pageSize);
			return mesList;
		}
		public int getListReplyCount(){
			String hql="from Message where gname is not null order by id Desc";
			int max=mesDao.list(hql).size();
			return max;
		}
		public int getListNotReplyCount(){
			String hql="from Message where gname is null order by id Desc";
			int max=mesDao.list(hql).size();
			return max;
		}
		public List<Message> listNotReply(int offset, int pageSize) {
			String hql="from Message where gname is null order by LYB_ADDTIME Desc";
			List<Message> newsList=mesDao.findByPage(hql, offset, pageSize);
			return newsList;
		}
}
