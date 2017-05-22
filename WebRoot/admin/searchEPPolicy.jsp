<%@ page language="java"
	import="java.util.*,com.opensymphony.xwork2.ActionContext"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String adminName = (String) request.getSession().getAttribute(
			"a_username");
	if (null == adminName || adminName.length() == 0) {
		response.sendRedirect(basePath + "admin/admAct!login.action");
	}
String type = "法律法规";
	try {
		type = (String) ActionContext.getContext().get("type");
	} catch (Exception e) {

	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>环保政策法规管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
<script type="text/javascript" src="admin/js/Manage.js"></script>
<link rel="stylesheet" type="text/css" href="admin/css/box_style.css">
<link rel="stylesheet" type="text/css" href="admin/css/common.css">


</head>

<body>
	<div style="height: 20px"></div>
	<div class="List_title">
		<img src="admin/images/g.png" width="17px" height="15px" /> 环保政策法规管理
	</div>
	<div class="List_column">
		<button class="List_button"
			onclick="addWindow('增加环保政策法规','admin/addEPPolicy.jsp',900,480)">增加</button>
		<button class="List_button"
			onclick="manage('policy/epPolAct!EPPolicy_index.action','verify','')">已审核</button>
		<button class="List_button"
			onclick="manage('policy/epPolAct!EPPolicy_index.action','notVerify','')">未审核</button>
		<span class="List_search" style="margin-left:425px;">
			<form action="policy/epPolAct!searchEPPolicy.action" method="post"
				style="margin-bottom:0px;margin-top: 0px;padding:0 0; ">
				<input type="text" name="key" class="List_search_input " /><select
					name="type" class="List_search_choice">
					<option value="">全部</option>
					<option value="法律法规" <%if (type.equals("法律法规")) {%>
						selected="selected" <%}%>>法律法规</option>
					<option value="导则标准" <%if (type.equals("导则标准")) {%>
						selected="selected" <%}%>>导则标准</option>
					<option value="政策解读" <%if (type.equals("政策解读")) {%>
						selected="selected" <%}%>>政策解读</option>
				</select> <img src="admin/images/jiansuo.gif"
					style="height:20px;margin-bottom: -6px;cursor: hand;-webkit-margin-after: -5px;"
					onclick="submit()" />
			</form>
		</span>
	</div>
	<div class="List_info">
		<table class="List_table">
			<tr>
				<th width="10px"><input type="checkbox"
					onclick="checkbox('checkbox')" name="_checkbox" /></th>
				<th width="80px">ID</th>
				<th width="200px">信息标题</th>
				<th width="60px">浏览量</th>
				<th width="100px">分类</th>
				<th width="100px">添加者</th>
				<th width="100px">审核者</th>
				<th width="135px">更新时间</th>
				<th width="180px">操作</th>
			</tr>
			<s:iterator value="entity" id="entity">
				<tr>
					<td align="center"><input type="checkbox" id="${entity.id}"
						name="checkbox" /></td>
					<td align="center">${entity.id}</td>
					<td align="left"><div class="longTextWrap" title="${entity.title}">${entity.title}</div></td>
					<td align="center">${entity.count}</td>
					<td align="center">${entity.column}</td>
					<td align="center">${entity.gname}</td>
					<td align="center">${entity.sname==null?'/':entity.sname}</td>
					<td align="center">${entity.addTime}</td>
					<td align="center">[<a
						href="policy/epPolAct!detail.action?id=${entity.id }"
						class="opLink" target="_blank">查看</a>][<a
						href="javascript:verify('policy/epPolAct!setVerify.action?id=${entity.id }','policy/epPolAct!EPPolicy_index.action')"
						class="opLink">审核</a>][<a
						href="javascript:changeWindow('修改环保新闻','policy/epPolAct!update.action?id=${entity.id }',900,590)"
						class="opLink">修改</a>][<a
						href="javascript:manage('policy/epPolAct!EPPolicy','delete',${entity.id})"
						class="opLink">删除</a>]
					</td>
				</tr>
			</s:iterator>
		</table>
		<table class="List_table_page">
			<tr>
				<td>共&nbsp;<s:property value="pager.maxPages" />&nbsp;页&nbsp;每页&nbsp;<s:property
						value="pager.getPageSize()" />&nbsp;条&nbsp;页次：<s:property
						value="pager.currentPage" />/<s:property value="pager.maxPages" /></td>
				<td width="30px"></td>
				<td><a
					href="<s:url value="policy/epPolAct!searchEPPolicy.action"><s:param name="method" value="%{'first'}"/><s:param name="pager.currentPage" value="pager.currentPage"/><s:param name="key" value="key"/><s:param name="type" value="type"/></s:url>">
						<span style="background:#DCD9D4;">首页</span>
				</a></td>
				<td><a
					href="<s:url value="policy/epPolAct!searchEPPolicy.action"><s:param name="method" value="%{'back'}"/><s:param name="pager.currentPage" value="pager.currentPage"/><s:param name="key" value="key"/><s:param name="type" value="type"/></s:url>">
						<span style="background:#DCD9D4;">上一页</span>
				</a></td>
				<td><span style="background:#EBEBEA;">&nbsp;<s:property
							value="pager.currentPage" />&nbsp;
				</span></td>
				<td><a
					href="<s:url value="policy/epPolAct!searchEPPolicy.action"><s:param name="method" value="%{'next'}"/><s:param name="pager.currentPage" value="pager.currentPage"/><s:param name="key" value="key"/><s:param name="type" value="type"/></s:url>">
						<span style="background:#DCD9D4;">下一页</span>
				</a></td>
				<td align="left">

					<form action="policy/epPolAct!searchEPPolicy.action" method="post" id="gotoform" 
						style="margin-bottom: 0">
						<s:hidden name="method" value="%{'jump'}" />
						<input type="hidden" name="key" value="${key}"> <input
							type="hidden" name="type" value="${type}"> <input
							type="text" name="pager.currentPage"
							style="width:20px;height:17px;font-size: 12px;padding:0 0 0 0;"
							value="" id="gotopage" /> <img src="admin/images/go.gif"
							onclick="goPage(<s:property value="pager.maxPages"/>)"
							style="height:15px;margin-top: 0px;margin-bottom: -3px;cursor: hand;" />
					</form>
				</td>
				<td width="0px"></td>
				<td><a
					href="<s:url value="policy/epPolAct!searchEPPolicy.action"><s:param name="method" value="%{'last'}"/><s:param name="pager.currentPage" value="pager.currentPage"/><s:param name="key" value="key"/><s:param name="type" value="type"/></s:url>">
						<span style="background:#DCD9D4;">尾页</span>
				</a></td>
			</tr>
		</table>
	</div>
	<div class="List_column">
		<div style="margin-top: 12px;font-size: 14px;">
			选择：<a href="javascript:checkbox('all')">全部</a> - <a
				href="javascript:checkbox('inverse')">反选</a> - <a
				href="javascript:manage('policy/epPolAct!EPPolicy','deleteChecked','')">所选删除</a>
		</div>
	</div>
</body>
</html>
