<%@ page language="java"
	import="java.util.*,org.apache.struts2.ServletActionContext,com.opensymphony.xwork2.ActionContext"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//HttpServletRequest _request = ServletActionContext.getRequest();
	String isSuper = (String) session.getAttribute("isSuper");
	String a_username = (String) session.getAttribute("a_username");
	if (a_username == null) {
%>
<jsp:forward page="Login.jsp"></jsp:forward>
<%
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>" />

<title>后台管理系统</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
<script type="text/javascript" src="admin/js/Manage.js"></script>
<link rel="stylesheet" type="text/css" href="admin/css/box_style.css" />
<link rel="stylesheet" type="text/css" href="admin/css/Manage.css" />
<link rel="stylesheet" type="text/css" href="admin/css/common.css" />

</head>

<body>
	<jsp:include page="backHeader.jsp"></jsp:include>
	<div id="nav">
		<div id="nav_menu">
			<ul>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="admin/admAct!login_reCheck.action">主界面</a></li>
				<%
					if (isSuper.equals("Yes")) {
				%>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('admin/admAct!AdminManage_index.action','','')">管理员信息管理</a></li>
				<%
					}
				%>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('news/epNewAct!EPNews_index.action','','')">环保新闻管理</a></li>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('term/epTerAct!EPTerm_index.action','','')">环保专业知识管理</a></li>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('policy/epPolAct!EPPolicy_index.action','','')">环保政策法规管理</a></li>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('message/mesAct!Message_index.action','','')">留言板管理</a></li>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('contact/conAct!Contact_index.action','','')">联系我们管理</a></li>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('link/linAct!Link_index.action','','')">友情链接管理</a></li>
				<li onmouseover="change(this)" onmouseout="back(this)"><a
					href="javascript:manage('analysis/anaAct!Statistics_index.action','','')">信息统计管理</a></li>
			</ul>
		</div>
		<div id="line"></div>
		<div id="main">
			<%
				String pageName = "Info";
				try {
					pageName = (String) ActionContext.getContext().get("pageName");
				} catch (Exception e) {

				}
				if (null == pageName) {
			%>
			<jsp:include page="Info.jsp" />
			<%
				} else if (pageName.equals("Admin")) {
			%>
			<jsp:include page="AdminManage.jsp" />
			<%
				} else if (pageName.equals("EPNews")) {
			%>
			<jsp:include page="EPNewsManage.jsp" />
			<%
				} else if (pageName.equals("EPTerm")) {
			%>
			<jsp:include page="EPTermManage.jsp" />
			<%
				} else if (pageName.equals("EPPolicy")) {
			%>
			<jsp:include page="EPPolicyManage.jsp" />
			<%
				} else if (pageName.equals("Message")) {
			%>
			<jsp:include page="MessageManage.jsp" />
			<%
				} else if (pageName.equals("Contact")) {
			%>
			<jsp:include page="ContactManage.jsp" />
			<%
				} else if (pageName.equals("Link")) {
			%>
			<jsp:include page="LinkManage.jsp" />
			<%
				} else if (pageName.equals("Statistics")) {
			%>
			<jsp:include page="Statistics.jsp" />
			<%
				} else {
			%>
			<jsp:include page="Info.jsp" />
			<%
				}
			%>
		</div>
	</div>
	<script type="text/javascript">
		alert('${user_check}');
	</script>
</body>
</html>
