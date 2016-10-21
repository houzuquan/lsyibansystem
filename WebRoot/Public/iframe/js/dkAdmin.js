var nowTableElmidArr = ['dateTable', 'info', 'logList'];
var nowSelectElmidArr = ['year1', 'month1'];
var ul = document.getElementById('nav');
var li = ul.getElementsByTagName('li');
var monthHaveLog; //当前月份所拥有的记录

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
			showMsg("获取数据错误!");
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
			showMsg("获取数据错误!");
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
				var tr = document.createElement('tr');
				var td0 = document.createElement('td');
				td0.setAttribute('colspan',7);
				td0.innerHTML='没有更多信息';
				tr.appendChild(td0);
				table.appendChild(tr);
				return false;
			}
			var date1 = new Date();
			var date2 = new Date();
			for(i = 0; i < data.list.length; i++) {
				var info = data.list[i];
				var tr = document.createElement('tr');
				var td0 = document.createElement('td');
				var td1 = document.createElement('td');
				var td2 = document.createElement('td');
				var td3 = document.createElement('td');
				var td4 = document.createElement('td');
				var td5 = document.createElement('td');
				var td6 = document.createElement('td');
				var time1 = info.time1.split(" ");
				var tt1 = time1[1].replace('.0','').split(':');
				var tt2 = info.run1.replace('.0','').split(':');
				td0.innerHTML=time1[0];
				td1.innerHTML=info.dktext;
				td2.innerHTML=time1[1].replace('.0','');
				date1.setHours(tt1[0]);
				date1.setMinutes(tt1[1]);
				date1.setSeconds(tt1[2]);
				date2.setHours(tt2[0]);
				date2.setMinutes(tt2[1]);
				date2.setSeconds(tt2[2]);
				td3.innerHTML='否';
				if(date1 > date2){
					td3.innerHTML='是';
					td3.style.background='rgba(255,0,0,0.5)';
					td3.style.color='blue';
				}
//				td3.innerHTML=date1 <= date2 ? "否" : "是" ;
				tr.appendChild(td0);
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				if(info.isqt*1 == 1){
				var time2 = info.time2.split(" ");
					td4.innerHTML=time2[1].replace('.0','');
					td5.innerHTML=info.text;
					td5.setAttribute('title',info.text);
				var tt1 = time2[1].replace('.0','').split(':');
				var tt2 = info.run2.replace('.0','').split(':');
					date1.setHours(tt1[0]);
					date1.setMinutes(tt1[1]);
					date1.setSeconds(tt1[2]);
					date2.setHours(tt2[0]);
					date2.setMinutes(tt2[1]);
					date2.setSeconds(tt2[2]);
					td6.innerHTML='否';
					if(date1 < date2){
						td6.innerHTML='是';
						td6.style.background='rgba(255,0,0,0.5)';
						td6.style.color='blue';
					}
//					td6.innerHTML=date1 <= date2 ? "否" : "是" ;
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
				}else{
					var td7 = document.createElement('td');
					td7.setAttribute('colspan',3);
					td7.innerHTML='此次签到并未签退';
					td7.style.background='rgba(180,180,180,0.3)';
					tr.appendChild(td7);
				}
				table.appendChild(tr);
			}
		},
		error: function(status) {
			console.log(status);
//			more.style.display='none';
			var tr = document.createElement('tr');
			var td0 = document.createElement('td');
			td0.setAttribute('colspan',7);
			td0.innerHTML='获取数据错误！没有更多信息';
			tr.appendChild(td0);
			table.appendChild(tr);
//			showMsg("获取数据错误！");
		}
	});
}
function getSection() {
	var section = document.getElementById('section');
//	section.innerHTML = '';
	$ajax({
		url: "../../DK?action=getSection",
		data: "",
		method: "post",
		dataType: "json",
		callBack: function(data) {
			console.log(data);
			if(data.code) {
				errorAction(data);
				return false;
			}
			if(data.list.length == 0) {
				return false;
			}
			for(i = 0; i < data.list.length; i++) {
				var info = data.list[i];
				var options = document.createElement('option');
				options.value=info.id;
				options.innerHTML=info.text;
				section.appendChild(options);
			}
		},
		error: function(status) {
			console.log(status);
			showMsg("获取数据错误!");
		}
	});
}

