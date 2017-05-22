package com.whucs.pgepk.service.inter;

import java.util.List;

import com.whucs.pgepk.hibernate.model.EPPolicy;

public interface IEPPolicyService {
public List<EPPolicy> list(int offset,int pageSize);												//取出所有环保政策法规
public EPPolicy detail(String id);																	//通过id获取法规详情
public List<EPPolicy> search(String key,String column,int offset,int pageSize);						//搜索所有环保政策法规
public List<EPPolicy> listVerify(int offset,int pageSize);											//取出已审核环保政策法规
public List<EPPolicy> searchVerify(String key,String column,int offset,int pageSize);				//搜索已审核环保政策法规
}
