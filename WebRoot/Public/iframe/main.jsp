<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/yibanUser" prefix="yb"  %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>主页面</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="../js/function.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/ajax.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
	<div class="card">
		<img src="img/qd.png" class="ban" />
		<ul>
			<li>打卡<span id="qdNum"><yb:getInfo action="getQdNum" /></span>次
			</li>
			<li>签退<span id="qtNum"><yb:getInfo action="getQtNum" /></span>次
			</li>
		</ul>
		<span class="more" id="moreBtn"> 查看打卡记录 </span>
	</div>
	<script type="text/javascript">
			window.onload = function() {
			}
			document.getElementById('moreBtn').onclick = function() {
				var Lnav = window.parent.document.getElementById('Lnav');
				var li = Lnav.getElementsByTagName('li');
				li[2].click();
			}
		</script>
</body>

</html>