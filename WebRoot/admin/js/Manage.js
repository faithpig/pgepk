function addWindow(name, target, width, height) {
	$.XYTipsWindow({
		___title : name,
		___content : "iframe:" + target,
		___width : width,
		___height : height,
		___showbg : true,
		___drag : "___boxTitle"
	});
}
function changeWindow(name, target, width, height) {
	$.XYTipsWindow({
		___title : name,
		___content : "iframe:" + target,
		___width : width,
		___height : height,
		___showbg : true,
		___drag : "___boxTitle"
	});
}
function changeImg() {
	document.getElementById("checkcode").src = "admin/code.jsp";
}

function manage(op, method, id) {
	var xmlHttp = false;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (error1) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (error2) {
				xmlHttp = false;
			}
		}
	}
	if (method == "delete") {
		if (confirm("确认删除")) {
			xmlHttp.open("POST", op + "_delete.action?gid=" + id, false);
			xmlHttp.send();
			if (xmlHttp.responseText.length < 5) {
				alert("操作失败,删除人不能包含自己或已不存在！");
				return;
			}
		}else{
			return ;
		}
		xmlHttp.open("POST", op + "_index.action?gid=" + id, false);
		xmlHttp.send();
	} else if (method == "deleteChecked") {
		if (confirm("确认删除")) {
			var inputs = document.getElementsByName("checkbox");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].checked) {
					xmlHttp.open("POST", op + "_delete.action?gid="
							+ inputs[i].value, false);
					xmlHttp.send();
					if (xmlHttp.responseText.length < 5) {
						alert("操作失败,删除人不能包含自己或已不存在！");
						return ;
					}
				}
			}
		}else{
			return ;
		}		
		xmlHttp.open("POST", op + "_index.action?gid=" + id, false);
		xmlHttp.send();

	} else if (method == "verify") {
		xmlHttp.open("POST", op + "?method=" + method, false);
		xmlHttp.send();
	} else if (method == "notVerify") {
		xmlHttp.open("POST", op + "?method=" + method, false);
		xmlHttp.send();
	} else {
		xmlHttp.open("POST", op + "?method=" + method, false);
		xmlHttp.send();
	}
	document.getElementById("main").innerHTML = xmlHttp.responseText;
}

function checkbox(method) {
	if (method == "checkbox") {
		var _checkbox = document.getElementsByName("_checkbox");

		if (_checkbox[0].checked) {
			var inputs = document.getElementsByName("checkbox");
			for ( var i = 0; i < inputs.length; i++)
				inputs[i].checked = true;
		} else {
			var inputs = document.getElementsByName("checkbox");
			for ( var i = 0; i < inputs.length; i++)
				inputs[i].checked = false;
		}
	} else if (method == "all") {
		var inputs = document.getElementsByName("checkbox");
		for ( var i = 0; i < inputs.length; i++) {
			inputs[i].checked = true;
		}

	} else if (method = "inverse") {
		var inputs = document.getElementsByName("checkbox");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].checked) {
				inputs[i].checked = false;
			} else {
				inputs[i].checked = true;
			}
		}

	}
}

function verify(op, page) {
	if (confirm("确认审核？")) {
		var xmlHttp = false;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (error1) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (error2) {
					xmlHttp = false;
				}
			}
		}
		xmlHttp.open("POST", op, false);
		xmlHttp.send();
		alert(xmlHttp.responseText);
		xmlHttp.open("POST", page, false);
		xmlHttp.send();
		document.getElementById("main").innerHTML = xmlHttp.responseText;
	}
}

function charts(op) {
	document.getElementById("chartsFrame").src = op;
}
function chartsDay(op) {
	var day = document.getElementById("statDate").value;
	if (day.length < 1) {
		var myDate = new Date();
		day = myDate.getFullYear() + "-" + myDate.getMonth() + "-"
				+ myDate.getDate();
	}
	document.getElementById("chartsFrame").src = op + "?ViewDay=" + day;
}

function checkEPNews() {
	var s1 = document.getElementsByName("title");
	var s2 = document.getElementsByName("detail");
}

function goPage(maxPage) {
	var m = document.getElementById("gotopage").value;
	if (!isNaN(m) && m > 0 && m <= maxPage) {
		document.getElementById("gotoform").submit();
	}
}