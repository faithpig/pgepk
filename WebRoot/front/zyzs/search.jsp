<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <base href="<%=basePath%>"/>
<title>环保专业知识</title>
<link rel="stylesheet" type="text/css" href="front/css/css.css"/>
<script src="front/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="front/js/setHome.js" type="text/javascript"></script>
<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" language="javascript" type="text/javascript">
</script>
<script language="javascript" type="text/javascript">
  DD_belatedPNG.fix('*');
  function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
</script>
<![endif]-->
<script type="text/javascript">
/* 文本溢出显示省略号 */
/* 用法：$(".box").ellipsis(50) */
/*box为需要实现的Class*/
(function($){
jQuery.fn.ellipsis = function(maxwidth){
	this.each(function(){
		if ($(this).text().length > maxwidth) {
			$(this).attr("title",$(this).text());
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '...');
		}
	});};
})(jQuery);
$(document).ready(function(){
	$(".hide").ellipsis(28);
});
</script>
</head>

<body>
	<div class="allbody">
        <div class="headertop">
        	<div class="fl logo"><img src="front/images/logo.jpg" width="299" height="60"/></div>
            <div class="fr logo_y"><a class="sy" href="#" onclick="return set_homepage(this,'<%=basePath%>');">设为首页</a><a class="sc" href="<%=basePath%>" onclick="return add_favorite(this,'电网环保知识科普');">加入收藏</a><a class="lx" href="<%=basePath%>contact/conAct!detail.action">联系我们</a><a class="dt" href="<%=basePath%>front/sitemap.jsp">网站地图</a></div>
            <div class="fr" style="width:660px;">
            	<a class="dh" href="<%=basePath%>index/homAct!home.action">网站首页</a>
                <a class="dh1" href="<%=basePath%>news/epNewAct!list.action?pager.currentPage=0">环保新闻</a>
               
                <a class="dh3" href="<%=basePath%>policy/epPolAct!list.action?pager.currentPage=0">环保政策法规</a>
                 <a class="dh2" href="<%=basePath%>term/epTerAct!list.action?pager.currentPage=0">环保专业知识</a>
                <a class="dh4" href="<%=basePath%>message/mesAct!list.action?pager.currentPage=0">留言板</a>
                <a class="dh5" href="<%=basePath%>link/linAct!list.action">友情链接</a>
            </div>
            <div class="clear"></div>
        </div>
        <div class="header" align="center"><img src="front/images/banner.jpg" width="1002" height="252"/></div>
        <div class="menu">
            <div style=" margin-top:10px;">
            	<div class="fl" style=" padding-left:0px; width:249px;">
                    <div class="zs_top"></div>
                    <div class="zs_zj">
                        <div>
                            <div class="fl xw">环保专业知识</div>
                            <div class="fr"></div>
                            <div class="clear"></div>
                        </div>
                        <div class="hx"></div>
                        <ul class="nynews3">
                            <li><a href="<%=basePath%>term/epTerAct!fsearch.action?column=基础知识&pager.currentPage=0">基础知识</a></li>
                            <li><a href="<%=basePath%>term/epTerAct!fsearch.action?column=技术问题解答&pager.currentPage=0">技术问题解答</a></li>
                            <li><a href="<%=basePath%>term/epTerAct!fsearch.action?column=技术措施&pager.currentPage=0">技术措施</a></li>
                        </ul>
                    </div>
                    <div class="zs_footer"></div>
            </div>
            	<div class="fr" style="width:744px;">
                	<div class="lxwm2" style=" width:744px; height:40px;">
                        <div class="fl lxwm_z">环保专业知识</div>
                        <%String abc=(String)ActionContext.getContext().get("column"); %>
                        <div class="fr">您现在的位置：<a href="<%=basePath%>index/homAct!home.action">首页</a> > <a href="<%=basePath%>term/epTerAct!list.action">环保专业知识</a><%if(!((abc==null)||abc.trim().equals(""))){%> > <%}%><%=abc%></div>
                		<div class="clear"></div>
            		</div>
                    <div>
                    	<div align="right" style="padding-top:16px;">
                    	<form action="<%=basePath%>term/epTerAct!fsearch.action" method="get">
                    	请输入检索内容：<input name="key" type="text" />
                    	<select name="column">
                    		<option value=" ">所有</option>
                    		<option value="基础知识" <%if(abc.equals("基础知识")){ %>selected="selected" <% }%>>基础知识</option>
                    		<option value="技术问题解答" <%if(abc.equals("技术问题解答")){ %>selected="selected" <% }%>>技术问题解答</option>
                    		<option value="技术措施" <%if(abc.equals("技术措施")){ %>selected="selected" <% }%>>技术措施</option>
                    	</select>
                    	<input name="pager.currentPage" type="hidden" value="0"/>
                    	<input name="submit" type="submit" value="检索" class="anniu" />
                    	</form>
                    	</div>
                    	<ul class="newslist">
                        	<li class="newstop"><a href="javascript:void(0)" class="fl">环保专业知识记录</a><span class="fl">浏览量</span><span class="fr">创建时间</span></li>
                        	<s:iterator value="list" id="entity">
                        	<li><a href="<%=basePath%>term/epTerAct!detail.action?id=<s:property value="id"/>" class="fl hide"><s:property value="title"/></a><span class="fl"><s:property value="count"/></span><span class="fr">[${entity.addTime}]</span></li>
                        	</s:iterator>
                        </ul>
     <div class="page_info" style="margin-top:10px;">
            共<span><s:if test="%{pager.maxPages!=0}"><s:property value="pager.maxPages"/></s:if><s:if test="%{pager.maxPages==0}">1</s:if></span>页&nbsp;&nbsp;&nbsp;&nbsp;页次：<s:property value="pager.currentPage" />/<s:if test="%{pager.maxPages!=0}"><s:property value="pager.maxPages"/></s:if><s:if test="%{pager.maxPages==0}">1</s:if>
		<span>　　　　　　　　　　　　　　　　　　　</span>
     <a href="<s:url value="/term/epTerAct!fsearch.action"><s:param name="key" value="%{key}"/><s:param name="column" value="%{column}"/><s:param name="method" value="%{'first'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">首页</span></a>
     <a href="<s:url value="/term/epTerAct!fsearch.action"><s:param name="key" value="%{key}"/><s:param name="column" value="%{column}"/><s:param name="method" value="%{'back'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">上一页</span></a>
     <span style="background:#EBEBEA;">&nbsp;<s:property value="pager.currentPage" />&nbsp;</span>
     <a href="<s:url value="/term/epTerAct!fsearch.action"><s:param name="key" value="%{key}"/><s:param name="column" value="%{column}"/><s:param name="method" value="%{'next'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">下一页</span></a>
     <form action="<%=basePath%>term/epTerAct!fsearch.action?key=${key}&column=${column}" method="post" id="gotoform" style="margin-bottom: 0;float:right;">
		<s:hidden name="method" value="%{'jump'}" />
		<input type="text" name="pager.currentPage" style="width:33px;height:17px;font-size: 12px;padding:0 0 0 0;" value="" id="gotopage" />
		<img src="front/images/go.gif" onclick="toPage()" style="height:15px;margin-top: 0px;margin-bottom: -3px;cursor: hand;"/>
	 </form>
<script type="text/javascript">
	function toPage() {
		var m = document.getElementById("gotopage").value;
		if (!isNaN(m) && m > 0 && m <= <s:property value="pager.maxPages"/>) {
			document.getElementById("gotoform").submit();
		}
	}
</script>
	 <a href="<s:url value="/term/epTerAct!fsearch.action"><s:param name="key" value="%{key}"/><s:param name="column" value="%{column}"/><s:param name="method" value="%{'last'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">尾页</span></a>
       </div>
                    </div>
                </div>
                
                <div class="clear"></div>
        </div>
    </div>
 
    <div class="footer">
    	<div>
            <a href="<%=basePath%>index/homAct!home.action">首页</a>
            <a href="<%=basePath%>news/epNewAct!list.action?pager.currentPage=0">|  环保新闻</a>
            <a href="<%=basePath%>policy/epPolAct!list.action?pager.currentPage=0">|  环保政策法规</a>
            <a href="<%=basePath%>term/epTerAct!list.action?pager.currentPage=0">|  环保专业知识</a>
            <a href="<%=basePath%>message/mesAct!list.action?pager.currentPage=0">|  留言板</a>
            <a href="<%=basePath%>link/linAct!list.action">|  友情链接</a>
        </div>
        <div style=" margin:0px 0 20px 0; text-align:center;">State Grid Corporation of China(SGCC)  Copyright ©All rights reserved</div>
    </div>
    </div>
</body>

</html>