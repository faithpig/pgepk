package com.whucs.pgepk.web.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.service.impl.EPNewsService;
import com.whucs.pgepk.service.impl.EPPolicyService;
import com.whucs.pgepk.service.impl.EPTermService;
import com.whucs.pgepk.service.impl.PictureService;
import com.whucs.pgepk.hibernate.model.EPPolicy;
import com.whucs.pgepk.hibernate.model.EPTerm;
import com.whucs.pgepk.hibernate.model.EPNews;
import com.whucs.pgepk.hibernate.model.Picture;


public class HomeAction extends ActionSupport{


	private EPNewsService epNewSer;
	private EPPolicyService epPolSer;
	private EPTermService epTerSer;
	private PictureService picSer;
	
	public void setEpNewSer(EPNewsService epNewSer) {
		this.epNewSer = epNewSer;
	}
	public void setEpPolSer(EPPolicyService epPolSer) {
		this.epPolSer = epPolSer;
	}
	public void setEpTerSer(EPTermService epTerSer) {
		this.epTerSer = epTerSer;
	}
	public void setPicSer(PictureService picSer) {
		this.picSer = picSer;
	}
	
	//获取Request
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public String home(){
		List<EPNews> epNewList=epNewSer.listVerify(0, 3);
		List<EPPolicy> epPolList=epPolSer.listVerify(0, 12);
		List<EPTerm> epTerList=epTerSer.listVerify(0,12);
		ActionContext.getContext().put("epNewList", epNewList);
		ActionContext.getContext().put("epPolList", epPolList);
		ActionContext.getContext().put("epTerList", epTerList);
		List<Picture> picList=picSer.getNewsList();
		Iterator<Picture> itr = picList.iterator();
		while (itr.hasNext()) {
		    Picture pic = itr.next();
		    String id = pic.getWzid();
		    EPNews entity = epNewSer.detail(id);
		    if(entity==null){
		    	itr.remove();
		    }
		    if(null != entity && null == entity.getSname()){
		    	itr.remove();
		    }
		}
		if(picList.size()>=5){
			String[][] picSrcStr=new String[5][3];									//3张图片在主页轮换，并且配有相应文章的title
			picSrcStr[0][0]=epNewSer.detail(picList.get(0).getWzid()).getTitle();
			picSrcStr[1][0]=epNewSer.detail(picList.get(1).getWzid()).getTitle();
			picSrcStr[2][0]=epNewSer.detail(picList.get(2).getWzid()).getTitle();
			picSrcStr[3][0]=epNewSer.detail(picList.get(3).getWzid()).getTitle();
			picSrcStr[4][0]=epNewSer.detail(picList.get(4).getWzid()).getTitle();
			picSrcStr[0][1]=picList.get(0).getName();
			picSrcStr[1][1]=picList.get(1).getName();
			picSrcStr[2][1]=picList.get(2).getName();
			picSrcStr[3][1]=picList.get(3).getName();
			picSrcStr[4][1]=picList.get(4).getName();
			picSrcStr[0][2]=((String)picList.get(0).getWzid()).toString();
			picSrcStr[1][2]=((String)picList.get(1).getWzid()).toString();
			picSrcStr[2][2]=((String)picList.get(2).getWzid()).toString();
			picSrcStr[3][2]=((String)picList.get(3).getWzid()).toString();
			picSrcStr[4][2]=((String)picList.get(4).getWzid()).toString();
			ActionContext.getContext().put("picSrcStr", picSrcStr);
		}
		else{
			String[][] picSrcStr=null;
			ActionContext.getContext().put("picSrcStr", picSrcStr);
		}
		return "home";
	}
}
