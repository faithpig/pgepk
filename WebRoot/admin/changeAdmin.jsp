<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    
    <title>管理员信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="admin/css/common.css">
	<script type="text/javascript">
	function checkAdmin(){
		var s1=document.getElementsByName("a_name");
		var s3=document.getElementsByName("number");
		if(s1[0].value.length<1){
			alert('请输入姓名！');
			return false;
		}
		if(s1[0].value.length>20){
			alert('姓名长度不能大于20');
			return false;
		}
		if(s3[0].value.length<1){
			alert('请输入证件号如身份证或员工号！');
			return false;
		}
		if(s3[0].value.length>20){
			alert('长度不能大于20');
			return false;
		}
		return true;
	}
	</script>

  </head>
  
  <body>
    <s:form onsubmit="return checkAdmin(this)" action="admin/admAct!changeAdminDo.action" method="post" target="_parent">
   <s:token/>
   <input type="hidden" value="${entity.id }" name="id"/>
   <table>
   <tr><th>用户名：</th><td><input type="text" style="border: 0px solid #00AA00;width: 200px;" name="userId" readonly="readonly" value="${entity.userId}"></td></tr>
   <tr><th>密　码：</th><td><input type="password" style="border: 0px solid #00AA00;width: 200px;" name="newPassword"readonly="readonly" value="********"></td></tr>
   <tr><th>姓　名：</th><td><input type="text" style="border: 1px solid #00AA00;width: 200px;" name="a_name" value="${entity.name }"></td></tr>
   <tr><th>证件号：</th><td><input type="text" style="border: 1px solid #00AA00;width: 200px;" name="number" value="${entity.number }"></td></tr>
   <tr><th>权　限：</th><td><input type="radio" name="isSuper" value="Yes">超级管理员<input type="radio" name="isSuper" value="No" checked="checked">普通管理员</td></tr>
   <tr><th></th><td><button type="submit" class="List_button">确认修改</button></td></tr>
   </table></s:form>
  </body>
</html>
