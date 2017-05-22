<%@ page language="java"
	import="java.util.*,com.opensymphony.xwork2.ActionContext,com.whucs.pgepk.hibernate.model.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	int visTimes[] = new int[24];
	visTimes = (int[]) ActionContext.getContext().get("visTime");

	String epNewsId[] = null;
	int epNewsCount[] = null;
	int epNewsSize = 0;
	try {
		epNewsSize = Integer.parseInt(ActionContext.getContext()
				.get("epNewsSize").toString());
		epNewsCount = (int[]) ActionContext.getContext().get(
				"epNewsCount");
		epNewsId = (String[]) ActionContext.getContext().get("epNewsId");
	} catch (Exception e) {
	}
	int epNewsMax = 0;
	for (int i = 0; i < epNewsSize; i++) {
		if (epNewsMax < epNewsCount[i])
			epNewsMax = epNewsCount[i];
	}
	epNewsMax = (epNewsMax / 10 + 1) * 10;

	int epPolId[] = null;
	int epPolCount[] = null;
	int epPolSize = 0;
	try {
		epPolSize = Integer.parseInt(ActionContext.getContext()
				.get("epPolSize").toString());
		epPolCount = (int[]) ActionContext.getContext().get(
				"epPolCount");
		epPolId = (int[]) ActionContext.getContext().get("epPolId");
	} catch (Exception e) {

	}
	int epPolMax = 0;
	for (int i = 0; i < epPolSize; i++) {
		if (epPolMax < epPolCount[i])
			epPolMax = epPolCount[i];
	}
	epPolMax = (epPolMax / 10 + 1) * 10;

	int epTermId[] = null;
	int epTermCount[] = null;
	int epTermSize = 0;
	try {
		epTermSize = Integer.parseInt(ActionContext.getContext()
				.get("epTermSize").toString());
		epTermCount = (int[]) ActionContext.getContext().get(
				"epTermCount");
		epTermId = (int[]) ActionContext.getContext().get("epTermId");
	} catch (Exception e) {
	}
	int epTermMax = 0;
	for (int i = 0; i < epTermSize; i++) {
		if (epTermMax < epTermCount[i])
			epTermMax = epTermCount[i];
	}
	epTermMax = (epTermMax / 10 + 1) * 10;

	String color[] = {"27A9E3", "28B779", "FFB748", "DA542E", "2255A4",
			"F74D4D", "FF0000", "00FF00", "0000FF", "888888", "27A9E3",
			"28B779"};
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="admin/js/ichart.1.2.min.js"></script>
<script type="text/javascript" src="admin/js/jquery.min.js"></script>
<script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
<script type="text/javascript" src="admin/js/Manage.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="admin/css/box_style.css">
<link rel="stylesheet" type="text/css" href="admin/css/common.css">

</head>

