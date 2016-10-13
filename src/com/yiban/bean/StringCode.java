package com.yiban.bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringCode {
	public static String enUnicode(String string) {
	    StringBuffer unicode = new StringBuffer();
	    for (int i = 0; i < string.length(); i++) {
	        // 取出每一个字符
	        char c = string.charAt(i);
	        // 转换为unicode
	        String cc = Integer.toHexString(c);
	        //补齐位数
	        for(int j = 4 ,len = cc.length(); j > len ; j--){
	        	cc = "0" + cc;
	        }
	        unicode.append("\\u" + cc);
	    }
	    return unicode.toString();
	}
	public static String deUnicode(String unicode) {
	    StringBuffer string = new StringBuffer();
	    String[] hex = unicode.split("\\\\u");
	    for (int i = 1; i < hex.length; i++) {
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	        // 追加成string
	        string.append((char) data);
	    }
	    return string.toString();
	}
	public static String MD5(String str){
		try{
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		}catch(Exception e){
			return null;
		}
	}
	public static boolean isInteger(String str){
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	public static String getRealIp(HttpServletRequest req) {
		String ip = null;
		try{
			ip = req.getHeader("X-Forwarded-For");
			if (ip != null) {
			    if (ip.indexOf(',') == -1) {
			    return ip;
			}
			return ip.split(",")[0];
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			    ip = req.getRemoteAddr();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ip;
	}
}