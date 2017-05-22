package com.whucs.pgepk.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.service.impl.LinkService;
import com.whucs.pgepk.hibernate.model.Link;

public class LinkAction extends ActionSupport {

	private LinkService linSer;
	private File file;
	private String fileFileName;
	private String fileContentType;

	public void setLinSer(LinkService linSer) {
		this.linSer = linSer;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileName() {
		return this.fileFileName;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileContentType() {
		return this.fileContentType;
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	public String list() {
		List<Link> linList = linSer.list();
		ActionContext.getContext().put("list", linList);
		ActionContext.getContext().put("size", linList.size());
		String[][] picSrc = null;
		if (linList != null && linList.size() >= 1) {
			picSrc = new String[linList.size()][2];
			for (int i = 0; i < linList.size(); i++) {
				// picSrc[i][0]=linList.get(i).getPath()+"/"+linList.get(i).getName();
				picSrc[i][0] = getRequest().getContextPath()
						+ "/file/linkPicture/" + linList.get(i).getName();
				picSrc[i][1] = linList.get(i).getLink();
			}
			ActionContext.getContext().put("picSrc", picSrc);
		}
		return "list";
	}

	public String Link_index() {
		List<Link> linList = linSer.list();
		ActionContext.getContext().put("entity", linList);
		return "Link_enter_success";
	}

	public String Link_delete() {
		String id = getRequest().getParameter("gid");
		Link entity = linSer.findFromId(id);
		if (null == entity) {
			ActionContext.getContext().put("pageName", "Link");
			ActionContext.getContext().put("entity", entity);
			return "updateFailure";
		}
		String picName=entity.getName();
		File address=new File(getRequest().getSession().getServletContext().getRealPath("/")
		+ "/file/linkPicture/"+picName);
		if(address.exists()){
			address.delete();
		}
		linSer.delete(entity);
		List<Link> linList = linSer.list();
		ActionContext.getContext().put("entity", linList);
		return "deleteSuccess";
	}

	public String add() {
		try {
			String title = getRequest().getParameter("title");
			String link = getRequest().getParameter("link");
			if (link.charAt(0) != 'h' && link.charAt(0) != 'H') {
				link = "http://" + link;
			}
			String adminName = (String) getRequest().getSession().getAttribute(
					"a_username");
			String gid = (String) getRequest().getSession().getAttribute("gid");
			
			if(null == adminName || null == gid || adminName.length() == 0){
				List<Link> linList = linSer.list();
				ActionContext.getContext().put("entity", linList);
				ActionContext.getContext().put("pageName", "Link");
				return "addFailure";
			}
			
			Link entity = new Link();
			entity.setGid(gid);
			entity.setGname(adminName);
			entity.setLink(link);
			entity.setTitle(title);

			// 写入数据库的byte图片
			FileInputStream fin = null;
			fin = new FileInputStream(file);
			byte pic[] = null;
			pic = new byte[(int) fin.available()];
			fin.read(pic);
			fin.close();

			// 存至文件的图片
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String fileExt = fileFileName.substring(
					fileFileName.lastIndexOf(".") + 1).toLowerCase();
			String newImgName = df.format(new Date()) + "_"
					+ new Random().nextInt(1000) + "." + fileExt;
			String savePath = getRequest().getContextPath()
					+ "/file/linkPicture/";
			String realPath = getRequest().getSession().getServletContext()
					.getRealPath("/")
					+ "/file/linkPicture/";
			// File f=new File(savePath + newImgName);
			FileOutputStream fos = null;
			fos = new FileOutputStream(realPath + newImgName);
			// 获取内存中当前文件输入流
			InputStream in = null;
			in = new FileInputStream(file);
			int num = 0;
			while ((num = in.read(pic)) > 0) {
				fos.write(pic, 0, num);
			}
			in.close();
			fos.close();

			entity.setPath(savePath);
			entity.setName(newImgName);
			entity.setPic(pic);

			linSer.add(entity);
			List<Link> linList = linSer.list();
			ActionContext.getContext().put("entity", linList);
			ActionContext.getContext().put("pageName", "Link");
			return "addSuccess";
		} catch (Exception e) {
			List<Link> linList = linSer.list();
			ActionContext.getContext().put("entity", linList);
			ActionContext.getContext().put("pageName", "Link");
			return "addFailure";
		}
	}

	public String updateDo() {
		try{
		String id = getRequest().getParameter("id");
		Link entity = linSer.findFromId(id);
		String title = getRequest().getParameter("title");
		String link = getRequest().getParameter("link");
		if (link.charAt(0) != 'h' && link.charAt(0) != 'H') {
			link = "http://" + link;
		}
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		String gid = (String) getRequest().getSession().getAttribute("gid");
		entity.setGid(gid);
		entity.setGname(adminName);
		entity.setLink(link);
		entity.setTitle(title);

		// 写入数据库的byte图片
		FileInputStream fin = null;
		fin = new FileInputStream(file);
		byte pic[] = null;
		pic = new byte[(int) fin.available()];
		fin.read(pic);
		fin.close();

		// 存至文件的图片
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String fileExt = fileFileName.substring(
				fileFileName.lastIndexOf(".") + 1).toLowerCase();
		String newImgName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		String savePath = getRequest().getContextPath() + "/file/linkPicture/";
		String realPath = getRequest().getSession().getServletContext()
				.getRealPath("/")
				+ "/file/linkPicture/";
		// File f=new File(savePath + newImgName);
		FileOutputStream fos = null;
		fos = new FileOutputStream(realPath + newImgName);
		// 获取内存中当前文件输入流
		InputStream in = null;
		in = new FileInputStream(file);
		int num = 0;
		while ((num = in.read(pic)) > 0) {
			fos.write(pic, 0, num);
		}
		in.close();
		fos.close();

		entity.setPath(savePath);
		entity.setName(newImgName);
		entity.setPic(pic);
		linSer.update(entity);
		List<Link> linList = linSer.list();
		ActionContext.getContext().put("entity", linList);
		ActionContext.getContext().put("pageName", "Link");
		return "updateSuccess";
		}catch (Exception e) {
			List<Link> linList = linSer.list();
			ActionContext.getContext().put("entity", linList);
			ActionContext.getContext().put("pageName", "Link");
			return "addFailure";
		}
	}

	public String update() {
		String id = getRequest().getParameter("id");
		Link entity = linSer.findFromId(id);
		if (null == entity) {
			return "updateFailure";
		}
		ActionContext.getContext().put("pageName", "Link");
		ActionContext.getContext().put("entity", entity);
		return "updateIndex";
	}
}