<body>
	<center>
		<%
			if (epNewsSize != 0) {
		%>
		<table>
			<tr>
				<th colspan="2" style="font-size: 20px;">环保新闻浏览量统计图</th>
			</tr>
			<tr>
				<td><script type="text/javascript">
					//定义数据
					$(function() {
						var data = [
				<%for (int i = 0; i < epNewsSize; i++) {
					out.print("{name:'" + epNewsId[i] + "',value:"
							+ epNewsCount[i] + ",color:" + "'#" + color[i % 12]
							+ "'}");
					if (i != epNewsSize - 1) {
						out.print(",");
					}
				}%>
					];
						new iChart.Column2D({
							render : 'canvasDivHbxw', //渲染的Dom目标,canvasDiv为Dom的ID
							data : data, //绑定数据
							title : {
								text : '',
								font : '微软雅黑',
								fontsize : 18,
								color : "#666666"
							},
							border : {
								width : 0
							},
							background_color : "#F6F6F6",
							width : 600, //设置宽度，默认单位为px
							height : 400, //设置高度，默认单位为px
							shadow : true, //激活阴影
							shadow_color : '#c7c7c7', //设置阴影颜色
							footnote : '',
							coordinate : {//配置自定义坐标轴
								scale : [ {//配置自定义值轴
									position : 'left', //配置左值轴
									start_scale : 0, //设置开始刻度为0
									end_scale :
				<%=epNewsMax%>
					,
									scale_space :
				<%=epNewsMax / 10%>
					,
									listeners : {//配置事件
										parseText : function(t, x, y) {//设置解析值轴文本
											return {
												text : t + " "
											};
										}
									}
								} ]
							}
						}).draw();
					});
				</script>
					<div id='canvasDivHbxw'></div></td>
				<td>
					<table>
						<tr>
							<th>环保新闻ID</th>
							<th>当日浏览量</th>
							<th>点击查看</th>
						</tr>
						<%
							for (int i = 0; i < epNewsSize; i++) {
									out.print("<tr><td align=\"center\">" + epNewsId[i]
											+ "</td><td align=\"center\">" + epNewsCount[i]
											+ "</td><td align=\"center\">" + "<a href=\""
											+ "news/epNewAct!detail.action?id=" + epNewsId[i]
											+ "\" target=\"_blank\">点击查看</a>" + "</td></tr>");
								}
						%>
					</table>
				</td>
			</tr>
		</table>
		<%
			}
		%>
		<%
			if (epPolSize != 0) {
		%>
		<table>
			<tr>
				<th colspan="2" style="font-size: 20px;">环保政策法规浏览量统计图</th>
			</tr>
			<tr>
				<td><script type="text/javascript">
					//定义数据
					$(function() {
						var data = [
				<%for (int i = 0; i < epPolSize; i++) {
					out.print("{name:'" + epPolId[i] + "',value:"
							+ epPolCount[i] + ",color:" + "'#" + color[i % 12]
							+ "'}");
					if (i != epPolSize - 1) {
						out.print(",");
					}
				}%>
					];
						new iChart.Column2D({
							render : 'canvasDivHbzcfg', //渲染的Dom目标,canvasDiv为Dom的ID
							data : data, //绑定数据
							title : {
								text : '',
								font : '微软雅黑',
								fontsize : 18,
								color : "#666666"
							},
							border : {
								width : 0
							},
							background_color : "#F6F6F6",
							width : 600, //设置宽度，默认单位为px
							height : 400, //设置高度，默认单位为px
							shadow : true, //激活阴影
							shadow_color : '#c7c7c7', //设置阴影颜色
							footnote : '',
							coordinate : {//配置自定义坐标轴
								scale : [ {//配置自定义值轴
									position : 'left', //配置左值轴
									start_scale : 0, //设置开始刻度为0
									end_scale :
				<%=epPolMax%>
					,
									scale_space :
				<%=epPolMax / 10%>
					,
									listeners : {//配置事件
										parseText : function(t, x, y) {//设置解析值轴文本
											return {
												text : t + " "
											};
										}
									}
								} ]
							}
						}).draw();
					});
				</script>
					<div id='canvasDivHbzcfg'></div></td>
				<td>
					<table>
						<tr>
							<th>政策法规ID</th>
							<th>当日浏览量</th>
							<th>点击查看</th>
						</tr>
						<%
							for (int i = 0; i < epPolSize; i++) {
									out.print("<tr><td align=\"center\">" + epPolId[i]
											+ "</td><td align=\"center\">" + epPolCount[i]
											+ "</td><td align=\"center\">" + "<a href=\""
											+ "policy/epPolAct!detail.action?id=" + epPolId[i]
											+ "\" target=\"_blank\">点击查看</a>" + "</td></tr>");
								}
						%>
					</table>
				</td>
			</tr>
		</table>
		<%
			}
		%>
		<%
			if (epTermSize != 0) {
		%>
		<table>
			<tr>
				<th colspan="2" style="font-size: 20px;">环保专业知识浏览量统计图</th>
			</tr>
			<tr>
				<td><script type="text/javascript">
					//定义数据
					$(function() {
						var data = [
				<%for (int i = 0; i < epTermSize; i++) {
					out.print("{name:'" + epTermId[i] + "',value:"
							+ epTermCount[i] + ",color:" + "'#" + color[i % 12]
							+ "'}");
					if (i != epTermSize - 1) {
						out.print(",");
					}
				}%>
					];
						new iChart.Column2D({
							render : 'canvasDivHbzyzs', //渲染的Dom目标,canvasDiv为Dom的ID
							data : data, //绑定数据
							title : {
								text : '',
								font : '微软雅黑',
								fontsize : 18,
								color : "#666666"
							},
							border : {
								width : 0
							},
							background_color : "#F6F6F6",
							width : 600, //设置宽度，默认单位为px
							height : 400, //设置高度，默认单位为px
							shadow : true, //激活阴影
							shadow_color : '#c7c7c7', //设置阴影颜色
							footnote : '',
							coordinate : {//配置自定义坐标轴
								scale : [ {//配置自定义值轴
									position : 'left', //配置左值轴
									start_scale : 0, //设置开始刻度为0
									end_scale :
				<%=epTermMax%>
					,
									scale_space :
				<%=epTermMax / 10%>
					,
									listeners : {//配置事件
										parseText : function(t, x, y) {//设置解析值轴文本
											return {
												text : t + " "
											};
										}
									}
								} ]
							}
						}).draw();
					});
				</script>
					<div id='canvasDivHbzyzs'></div></td>

				<td>
					<table>
						<tr>
							<th>环保知识ID</th>
							<th>当日浏览量</th>
							<th>点击查看</th>
						</tr>
						<%
							for (int i = 0; i < epTermSize; i++) {
									out.print("<tr><td align=\"center\">" + epTermId[i]
											+ "</td><td align=\"center\">" + epTermCount[i]
											+ "</td><td align=\"center\">" + "<a href=\""
											+ "term/epTerAct!detail.action?id=" + epTermId[i]
											+ "\" target=\"_blank\">点击查看</a>" + "</td></tr>");
								}
						%>
					</table>
				</td>
			</tr>


		</table>
		<%
			}
		%>
		<table>
			<tr>
				<th style="font-size: 20px;">网站入站浏览时间段</th>
			</tr>
			<tr>
				<td valign="top"><script type="text/javascript">
					$(function() {
						var data = [ {
							name : '时刻',
							value : [
				<%for (int i = 0; i < 24; i++) {
				out.print("" + visTimes[i] + "");
				if (i != 23) {
					out.print(",");
				}
			}%>
					],
							color : '#33afe6',
							line_width : 3
						} ];
						var chart = new iChart.LineBasic2D({
							render : 'canvasDivVisTimeAll',
							data : data,
							title : '',
							width : 950,
							height : 280,
							coordinate : {
								background_color : '#f6f9fa'
							},
							sub_option : {
								hollow_inside : false,//设置一个点的亮色在外环的效果
								point_size : 16
							},
							labels : [ "0时", "1时", "2时", "3时", "4时", "5时",
									"6时", "7时", "8时", "9时", "10时", "11时",
									"12时", "13时", "14时", "15时", "16时", "17时",
									"18时", "19时", "20时", "21时", "22时", "23时", ]
						});
						chart.draw();
					});
				</script>
					<div id='canvasDivVisTimeAll'></div></td>
			</tr>
			<tr>
				<td valign="top" align="center">
					<table class="chartTable">
						<tr>
							<th>时间段</th>
							<th>浏览量</th>
							<th>时间段</th>
							<th>浏览量</th>
							<th>时间段</th>
							<th>浏览量</th>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">00:00-00:59</td>
							<td width="100px" align="center">${visTime[0]}</td>
							<td width="100px" align="center">01:00-01:59</td>
							<td width="100px" align="center">${visTime[1]}</td>
							<td width="100px" align="center">02:00-02:59</td>
							<td width="100px" align="center">${visTime[2]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">03:00-03:59</td>
							<td width="100px" align="center">${visTime[3]}</td>
							<td width="100px" align="center">04:00-04:59</td>
							<td width="100px" align="center">${visTime[4]}</td>
							<td width="100px" align="center">05:00-05:59</td>
							<td width="100px" align="center">${visTime[5]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">06:00-06:59</td>
							<td width="100px" align="center">${visTime[6]}</td>
							<td width="100px" align="center">07:00-07:59</td>
							<td width="100px" align="center">${visTime[7]}</td>
							<td width="100px" align="center">08:00-08:59</td>
							<td width="100px" align="center">${visTime[8]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">09:00-09:59</td>
							<td width="100px" align="center">${visTime[9]}</td>
							<td width="100px" align="center">10:00-10:59</td>
							<td width="100px" align="center">${visTime[10]}</td>
							<td width="100px" align="center">11:00-11:59</td>
							<td width="100px" align="center">${visTime[11]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">12:00-12:59</td>
							<td width="100px" align="center">${visTime[12]}</td>
							<td width="100px" align="center">13:00-13:59</td>
							<td width="100px" align="center">${visTime[13]}</td>
							<td width="100px" align="center">14:00-14:59</td>
							<td width="100px" align="center">${visTime[14]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">15:00-15:59</td>
							<td width="100px" align="center">${visTime[15]}</td>
							<td width="100px" align="center">16:00-16:59</td>
							<td width="100px" align="center">${visTime[16]}</td>
							<td width="100px" align="center">17:00-17:59</td>
							<td width="100px" align="center">${visTime[17]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">18:00-18:59</td>
							<td width="100px" align="center">${visTime[18]}</td>
							<td width="100px" align="center">19:00-19:59</td>
							<td width="100px" align="center">${visTime[19]}</td>
							<td width="100px" align="center">20:00-20:59</td>
							<td width="100px" align="center">${visTime[20]}</td>
						</tr>
						<tr height="26px">
							<td width="100px" align="center">21:00-21:59</td>
							<td width="100px" align="center">${visTime[21]}</td>
							<td width="100px" align="center">22:00-22:59</td>
							<td width="100px" align="center">${visTime[22]}</td>
							<td width="100px" align="center">23:00-23:59</td>
							<td width="100px" align="center">${visTime[23]}</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>