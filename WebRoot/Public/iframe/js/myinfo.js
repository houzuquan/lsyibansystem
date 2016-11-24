function showInfo(Pid,isChange) {
	var show = {
		showBank: function(isChange) {
			var bankTxt_in = document.getElementById('bankTxt_in');
			var bankTxt = document.getElementById('bankTxt');
			bankTxt_in.style.display = 'none';
			bankTxt.style.display = 'inline-block';
			if(isChange){
				bankTxt.innerHTML = bankTxt_in.value != "" ? bankTxt_in.value :"(未设置)";
				bankTxt.setAttribute('data-value',bankTxt_in.value);
				bankTxt.setAttribute('title',bankTxt_in.value);
			}else{
				bankTxt_in.value = bankTxt.getAttribute('data-value');
			}
			var btn = bankTxt.parentElement.getElementsByClassName('btnBack');
			var btn2 = bankTxt.parentElement.getElementsByClassName('btnChange');
			btn[0].style.display='none';
			btn2[0].setAttribute('data-state',0);
		},
		showPhone: function(isChange) {
			var phoneTxt_in = document.getElementById('phoneTxt_in');
			var phoneTxt = document.getElementById('phoneTxt');
			phoneTxt_in.style.display = 'none';
			phoneTxt.style.display = 'inline-block';
			if(isChange){
				phoneTxt.innerHTML = phoneTxt_in.value != "" ? phoneTxt_in.value :"(未设置)";
				phoneTxt.setAttribute('data-value',phoneTxt_in.value);
				phoneTxt.setAttribute('title',phoneTxt_in.value);
			}else{
				phoneTxt_in.value = phoneTxt.getAttribute('data-value');
			}
			var btn = phoneTxt.parentElement.getElementsByClassName('btnBack');
			var btn2 = phoneTxt.parentElement.getElementsByClassName('btnChange');
			btn[0].style.display='none';
			btn2[0].setAttribute('data-state',0);

		},
		showBirth: function(isChange) {
			var birthday_in = document.getElementById('birthday_in');
			var birthdayType_in = document.getElementById('birthdayType_in');
			var birthday = document.getElementById('birthday');
			var birthdayType = document.getElementById('birthdayType');
			birthday_in.style.display = 'none';
			birthdayType_in.style.display = 'none';
			birthday.style.display = 'inline-block';
			birthdayType.style.display = 'inline-block';
			if(isChange){
				birthday.innerHTML = birthday_in.value != "" ? birthday_in.value :"(未设置)";
				birthdayType.innerHTML = birthdayType_in.value != "" ? birthdayType_in.value :"(未设置)";
				birthday.setAttribute('data-value',birthday_in.value);
				birthday.setAttribute('title',birthday_in.value);
				birthdayType.setAttribute('data-value',birthdayType_in.value);
				birthdayType.setAttribute('title',birthdayType_in.value);
			}else{
				birthday_in.value = birthday.getAttribute('data-value');
				birthdayType_in.value = birthdayType.getAttribute('data-value');
			}
			var btn = birthdayType.parentElement.getElementsByClassName('btnBack');
			var btn2 = birthdayType.parentElement.getElementsByClassName('btnChange');
			btn[0].style.display='none';
			btn2[0].setAttribute('data-state',0);
			
		},
		showHobby: function(isChange) {
			var hobbyTxt_in = document.getElementById('hobbyTxt_in');
			var hobbyTxt = document.getElementById('hobbyTxt');
			hobbyTxt_in.style.display = 'none';
			hobbyTxt.style.display = 'inline-block';
			if(isChange){
				hobbyTxt.innerHTML = hobbyTxt_in.value != "" ? hobbyTxt_in.value :"(未设置)";
				hobbyTxt.setAttribute('data-value',hobbyTxt_in.value);
				hobbyTxt.setAttribute('title',hobbyTxt_in.value);
			}else{
				hobbyTxt_in.value = hobbyTxt.getAttribute('data-value');
			}
			var btn = hobbyTxt.parentElement.getElementsByClassName('btnBack');
			var btn2 = hobbyTxt.parentElement.getElementsByClassName('btnChange');
			btn[0].style.display='none';
			btn2[0].setAttribute('data-state',0);
		}
	};

	if(Pid) {
		switch(Pid){
			case "bank":show.showBank(isChange);
			break;
			case "phone":show.showPhone(isChange);
			break;
			case "birth":show.showBirth(isChange);
			break;
			case "myinfo":show.showHobby(isChange);
			break;
		}
	}else{
		show.showBank(isChange);
		show.showPhone(isChange);
		show.showBirth(isChange);
		show.showHobby(isChange);
	}


}
window.onload = function() {
	showInfo(false,true);
	var btn = document.getElementsByClassName('btnChange');
	for(i = 0; i < btn.length; i++) {
		btn[i].onclick = function() {
			var state = this.getAttribute('data-state');
			var pr = this.parentElement;
			if(state*1 == 0){
				this.setAttribute('data-state',1);
				this.value = "保存";
				var span = pr.getElementsByTagName('span');
				var input = pr.getElementsByTagName('input');
				var sel = pr.getElementsByTagName('select');
				var area = pr.getElementsByTagName('textarea');
				for(i = 0; i < span.length; i++) {
					if(span[i].className != 'tab') {
						span[i].style.display = 'none';
					}
				}
				for(i = 0; i < input.length; i++) {
					input[i].style.display = 'inline-block';
				}
				for(i = 0; i < sel.length; i++) {
					sel[i].style.display = 'inline-block';
				}
				for(i = 0; i < area.length; i++) {
					area[i].style.display = 'inline-block';
				}
				if(input[0]){
					input[0].focus();
					input[0].select();
				}
				if(area[0]){
					area[0].focus();
					area[0].select();
				}
			}else{
				this.setAttribute('data-state',0);
				this.value = "修改";
				alert(1);
				showInfo(pr.getAttribute('id'),true);
			}
			
		}
	}
	var btn2 = document.getElementsByClassName('btnBack');
	for(i = 0; i < btn2.length; i++) {
		btn2[i].onclick = function() {
			var pr = this.parentElement;
			var input2 = pr.getElementsByTagName('input');
			var sel2 = pr.getElementsByTagName('select');
			var area2 = pr.getElementsByTagName('textarea');
			for(i = 0; i < input2.length; i++) {
				if(input2[i].className == 'btnChange') {
					input2[i].value = '修改';
				} else {
					input2[i].style.display = 'none';
				}
			}
			for(i = 0; i < sel2.length; i++) {
				sel2[i].style.display = 'none';
			}
			for(i = 0; i < area2.length; i++) {
				area2[i].style.display = 'none';
			}
			showInfo(pr.getAttribute('id'),false);
		}
	}
}