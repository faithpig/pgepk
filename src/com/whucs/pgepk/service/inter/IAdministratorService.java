package com.whucs.pgepk.service.inter;

import java.util.List;

import com.whucs.pgepk.hibernate.model.Administrator;
import com.whucs.pgepk.util.Pager;

public interface IAdministratorService {
	public Administrator findFromName(String a_username);
	public void update(Administrator adm);
	public void add(Administrator adm);
	public int getRows();
	public Pager pageInitial(Pager pager,String method);
	public List<Administrator> searchAdmin(String key,String column,int offset,int pageSize);				//搜索已审核环保政策法规

}
