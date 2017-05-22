package com.whucs.pgepk.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.whucs.pgepk.hibernate.model.Administrator;
import com.whucs.pgepk.service.impl.AdministratorService;
import com.whucs.pgepk.util.*;

public class AdministratorAction {
	Administrator adm = new Administrator();
	AdministratorService admSer;

	public void setAdmSer(AdministratorService admSer) {
		this.admSer = admSer;
	}

	String a_username;// 用户名
	String a_password;
	String newPassword;// 新密码和新增密码

	String a_checkcode;// 验证码
	int a_super;
	String isSuper = "No";

	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}

	String a_name;// 真实姓名
	String gid;// 管理员id

	String user_check;
	String ip;
	String enterTime;

	String userId;// 管理员用户名
	List<Administrator> admList;
	Pager pager = new Pager();
	String method;
	String key;
	String type;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getA_username() {
		return this.a_username;
	}

	public void setA_username(String a_username) {
		this.a_username = a_username;
	}

	public String getA_password() {
		return this.a_password;
	}

	public void setA_password(String a_password) {
		this.a_password = a_password;
	}

	public String getA_checkcode() {
		return this.a_checkcode;
	}

	public void setA_checkcode(String a_checkcode) {
		this.a_checkcode = a_checkcode;
	}

	public String getUser_check() {
		return this.user_check;
	}

	public int getA_super() {
		return this.a_super;
	}

	public String getA_name() {
		return this.a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getIp() {
		return this.ip;
	}

	public String getEnterTime() {
		return this.enterTime;
	}

	public String getIsSuper() {
		return isSuper;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public List<Administrator> getAdmList() {
		return admList;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String login() {
		user_check = null;
		return "login_index";
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String log_out() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();// request.getSession().removeAttribute("单个属性");
		a_username = null;
		a_name = null;
		a_password = null;
		return "log_out";
	}

	public String login_check() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");
		if (!(a_checkcode.equals(rand))) {
			user_check = "您输入的验证码错误！";
			return "login_failure";
		} else {
			adm = admSer.findFromName(a_username);
			if (null == adm) {
				user_check = "用户名不存在！";
				return "login_failure";
			} else {
				if (adm.getPassword().equals(MD5Util.MD5(a_password))) {
					a_super = adm.getIsSuper();
					a_name = adm.getName();
					userId = adm.getUserId();
					ip = getIpAddr(request);
					Long t = new Date().getTime();
					java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					enterTime = "" + df.format(t);
					session.setAttribute("a_username", a_username);
					session.setAttribute("gid", adm.getId());
					if (a_super == 1) {
						isSuper = "Yes";
					} else {
						isSuper = "No";
					}
					session.setAttribute("isSuper", isSuper);
					user_check = "";
					ActionContext.getContext().put("pageName", "Info");

					return "login_success";
				} else {
					user_check = "密码输入错误！请重新输入！";
					return "login_failure";
				}
			}
		}
	}

	public String login_reCheck() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		a_username = (String) session.getAttribute("a_username");
		if (null == a_username) {
			return "login_failure";
		} else {
			adm = admSer.findFromName(a_username);
			if (null == adm) {
				return "login_failure";
			} else {
				a_super = adm.getIsSuper();
				a_name = adm.getName();
				userId = adm.getUserId();
				ip = getIpAddr(request);
				Long t = new Date().getTime();
				java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				enterTime = "" + df.format(t);
				session.setAttribute("a_username", a_username);
				session.setAttribute("gid", adm.getId());
				if (a_super == 1) {
					isSuper = "Yes";
				} else {
					isSuper = "No";
				}
				session.setAttribute("isSuper", isSuper);
				user_check = "";
				ActionContext.getContext().put("pageName", "Info");
				return "login_success";
			}
		}
	}

	public String changePassword() {
		String newPassword2 = getRequest().getParameter("newPassword2");
		if (null == a_password || null == newPassword || null == newPassword2) {
			user_check = "您的密码输入不合法！";
			return "updateFailure";
		} else if (!newPassword2.equals(newPassword)) {
			user_check = "您的新密码输入不正确！";
			return "updateFailure";
		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			a_username = (String) session.getAttribute("a_username");
			if (a_username == null) {
				user_check = "您已退出！";
				return "updateFailure";
			} else {
				adm = admSer.findFromName(a_username);
				System.out.println("原密码：" + a_password + ",MD5："
						+ adm.getPassword());
				System.out.println("新密码：" + newPassword + ",MD5:"
						+ MD5Util.MD5(newPassword));
				if (!adm.getPassword().equals(MD5Util.MD5(a_password))) {
					user_check = "原密码输入错误！";
					return "updateFailure";
				}
				adm.setPassword(MD5Util.MD5(newPassword));
				admSer.update(adm);
				return "updateSuccess";
			}

		}
	}

	public String changeAdmin() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = getRequest().getParameter("id");
		Administrator entity = admSer.findFromId(id);
		ActionContext.getContext().put("entity", entity);
		return "changeAdmin";
	}

	public String changeAdminDo() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String id = getRequest().getParameter("id");
			Administrator entity = admSer.findFromId(id);
			entity.setIsSuper(getRequest().getParameter("isSuper")
					.equals("Yes") ? 1 : 0);
			entity.setName(getRequest().getParameter("a_name"));
			entity.setNumber(getRequest().getParameter("number"));
			admSer.update(entity);
		} catch (Exception e) {
			user_check = "修改信息未全部输入！";
			return "updateFailure";
		}
		int max = admSer.getRows();
		pager.setPageSize(10);
		pager.setMaxRecords(max);
		paging(max, method);
		admList = admSer
				.viewAdminByList(pager.getOffset(), pager.getPageSize());
		ActionContext.getContext().put("entity", admList);
		ActionContext.getContext().put("pageName", "Admin");
		return "updateAdminSuccess";
	}

	public String AdminManage_index() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int max = admSer.getRows();
		pager.setPageSize(10);
		pager.setMaxRecords(max);
		paging(max, method);
		admList = admSer
				.viewAdminByList(pager.getOffset(), pager.getPageSize());
		ActionContext.getContext().put("entity", admList);
		return "AdminManage_enter_success";
	}

	public String AdminManage_page() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int max = admSer.getRows();
		pager.setPageSize(10);
		pager.setMaxRecords(max);
		paging(max, method);
		admList = admSer
				.viewAdminByList(pager.getOffset(), pager.getPageSize());
		ActionContext.getContext().put("entity", admList);
		ActionContext.getContext().put("pageName", "Admin");
		return "enterManage";
	}

	public String AdminManage_delete() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("要删除的管理员ID为" + gid);
		Administrator entity = admSer.findFromId(gid);
		if (entity == null) {
			user_check = "删除出错！";
			ActionContext.getContext().put("user_check", user_check);
			return "deleteFailure";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String logName = (String) session.getAttribute("a_username");
		System.out.println("当前管理员ID为" + logName);
		Administrator entityTmp = admSer.findFromName(logName);
		if (entityTmp.getId() == gid) {
			user_check = "0";
			ActionContext.getContext().put("user_check", user_check);
			return "deleteFailure";
		}
		admSer.delete(entity);
		admList = admSer
				.viewAdminByList(pager.getOffset(), pager.getPageSize());
		ActionContext.getContext().put("entity", admList);
		ActionContext.getContext().put("pageName", "Admin");
		return "deleteSuccess";
	}

	public String addAdmin() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newPassword2 = getRequest().getParameter("newPassword2");
		if (!newPassword2.equals(newPassword)) {
			user_check = "密码前后输入不正确！";
			int max = admSer.getRows();
			pager.setPageSize(10);
			pager.setMaxRecords(max);
			paging(max, method);
			admList = admSer.viewAdminByList(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", admList);
			ActionContext.getContext().put("pageName", "Admin");
			return "updateFailure";
		}
		if (null == userId || null == isSuper || null == newPassword
				|| null == a_name || null == number) {
			user_check = "数据不能为空！";
			int max = admSer.getRows();
			pager.setPageSize(10);
			pager.setMaxRecords(max);
			paging(max, method);
			admList = admSer.viewAdminByList(pager.getOffset(),
					pager.getPageSize());
			ActionContext.getContext().put("entity", admList);
			ActionContext.getContext().put("pageName", "Admin");
			return "updateFailure";
		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			a_username = (String) session.getAttribute("a_username");
			if (null == a_username) {
				user_check = "您已退出！";
				int max = admSer.getRows();
				pager.setPageSize(10);
				pager.setMaxRecords(max);
				paging(max, method);
				admList = admSer.viewAdminByList(pager.getOffset(),
						pager.getPageSize());
				ActionContext.getContext().put("entity", admList);
				ActionContext.getContext().put("pageName", "Admin");
				return "updateFailure";
			} else {
				//userId是用户名，a_name是真是姓名
				Administrator entityCheck = admSer.findFromName(userId);
				System.out.println("要增加的用户名是" + userId);
				if (null != entityCheck) {
					user_check = "已存在相同用户名！";
					int max = admSer.getRows();
					pager.setPageSize(10);
					pager.setMaxRecords(max);
					paging(max, method);
					admList = admSer.viewAdminByList(pager.getOffset(),
							pager.getPageSize());
					ActionContext.getContext().put("entity", admList);
					ActionContext.getContext().put("pageName", "Admin");
					return "updateFailure";
				}
				Administrator entity = new Administrator();
				entity.setIsSuper(isSuper.equals("Yes") ? 1 : 0);
				entity.setName(a_name);
				entity.setNumber(number);
				entity.setPassword(MD5Util.MD5(newPassword));
				entity.setUserId(userId);
				admSer.add(entity);
				int max = admSer.getRows();
				pager.setPageSize(10);
				pager.setMaxRecords(max);
				paging(max, method);
				admList = admSer.viewAdminByList(pager.getOffset(),
						pager.getPageSize());
				ActionContext.getContext().put("entity", admList);
				ActionContext.getContext().put("pageName", "Admin");
				return "enterManage";
			}
		}
	}

	public String searchAdmin() {
		try {
			getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int max = admSer.searchAdminMax(key, type);
		pager.setPageSize(10);
		pager.setMaxRecords(max);
		paging(max, method);
		List<Administrator> entity = admSer.searchAdmin(key, type,
				pager.getOffset(), pager.getPageSize());
		ActionContext.getContext().put("entity", entity);
		ActionContext.getContext().put("pageName", "Admin");
		ActionContext.getContext().put("key", key);
		ActionContext.getContext().put("type", type);
		return "searchSuccess";
	}

	public void paging(int max, String method) {
		pager.setMaxRecords(max);
		pager.newPager(pager.getCurrentPage(), method, pager.getMaxRecords(),
				pager.getPageSize());
	}

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

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession();
	}
}
