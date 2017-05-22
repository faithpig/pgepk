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
    
    <title>网站地图</title>
    
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
        	<div class="lxwm" style=" width:1000px;margin-top:10px;">
                <div class="fl lxwm_z">网站地图</div>
                <div class="fr">您现在的位置：<a href="<%=basePath%>index/homAct!home.action">首页</a> > <a href="<%=basePath%>front/sitemap.jsp">网站地图</a></div>
                <div class="clear"></div>
            </div>
            <div class="wzdt">
                <div class="dt_bj"><span><a href="<%=basePath%>index/homAct!home.action">网站首页</a></span></div> 
                <div class="dt_wbj"><span><a href="<%=basePath%>news/epNewAct!list.action">环保新闻</a></span><a href="<%=basePath%>news/epNewAct!fsearch.action?column=会议动态">会议动态</a><a href="<%=basePath%>news/epNewAct!fsearch.action?column=宣传活动">宣传活动</a><a href="<%=basePath%>news/epNewAct!fsearch.action?column=问答互动">问答互动</a><a href="<%=basePath%>news/epNewAct!fsearch.action?column=开放日活动">开放日活动</a></div>
                <div class="dt_wbj"><span><a href="<%=basePath%>policy/epPolAct!list.action">环保政策法规</a></span><a href="<%=basePath%>policy/epPolAct!fsearch.action?column=法律法规">法律法规</a><a href="<%=basePath%>policy/epPolAct!fsearch.action?column=导则标准">导则标准</a><a href="<%=basePath%>policy/epPolAct!fsearch.action?column=政策解读">政策解读</a></div>         
                <div class="dt_bj"><span><a href="<%=basePath%>term/epTerAct!list.action">环保专业知识</a></span><a href="<%=basePath%>term/epTerAct!fsearch.action?column=基础知识">基础知识</a><a href="<%=basePath%>term/epTerAct!fsearch.action?column=技术问题解答">技术问题解答</a><a href="<%=basePath%>term/epTerAct!fsearch.action?column=技术措施">技术措施</a></div> 
                        
                <div class="dt_bj"><span><a href="<%=basePath%>contact/conAct!detail.action">联系我们</a></span></div> 
                <div class="dt_wbj"><span><a href="<%=basePath%>message/mesAct!list.action">留言板</a></span></div>
                <div class="dt_bj"><span><a href="<%=basePath%>link/linAct!list.action">友情链接</a></span></div>
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
