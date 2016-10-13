var nowTableElmidArr = ['dateTable', 'info', 'logList'];
var nowSelectElmidArr = ['year1', 'month1'];
var ul = document.getElementById('nav');
var li = ul.getElementsByTagName('li');
var monthHaveLog; //当前月份所拥有的记录
ListingTag('nav');

function dateView(year, month, tableElmid) {//绘制日期表格
	var date = new Date();
	var header = '<tr><th>星期日</th><th>星期一</th><th>星期二</th><th>星期三</th><th>星期四</th><th>星期五</th><th>星期六</th></tr>';
	var table = document.getElementById(tableElmid[0]);
	var toWeek = date.getDay(); //周几
	var toDay = date.getDate(); //几日
	var toMonth = date.getMonth(); //几月
	var toYear = date.getFullYear(); //几年
	var tr = document.createElement('tr');
	table.innerHTML = header;
	table.appendChild(tr);
	if(!year) year = toYear;
	if(!month) {
		month = toMonth;
	} else {
		month--;
	}
	date.setFullYear(year, month, 1); //跳转到这个月的第一天
	for(i = 0; i < date.getDay(); i++) { //填充前面空单元格
		var td = document.createElement('td');
		tr.appendChild(td);
	}
	var toDayDate = year + '-' + PrefixInteger(month + 1, 2) + '-' + PrefixInteger(toDay, 2);
	do {
		var Day = date.getDate(); //几日
		var td = document.createElement('td');
		td.innerHTML = Day;
		if(toDay == Day) { //year == toYear &&  && month == toMonth
			td.className += ' active';
		}
		var theDate = year + '-' + PrefixInteger(month + 1, 2) + '-' + PrefixInteger(Day, 2);
		/*对单元格着色*/
		if(theMonthLog(theDate)) { //有信息的单元格
			td.className += ' active2';
			td.setAttribute('data-ishave', 1);
		}

		//td.className+=' active3';//选中的单元格

		td.id = 'D' + theDate;
		td.setAttribute('data-thedate', theDate);
		td.setAttribute('data-logListid', tableElmid[2]);
		td.onclick = function() {
			var theDate2 = this.getAttribute('data-thedate');
			var ishave = this.getAttribute('data-ishave');
			var logListid = this.getAttribute('data-logListid');
			if(ishave * 1 == 1) {
				getDKDateLog(theDate2, logListid);
			} else {
				var logList = document.getElementById(logListid);
				logList.innerHTML = '';
				var li1 = document.createElement('li');
				li1.innerHTML = "这天没有签到信息[" + theDate2 + "]";
				logList.appendChild(li1);
			}
		}
		tr.appendChild(td);

		date.setDate(date.getDate() + 1); //转到下一天
		if(date.getDay() == 0) { //判断是否是一个新的星期
			tr = document.createElement('tr');
			table.appendChild(tr);
		}
	} while (month == date.getMonth()); //当月份不是当前月时退出
	for(i = date.getDay(); i < 7 && i != 0; i++) { //填充后面空单元格
		var td = document.createElement('td');
		tr.appendChild(td);
	}
	var info = document.getElementById(tableElmid[1]);
	info.style.height = table.parentElement.offsetHeight + 'px';
	info.parentElement.style.width = (table.offsetWidth + info.offsetWidth + 10) + 'px';
	getDKDateLog(toDayDate, tableElmid[2]);

}

function createSelectInfo(year, month, tableElmid, selectElmid) {//创建下拉框内容并监听选择内容改变时间
	var date = new Date();
	var toMonth = date.getMonth(); //几月
	var toYear = date.getFullYear(); //几年
	if(!year) year = toYear;
	if(!month) {
		month = toMonth;
	} else {
		month--;
	}
	var year1 = document.getElementById(selectElmid[0]);
	var month1 = document.getElementById(selectElmid[1]);
	year1.innerHTML = '';
	month1.innerHTML = '';
	var str = JSON.stringify(tableElmid);
	year1.setAttribute('data-tableElmid', str);
	month1.setAttribute('data-tableElmid', str);
	for(i = year - 2; i <= year; i++) {
		var option = document.createElement('option');
		option.value = i;
		option.innerHTML = i + '年';
		if(i == toYear) {
			option.setAttribute('selected', 'selected');
		}
		year1.appendChild(option);
	}
	for(i = 1; i <= 12; i++) {
		var option = document.createElement('option');
		option.value = i;
		option.innerHTML = i + '月';
		if(i == month + 1) {
			option.setAttribute('selected', 'selected');
		}
		month1.appendChild(option);
	}
	year1.onchange = function() {
		var year1 = document.getElementById(selectElmid[0]);
		var month1 = document.getElementById(selectElmid[1]);
		var str = this.getAttribute('data-tableElmid');
		var arr = eval('(' + str + ')');
		backfun = function() {
			dateView(year1.value * 1, month1.value * 1, arr);
		}
		getDKMonthHaveLog(year1.value * 1 + "-" + PrefixInteger(month1.value * 1, 2) + "-01", backfun);
	}
	month1.onchange = function() {
		var year1 = document.getElementById(selectElmid[0]);
		var month1 = document.getElementById(selectElmid[1]);
		var str = this.getAttribute('data-tableElmid');
		var arr = eval('(' + str + ')');
		backfun = function() {
			dateView(year1.value * 1, month1.value * 1, arr);
		}
		getDKMonthHaveLog(year1.value * 1 + "-" + PrefixInteger(month1.value * 1, 2) + "-01", backfun);
	}
}