document.getElementById('bmDKLogTableShowMore').onclick=function(){
	var page = this.getAttribute('data-nowpage');
	this.setAttribute('data-nowpage',page*1+1);
	searchOtherDK(page);
}
function searchOtherDK(page){
	var table = document.getElementById('bmDKLogTable');
	var more = document.getElementById('bmDKLogTableShowMore');
	var section = document.getElementById('section');
	var time1 = document.getElementById('otherTime1');
	var time2 = document.getElementById('otherTime2');
	if(page*1 == 1){
		var trH = table.getElementsByTagName('tr')[0].outerHTML.toString();
		table.innerHTML='';
		table.innerHTML=trH;
		more.setAttribute('data-nowpage',2);
	}
	$ajax({
		url: "../../DK?action=searchOtherDK",
		data: "page="+page+"&section="+section.value+"&time1="+time1.value+"&time2="+time2.value,
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
				var tr = document.createElement('tr');
				var td0 = document.createElement('td');
				td0.setAttribute('colspan',10);
				td0.innerHTML='没有更多信息';
				tr.appendChild(td0);
				table.appendChild(tr);
				return false;
			}
			if(page*1==1){
				more.style.display='inline-block';
			}
			var date1 = new Date();
			var date2 = new Date();
			for(i = 0; i < data.list.length; i++) {
				var info = data.list[i];
				var tr = document.createElement('tr');
				var tdName = document.createElement('td');
				var tdSection = document.createElement('td');
				var tdDate = document.createElement('td');
				var tdDktext = document.createElement('td');
				var tdQd = document.createElement('td');
				var tdC = document.createElement('td');
				var tdQt = document.createElement('td');
				var tdQttext = document.createElement('td');
				var tdZ = document.createElement('td');
				
				var time1 = info.time1.split(" ");
				var tt1 = time1[1].replace('.0','').split(':');
				console.log(tt1);
				var tt2 = info.run1.replace('.0','').split(':');
				tdName.innerHTML=info.name;
				tdSection.innerHTML=info.sectionName;
				tdDate.innerHTML=time1[0];
				tdDktext.innerHTML=info.dktext;
				tdQd.innerHTML=time1[1].replace('.0','');
				date1.setHours(tt1[0]);
				date1.setMinutes(tt1[1]);
				date1.setSeconds(tt1[2]);
				date2.setHours(tt2[0]);
				date2.setMinutes(tt2[1]);
				date2.setSeconds(tt2[2]);
				tdC.innerHTML='否';
				if(date1 > date2){
					tdC.innerHTML='是';
					tdC.style.background='rgba(255,0,0,0.5)';
					tdC.style.color='blue';
				}
//				td3.innerHTML=date1 <= date2 ? "否" : "是" ;
				tr.appendChild(tdName);
				tr.appendChild(tdSection);
				tr.appendChild(tdDate);
				tr.appendChild(tdDktext);
				tr.appendChild(tdQd);
				tr.appendChild(tdC);
				if(info.isqt*1 == 1){
				var time2 = info.time2.split(" ");
					tdQt.innerHTML=time2[1].replace('.0','');
					tdQttext.innerHTML=info.text;
					tdQttext.setAttribute('title',info.text);
				var tt1 = time2[1].replace('.0','').split(':');
				var tt2 = info.run2.replace('.0','').split(':');
					date1.setHours(tt1[0]);
					date1.setMinutes(tt1[1]);
					date1.setSeconds(tt1[2]);
					date2.setHours(tt2[0]);
					date2.setMinutes(tt2[1]);
					date2.setSeconds(tt2[2]);
					tdZ.innerHTML='否';
					if(date1 < date2){
						tdZ.innerHTML='是';
						tdZ.style.background='rgba(255,0,0,0.5)';
						tdZ.style.color='blue';
					}
//					td6.innerHTML=date1 <= date2 ? "否" : "是" ;
					tr.appendChild(tdQt);
					tr.appendChild(tdQttext);
					tr.appendChild(tdZ);
				}else{
					var td7 = document.createElement('td');
					td7.setAttribute('colspan',3);
					td7.innerHTML='此次签到并未签退';
					td7.style.background='rgba(180,180,180,0.3)';
					tr.appendChild(td7);
				}
				table.appendChild(tr);
			}
		},
		error: function(status) {
			console.log(status);
//			more.style.display='none';
			var tr = document.createElement('tr');
			var td0 = document.createElement('td');
			td0.setAttribute('colspan',9);
			td0.innerHTML='获取数据错误！没有更多信息';
			tr.appendChild(td0);
			table.appendChild(tr);
//			showMsg("获取数据错误!");
		}
	});
}
ListingTag('nav');