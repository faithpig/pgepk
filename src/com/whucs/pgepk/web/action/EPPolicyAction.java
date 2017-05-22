package com.whucs.pgepk.web.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.hibernate.model.ArticleVisitor;
import com.whucs.pgepk.hibernate.model.EPPolicy;
import com.whucs.pgepk.hibernate.model.Picture;
import com.whucs.pgepk.service.impl.ArticleVisitorService;
import com.whucs.pgepk.service.impl.EPPolicyService;
import com.whucs.pgepk.service.impl.PictureService;
import com.whucs.pgepk.util.Pager;

public class EPPolicyAction extends ActionSupport {


	private EPPolicyService epPolSer;
	private PictureService picSer;

	public void setEpPolSer(EPPolicyService epPolSer) {
		this.epPolSer = epPolSer;
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
		int max = epPolSer.getListVerifyCount();
		List<EPPolicy> epPolList = null;
		if (max != 0) {
			paging(max, method);
			epPolList = epPolSer.listVerify(pager.getOffset(),
					pager.getPageSize());
		} else {
			paging(0, null);
		}
		ActionContext.getContext().put("list", epPolList);
		return "list";
	}

	public String detail() {
		String id = getRequest().getParameter("id");
		EPPolicy entity = epPolSer.detail(id);
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
		List<Picture> picList = picSer.get("环保政策法规", entity.getId());
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
		epPolSer.update(entity);
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
		int max = epPolSer.getSearchVerifyCount(key, column);
		List<EPPolicy> epPolList = null;
		if (max != 0) {
			paging(max, method);
			epPolList = epPolSer.searchVerify(key, column, pager.getOffset(),
					pager.getPageSize());
		} else {
			paging(0, null);
		}
		ActionContext.getContext().put("key", key);
		ActionContext.getContext().put("column", column);
		ActionContext.getContext().put("list", epPolList);
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
	public String EPPolicy_index() {
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
			max = epPolSer.getListVerifyCount();
		} else if (verify.equals("no")) {
			max = epPolSer.getListNotVerifyCount();
		} else {
			max = epPolSer.getAllCount();
		}
		List<EPPolicy> epPolList = null;
		if (max > 0) {
			paging(max, method);
			if (verify.equals("yes")) {
				epPolList = epPolSer.listVerify(pager.getOffset(),
						pager.getPageSize());
			} else if (verify.equals("no")) {
				epPolList = epPolSer.listNotVerify(pager.getOffset(),
						pager.getPageSize());
			} else {
				epPolList = epPolSer.list(pager.getOffset(),
						pager.getPageSize());
			}
		}
		if (epPolList == null || epPolList.size() < 1) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", epPolList);
		return "EPPolicy_enter_success";
	}

