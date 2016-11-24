<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/yibanUser" prefix="yb"  %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>个人信息</title>
		<link rel="stylesheet" type="text/css" href="css/myinfo.css" />
		<script src="../js/function.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/ajax.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/myinfo.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="info">
			<img src="#" id="headerImg" alt="我的头像" />
			<p id="name" class="infoBox">
				<yb:getInfo action="getName" /><span id="stuid">(<yb:getInfo action="getStuId" />)</span> <span id="section"><yb:getInfo action="getSectionName" /></span>
			</p>
			<p id="bank" class="infoBox">
				<span class="tab">银行卡：</span> <span id="bankTxt"></span> <input type="text" name="" id="bankTxt_in" value="<yb:getInfo action="getBankId" />" placeholder="银行卡号" size="25" /> <input type="button" name="" id="" class="btnChange" value="修改" data-state="0" /> <input type="button" name="" id="" class="btnBack" value="取消" />
			</p>
			<p id="phone" class="infoBox">
				<span class="tab">手机号：</span> <span id="phoneTxt"></span> <input type="text" name="" id="phoneTxt_in" value="<yb:getInfo action="getPhone" />" placeholder="手机号" maxlength="11" size="15" /> <input type="button" name="" id="" class="btnChange" value="修改" data-state="0" /> <input type="button" name="" id="" class="btnBack" value="取消" />
			</p>
			<p id="birth" class="infoBox">
				<span class="tab">生日：</span> <span id="birthday"></span> <span id="birthdayType"></span> <input type="date" name="" id="birthday_in" value="<yb:getInfo action="getBirthday" />" placeholder="出生日期" size="8" />
				<select name="" id="birthdayType_in">
					<option value="农历" <yb:getInfo action="getBirthdayType" birthdayType="农历" />>农历</option>
					<option value="阳历" <yb:getInfo action="getBirthdayType" birthdayType="阳历" />>阳历</option>
				</select> <input type="button" name="" id="" class="btnChange" value="修改" data-state="0" /> <input type="button" name="" id="" class="btnBack" value="取消" />
			</p>
			<p id="myinfo" class="infoBox">
				<span class="tab">爱好：</span> <span id="hobbyTxt"></span>
				<textarea name="" rows="" cols="" id="hobbyTxt_in" placeholder="爱好"><yb:getInfo action="getHobby" /></textarea>
				<input type="button" name="" id="" class="btnChange" value="修改" data-state="0" /> <input type="button" name="" id="" class="btnBack" value="取消" />
			</p>
		</div>
	</body>

</html>