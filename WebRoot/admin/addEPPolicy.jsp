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
    
    <title>增加环保政策法规</title>
    
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
	function checkEPPolicy(){
		var s1=document.getElementsByName("title");
		if(s1[0].value.length<1){
			alert('请输入标题！');
			return false;
		}
		if (detailLength == 0) {
			alert('请输入详细图文！');
			return false;
		}
		return true;
	}
	</script>
  </head>
  
  <body>
  <form name="subForm"onsubmit="return checkEPPolicy(this)"  action="policy/epPolAct!add.action" target="_parent" method="post">
    <table border="0" class="addTable">
    	<tr><th>法规标题：</th><td><input type="text" size="90" class="addInput" name="title"/></td></tr>
    	<tr><th>法规分类：</th><td>
    		<select class="addInput"name="type">
    		<option value="法律法规">法律法规</option>
    		<option value="导则标准">导则标准</option>
    		<option value="政策解读">政策解读</option>
    		</select></td></tr>
    	<tr valign="top"><th>详细解读：</th><td>
    	<textarea name="detail" style="width:700px;height:350px;visibility:hidden;"></textarea>
    	</td></tr>
    	<tr><th></th><td><button type="submit" class="List_button">确认增加</button></td></tr>

    
    </table></form>
  </body>
</html>
