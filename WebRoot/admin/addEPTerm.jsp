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
    
    <title>增加环保专业知识</title>
    
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
	var detailLength = 0;
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
				},
				afterChange : function() {
					detailLength = this.count('text');
				},
			});
			prettyPrint();
		});
	</script>
			<script type="text/javascript">
	function checkEPTerm(){
		var s1=document.getElementsByName("title");
		if(s1[0].value.length<1){
			alert('请输入标题！');
			return false;
		}
		if (detailLength == 0) {
			alert('请输入详细介绍！');
			return false;
		}
		
		var s3=document.getElementsByName("file");
		if(s3[0].value.length<1){
			if(confirm("你确定本文章不增加视频？")){
				return true;
			}else{
				return false;
			}
		}		
		return true;
	}
	</script>
  </head>
  
  <body>
  <form name="subForm" onsubmit="return checkEPTerm(this)"  action="term/epTerAct!add.action" target="_parent" method="post" enctype="multipart/form-data">
    
    <table border="0" class="addTable">
    	<tr><th>知识标题：</th><td><input type="text" size="90" class="addInput" name="title"/></td></tr>
    	<tr><th>知识分类：</th><td>
    		<select class="addInput"name="type">
    		<option value="基础知识">基础知识</option>
    		<option value="技术问题解答">技术问题解答</option>
    		<option value="技术措施">技术措施</option>
    		</select></td></tr>
		<tr><th>视　　频：</th><td><input type="file" style="width: 200px;"name="file"/></td></tr>  	
    	<tr valign="top"><th>详细介绍：</th><td>
    	<textarea name="detail" style="width:700px;height:350px;visibility:hidden;"></textarea>
    	</td></tr>
    	<tr><th></th><td><button type="submit" class="List_button">确认增加</button></td></tr>

    
    </table></form>
  </body>
</html>