function goToday(tableElmid, selectElmid) {//日期表格，跳转到某一天
	nowTableElmidArr = tableElmid;
	nowSelectElmidArr = selectElmid;
	var date = new Date();
	var toMonth = date.getMonth(); //几月
	var toYear = date.getFullYear(); //几年
	var toDay = date.getDate(); //几日
	backfun = function() {
		dateView(toYear, toMonth + 1, tableElmid);
		createSelectInfo(toYear, toMonth + 1, tableElmid, selectElmid);
	}
	getDKMonthHaveLog(toYear + "-" + PrefixInteger(toMonth + 1, 2) + "-" + PrefixInteger(toDay, 2), backfun);

}

function getDKMonthHaveLog(month, backfun) {//获取当前月份有多少天有签到信息
	$ajax({
		url: "../../DK?action=getDKMonthHaveLog",
		data: "month=" + month,
		method: "post",
		dataType: "json",
		callBack: function(data) {
			console.log(data);
			if(data.code) {
				errorAction(data);
				return false;
			}
			monthHaveLog = data;
			backfun();
		},
		error: function(status) {
			console.log(status);
			alert("获取数据错误！");
		}
	});
}

function theMonthLog(theDate) {//传入一个日期，判断这个日期是否存在签到信息
	for(i = 0; i < monthHaveLog.list.length; i++) {
		if(theDate == monthHaveLog.list[i]) {
			return true;
		}
	}
	return false;
}

function getDKDateLog(theDate, logListid) {//按照日期，获取这天的签到内容
	var logList = document.getElementById(logListid);
	console.log(theDate);
	logList.innerHTML = '';
	$ajax({
		url: "../../DK?action=getDKDateLog",
		data: "date=" + theDate,
		method: "post",
		dataType: "json",
		callBack: function(data) {
			console.log(data);
			if(data.code) {
				errorAction(data);
				return false;
			}
			if(data.list.length == 0) {
				var li1 = document.createElement('li');
				li1.innerHTML = "这天没有签到信息[" + theDate + "]";
				logList.appendChild(li1);
				return false;
			}
			for(i = 0; i < data.list.length; i++) {
				var info = data.list[i];
				var li1 = document.createElement('li');
				var ul = document.createElement('ul');
				var li21 = document.createElement('li');
				var li22 = document.createElement('li');
				li1.innerHTML = info.dktext;
				ul.className = "logListMy";
				var time1 = info.time1.replace(theDate, '');
				var time2 = info.time2.replace(theDate, '');

				li21.innerHTML = time1 + "签到";
				li22.innerHTML = info.isqt * 1 == 1 ? time2 + "签退" : "未签退";
				ul.appendChild(li21);
				ul.appendChild(li22);
				li1.appendChild(ul);
				logList.appendChild(li1);
			}
		},
		error: function(status) {
			console.log(status);
			alert("获取数据错误！");
		}
	});
}
document.getElementById('AllDKLogTableShowMore').onclick=function(){
	var page = this.getAttribute('data-nowpage');
	this.setAttribute('data-nowpage',page*1+1);
	getDKForPage(page);
}
function getDKForPage(page) {//通过分页显示签到内容
	var table = document.getElementById("AllDKLogTable");
	var more = document.getElementById('AllDKLogTableShowMore');
	console.log(page);
	$ajax({
		url: "../../DK?action=getDKForPage",
		data: "page=" + page,
		method: "post",
		dataType: "json",
		callBack: function(data) {
			console.log(data);
			if(data.code) {
				errorAction(data);
				return false;
			}
			if(data.list.length == 0) {
				more.style.display='none';
				return false;
			}
			for(i = 0; i < data.list.length; i++) {
				var info = data.list[i];
				var tr = document.createElement('tr');
				var td1 = document.createElement('td');
				var td2 = document.createElement('td');
				var td3 = document.createElement('td');
				var td4 = document.createElement('td');
				var td5 = document.createElement('td');
				var td6 = document.createElement('td');
				td1.innerHTML=info.dktext;
				td2.innerHTML=info.time1;
				td3.innerHTML="否";
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				if(info.isqt*1 == 1){
					td4.innerHTML=info.time2;
					td5.innerHTML=info.text;
					td6.innerHTML='否';
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
				}else{
					var td7 = document.createElement('td');
					td7.setAttribute('colspan',3);
					tr.appendChild(td7);
				}
			}
		},
		error: function(status) {
			console.log(status);
			alert("获取数据错误！");
		}
	});
}