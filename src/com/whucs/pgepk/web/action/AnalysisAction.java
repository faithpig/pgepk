package com.whucs.pgepk.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whucs.pgepk.hibernate.model.ArticleVisitor;
import com.whucs.pgepk.hibernate.model.EPNews;
import com.whucs.pgepk.hibernate.model.EPPolicy;
import com.whucs.pgepk.hibernate.model.EPTerm;
import com.whucs.pgepk.service.impl.ArticleVisitorService;
import com.whucs.pgepk.service.impl.HomePageHistoryService;
import com.whucs.pgepk.service.impl.HomePageVisitorService;

public class AnalysisAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6032820029931037090L;
	private ArticleVisitorService artVisSer;
	private HomePageVisitorService homPagVisSer;
	@SuppressWarnings("unused")
	private HomePageHistoryService homPagHisSer;

	public void setArtVisSer(ArticleVisitorService artVisSer) {
		this.artVisSer = artVisSer;
	}

	public void setHomPagVisSer(HomePageVisitorService homPagVisSer) {
		this.homPagVisSer = homPagVisSer;
	}

	public void setHomPagHisSer(HomePageHistoryService homPagHisSer) {
		this.homPagHisSer = homPagHisSer;
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public void addArtVis() {
		Date enterTime = new Date();
		Date closeTime = new Date();
		enterTime.setTime(Long.valueOf((String) getRequest().getParameter(
				"enterTime")));
		closeTime.setTime(Long.valueOf((String) getRequest().getParameter(
				"closeTime")));
		String ip = getIpAddr(ServletActionContext.getRequest());
		String type = getRequest().getParameter("type").toString();
		String aid = getRequest().getParameter("aid");
		artVisSer.add(enterTime, closeTime, ip, type, aid);
	}

	public void addHomVis() {
		Date enterTime = new Date();
		enterTime.setTime(Long.valueOf((String) getRequest().getParameter(
				"enterTime")));
		String ip = getIpAddr(ServletActionContext.getRequest());
		homPagVisSer.add(enterTime, ip);
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
	public String Statistics_index() {
		return "Statistics_enter_success";
	}

	public String allTop10() {
		ActionContext.getContext().put("type", "allTop10");
		return "allTop10";
	}

	public String hbxw() {
		List<EPNews> entity = artVisSer.newsList();
		String title[] = new String[entity.size()];
		int count[] = new int[entity.size()];
		String[] id = new String[entity.size()];
		for (int i = 0; i < entity.size(); i++) {
			id[i] = entity.get(i).getId();
			count[i] = (int) entity.get(i).getCount();
			title[i] = entity.get(i).getTitle();
		}
		ActionContext.getContext().put("title", title);
		ActionContext.getContext().put("count", count);
		ActionContext.getContext().put("id", id);
		ActionContext.getContext().put("type", "hbxw");
		ActionContext.getContext().put("entity", entity);
		return "statistics";
	}

	public String hbzyzs() {
		List<EPTerm> entity = artVisSer.termList();
		String title[] = new String[entity.size()];
		int count[] = new int[entity.size()];
		String[] id = new String[entity.size()];
		for (int i = 0; i < entity.size(); i++) {
			id[i] =  entity.get(i).getId();
			count[i] = (int) entity.get(i).getCount();
			title[i] = entity.get(i).getTitle();
		}
		ActionContext.getContext().put("title", title);
		ActionContext.getContext().put("count", count);
		ActionContext.getContext().put("id", id);
		ActionContext.getContext().put("type", "hbzyzs");
		ActionContext.getContext().put("entity", entity);
		return "statistics";
	}

	public String hbzcfg() {
		List<EPPolicy> entity = artVisSer.policyList();
		String title[] = new String[entity.size()];
		int count[] = new int[entity.size()];
		String[] id = new String[entity.size()];
		for (int i = 0; i < entity.size(); i++) {
			id[i] = entity.get(i).getId();
			count[i] = (int) entity.get(i).getCount();
			title[i] = entity.get(i).getTitle();
		}
		ActionContext.getContext().put("title", title);
		ActionContext.getContext().put("count", count);
		ActionContext.getContext().put("id", id);
		ActionContext.getContext().put("type", "hbzcfg");
		ActionContext.getContext().put("entity", entity);
		return "statistics";
	}

	public String visitTimeAll() {
		int visTime[] = new int[24];
		@SuppressWarnings("rawtypes")
		List tmpTime = artVisSer.visitTimeAll();
		int len = tmpTime.size();
		for (int i = 0; i < len; i++) {
			visTime[Integer.parseInt(tmpTime.get(i).toString())]++;
		}
		ActionContext.getContext().put("visTime", visTime);
		ActionContext.getContext().put("type", "visitTimeAll");
		return "statistics";
	}

	@SuppressWarnings("rawtypes")
	public String visitByDay() {
		String day = getRequest().getParameter("ViewDay");
		System.out.println("所得日期为：" + day);
		if (day.length() < 10) {
			String parts[] = day.split("-");
			day = parts[0] + "-" + "0" + parts[1] + "-" + parts[2];
		}
		int k = 0;

		int visTime[] = new int[24];
		List tmpTime = null;
		try {
			tmpTime = artVisSer.visitTimeAllDay(day);
		} catch (Exception e) {
		}
		int len = tmpTime.size();
		for (int i = 0; i < len; i++) {
			visTime[Integer.parseInt(tmpTime.get(i).toString())]++;
		}
		ActionContext.getContext().put("visTime", visTime);

		List<ArticleVisitor> epNewsEntity = null;
		len = 0;
		try {
			epNewsEntity = artVisSer.newsListDay(day);
			len = epNewsEntity.size();
		} catch (Exception e) {
		}
		if (len != 0) {
			int epNewsCount[] = new int[len];
			String[] epNewsId = new String[len];
			for (int i = 0; i < len; i++) {
				boolean flag = true;
				for (int j = 0; j < k; j++) {
					if (epNewsEntity.get(i).getAid().equals(epNewsId[j])) {
						epNewsCount[j]++;
						flag = false;
						break;
					}
				}
				if (flag == true) {
					epNewsId[k] = epNewsEntity.get(i).getAid();
					epNewsCount[k]++;
					k++;
				}
			}
			List<String[]> topList = getTop10(epNewsCount, epNewsId, k);
			String[] ll = new String[topList.get(0).length];
			epNewsCount = new int[ll.length];
			for(int i=0;i<ll.length;i++) epNewsCount[i] = Integer.parseInt(ll[i]);

			epNewsId = topList.get(1);
			k = epNewsCount.length;
			ActionContext.getContext().put("epNewsCount", epNewsCount);
			ActionContext.getContext().put("epNewsId", epNewsId);
			ActionContext.getContext().put("epNewsSize", k);
		}

		List<ArticleVisitor> epTermEntity = null;
		len = 0;
		try {
			epTermEntity = artVisSer.termListDay(day);
			len = epTermEntity.size();
		} catch (Exception e) {

		}
		if (len != 0) {
			int epTermCount[] = new int[len];
			String[] epTermId = new String[len];
			k = 0;
			for (int i = 0; i < len; i++) {
				boolean flag = true;
				for (int j = 0; j < k; j++) {
					if (epTermEntity.get(i).getAid().equals(epTermId[j])) {
						epTermCount[j]++;
						flag = false;
						break;
					}
				}
				if (flag == true) {
					epTermId[k] = epTermEntity.get(i).getAid();
					epTermCount[k]++;
					k++;
				}
			}
			List<String[]> topList = getTop10(epTermCount, epTermId, k);
			String[] ll = new String[topList.get(0).length];
			epTermCount = new int[ll.length];
			for(int i=0;i<ll.length;i++) epTermCount[i] = Integer.parseInt(ll[i]);
			epTermId = topList.get(1);
			k = epTermCount.length;
			ActionContext.getContext().put("epTermCount", epTermCount);
			ActionContext.getContext().put("epTermId", epTermId);
			ActionContext.getContext().put("epTermSize", k);
		}

		List<ArticleVisitor> epPolEntity = null;
		len = 0;
		try {
			epPolEntity = artVisSer.policyListDay(day);
			len = epPolEntity.size();
		} catch (Exception e) {

		}
		if (len != 0) {
			int epPolCount[] = new int[len];
			String[] epPolId = new String[len];
			k = 0;
			for (int i = 0; i < len; i++) {
				boolean flag = true;
				for (int j = 0; j < k; j++) {
					if (epPolEntity.get(i).getAid().equals(epPolId[j])) {
						epPolCount[j]++;
						flag = false;
						break;
					}
				}
				if (flag == true) {
					epPolId[k] = epPolEntity.get(i).getAid();
					epPolCount[k]++;
					k++;
				}
			}
			List<String[]> topList = getTop10(epPolCount, epPolId, k);
			String[] ll = new String[topList.get(0).length];
			epPolCount = new int[ll.length];
			for(int i=0;i<ll.length;i++) epPolCount[i] = Integer.parseInt(ll[i]);
			epPolId = topList.get(1);
			k = epPolId.length;
			ActionContext.getContext().put("epPolCount", epPolCount);
			ActionContext.getContext().put("epPolId", epPolId);
			ActionContext.getContext().put("epPolSize", k);
		}
		return "statisticsDay";
	}

	public List<String[]> getTop10(int[] count, String id[], int k) {
		int size = Math.min(10, k);
		int[] countSize = new int[size];
		String[] idSize = new String[size];
		List<String[]> tmpList = new ArrayList<String[]>();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < k; j++) {
				if (count[i] < count[j]) {
					int tmpCount = count[i];
					count[i] = count[j];
					count[j] = tmpCount;
					String tmpId = id[i];
					id[i] = id[j];
					id[j] = tmpId;
				}
			}
			countSize[i] = count[i];
			idSize[i] = id[i];
		}
		String[] tempcount = new String[countSize.length];
		for(int i =0;i<countSize.length;i++) tempcount[i] = new Integer(countSize[i]).toString();
		tmpList.add(tempcount);
		tmpList.add(idSize);
		return tmpList;
	}

}
