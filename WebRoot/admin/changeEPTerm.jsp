<%@ page language="java"
	import="java.util.*,com.opensymphony.xwork2.ActionContext"
	pageEncoding="UTF-8"%>
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
	String type = "基础知识";
	try {
		type = (String) ActionContext.getContext().get("type");
	} catch (Exception e) {

	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改环保专业知识</title>

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
			alert('请输入详细图文！');
			return false;
		}
		var s2=document.getElementsByName("deleteVideo");
		if(s2[0].checked){
			if(confirm("若原有视频确认删除视频？")){
				return true;
			}else{
				return false;
			}
		}
		
		var s3=document.getElementsByName("file");
		if(s3[0].value.length<1){
			if(confirm("如果原来有视频将保留原视频？")){
				return true;
			}else{
				return false;
			}
		}else{
			if(confirm("确认替换原视频？")){
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
	<form onsubmit="return checkEPTerm(this)" name="subForm"
		action="term/epTerAct!updateDo.action" target="_parent" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${entity.id }">
		<table border="0" class="addTable">
			<tr>
				<th>知识标题：</th>
				<td><input type="text" size="90" class="addInput" name="title"
					value="${entity.title }" /></td>
			</tr>
			<tr>
				<th>知识分类：</th>
				<td><select class="addInput" name="type">
						<option value="基础知识" <%if (type.equals("基础知识")) {%>
							selected="selected" <%}%>>基础知识</option>
						<option value="技术问题解答" <%if (type.equals("技术问题解答")) {%>
							selected="selected" <%}%>>技术问题解答</option>
						<option value="技术措施" <%if (type.equals("技术措施")) {%>
							selected="selected" <%}%>>技术措施</option>
				</select></td>
			</tr>
			<tr>
				<th>视 频：</th>
				<td><input type="file" style="width: 200px;" name="file" /><input
					type="checkbox" value="yes" name="deleteVideo">删除原视频</td>
			</tr>
			<tr><th></th><td>注：如果不选择视频文件，则默认保留原来的状态。若选择文件将默认替换</td></tr>
			<tr valign="top">
				<th>详细介绍：</th>
				<td><textarea name="detail"
						style="width:700px;height:350px;visibility:hidden;">${entity.detail }</textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<td><button type="submit" class="List_button">确认修改</button></td>
			</tr>


		</table>
	</form>
</body>
</html>
