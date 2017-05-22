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
    
    <title>增加管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="admin/css/common.css">
	<script type="text/javascript">
	function checkAddAdmin(){
		var s1=document.getElementsByName("userId");
		var s2=document.getElementsByName("newPassword");
		var s3=document.getElementsByName("newPassword2");
		var s4=document.getElementsByName("a_name");
		var s5=document.getElementsByName("number");
		if(s1[0].value.length<1){
			alert('请输入登录用户名昵称！');
			return false;
		}
		if(s1[0].value.length>20){
			alert('用户名不能长于20');
			return false;
		}
		if(s2[0].value.length<1){
			alert('请输入密码！');
			return false;
		}
		if(s2[0].value.length>20){
			alert('密码长度不能大于20');
			return false;
		}
		if(s3[0].value!=s2[0].value){
			alert('两次密码输入不对！');
			return false;
		}
		if(s4[0].value.length<1){
			alert('请输入新增管理员的姓名！');
			return false;
		}
		if(s4[0].value.length>20){
			alert('管理员姓名长度不能超过20');
			return false;
		}
		if(s5[0].value.length<1){
			alert('请输入证件号如工号、身份证！');
			return false;
		}
		if(s5[0].value.length>20){
			alert('证件号不能超过20');
			return false;
		}
		return true;

	}
	</script>
  </head>
  
  <body><s:form onsubmit="return checkAddAdmin(this)" action="admin/admAct!addAdmin.action" method="post" target="_parent">
   <s:token/>
   <table>
   <tr><th>用户名：</th><td><input type="text" style="border: 1px solid #00AA00;width: 200px;" name="userId"></td></tr>
   <tr><th>密　码：</th><td><input type="password" style="border: 1px solid #00AA00;width: 200px;" name="newPassword"></td></tr>
   <tr><th>确　认：</th><td><input type="password" style="border: 1px solid #00AA00;width: 200px;" name="newPassword2"></td></tr>
   <tr><th>姓　名：</th><td><input type="text" style="border: 1px solid #00AA00;width: 200px;" name="a_name"></td></tr>
   <tr><th>证件号：</th><td><input type="text" style="border: 1px solid #00AA00;width: 200px;" name="number"></td></tr>
   <tr><th>权　限：</th><td><input type="radio" name="isSuper" value="Yes">超级管理员<input type="radio" name="isSuper" value="No" checked="checked">普通管理员</td></tr>
   <tr><th></th><td><button type="submit" class="List_button">确认增加</button></td></tr>
   </table></s:form>
  </body>
</html>