	public String EPPolicy_page() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			max = epPolSer.getListVerifyCount();
		} else if (verify.equals("no")) {
			max = epPolSer.getListNotVerifyCount();
		} else {
			max = epPolSer.getAllCount();
		}
		List<EPPolicy> epPolList = null;
		if (max > 0) {
			paging(max, method);
			if (verify.equals("yes")) {
				epPolList = epPolSer.listVerify(pager.getOffset(),
						pager.getPageSize());
			} else if (verify.equals("no")) {
				epPolList = epPolSer.listNotVerify(pager.getOffset(),
						pager.getPageSize());
			} else {
				epPolList = epPolSer.list(pager.getOffset(),
						pager.getPageSize());
			}
		}
		if (epPolList == null || epPolList.size() < 1) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", epPolList);
		ActionContext.getContext().put("pageName", "EPPolicy");
		return "enterManage";
	}

	public String EPPolicy_delete() {
		String id = getRequest().getParameter("gid");
		EPPolicy entity = epPolSer.findFromId(id);
		if (null == entity) {
			List<EPPolicy> _entity = epPolSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("pageName", "EPPolicy");
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
		epPolSer.delete(entity);
		return "deleteSuccess";
	}

	public String searchEPPolicy() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String key = getRequest().getParameter("key");
		String type = getRequest().getParameter("type");
		System.out.println("类型是" + type);
		int max = epPolSer.getSearchCount(key, type);
		pager.setPageSize(9);
		pager.newPager(pager.getCurrentPage(), method, max, pager.getPageSize());
		List<EPPolicy> entity = epPolSer.search(key, type, pager.getOffset(),
				pager.getPageSize());
		if (entity == null || entity.size() < 1) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", entity);
		ActionContext.getContext().put("pageName", "EPPolicy");
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
		EPPolicy epPol = epPolSer.findFromId(id);
		if (adminName.equals(epPol.getGname())) {
			String verifyInfo = "审核员自身不能审核自己上传的新闻！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		if (null != epPol.getSname()) {
			String verifyInfo = epPol.getSname() + "已审核！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		epPol.setSname(adminName);
		epPolSer.update(epPol);
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
		String title = "没有相应标题！";
		String type = "导则标准";
		String detail = "没有相关说明！";
		try {
			title = getRequest().getParameter("title");
			type = getRequest().getParameter("type");
			detail = getRequest().getParameter("detail");
		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
			detail = "没有相关说明！";
		}
		if (null == title || title.length() == 0) {
			title = "没有相应标题！";
		}
		if (null == detail || detail.length() == 0) {
			detail = "没有相关说明！";
		}
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		String gid = (String) getRequest().getSession().getAttribute("gid");
		if (null == adminName || null == gid || adminName.length() == 0) {
			ActionContext.getContext().put("pageName", "EPPolicy");
			List<EPPolicy> epPolList = epPolSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("type", "全部");
			ActionContext.getContext().put("entity", epPolList);
			return "addFailure";
		}

		try {
			Date addTime = new Date();
			System.out.println(addTime);
			EPPolicy entity = new EPPolicy();
			entity.setColumn(type);
			entity.setDetail(detail);
			entity.setTitle(title);
			entity.setGname(adminName);
			entity.setAddTime(addTime);
			entity.setGid(gid);
			entity.setCount(0);

			String s = detail;
			Pattern p = Pattern
					.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>");
			Matcher m = p.matcher(s);
			List<String> result = new ArrayList<String>();
			while (m.find()) {
				result.add(m.group());
			}
			entity.setHaspic(result.size());
			epPolSer.add(entity);
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
					String realPath = getRequest().getSession()
							.getServletContext().getRealPath("/");
					System.out.println(realPath);
					String picPath = realPath.replace("\\pgepk", sb);
					System.out.println("\n图片路径" + picPath);
					File f = new File(picPath);

					if (!f.exists()) {
						continue;
					}

					String picName = sb.toString().replace(
							"/pgepk/file/picture/", "");
					Picture pic = new Picture();
					pic.setWzid(entity.getId());
					pic.setType("环保政策法规");
					pic.setName(picName);
					picSer.addPic(pic);
				}
			}

		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
			ActionContext.getContext().put("pageName", "EPPolicy");
			List<EPPolicy> epPolList = epPolSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epPolList);
			return "addFailure";
		}
		ActionContext.getContext().put("pageName", "EPPolicy");
		List<EPPolicy> epPolList = epPolSer.list(pager.getOffset(),
				pager.getPageSize());
		ActionContext.getContext().put("entity", epPolList);
		return "addSuccess";

	}

	public String update() {
		String id = getRequest().getParameter("id");
		EPPolicy entity = epPolSer.findFromId(id);
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
		String title = "没有相应标题！";
		String type = "导则标准";
		String detail = "没有相关说明！";
		try {
			title = getRequest().getParameter("title");
			type = getRequest().getParameter("type");
			detail = getRequest().getParameter("detail");
		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
			detail = "没有相关说明！";
		}
		if (null == title || title.length() == 0) {
			title = "没有相应标题！";
		}
		if (null == detail || detail.length() == 0) {
			detail = "没有相关说明！";
		}
		String adminName = (String) getRequest().getSession().getAttribute(
				"a_username");
		String gid = (String) getRequest().getSession().getAttribute("gid");
		if (null == adminName || null == gid || adminName.length() == 0) {
			ActionContext.getContext().put("pageName", "EPPolicy");
			List<EPPolicy> epPolList = epPolSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epPolList);
			ActionContext.getContext().put("type", "全部");
			return "updateFailure";
		}
		try {
			Date addTime = new Date();
			System.out.println(addTime);
			EPPolicy entity = epPolSer.findFromId(id);
			entity.setColumn(type);
			entity.setDetail(detail);
			entity.setTitle(title);
			entity.setGname(adminName);
			entity.setAddTime(addTime);
			entity.setGid(gid);
			entity.setSname(null);

			String s = detail;
			Pattern p = Pattern
					.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>");
			Matcher m = p.matcher(s);
			List<String> result = new ArrayList<String>();
			while (m.find()) {
				result.add(m.group());
			}
			entity.setHaspic(result.size());

			epPolSer.update(entity);

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
					String realPath = getRequest().getSession()
							.getServletContext().getRealPath("/");
					System.out.println(realPath);
					String picPath = realPath.replace("\\pgepk", sb);
					System.out.println("\n图片路径" + picPath);
					File f = new File(picPath);

					if (!f.exists()) {
						continue;
					}

					String picName = sb.toString().replace(
							"/pgepk/file/picture/", "");
					Picture pic = new Picture();
					pic.setWzid(entity.getId());
					pic.setType("环保政策法规");
					pic.setName(picName);
					picSer.addPic(pic);
				}
			}

		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
			ActionContext.getContext().put("pageName", "EPPolicy");
			List<EPPolicy> epPolList = epPolSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", epPolList);
			return "updateFailure";
		}
		ActionContext.getContext().put("pageName", "EPPolicy");
		List<EPPolicy> epPolList = null;
		epPolList = epPolSer.list(pager.getOffset(), pager.getPageSize());
		if (epPolList == null || epPolList.size() == 0) {
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", epPolList);
		return "updateSuccess";
	}

	private ArticleVisitorService artVisSer;

	public void setArtVisSer(ArticleVisitorService artVisSer) {
		this.artVisSer = artVisSer;
	}

}
