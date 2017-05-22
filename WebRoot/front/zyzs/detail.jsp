<%@page import="com.opensymphony.xwork2.ActionContext,com.whucs.pgepk.hibernate.model.EPTerm"%>
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
<script src="front/js/fontSize.js" type="text/javascript"></script>
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

<script type="text/javascript" language="javascript" src="">
setInterval(show1,5000);//5s显示一次下载进度
var bstartnextplay = false;
function show1()
{
	var pos = CoolPlayer.get_CurTaskProcess()/10;
	if(pos > 60)
	{
		StartNextDownFile();
	}
	var posv = "当前下载进度为:  "+ pos.toString(10) + "%";
	var mediaInfo1 = document.getElementById("mediaInfo");
	mediaInfo1.innerText = posv;
}
function PointPlay(playername)
{
	CoolPlayer.URL = playername;
	CoolPlayer.Play();
}
function Stop()
{
	CoolPlayer.Stop();
}
function Pause()
{
	CoolPlayer.Pause();
}
function Play()
{
	CoolPlayer.Play();
}
function GetS()
{
	var is = CoolPlayer.PlayState;
	alert(is);
}


function putview(ViewFrame)
{
	if(ViewFrame>4)
	{
		ViewFrame = 0;
	}
	
	CoolPlayer.ViewFrame = ViewFrame;	
}


function puttrack(v)
{
	CoolPlayer.SoundTrack = v;	
}

function putAdjustVideo(v)
{
	CoolPlayer.AdjustVideo(v);	
}

function putfull()
{
	var v = true;
	CoolPlayer.Full = v;
}

function FullScreen()
{
	var v = CoolPlayer.FullScreen();
}



function IsFullScreen()
{
	var is= CoolPlayer.IsFullScreen();
	alert(is);	
}

function IsPause()
{
	var is = CoolPlayer.IsPause();
	alert(is);	
}

function IsBuffing()
{
	var is = CoolPlayer.IsBuffing();
	alert(is);	
}

function IsPlaying()
{
	var is = CoolPlayer.IsPlaying();
	alert(is);
}

function GetVersion()
{
	var ver = CoolPlayer.GetVersion();
	alert(ver);
}

var mute = false;
function putmute()
{
	if(mute)
	{
		mute = false;
		CoolPlayer.Mute = mute;
	}
	else
	{
		mute = true;
		CoolPlayer.Mute = mute;
	}
	
}
function curpos()
{
	var pos = 0;
	pos  = CoolPlayer.Currentpos;
	alert(pos);
}
function setpos(pos)
{
	CoolPlayer.Currentpos = pos;  
}
function setvolue(pos)
{
	CoolPlayer.Volume = pos;
}
function OnLoad()
{
	var i = navigator.appName;
	var b = navigator.userAgent;
}
function get_MainInfo()
{
	var filename ;
	filename="this";
	filename = CoolPlayer.MainInfo;	
	alert(filename);
}
function get_TaskDown()
{
	var taskdown1 = CoolPlayer.get_CurTaskProcess();
	alert(taskdown1);
}

function StartNextDownFile()
{
	if(CoolPlayer.get_CurTaskProcess() > 600 && !bstartnextplay)
	{
		var url = "";
		var rv = CoolPlayer.StartNextDown(url);
		bstartnextplay = true;
	}
}

function AddURL()
{
	var rv = CoolPlayer.CoolAdUrl ="http://www.google.hk";
}

function SetShareFriendsURL()
{
	var localURL = document.URL;		//或者直接填写该站域名
	 CoolPlayer.ShareFriendsURL = localURL;
	 alert(localURL);
}

var bEnable = false;		//true 开启 | false 关闭

//设置某广告类型的广告是否开启
function EnableAD(nAdType)
{
	bEnable = !bEnable;

	CoolPlayer.EnableAD(nAdType, bEnable);
	alert(bEnable);
}

//设置某广告类型的广告地址
function SetAdURL(nAdType)
{
	var strURL ="http://www.google.hk";		//各种广告模式地址可以不一样
	CoolPlayer.SetAdURL(nAdType, strURL);
	alert(nAdType);
}

//设置某广告类型的广告地址
function SetAdURL_TextLink(nAdType)
{
	var strURL ="";		//替换为自定义的文字链广告文件，格式参考textLinkUserDemo.htm
	
	//文字链接配置文件的每条文字链的格式:[ad_text_s]自定义文字链1[ad_text_e][ad_link_s]http://www.sina.com/1.htm[ad_link_e]
	CoolPlayer.SetAdURL(nAdType, strURL);
}

//设置开始必看的广告时间
function SetStartPlayAdTime(nSecond)
{
	CoolPlayer.SetStartPlayAdTime(nSecond);
}

</script>
<script type="text/javascript">
var enterTime;
var closeTime;
var lookingTime;
var aid;
var type="环保专业知识";
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
  
<body onload="getEnterTime()" onunload="getLookingTime()" >
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
                        <div class="fr">您现在的位置：<a href="<%=basePath%>index/homAct!home.action">首页</a> > <a href="<%=basePath%>term/epTerAct!list.action">环保专业知识</a> > ${entity.column}</div>
                		<div class="clear"></div>
            		</div>
                    <div class="hb">
                    <%!String src;int len;%>
                    <%src=((EPTerm)ActionContext.getContext().get("entity")).getVideo();
                      if(src!=null){
                      	len=src.length();
                      }                   
                    %>
                    <%if(src==null||src.trim().equals("")||src.equals("0")){%>
            			<div class="hb_wz">${entity.title}</div>
                        <div class="hb_fb">发布于：${entity.addTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传人：${entity.gname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：${entity.sname }</div>
                        <div class="hb_tw" id="entityDetail">${entity.detail}</div>
               <%}else{
               src=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+src;
               %>
               <div class="hb_wz">${entity.title}</div>
               <div class="hb_fb">发布于：${entity.addTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传人：${entity.gname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人：${entity.sname }</div>
              <div class="hb_tw" id="entityDetail">${entity.detail}</div>
              <div class="hb_wz" style="margin: 40px 0 0 0;font: bold;font-family: 微软雅黑;">相关视频：</div>
              <div style="height:400px;width:684px;text-align: center;padding: 0 82px;">
              <div style="height:400px;text-align: center;border: solid 6px #eee;width: 500px;">
				<object classid='clsid:73BAB958-AC02-5108-B2B8-665834A9C63A' width='500' height='400' name='CoolPlayer' id='CoolPlayer' onError="if(window.confirm('请您先安装CoolPlayer软件,然后刷新本页才可以正常播放.')){window.open('http://www.mycoolplayer.com/download.html');}else{}">
				<param name='URL' value='<%=src%>'/>
				<param name='NextWebPage' value='http://www.xxx.com/nextVedioPage.htm'/>
				<param name='Autoplay' value='1'/>
				<param name='Showcontrol' value='1'/>
				<embed width="500px" height="400px" url='<%=src%>' nextwebpage='http://www.xxx.com/nextVedioPage.htm' autoplay='1' showcontrol='1' type='application/cool-plugin'></embed>
				</object>
			   </div>
			   </div>
				<br/>    
                 <%}%>
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
