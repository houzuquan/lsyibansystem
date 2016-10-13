function $ajax(data, cBack) {
	var XMLHttpReq = null;
	try {
		XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP"); //IE高版本创建XMLHTTP
	} catch(E) {
		try {
			XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP"); //IE低版本创建XMLHTTP
		} catch(E) {
			XMLHttpReq = new XMLHttpRequest(); //兼容非IE浏览器，直接创建XMLHTTP对象
		}
	}
	var url, method, jsonP, callBack, postData, dataType, errorBack;
	url = method = jsonP = callBack = postData = dataType = errorBack = "";
	if(typeof data == "string") { //直接传入地址
		url = data;
		method = "GET";
		callBack = cBack;
	} else {
		url = data.url;
		method = data.method == "" || data.method == undefined || data.method == "undefined" ? "get" : data.method;
		jsonP = data.jsonp;
		postData = data.data;
		callBack = data.callBack;
		dataType = data.dataType;
		errorBack = data.error;
		if(jsonP != "" && jsonP != undefined && jsonP != "undefined") {
			if(url.indexOf("?") >= 0) {
				url += "&jsonp=" + jsonp;
			} else {
				url += "?jsonp=" + jsonP;
			}
		}
	}
	XMLHttpReq.open(method, url, true);
	XMLHttpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	XMLHttpReq.setRequestHeader("x-requested-with", "XMLHttpRequest");
	XMLHttpReq.onreadystatechange = function() {
		if(XMLHttpReq.readyState == 4) {
			if(XMLHttpReq.status == 200) {
				var text = XMLHttpReq.responseText;
				/**
				 *实现回调
				 */
				try{
					if(jsonP == "" || jsonP == undefined || jsonP == "undefined") {
						if(dataType == "json") {
							text = eval("("+text+")");
						}
						if(typeof callBack == "function"){
							callBack(text);
						}else{
							console.log("回调函数错误！");
						}
						
					} else { //jsonP
						eval(text);
					}
				}catch(E){
					console.log(E);
					errorBack("服务器返回信息处理失败");
				}
				//text = window.decodeURI(text);
			} else {
				errorBack(XMLHttpReq.status);
			}
		}
	}
	XMLHttpReq.send(postData);
}
/*
$ajax({
	url: "a.html",
	data: "",
	method: "get",
	dataType: "json",
	jsonp: "send",
	callBack: function(data) {
		alert(data);
	},
	error: function(status) {
		//alert(status);
	}
});
*/