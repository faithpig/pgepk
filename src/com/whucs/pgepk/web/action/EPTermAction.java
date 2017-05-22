package com.whucs.pgepk.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.hibernate.model.ArticleVisitor;
import com.whucs.pgepk.hibernate.model.EPTerm;
import com.whucs.pgepk.hibernate.model.Picture;
import com.whucs.pgepk.service.impl.ArticleVisitorService;
import com.whucs.pgepk.service.impl.EPTermService;
import com.whucs.pgepk.service.impl.PictureService;
import com.whucs.pgepk.util.Pager;

public class EPTermAction extends ActionSupport {

	private EPTermService epTerSer;
	private PictureService picSer;
	private File file;
	private String fileFileName;
	private String fileContentType;
	String user_check;

	public String getUser_check() {
		return user_check;
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

	public void setEpTerSer(EPTermService epTerSer) {
		this.epTerSer = epTerSer;
	}

	public void setPicSer(PictureService picSer) {
		this.picSer = picSer;
	}

	private Pager pager = new Pager(); // 分页类
	private String method; // 分页相关方法

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public String list() {
		int max = epTerSer.getListVerifyCount();
		List<EPTerm> epTerList = null;
		if (max != 0) {
			paging(max, method);
			epTerList = epTerSer.listVerify(pager.getOffset(),
					pager.getPageSize());
		} else {
			paging(0, null);
		}
		ActionContext.getContext().put("list", epTerList);
		return "list";
	}

	public String detail() {
		String id = getRequest().getParameter("id");
		EPTerm entity = epTerSer.detail(id);
		if (null == entity) {
			return "notFound";
		}
		if (null == entity.getSname() || entity.getSname().length() == 0
				|| entity.getSname().toLowerCase().equals("null")) {
			String adminName = (String) getRequest().getSession().getAttribute(
					"a_username");
			if (null == adminName || adminName.length() == 0) {
				return "notFound";
			}
		}
		ActionContext.getContext().put("entity", entity);
		List<Picture> picList = picSer.get("环保专业知识", entity.getId());
		String[] picSrc = null;
		if (picList != null && picList.size() >= 1) {
			picSrc = new String[picList.size()];
			for (int i = 0; i < picList.size(); i++)
				picSrc[i] = picList.get(i).getPath() + "/"
						+ picList.get(i).getName();
		}
		ActionContext.getContext().put("picSrc", picSrc);
		ActionContext.getContext().put("picList", picList);
		entity.setCount(entity.getCount() + 1);
		epTerSer.update(entity);
		return "detail";
	}

	public String fsearch() throws UnsupportedEncodingException {
		String column = getRequest().getParameter("column");
		if (column == null) {
			column = "";
		}
		String key;
		if (getRequest().getParameter("key") == null) {
			key = "";
		} else {
			key = getRequest().getParameter("key");
		}
		column = new String(column.getBytes("utf-8"), "utf-8");
		key = new String(key.getBytes("utf-8"), "utf-8");
		if (key.trim().equals("") || key == null) {
			key = "";
		}
		int max = epTerSer.getSearchVerifyCount(key, column);
		List<EPTerm> epTerList = null;
		if (max != 0) {
			paging(max, method);
			epTerList = epTerSer.searchVerify(key, column, pager.getOffset(),
					pager.getPageSize());
		} else {
			paging(0, null);
		}
		ActionContext.getContext().put("key", key);
		ActionContext.getContext().put("column", column);
		ActionContext.getContext().put("list", epTerList);
		return "fsearch";
	}

	// 分页的方法
	public void paging(int max, String method) {
		pager.setMaxRecords(max);
		pager.setPageSize(9);
		pager.newPager(pager.getCurrentPage(), method, pager.getMaxRecords(),
				pager.getPageSize());
	}

	// BY JIANGYI
	public String EPTerm_index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (method.equals("verify")) {
			session.setAttribute("verify", "yes");
		} else if (method.equals("notVerify")) {
			session.setAttribute("verify", "no");
		} else if (method.length() < 1) {
			session.setAttribute("verify", "none");
		}
		String verify = (String) session.getAttribute("verify");
		int max = 0;
		if (verify.equals("yes")) {
			max = epTerSer.getListVerifyCount();
		} else if (verify.equals("no")) {
			max = epTerSer.getListNotVerifyCount();
		} else {
			max = epTerSer.getAllCount();
		}
		List<EPTerm> epTermList = null;
		if (max > 0) {
			paging(max, method);
			if (verify.equals("yes")) {
				epTermList = epTerSer.listVerify(pager.getOffset(),
						pager.getPageSize());
			} else if (verify.equals("no")) {
				epTermList = epTerSer.listNotVerify(pager.getOffset(),
						pager.getPageSize());
			} else {
				epTermList = epTerSer.list(pager.getOffset(),
						pager.getPageSize());
			}
		}
		if (epTermList == null || epTermList.size() < 1) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", epTermList);
		return "EPTerm_enter_success";
	}

