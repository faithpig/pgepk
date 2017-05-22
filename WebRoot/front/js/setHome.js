	function addBookmark(title,url) {   
		if (window.sidebar) {   
		window.sidebar.addPanel(title, url,"");   
		} else if( document.all ) {   
		window.external.AddFavorite( url, title);   
		} else if( window.opera && window.print ) {   
		return true;   
		}   
		}   
	function setHome(url)   
		{   
		if (document.all){   
		document.body.style.behavior='url(#default#homepage)';   
		document.body.setHomePage(url);   
		}else if (window.sidebar){   
		if(window.netscape){   
		try{   
		netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");   
		}catch (e){   
		alert( "该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true" );   
		}   
		}   
		if(window.confirm("你确定要设置"+url+"为首页吗？")==1){   
		var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);   
		prefs.setCharPref('browser.startup.homepage',url);   
		}   
		}   
		}
	
	function add_favorite(a, title, url) {
		url = url || a.href;
		title = title || a.title;
		try{ // IE
		window.external.addFavorite(url, title);
		} catch(e) {
		try{ // Firefox
		window.sidebar.addPanel(title, url, "");
		} catch(e) {
		if (/Opera/.test(window.navigator.userAgent)) { // Opera
		a.rel = "sidebar";
		a.href = url;
		return true;
		}
		alert('加入收藏失败，请使用 Ctrl+D 进行添加');
		}
		}
		return false;
		}
	
		function set_homepage(a, url) {
		var tip = '您的浏览器不支持此操作\n请使用浏览器的“选项”或“设置”等功能设置首页';
		if (/360se/i.test(window.navigator.userAgent)) {
		alert(tip);
		return false;
		}
		url = url || a.href;
		try {
		a.style.behavior = 'url(#default#homepage)';
		a.setHomePage(url);
		} catch(e) {
		alert(tip);
		}
		return false;
		}