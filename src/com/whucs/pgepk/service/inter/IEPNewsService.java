package com.whucs.pgepk.service.inter;
import java.util.List;

import com.whucs.pgepk.hibernate.model.EPNews;

public interface IEPNewsService {
public List<EPNews> list(int offset,int pageSize);												//取出所有环保新闻
public EPNews detail(String id);																	//ͨ通过id获取环保新闻详细内容
public List<EPNews> search(String key,String column,int offset,int pageSize);					//搜索所有环保新闻
public List<EPNews> listVerify(int offset,int pageSize);										//取出已审核的环保新闻
public List<EPNews> searchVerify(String key,String column,int offset,int pageSize);				//搜索已审核的环保新闻
}
