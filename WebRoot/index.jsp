﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/yibanUser" prefix="yb"  %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<title>易班日常管理系统</title>
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="Public/css/adminIndex.css" />
		<link rel="stylesheet" type="text/css" href="Public/js/alert/easydialog.css" />
		<script src="Public/js/alert/easydialog.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="Public/js/function.js" type="text/javascript" charset="utf-8"></script>
		<script src="Public/js/ajax.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div id="header">
			<div id="logo">
				<img src="Public/img/logo.png" onclick="goUrl('./');" />
			</div>
			<div id="headerNav">
				<ul id="userInfo">
					<li>
						值班<br>打卡
						<ul>
							<li onclick="goUrl('Public/iframe/dk.html?qian','iContent');">值班签到</li>
							<li onclick="goUrl('Public/iframe/dk.html?out','iContent');">值班签退</li>
						</ul>
					</li>
					<li>
						<span id="MyInfoStuName" style="font-weight: 900;"><yb:getInfo action="getName" /></span><br><span id="MyInfoSectionName" style="color:#0000FF;font-weight: 900;">(<yb:getInfo action="getSectionName" />)</span>
						<ul>
							<li onclick="goUrl('Public/iframe/myinfo.jsp','iContent');">个人资料</li>
							<li>修改密码</li>
						</ul>
					</li>
					<li onclick="goUrl('Login?action=loginout');">
						退出<br>系统
					</li>
					<div class="clear"></div>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div id="section">
			<div id="leftNav">
				<ul id="Lnav">
					<li data-fun="goUrl('Public/iframe/main.jsp','iContent');">概况</li>
					<li>通知管理</li>
					<li data-fun="goUrl('Public/iframe/dkAdmin.html','iContent');">打卡管理</li>
					<li>部门管理</li>
					<li>人员管理</li>
					<li>权限管理</li>
					<li class="hr"></li>
					<li>记事本</li>
					<li>文件系统</li>
				</ul>
			</div>
			<div id="content">
				<iframe src="Public/iframe/main.html" id="iContent" name="iContent"></iframe>
			</div>
			<div class="clear"></div>
		</div>
		<script type="text/javascript">
			ListingTag('Lnav');
			window.onload = function() {
				changeSectionSize();
			}
			window.onresize = function() {
				changeSectionSize();
			}

			function changeSectionSize() {
				var header = document.getElementById('header');
				var content = document.getElementById('content');
				var leftNav = document.getElementById('leftNav');
				var Lnav = document.getElementById('Lnav');
				var minHeigth = (Lnav.offsetHeight + 50);
				var headerH = header.offsetHeight;
				leftNav.style.minHeight = 768 + 'px';
				leftNav.parentElement.style.minHeight = 768 + 'px';
				if(document.body.clientHeight > minHeigth + headerH) {
					leftNav.parentElement.style.height = (document.body.clientHeight - headerH) + 'px';
				}
				content.style.height = (content.parentElement.offsetHeight - 2) + 'px';
			}
		</script>
	</body>

</html>