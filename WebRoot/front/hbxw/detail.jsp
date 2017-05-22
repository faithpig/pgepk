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
    
    <title>环保新闻</title>
<link rel="stylesheet" type="text/css" href="front/css/css.css"/>
<script src="front/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="front/js/setHome.js" type="text/javascript"></script>
<script src="front/js/fontSize.js" type="text/javascript"></script>
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
var enterTime;
var closeTime;
var lookingTime;
var aid;
var type="环保新闻";
function getEnterTime(){
	enterTime=new Date();
}
var xmlHttp;
function createXMLHttpRequest(){
	if(window.ActiveXObject){
	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if(window.XMLHttpRequest){
	xmlHttp = new XMLHttpRequest();
	}
}
function getLookingTime(){
	createXMLHttpRequest();
	closeTime=new Date();
	lookingTime=closeTime.getTime()-enterTime.getTime();
	aid='${entity.id}';
	enterTime=enterTime.getTime();
	closeTime=closeTime.getTime();
	xmlHttp.open("POST","<%=basePath%>analysis/anaAct!addArtVis.action",true);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send("totalTime="+lookingTime+"&type="+type+"&aid="+aid+"&enterTime="+enterTime+"&closeTime="+closeTime);
}
</script>
</head>
  
<body onload="getEnterTime()" onunload="getLookingTime()">
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
                            <div class="fl xw">环保新闻分类</div>
                            <div class="fr"></div>
                            <div class="clear"></div>
                        </div>
                        <div class="hx"></div>
                        <ul class="nynews3">
                            <li><a href="<%=basePath%>news/epNewAct!fsearch.action?column=会议动态&pager.currentPage=0">会议动态</a></li>
                            <li><a href="<%=basePath%>news/epNewAct!fsearch.action?column=宣传活动&pager.currentPage=0">宣传活动</a></li>
                            <li><a href="<%=basePath%>news/epNewAct!fsearch.action?column=问答互动&pager.currentPage=0">问答互动</a></li>
                            <li><a href="<%=basePath%>news/epNewAct!fsearch.action?column=开放日活动&pager.currentPage=0">开放日活动</a></li>
                        </ul>
                    </div>
                    <div class="zs_footer"></div>
            </div>
            	<div class="fr" style="width:744px;">
                	<div class="lxwm" style=" width:744px; height:40px;">
                        <div class="fl lxwm_z">环保新闻</div>
                        <div class="fr">您现在的位置：<a href="<%=basePath%>index/homAct!home.action">首页</a> > <a href="<%=basePath%>news/epNewAct!list.action">环保新闻</a> > ${entity.column}</div>
                		<div class="clear"></div>
            		</div>
                    <div class="hb">
            			<div class="hb_wz">${entity.title}</div>
                        <div class="hb_fb">发布于：${entity.addTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传人：${entity.gname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：${entity.sname }</div>
                        <div class="hb_tw" id="entityDetail">${entity.detail}</div>
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
