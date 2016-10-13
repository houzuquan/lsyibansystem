<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8" />
		<meta name="Keywords" content=""/>
		<meta name="Description" content=""/>
		<meta http-equiv="Pragma" content="no-cache" />
		<title>后台登陆程序</title>
		<link rel="stylesheet" type="text/css" href="Public/css/login.css"/>
		<script src="Public/js/login.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8">
		<%
			if(request.getAttribute("isLogin") != null && (boolean)request.getAttribute("isLogin") == false){
				out.print("alert(\""+(String)request.getAttribute("error")+"\");");
			}
			try{
				window.parent.location.href='login.jsp';
			}catch(e){
				
			}
		%>
		</script>
	</head>
	<body>
		<form action="Login" method="post" id="loginBox" onsubmit="checkFormSubmit(this);return false;">
			<h1>用户登陆</h1>
			<input type="text" name="stuId" id="username" value="" placeholder="用户名/邮箱"/>
			<input type="password" name="pass" id="password" value="" placeholder="密码"/>
			<!--
            	作者：1184511588@qq.com
            	时间：2016-10-08
            	描述：暂时不用验证码
			<input type="text" name="yzmText" id="yzmText" value="" placeholder="验证码"/>
            	<img src="Verify.php" id="yzm" alt="点击刷新验证码" title="点击刷新验证码"/>
            -->
			<input type="submit" value="登陆"/>
			<p class="fen"><span></span><span>使用第三方账号登陆</span><span></span></p>
			<!--<div class="loginMore">
				<img src="Public/img/QQ.png"/>
				<img src="Public/img/weixin.png"/>
				<img src="Public/img/weibo.png"/>
			</div>-->
		</form>
		<script type="text/javascript">
//			var yzmUrl=document.getElementById("yzm").getAttribute('src');
//			var time = new Date();
//			document.getElementById("yzm").onclick=function(){
//				var url = yzmUrl + "?r=" + time.getTime();
//				this.setAttribute("src",url);
//			}
			function checkFormSubmit(ff){
				var username = document.getElementById("username").value;
				var password = document.getElementById("password").value;
//				var yzm = document.getElementById("yzm").value;
				if(username == "" || password == ""){
					alert("请检查是否输入完整！");
					return false;
				}
				
				ff.submit();
				return true;
			}
		</script>
	</body>
</html>
