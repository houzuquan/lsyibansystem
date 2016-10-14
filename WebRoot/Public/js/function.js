function goUrl(url, iframeId) {
	if(iframeId) {
		document.getElementById(iframeId).setAttribute('src', url);
		return true;
	}
	window.location.href = url;
}
function PrefixInteger(num, n) {
	/*
	 * num传入的数字，n需要的字符长度
	 * 例如：传入6，需要的字符长度为3，调用方法后字符串结果为：006
	 */
	return(Array(n).join(0) + num).slice(-n);
}
function ListingTag(id) {
	/*
	 * TAB页面切换
	 */
	var nav = document.getElementById(id);
	var li = nav.getElementsByTagName('li');
	for(i = 0; i < li.length; i++) {
		li[i].onclick = function() {
//			console.log(this.innerHTML);
			var li = this.parentElement.getElementsByTagName('li');
			for(j = 0; j < li.length; j++) {
				li[j].className = li[j].className.replace('active','');
				try {
					var elm = document.getElementById(li[j].getAttribute('data-go'));
					elm.style.display = 'none';
				}catch(e){
					
				}
			}
			this.className += ' active';
			var elm = document.getElementById(this.getAttribute('data-go'));
			if(elm){
				elm.style.display = 'block';
			}
			var fun = this.getAttribute('data-fun');
			if(fun){
				try{
					eval(''+fun+'');
				}catch(e){
					
				}
			}
			var loadFun = this.getAttribute('data-loadFun');
			if(loadFun){
				this.removeAttribute('data-loadFun');
				try{
					eval(''+loadFun+'');
				}catch(e){
					
				}
			}
		}
	}
	li[0].click();
}
function errorAction(codeData){
	switch(codeData.code*1){
		case 301:
			try{
				window.parent.location.href=codeData.url;
			}catch(e){
				try{
					window.location.href=codeData.url;
				}catch(e2){
					alert(codeData.Msg);
				}
			}
		break;
		case 201:
			alert(codeData.Msg);
		break;
		default:
			alert(codeData.Msg);
	}
}