	public String EPTerm_page() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();

		String verify = (String) session.getAttribute("verify");
		int max = 0;
		if (verify.equals("yes")) {
			max = epTerSer.getListVerifyCount();
		} else if (verify.equals("no")) {
			max = epTerSer.getListNotVerifyCount();
		} else {
			max = epTerSer.getAllCount();
		}
		List<EPTerm> epTermList = null;
		if (max > 0) {
			paging(max, method);
			if (verify.equals("yes")) {
				epTermList = epTerSer.listVerify(pager.getOffset(),
						pager.getPageSize());
			} else if (verify.equals("no")) {
				epTermList = epTerSer.listNotVerify(pager.getOffset(),
						pager.getPageSize());
			} else {
				epTermList = epTerSer.list(pager.getOffset(),
						pager.getPageSize());
			}
		}
		if (epTermList == null || epTermList.size() < 1) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", epTermList);
		ActionContext.getContext().put("pageName", "EPTerm");
		return "enterManage";
	}

	public String EPTerm_delete() {
		String id = getRequest().getParameter("gid");
		EPTerm entity = epTerSer.findFromId(id);
		if (null == entity) {
			List<EPTerm> _entity = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("pageName", "EPTerm");
			ActionContext.getContext().put("entity", _entity);
			return "updateFailure";
		}

		List<ArticleVisitor> artList = null;
		try {
			artList = artVisSer.findAid(id);
		} catch (Exception e) {
		}
		if (artList != null && artList.size() > 0) {
			for (int i = 0; i < artList.size(); i++) {
				artVisSer.delete(artList.get(i));
			}
		}

		String s = entity.getDetail();
		Pattern p = Pattern.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>");
		Matcher m = p.matcher(s);
		List<String> result = new ArrayList<String>();
		while (m.find()) {
			result.add(m.group());
		}
		for (String s1 : result) {
			StringBuffer sb = new StringBuffer();
			char s2[] = s1.toCharArray();
			for (int i = 0; i < s2.length; i++) {
				if (s2[i] == 's' && s2[i + 1] == 'r' && s2[i + 2] == 'c'
						&& s2[i + 3] == '=' && s2[i + 4] == '"') {
					for (i = i + 5; i < s2.length; i++) {
						if (s2[i] != '"') {
							sb.append(s2[i]);
						} else {
							break;
						}
					}
				}
			}
			System.out.println(sb);
			String realPath = getRequest().getSession().getServletContext()
					.getRealPath("/");
			String picPath = realPath.replace("\\pgepk", sb);
			System.out.println("\n图片路径" + picPath);
			File f = new File(picPath);
			if (f.exists()) {
				f.delete();
			}

			try {
				String picName = sb.toString().replace("/pgepk/file/picture/",
						"");
				Picture pic = picSer.findFromName(picName).get(0);
				picSer.delete(pic);
			} catch (Exception e) {

			}
		}

		if (null != entity.getVideo() && !entity.getVideo().equals("0")
				&& entity.getVideo().length() > 1) {
			String videoName = entity.getVideo();
			String realPath = getRequest().getSession().getServletContext()
					.getRealPath("/");
			String videoPath = realPath.replace("\\pgepk", videoName);
			System.out.println("\n视频路径" + videoPath);
			File f = new File(videoPath);
			if (f.exists()) {
				f.delete();
			}
		}
		epTerSer.delete(entity);
		return "deleteSuccess";
	}

	public String searchEPTerm() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		String key = getRequest().getParameter("key");
		String type = getRequest().getParameter("type");
		int max = epTerSer.getSearchCount(key, type);
		pager.setPageSize(10);
		pager.setMaxRecords(max);
		paging(max, method);
		List<EPTerm> entity = epTerSer.search(key, type, pager.getOffset(),
				pager.getPageSize());
		if (entity == null || entity.size() < 1) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", entity);
		ActionContext.getContext().put("pageName", "EPTerm");
		ActionContext.getContext().put("key", key);
		ActionContext.getContext().put("type", type);
		return "searchSuccess";
	}

	public String setVerify() {
		String id = getRequest().getParameter("id");
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		if (null == adminName) {
			String verifyInfo = "当前用户已退出登录，请重新登录！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		System.out.println("当前用户：" + adminName);
		EPTerm epTerm = epTerSer.findFromId(id);
		if (adminName.equals(epTerm.getGname())) {
			String verifyInfo = "审核员自身不能审核自己上传的新闻！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		if (null != epTerm.getSname()) {
			String verifyInfo = epTerm.getSname() + "已审核！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		epTerm.setSname(adminName);
		epTerSer.update(epTerm);
		String verifyInfo = "审核成功！";
		ActionContext.getContext().put("verifyInfo", verifyInfo);
		return "VerifySuccess";
	}

	public String add() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = "没有相关标题！", type = "基础知识", detail = "没有相应说明！";
		try {
			title = getRequest().getParameter("title");
			type = getRequest().getParameter("type");
			detail = getRequest().getParameter("detail");
		} catch (Exception e) {
			detail = "没有相关说明！";
			System.out.println(e.getStackTrace());
		}
		if (null == title || title.length() == 0) {
			title = "没有相应标题！";
		}
		if (null == detail || detail.length() == 0) {
			detail = "没有相关说明！";
		}
		// 文件不能超过1G大小
		if (file != null && file.length() > 1073741824) {
			user_check = "文件过大，请上传小于1GB的视频";
			ActionContext.getContext().put("pageName", "EPTerm");
			List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epTermList);
			ActionContext.getContext().put("type", "全部");
			return "addFailure";
		}
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		String gid = (String) getRequest().getSession().getAttribute("gid");
		if (null == adminName || null == gid || adminName.length() == 0) {
			ActionContext.getContext().put("pageName", "EPTerm");
			List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epTermList);
			ActionContext.getContext().put("type", "全部");
			return "addFailure";
		}

		try {
			Date addTime = new Date();
			System.out.println(addTime);

			/*
			 * 以下增加视频
			 */
			String saveVideoPath = null;
			String videoName = null;
			if (null != file) {
				byte video[] = null;
				long videoSize = file.length();
				int videoPerSize = (int) Math.min(10485760, videoSize);// 以最大10M为单位上传视频
				video = new byte[videoPerSize];
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				String fileExt = fileFileName.substring(
						fileFileName.lastIndexOf(".") + 1).toLowerCase();
				videoName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				saveVideoPath = getRequest().getContextPath() + "/file/video/";
				String realVideoPath = getRequest().getSession()
						.getServletContext().getRealPath("/")
						+ "/file/video/";
				FileOutputStream fos = null;
				fos = new FileOutputStream(realVideoPath + videoName);
				// 获取内存中当前文件输入流
				InputStream in = null;
				in = new FileInputStream(file);
				int num = 0;
				while ((num = in.read(video)) > 0) {
					fos.write(video, 0, num);
					videoSize -= videoPerSize;
					videoPerSize = (int) Math.min(10485760, videoSize);
					video = new byte[videoPerSize];
				}
				in.close();
				fos.close();
			}

			EPTerm entity = new EPTerm();
			entity.setColumn(type);
			entity.setDetail(detail);
			entity.setTitle(title);
			entity.setGname(adminName);
			entity.setAddTime(addTime);
			entity.setGid(gid);
			entity.setCount(0);
			if (null != saveVideoPath && null != videoName) {
				entity.setVideo(saveVideoPath + videoName);
			} else {
				entity.setVideo("0");
			}

			String s = detail;
			Pattern p = Pattern
					.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>");
			Matcher m = p.matcher(s);
			List<String> result = new ArrayList<String>();
			while (m.find()) {
				result.add(m.group());
			}
			entity.setHaspic(result.size());
			epTerSer.add(entity);
			file = null;

			if (result.size() > 0) {
				for (String s1 : result) {
					StringBuffer sb = new StringBuffer();
					char s2[] = s1.toCharArray();
					for (int i = 0; i < s2.length; i++) {
						if (s2[i] == 's' && s2[i + 1] == 'r'
								&& s2[i + 2] == 'c' && s2[i + 3] == '='
								&& s2[i + 4] == '"') {
							for (i = i + 5; i < s2.length; i++) {
								if (s2[i] != '"') {
									sb.append(s2[i]);
								} else {
									break;
								}
							}
						}
					}
					System.out.println(sb);
					String realPicPath = getRequest().getSession()
							.getServletContext().getRealPath("/");
					System.out.println(realPicPath);
					String picPath = realPicPath.replace("\\pgepk", sb);
					System.out.println("\n图片路径" + picPath);
					File f = new File(picPath);
					if (!f.exists()) {
						continue;
					}

					String picName = sb.toString().replace(
							"/pgepk/file/picture/", "");
					Picture pic = new Picture();
					pic.setWzid(entity.getId());
					pic.setType("环保专业知识");
					pic.setName(picName);
					picSer.addPic(pic);
				}
			}

		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
			user_check = "请检查输入是否为空";
			ActionContext.getContext().put("pageName", "EPTerm");
			List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epTermList);
			ActionContext.getContext().put("type", "全部");
			return "addFailure";
		}
		ActionContext.getContext().put("pageName", "EPTerm");
		List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
				pager.getPageSize());
		ActionContext.getContext().put("entity", epTermList);
		ActionContext.getContext().put("type", "全部");
		return "addSuccess";

	}

	public String update() {
		String id = getRequest().getParameter("id");
		EPTerm entity = epTerSer.findFromId(id);
		if (null == entity) {
			return "updateFailure";
		}
		ActionContext.getContext().put("entity", entity);
		ActionContext.getContext().put("type", entity.getColumn());
		return "updateIndex";
	}

	public String updateDo() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = getRequest().getParameter("id");
		String title = "没有相关标题！", type = "基础知识", detail = "没有相应说明！";
		String deleteVideo = "no";
		try {
			title = getRequest().getParameter("title");
			type = getRequest().getParameter("type");
			detail = getRequest().getParameter("detail");
			deleteVideo = getRequest().getParameter("deleteVideo");
		} catch (Exception e) {
			detail = "没有相关说明！";
		}
		if (null == title || title.length() == 0) {
			title = "没有相应标题！";
		}
		if (null == detail || detail.length() == 0) {
			detail = "没有相关说明！";
		}
		if (file != null && file.length() > 1073741824) {
			user_check = "文件过大，请上传小于1GB的视频";
			ActionContext.getContext().put("pageName", "EPTerm");
			List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epTermList);
			return "updateFailure";
		}
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		String gid = (String) getRequest().getSession().getAttribute("gid");
		if (null == adminName || null == gid || adminName.length() == 0) {
			ActionContext.getContext().put("pageName", "EPTerm");
			List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epTermList);
			return "updateFailure";
		}
		try {
			Date addTime = new Date();
			System.out.println(addTime);

			String saveVideoPath = null;
			String videoName = null;
			if (null != file && null == deleteVideo) {
				byte video[] = null;
				long videoSize = file.length();
				int videoPerSize = (int) Math.min(10485760, videoSize);// 以最大10M为单位上传视频
				video = new byte[videoPerSize];
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");
				String fileExt = fileFileName.substring(
						fileFileName.lastIndexOf(".") + 1).toLowerCase();
				videoName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				saveVideoPath = getRequest().getContextPath() + "/file/video/";
				String realVideoPath = getRequest().getSession()
						.getServletContext().getRealPath("/")
						+ "/file/video/";
				FileOutputStream fos = null;
				fos = new FileOutputStream(realVideoPath + videoName);
				// 获取内存中当前文件输入流
				InputStream in = null;
				in = new FileInputStream(file);
				int num = 0;
				while ((num = in.read(video)) > 0) {
					fos.write(video, 0, num);
					videoSize -= videoPerSize;
					videoPerSize = (int) Math.min(10485760, videoSize);
					video = new byte[videoPerSize];
				}
				in.close();
				fos.close();
			}

			EPTerm entity = epTerSer.findFromId(id);
			entity.setColumn(type);
			entity.setDetail(detail);
			entity.setTitle(title);
			entity.setGname(adminName);
			entity.setAddTime(addTime);
			entity.setGid(gid);
			entity.setSname(null);
			if (null != deleteVideo) {
				entity.setVideo("0");
			} else if (null != saveVideoPath && null != videoName) {
				entity.setVideo(saveVideoPath + videoName);
			}

			String s = detail;
			Pattern p = Pattern
					.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>");
			Matcher m = p.matcher(s);
			List<String> result = new ArrayList<String>();
			while (m.find()) {
				result.add(m.group());
			}
			entity.setHaspic(result.size());

			epTerSer.update(entity);
			if (result.size() > 0) {
				for (String s1 : result) {
					StringBuffer sb = new StringBuffer();
					char s2[] = s1.toCharArray();
					for (int i = 0; i < s2.length; i++) {
						if (s2[i] == 's' && s2[i + 1] == 'r'
								&& s2[i + 2] == 'c' && s2[i + 3] == '='
								&& s2[i + 4] == '"') {
							for (i = i + 5; i < s2.length; i++) {
								if (s2[i] != '"') {
									sb.append(s2[i]);
								} else {
									break;
								}
							}
						}
					}
					System.out.println(sb);
					String realPicPath = getRequest().getSession()
							.getServletContext().getRealPath("/");
					System.out.println(realPicPath);
					String picPath = realPicPath.replace("\\pgepk", sb);
					System.out.println("\n图片路径" + picPath);
					File f = new File(picPath);
					if (!f.exists()) {
						continue;
					}

					String picName = sb.toString().replace(
							"/pgepk/file/picture/", "");
					Picture pic = new Picture();
					pic.setWzid(entity.getId());
					pic.setType("环保专业知识");
					pic.setName(picName);
					picSer.addPic(pic);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			ActionContext.getContext().put("pageName", "EPTerm");
			List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epTermList);
			return "updateFailure";
		}
		ActionContext.getContext().put("pageName", "EPTerm");
		List<EPTerm> epTermList = epTerSer.list(pager.getOffset(),
				pager.getPageSize());
		ActionContext.getContext().put("entity", epTermList);
		return "updateSuccess";
	}

	private ArticleVisitorService artVisSer;

	public void setArtVisSer(ArticleVisitorService artVisSer) {
		this.artVisSer = artVisSer;
	}
}
