package com.whucs.pgepk.dao.inter;

import java.util.List;

public interface IBaseDao<T>{
public String add(T entity);			//添加实体
public void delete(T entity);		//删除实体
public void update(T entity);		//更新实体
public List<T> list(String hql);	//获取实体列表
public T find(String id);				//根据id获取实体
}
