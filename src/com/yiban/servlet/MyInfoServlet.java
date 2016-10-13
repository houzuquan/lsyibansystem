package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yiban.dao.YbUserDao;

/**
 * Servlet implementation class MyInfoServlet
 */
//@WebServlet("/MyInfoServlet")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		out.print("{\"code\":201,\"Msg\":\"∑«∑®∑√Œ 1\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
//		String action = request.getParameter("action");
		YbUserDao user = (YbUserDao)session.getAttribute("User");
		HashMap<String,String> myinfo = user.getMyUserInfo();
//		System.out.println(myinfo);
//		System.out.println(myinfo.keySet());
//		System.out.println(myinfo.values());
//		StringBuffer json=new StringBuffer();
		String json2="{";
		json2 += "\"id\":\"" + myinfo.get("id")+"\"";
		json2 += ",\"stuId\":\"" + myinfo.get("stuId")+"\"";
		json2 += ",\"stuName\":\"" + myinfo.get("stuName")+"\"";
//		json2 += ",\"pass\":\"" + myinfo.get("pass")+"\"";
//		json2 += ",\"sectionId\":\"" + myinfo.get("sectionId")+"\"";
		json2 += ",\"stuClass\":\"" + myinfo.get("stuClass")+"\"";
//		json2 += ",\"bankId\":\"" + myinfo.get("bankId")+"\"";
//		json2 += ",\"birthday\":\"" + myinfo.get("birthday")+"\"";
//		json2 += ",\"birthdayType\":\"" + myinfo.get("birthdayType")+"\"";
//		json2 += ",\"hobby\":\"" + myinfo.get("hobby")+"\"";
//		json2 += ",\"phone\":\"" + myinfo.get("phone")+"\"";
//		json2 += ",\"addTime\":\"" + myinfo.get("addTime")+"\"";
//		json2 += ",\"addIP\":\"" + myinfo.get("addIP")+"\"";
//		json2 += ",\"addUa\":\"" + myinfo.get("addUa")+"\"";
//		json2 += ",\"loginTime\":\"" + myinfo.get("loginTime")+"\"";
//		json2 += ",\"loginIP\":\"" + myinfo.get("loginIP")+"\"";
//		json2 += ",\"loginUa\":\"" + myinfo.get("loginUa")+"\"";
		json2 += ",\"sectionName\":\"" + myinfo.get("sectionName")+"\"";
		json2 += "}";
		out.print(json2);
	}

}
