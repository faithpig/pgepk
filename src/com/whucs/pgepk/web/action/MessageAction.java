package com.whucs.pgepk.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.hibernate.model.Message;
import com.whucs.pgepk.service.impl.MessageService;
import com.whucs.pgepk.util.Pager;

public class MessageAction extends ActionSupport {

	private MessageService mesSer;
	private Pager pager = new Pager(); // 分页类
	private String method; // 分页相关方法

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public void setMesSer(MessageService mesSer) {
		this.mesSer = mesSer;
	}

	// 获取request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public String list() {
		List<Message> mesList = null;
		if (mesSer.getListVerifyCount() != 0) {
			int max = mesSer.getListVerifyCount();
			paging(max, method);
			mesList = mesSer.listVerify(pager.getOffset(), pager.getPageSize());
		} else {
			paging(0, null);
		}
		ActionContext.getContext().put("list", mesList);
		return "list";
	}

	public String fsearch() throws UnsupportedEncodingException {
		List<Message> mesList = null;
		String column = getRequest().getParameter("column");
		if (column == null) {
			column = "";
		} else {
			column = new String(column.getBytes("utf-8"), "utf-8");
		}
		if (mesSer.getSearchVerifyCount("", column) != 0) {
			int max = mesSer.getSearchVerifyCount("", column);
			paging(max, method);
			mesList = mesSer.searchVerify("", column, pager.getOffset(),
					pager.getPageSize());
		} else {
			paging(0, null);
		}
		ActionContext.getContext().put("column", column);
		ActionContext.getContext().put("list", mesList);
		return "fsearch";
	}

	public String add() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String detail = " ";
		String column = " ";
		try {
			detail = getRequest().getParameter("detail");
			column = getRequest().getParameter("columnName");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		String ip = getIpAddr(ServletActionContext.getRequest());
		mesSer.add(ip, detail, column);
		String js = "<script type=\"text/javascript\">window.alert('提交留言成功！');</script>";
		ActionContext.getContext().put("addsuccess", js);
		return list();
	}

	// 分页的方法
	public void paging(int max, String method) {
		pager.setMaxRecords(max);
		pager.setPageSize(9);
		pager.newPager(pager.getCurrentPage(), method, pager.getMaxRecords(),
				pager.getPageSize());
	}

	// 获得当前发送请求的ip地址
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// BY JIANGYI
	public String Message_index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (method.equals("verify")) {
			session.setAttribute("verify", "yes");
		} else if (method.equals("notVerify")) {
			session.setAttribute("verify", "no");
		} else if (method.equals("reply")) {
			session.setAttribute("verify", "reply");
		} else if (method.equals("notReply")) {
			session.setAttribute("verify", "notReply");
		} else if (method.length() < 1) {
			session.setAttribute("verify", "none");
		}
		String verify = (String) session.getAttribute("verify");
		System.out.println("\n" + verify);
		int max = 0;
		if (verify.equals("yes")) {
			max = mesSer.getListVerifyCount();

		} else if (verify.equals("no")) {
			max = mesSer.getListNotVerifyCount();

		} else if (verify.equals("reply")) {
			max = mesSer.getListReplyCount();
		} else if (verify.equals("notReply")) {
			max = mesSer.getListNotReplyCount();
		} else {
			max = mesSer.getAllCount();
		}
		System.out.println("\n" + max);
		List<Message> mesList = null;
		if (max > 0) {
			paging(max, method);
			if (verify.equals("yes")) {
				mesList = mesSer.listVerify(pager.getOffset(),
						pager.getPageSize());

			} else if (verify.equals("no")) {
				mesList = mesSer.listNotVerify(pager.getOffset(),
						pager.getPageSize());

			} else if (verify.equals("reply")) {
				mesList = mesSer.listReply(pager.getOffset(),
						pager.getPageSize());
			} else if (verify.equals("notReply")) {
				mesList = mesSer.listNotReply(pager.getOffset(),
						pager.getPageSize());
			} else {
				mesList = mesSer.list(pager.getOffset(), pager.getPageSize());
			}
		}
		if(null == mesList || mesList.size() == 0){
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", mesList);
		return "Message_enter_success";
	}

	public String Message_page() {
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
			max = mesSer.getListVerifyCount();

		} else if (verify.equals("no")) {
			max = mesSer.getListNotVerifyCount();

		} else if (verify.equals("reply")) {
			max = mesSer.getListReplyCount();
		} else if (verify.equals("notReply")) {
			max = mesSer.getListNotReplyCount();
		} else {
			max = mesSer.getAllCount();
		}
		List<Message> mesList = null;
		if (max > 0) {
			paging(max, method);
			if (verify.equals("yes")) {
				mesList = mesSer.listVerify(pager.getOffset(),
						pager.getPageSize());

			} else if (verify.equals("no")) {
				mesList = mesSer.listNotVerify(pager.getOffset(),
						pager.getPageSize());

			} else if (verify.equals("reply")) {
				mesList = mesSer.listReply(pager.getOffset(),
						pager.getPageSize());
			} else if (verify.equals("notReply")) {
				mesList = mesSer.listNotReply(pager.getOffset(),
						pager.getPageSize());
			} else {
				mesList = mesSer.list(pager.getOffset(), pager.getPageSize());
			}
		}
		if(null == mesList || mesList.size() == 0){
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", mesList);
		ActionContext.getContext().put("pageName", "Message");
		return "enterManage";
	}

	public String Message_delete() {
		String id = getRequest().getParameter("gid");
		Message entity = mesSer.findFromId(id);
		if (null == entity) {
			List<Message> _entity = mesSer.list(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("pageName", "Message");
			ActionContext.getContext().put("entity", _entity);
			return "updateFailure";
		}
		mesSer.delete(entity);
		return "deleteSuccess";
	}

	public String searchMessage() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = getRequest().getParameter("type");
		int max = mesSer.getColumnCount(type);
		pager.setPageSize(9);
		pager.setMaxRecords(max);
		paging(max, method);
		List<Message> entity = mesSer.searchColumn(type, pager.getOffset(),
				pager.getPageSize());
		if(null == entity || entity.size() == 0){
			pager.setPageSize(0);
			pager.setMaxPages(0);
			pager.setCurrentPage(0);
			pager.setMaxRecords(0);
		}
		ActionContext.getContext().put("entity", entity);
		ActionContext.getContext().put("pageName", "Message");
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
		Message msg = mesSer.findFromId(id);
		if (null==msg.getGname()) {
			String verifyInfo = "尚未回复，不能审核！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		if (adminName.equals(msg.getGname())) {
			String verifyInfo = "审核员自身不能审核自己上传的新闻！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		if (null != msg.getSname()) {
			String verifyInfo = msg.getSname() + "已审核！";
			ActionContext.getContext().put("verifyInfo", verifyInfo);
			return "VerifyForbid";
		}
		msg.setSname(adminName);
		mesSer.update(msg);
		String verifyInfo = "审核成功！";
		ActionContext.getContext().put("verifyInfo", verifyInfo);
		return "VerifySuccess";
	}

	public String reply() {
		try {
			String id = getRequest().getParameter("id");
			Message entity = mesSer.findFromId(id);
			ActionContext.getContext().put("entity", entity);
		} catch (Exception e1) {
			return "replyFailure";
		}
		return "replySuccess";
	}

	public String replyDo() {
		try {
			String id = getRequest().getParameter("id");
			String reply = getRequest().getParameter("reply");
			String adminName = (String) getRequest().getSession().getAttribute(
					"a_username");
			String gid = (String) getRequest().getSession().getAttribute("gid");
			Date addTime = new Date();
			System.out.println(addTime);
			Message entity = mesSer.findFromId(id);
			entity.setReply(reply);
			entity.setGname(adminName);
			entity.setReplyTime(addTime);
			entity.setGid(gid);
			entity.setSname(null);
			mesSer.update(entity);
		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
			return "replyDoFailure";
		}
		ActionContext.getContext().put("pageName", "Message");
		int max = mesSer.getAllCount();
		pager.setPageSize(9);
		pager.setMaxRecords(max);
		List<Message> mesList = mesSer.list(pager.getOffset(),
				pager.getPageSize());
		ActionContext.getContext().put("entity", mesList);
		return "replyDoSuccess";
	}
}
