package com.whucs.pgepk.service.impl;

import com.whucs.pgepk.dao.impl.ContactDao;
import com.whucs.pgepk.hibernate.model.Contact;
import com.whucs.pgepk.service.inter.IContactService;

public class ContactService implements IContactService{

	private ContactDao conDao;
	
	public void setConDao(ContactDao conDao) {
		this.conDao = conDao;
	}

	@Override
	public Contact detail() {
		String hql="from Contact order by id Desc";
		Contact entity = new Contact();
		try{
			entity = conDao.list(hql).get(0);
		}
		catch (IndexOutOfBoundsException e) {
		}
		return entity;
	}
	
	public void update(Contact entity){
		conDao.update(entity);
	}

}
