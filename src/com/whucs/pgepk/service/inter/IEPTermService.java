package com.whucs.pgepk.service.inter;

import java.util.List;
import com.whucs.pgepk.hibernate.model.EPTerm;

public interface IEPTermService {
public List<EPTerm> list(int offset,int pageSize);												//取出所有环保专业知识
public EPTerm detail(String id);																	//按id取出专业知识详细信息
public List<EPTerm> search(String key,String column,int offset,int pageSize);					//搜索所有环保专业知识
public List<EPTerm> listVerify(int offset,int pageSize);										//取出已审核环保专业知识
public List<EPTerm> searchVerify(String key,String column,int offset,int pageSize);				//搜索已审核环保专业知识
}
