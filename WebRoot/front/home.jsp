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
    
    <title>电网环保知识科普网站</title>
    
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
	$(".hide1").ellipsis(23);
	$(".hide2").ellipsis(17);
	$(".hide3").ellipsis(10);
	$(".hide4").ellipsis(14);
});
</script>

<style type="text/css">
img{
border:0;
}
*{margin:0;padding:0;list-style-type:none;}
/*图片轮换*/
#slideBox{width:377px;height:199px;overflow:hidden;position:relative;margin:20px auto;}
#slideBox ul#show_pic{margin:0;padding:0;list-style:none;height:300px;width:4750px;position:absolute;}
#slideBox ul#show_pic li{float:left;margin:0;padding:0;height:285px;}
#slideBox ul#show_pic li img{display:block;padding:0 0;}
#iconBall{position:absolute;bottom:0;right:0;}
#iconBall li{float:left;color:#7a7a7a;width:32px;height:28px;line-height:28px;cursor:pointer;text-align:center;font-size:14px;font-weight:bold;padding-top:4px;}
#iconBall li.active{background:url(images/iconbg.png) no-repeat;color:#fff;}
#slideText {width:475px;height:28px;background:rgba(0,0,0,0.7);color:#fff;position:absolute;left:0px;bottom:0px;*background:transparent;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#b2000000,endColorstr=#b2000000);}
#textBall{position:absolute;left:10px;bottom:3px;}
#textBall li{float:left;cursor:pointer;display:none;color:#fff;font-size:14px;}
#textBall li.active{display:block;}
#textBall li a {text-decoration:none;color:#fff;}
</style>
<script type="text/javascript">
var enterTime;
var xmlHttp;
function createXMLHttpRequest(){
	if(window.ActiveXObject){
	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if(window.XMLHttpRequest){
	xmlHttp = new XMLHttpRequest();
	}
}
function postTime(){
	enterTime=new Date();
	createXMLHttpRequest();
	enterTime=enterTime.getTime();
	xmlHttp.open("POST","<%=basePath%>analysis/anaAct!addHomVis.action",true);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send("enterTime="+enterTime);
}
</script>
</head>
  
<body onload="postTime()">
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
        	<div class="fl" style=" width:426px;">
            	<div class="xw_top"></div>
                <div class="xw_zj">
                	<div>
                    	<div class="fl xw">环保新闻</div>
                        <div class="fr"><input class="xw_gd" name="" type="button" onclick="window.location.href='<%=path %>/news/epNewAct!list.action'"/></div>
                        <div class="clear"></div>
                    </div>
<div class="xw_tp">
                    
                    <!--头部结束，slidebox开始 -->
	<div id="slideBox">
		<%if(ActionContext.getContext().get("picSrcStr")!=null){
			String picSrcStr[][]=(String[][])ActionContext.getContext().get("picSrcStr");		
		%>
		<ul id="show_pic" style="left:0px">
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[0][2]%>" target="_blank"><img  alt=""  title="" height="300" width="475" src="<%=basePath %>file/picture/<%=picSrcStr[0][1]%>" /></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[1][2]%>" target="_blank"><img  alt=""  title="" height="300" width="475" src="<%=basePath %>file/picture/<%=picSrcStr[1][1]%>" /></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[2][2]%>" target="_blank"><img  alt=""  title="" height="300" width="475" src="<%=basePath %>file/picture/<%=picSrcStr[2][1]%>" /></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[3][2]%>" target="_blank"><img  alt=""  title="" height="300" width="475" src="<%=basePath %>file/picture/<%=picSrcStr[3][1]%>" /></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[4][2]%>" target="_blank"><img  alt=""  title="" height="300" width="475" src="<%=basePath %>file/picture/<%=picSrcStr[4][1]%>" /></a></li>
		</ul>
		<div id="slideText"></div>
		<ul id="iconBall">
			<li class="active">1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
			<li>5</li>
		</ul>
		<ul id="textBall">
			<li class="active"><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[0][2]%>" class="hide4"><%=picSrcStr[0][0]%></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[1][2]%>" class="hide4"><%=picSrcStr[1][0]%></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[2][2]%>" class="hide4"><%=picSrcStr[2][0]%></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[3][2]%>" class="hide4"><%=picSrcStr[3][0]%></a></li>
			<li><a href="<%=basePath%>news/epNewAct!detail.action?id=<%=picSrcStr[4][2]%>" class="hide4"><%=picSrcStr[4][0]%></a></li>
		</ul>
		<%}else{}%>
	</div>

<script type="text/javascript">
var glide =new function(){
	function $id(id){return document.getElementById(id);};
	this.layerGlide=function(auto,oEventCont,oTxtCont,oSlider,sSingleSize,second,fSpeed,point){
		var oSubLi = $id(oEventCont).getElementsByTagName('li');
		var oTxtLi = $id(oTxtCont).getElementsByTagName('li');
		var interval,timeout,oslideRange;
		var time=1; 
		var speed = fSpeed 
		var sum = oSubLi.length;
		var a=0;
		var delay=second * 1000; 
		var setValLeft=function(s){
			return function(){
				oslideRange = Math.abs(parseInt($id(oSlider).style[point]));	
				$id(oSlider).style[point] =-Math.floor(oslideRange+(parseInt(s*sSingleSize) - oslideRange)*speed) +'px';		
				if(oslideRange==[(sSingleSize * s)]){
					clearInterval(interval);
					a=s;
				}
			}
		};
		var setValRight=function(s){
			return function(){	 	
				oslideRange = Math.abs(parseInt($id(oSlider).style[point]));							
				$id(oSlider).style[point] =-Math.ceil(oslideRange+(parseInt(s*sSingleSize) - oslideRange)*speed) +'px';
				if(oslideRange==[(sSingleSize * s)]){
					clearInterval(interval);
					a=s;
				}
			}
		}
		
		function autoGlide(){
			for(var c=0;c<sum;c++){oSubLi[c].className='';oTxtLi[c].className='';};
			clearTimeout(interval);
			if(a==(parseInt(sum)-1)){
				for(var c=0;c<sum;c++){oSubLi[c].className='';oTxtLi[c].className='';};
				a=0;
				oSubLi[a].className="active";
				oTxtLi[a].className = "active";
				interval = setInterval(setValLeft(a),time);
				timeout = setTimeout(autoGlide,delay);
			}else{
				a++;
				oSubLi[a].className="active";
				oTxtLi[a].className = "active";
				interval = setInterval(setValRight(a),time);	
				timeout = setTimeout(autoGlide,delay);
			}
		}
	
		if(auto){timeout = setTimeout(autoGlide,delay);};
		for(var i=0;i<sum;i++){	
			oSubLi[i].onmouseover = (function(i){
				return function(){
					for(var c=0;c<sum;c++){oSubLi[c].className='';oTxtLi[c].className='';};
					clearTimeout(timeout);
					clearInterval(interval);
					oSubLi[i].className = "active";
					oTxtLi[i].className = "active";
					if(Math.abs(parseInt($id(oSlider).style[point]))>[(sSingleSize * i)]){
						interval = setInterval(setValLeft(i),time);
						this.onmouseout=function(){if(auto){timeout = setTimeout(autoGlide,delay);};};
					}else if(Math.abs(parseInt($id(oSlider).style[point]))<[(sSingleSize * i)]){
							interval = setInterval(setValRight(i),time);
						this.onmouseout=function(){if(auto){timeout = setTimeout(autoGlide,delay);};};
					}
				}
			})(i)			
		}
	}
}

//调用语句
glide.layerGlide(
	true,         //设置是否自动滚动
	'iconBall',   //对应索引按钮
	'textBall',   //标题内容文本
	'show_pic',   //焦点图片容器
	475,          //设置滚动图片位移像素
	3,			  //设置滚动时间2秒 
	1,          //设置过渡滚动速度
	'left'		  //设置滚动方向“向左”
);
</script>
<!--slideBox end-->

					</div>
                    <ul class="nynews">
                    	<s:iterator value="epNewList" id="entity">
                    	<s:if test='%{(#entity.sname!="")&&(#entity.sname!=NULL)}'>
                    	<li><a href="<%=basePath%>news/epNewAct!detail.action?id=${entity.id}" class="hide1"><s:property value="title"/></a><span>${entity.addTime}</span></li>
                        </s:if>
                        </s:iterator>
                    </ul>
                </div>
                <div class="xw_footer"></div>
            </div>
            <div class="fl" style=" padding-left:12px; width:300px;">
            	<div class="fg_top"></div>
                <div class="fg_zj">
                	<div>
                    	<div class="fl xw">环保政策法规</div>
                        <div class="fr"><input class="xw_gd" name="" type="button" onclick="window.location.href='<%=path %>/policy/epPolAct!list.action'"/></div>
                        <div class="clear"></div>
                    </div>
                    <div class="hx"></div>
                    <ul class="nynews1">
                    	<s:iterator value="epPolList" id="entity">
                    	<li><a href="<%=basePath%>policy/epPolAct!detail.action?id=${entity.id}" class="hide2"><s:property value="title"/></a></li>
 						</s:iterator>                   
                    </ul>
                </div>
                <div class="fg_footer"></div>
            </div>
            <div class="fr" style=" padding-left:12px; width:249px;">
            	<div class="zs_top"></div>
                <div class="zs_zj">
                	<div>
                    	<div class="fl xw">环保专业知识</div>
                        <div class="fr"><input class="xw_gd" name="" type="button" onclick="window.location.href='<%=path %>/term/epTerAct!list.action'" /></div>
                        <div class="clear"></div>
                    </div>
                    <div class="hx"></div>
                    <ul class="nynews2">
                    	<s:iterator value="epTerList" id="entity">
                    	<li><a href="<%=basePath%>term/epTerAct!detail.action?id=${entity.id}" class="hide3"><s:property value="title"/></a></li>
                    	</s:iterator>
                    </ul>
                </div>
                <div class="zs_footer"></div>
            </div>
            <div class="clear"></div>
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
        <div style=" margin:0px 0 20px 0; text-align:center;">State Grid Corporation of China(<a href="<%=path%>/admin.jsp" style="color:#000">SGCC</a>)  Copyright ©All rights reserved</div>
    </div>
    </div>
</body>

</html>
