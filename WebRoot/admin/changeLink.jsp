<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String adminName = (String) request.getSession().getAttribute(
		"a_username");
if (null == adminName || adminName.length() == 0) {
	response.sendRedirect(basePath + "admin/admAct!login.action");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>友情链接修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
	 <script type="text/javascript" src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
	 <script type="text/javascript" src="admin/js/Manage.js"></script>
	 <link rel="stylesheet" type="text/css" href="admin/css/box_style.css">
	<link rel="stylesheet" type="text/css" href="admin/css/common.css">
<script type="text/javascript">
	function checkLink(){
		var s1=document.getElementsByName("title");
		if(s1[0].value.length<1){
			alert('请输入标题！');
			return false;
		}
		var s1=document.getElementsByName("link");
		if(s1[0].value.length<1){
			alert('请输入网址！');
			return false;
		}
		var s3=document.getElementsByName("file");
		if(s3[0].value.length<1){
				alert('请添加图片！');
				return false;
		}		
		return true;
	}
	</script>
  </head>
  
  <body>
      <form name="subForm" onsubmit="return checkLink(this)"  action="link/linAct!updateDo.action"enctype="multipart/form-data" method="post" target="_parent">
      <input type="hidden" name="id" value="${entity.id }">
    <table border="0" class="addTable">
    	<tr><th>链接标题：</th><td><input type="text" size="90" class="addInput" value="${entity.title }" name="title"/></td></tr>
    	<tr><th>链接网址：</th><td><input type="text" size="90" class="addInput"value="${entity.link }" name="link"/></td></tr>
    	<tr><th>显示图片：</th><td><input type="file"  class="addInput" name="file"/></td></tr>
    	<tr><th></th><td><button type="submit" class="List_button">确认修改</button></td></tr> 
    </table></form>
  </body>
</html>
