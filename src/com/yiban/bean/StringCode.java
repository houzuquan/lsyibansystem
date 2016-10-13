package com.yiban.bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringCode {
	public static String enUnicode(String string) {
	    StringBuffer unicode = new StringBuffer();
	    for (int i = 0; i < string.length(); i++) {
	        // ȡ��ÿһ���ַ�
	        char c = string.charAt(i);
	        // ת��Ϊunicode
	        String cc = Integer.toHexString(c);
	        //����λ��
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
	        // ת����ÿһ�������
	        int data = Integer.parseInt(hex[i], 16);
	        // ׷�ӳ�string
	        string.append((char) data);
	    }
	    return string.toString();
	}
	public static String MD5(String str){
		try{
			// ����һ��MD5���ܼ���ժҪ
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ����md5����
			md.update(str.getBytes());
			// digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
			// BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
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