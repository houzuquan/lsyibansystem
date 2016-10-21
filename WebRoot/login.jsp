<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8" />
		<meta name="Keywords" content=""/>
		<meta name="Description" content=""/>
		<meta http-equiv="Pragma" content="no-cache" />
		<title>易班日常管理系统</title>
		<link rel="stylesheet" type="text/css" href="Public/css/login.css"/>
		<link rel="stylesheet" type="text/css" href="Public/js/alert/easydialog.css"/>
		<script src="Public/js/alert/easydialog.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="Public/js/login.js" type="text/javascript" charset="utf-8"></script>
		<script src="Public/js/function.js" type="text/javascript" charset="utf-8"></script>
		<script src="Public/js/ajax.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8">
		<%
			if(request.getAttribute("isLogin") != null && (boolean)request.getAttribute("isLogin") == false){
				out.print("showMsg(\""+(String)request.getAttribute("error")+"\");");
			}
		%>
		try{
			if(window.parent != window){
				window.parent.location.href="login.jsp";
			}
		}catch(e){
			
		}
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
            	描述：暂时不用验证码-->
			<input type="text" name="yzmText" id="yzmText" value="" placeholder="验证码"/>
            	<img src="./Yzm" id="yzm" alt="点击刷新验证码" title="点击刷新验证码"/>
            
			<input type="submit" value="登陆"/>
			<p class="fen"><span></span><span>使用第三方账号登陆</span><span></span></p>
			<!--<div class="loginMore">
				<img src="Public/img/QQ.png"/>
				<img src="Public/img/weixin.png"/>
				<img src="Public/img/weibo.png"/>
			</div>-->
		</form>
		<script type="text/javascript">
			var yzmUrl=document.getElementById("yzm").getAttribute('src');
			document.getElementById("yzm").onclick=function(){
				var time = new Date();
				var url = yzmUrl + "?r=" + time.getTime();
				this.setAttribute("src",url);
			}
			function checkFormSubmit(ff){
				var username = document.getElementById("username").value;
				var password = document.getElementById("password").value;
				var yzmText = document.getElementById("yzmText").value;
				if(username == "" || password == "" || yzmText == ""){
					showMsg("请检查是否输入完整!");
					return false;
				}
				$ajax({
					url: "./Login",
					data: "stuId="+username+"&pass="+password+"&yzmText="+yzmText,
					method: "post",
					dataType: "json",
					callBack: function(data) {
						console.log(data);
						if(data.code){
							errorAction(data);
							document.getElementById("yzm").click();
							document.getElementById("yzmText").value='';
							return false;
						}
					},
					error: function(status) {
						console.log(status);
						showMsg("获取数据错误!");
					}
				});
//				ff.submit();
				return false;
			}
			
		</script>
	</body>
</html>
