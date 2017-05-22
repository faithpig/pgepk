package com.whucs.pgepk.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.hibernate.model.Contact;
import com.whucs.pgepk.service.impl.ContactService;

public class ContactAction extends ActionSupport{
	
	private ContactService conSer;

	public void setConSer(ContactService conSer) {
		this.conSer = conSer;
	}
	
	//获取Request
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	//导向联系我们页面
	public String detail(){
		Contact con=conSer.detail();
		ActionContext.getContext().put("entity",con);
		return "detail";
	}
	
	//BY JIANGYI 
	public String Contact_index() {
		Contact con=conSer.detail();
		ActionContext.getContext().put("entity",con);
		return "Contact_enter_success";
	}
	
	public String change(){
		try{
		String address = getRequest().getParameter("address");
		String postcode=getRequest().getParameter("postcode");
		String linkman=getRequest().getParameter("linkman");
		String phone=getRequest().getParameter("phone");
		String e_mail=getRequest().getParameter("e_mail");
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		String gid = (String) getRequest().getSession().getAttribute("gid");	
		Contact entity=conSer.detail();
		entity.setAddress(address);
		entity.setE_mail(e_mail);
		entity.setGid(gid);
		entity.setGname(adminName);
		entity.setLinkman(linkman);
		entity.setPhone(phone);
		entity.setPostcode(postcode);
		conSer.update(entity);
		}catch(Exception e1){
			String js = "<script type=\"text/javascript\">window.alert('提交留言成功！');</script>";
			ActionContext.getContext().put("changeFailure", js);
			return "changeFailure";
		}		
		ActionContext.getContext().put("pageName", "Contact");
		Contact con=conSer.detail();
		ActionContext.getContext().put("entity",con);
		return "changeSuccess";
	}
	
}
