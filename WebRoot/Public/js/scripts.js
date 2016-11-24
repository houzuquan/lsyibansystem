var text = document.getElementById("text");
var passkey = document.getElementById("passkey");
var result = document.getElementById("result");

var encode = document.getElementById("encode");
var decode = document.getElementById("decode");
var totext = document.getElementById("toText");


var key = document.getElementById("key");
var re = document.getElementById("re");

var write = document.getElementById("write");
var read = document.getElementById("read");
var cleae = document.getElementById("clear");

encode.onclick = function() {
	try
	{
		var key_hash = CryptoJS.MD5(passkey.value);
		var key = CryptoJS.enc.Utf8.parse(key_hash);
	  	var aes = CryptoJS.AES.encrypt(text.value, key, { 
										   mode: CryptoJS.mode.ECB,
										   padding: CryptoJS.pad.Pkcs7
									   });
		var aes_en = aes.ciphertext.toString();
		result.value = aes_en;
	}catch(e){
		result.value = e;
	}
};
decode.onclick = function() {
	var key_hash = CryptoJS.MD5(passkey.value);
	var key = CryptoJS.enc.Utf8.parse(key_hash);
	var data = CryptoJS.enc.Hex.parse(text.value);
	var base64 = CryptoJS.enc.Base64.stringify(data);
	var decryptedData = CryptoJS.AES.decrypt(base64, key, { 
												 mode: CryptoJS.mode.ECB,
												 padding: CryptoJS.pad.Pkcs7
											 });
	var re = decryptedData.toString(CryptoJS.enc.Utf8); 
	result.value = re;
};
totext.onclick = function() {
	text.value = result.value;
};

write.onclick = function() {
	store.set(key.value, re.value);
};
read.onclick = function() {
	re.value = store.get(key.value);
};
clear.onclick = function() {
	store.remove(key.value);
};
