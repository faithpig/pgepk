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
    
    <title>增加友情链接</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="admin/css/common.css">
	
	<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="detail"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : 'kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['subForm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['subForm'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
	
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
  <form name="subForm"  onsubmit="return checkLink(this)"  action="link/linAct!add.action"  enctype="multipart/form-data" method="post" target="_parent">
    <table border="0" class="addTable">
    	<tr><th>链接标题：</th><td><input type="text" size="70" class="addInput"name="title"/></td></tr>
    	<tr><th>链接网址：</th><td><input type="text" size="70" class="addInput"name="link"/></td></tr>
    	<tr><th>显示图片：</th><td><input type="file"  class="addInput" name="file"/>建议像素180x41</td></tr>
    	<tr><th></th><td><button type="submit" class="List_button">确认增加</button></td></tr>

    
    </table></form>
  </body>
</html>
