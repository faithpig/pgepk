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
    
    <title>留言板</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
<link rel="stylesheet" type="text/css" href="front/css/css.css"/>
<script src="front/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<!--加入收藏，设为主页代码-->
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
            <div class="fl xw">留言分类</div>
            <div class="fr"></div>
            <div class="clear"></div>
          </div>
          <div class="hx"></div>
          <ul class="nynews3">
            <li><a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{'基础知识'}"/></s:url>">基础知识</a></li>
            <li><a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{'政策解答'}"/></s:url>">政策解答</a></li>
            <li><a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{'技术支持'}"/></s:url>">技术支持</a></li>
            <li><a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{'主题活动'}"/></s:url>">主题活动</a></li>
          </ul>
        </div>
        <div class="zs_footer"></div>
      </div>
      <div class="fr" style="width:744px;">
        <div class="lxwm3" style=" width:744px; height:40px;">
          <div class="fl lxwm_z">留言板</div>
          <%String abc=(String)ActionContext.getContext().get("column"); %>
          <div class="fr">您现在的位置：<a href="<%=basePath%>index/homAct!home.action">首页</a> > <a href="<%=basePath%>message/mesAct!list.action">留言板</a> > <%=abc%></div>
          <div class="clear"></div>
        </div>
        <div class="subCont">
          <div class="message_warp">
            <div class="message_bg">
              <div class="message_wz">我们热忱接受您的意见和建议</div>
              <form name="form" id="form" method="post" action="<%=basePath%>message/mesAct!add.action">
                <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">

                  <tr>
                    <td height="34" align="right" class="message_labes">栏　　目：&nbsp;</td>
                    <td><select name="columnName" id="column">
                    		<option value="基础知识" <%if(abc.equals("基础知识")){ %>selected="selected" <% }%>>基础知识</option>
                    		<option value="政策解答" <%if(abc.equals("政策解答")){ %>selected="selected" <% }%>>政策解答</option>
                    		<option value="技术支持" <%if(abc.equals("技术支持")){ %>selected="selected" <% }%>>技术支持</option>
                    		<option value="主题活动" <%if(abc.equals("主题活动")){ %>selected="selected" <% }%>>主题活动</option>
                    	</select>
                    </td>
                  </tr>
                  <tr>
                    <td height="116" align="right" class="message_labes">内　　容：&nbsp;</td>
                    <td><textarea name="detail" class="message_input" style="height:100px;" id="content" ></textarea></td>
                  </tr>
                  <tr>
                    <td colspan="2"><div class="msg_btn_area"> 
                    <a href="javascript:void(0)" onclick="cfm_msg();">提　交</a>
                    <center><span style="color:#aaa;">（不得超过240个汉字）</span></center>
                    ${addsuccess}
<script type="text/javascript">
function cfm_msg(){
	var column=document.getElementById("form");
	var detail=document.getElementById("content");
	if(detail.value.trim()==""||detail==null){
		window.alert('留言内容不可为空');
		return;
	}
	if(detail.value.length>=240){
		window.alert('留言内容不得多于240个汉字');
		return;
	}
	var form=document.getElementById("form");
	form.submit();
}
</script>
                        <input name="action" type="hidden" id="action" value="add" />
                      </div></td>
                  </tr>
                </table>
                <input type="hidden" name="action" id="action" value="add" />
              </form>
            </div>
            <div class="message_block">
            <s:iterator value="list" id="entity" status="ab">
              <div class="message_title">
                <h2 style="margin:0 0;font-family:New Timer;">IP：<s:property value="ip"/></h2>
                <span>留言时间：${entity.addTime}　　　${ab.count}#</span></div>
              <p><s:property value="detail"/></p>
              <s:if test="%{#entity.reply!=null&&#entity.reply!=''}">
              <div>
              <div class="message_replay"><strong>回复：</strong><s:property value="reply"/></div>
              <div class="message_info">回复时间：${entity.replyTime}</div>
              </div>
              </s:if>
              <br/>
            </s:iterator>
            </div>
            <div class="page_info">
             共<span><s:if test="%{pager.maxPages!=0}"><s:property value="pager.maxPages"/></s:if><s:if test="%{pager.maxPages==0}">1</s:if></span>页&nbsp;&nbsp;&nbsp;&nbsp;页次：<s:property value="pager.currentPage" />/<s:if test="%{pager.maxPages!=0}"><s:property value="pager.maxPages"/></s:if><s:if test="%{pager.maxPages==0}">1</s:if>
            <span>　　　　　　　　　　　　　</span>
     <a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{column}"/><s:param name="method" value="%{'first'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">首页</span></a>
     <a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{column}"/><s:param name="method" value="%{'back'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">上一页</span></a>
     <span style="background:#EBEBEA;">&nbsp;<s:property value="pager.currentPage" />&nbsp;</span>
     <a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{column}"/><s:param name="method" value="%{'next'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">下一页</span></a>
     <form action="<%=basePath%>message/mesAct!fsearch.action?column=${column}" method="post" id="gotoform" style="margin-bottom: 0;float:right;">
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
	 <a href="<s:url value="/message/mesAct!fsearch.action"><s:param name="column" value="%{column}"/><s:param name="method" value="%{'last'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>"><span style="background:#DCD9D4;">尾页</span></a>
          </div>
        </div>
      </div>
      <div class="clear"></div>
    </div>
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
