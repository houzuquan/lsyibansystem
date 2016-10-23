package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
		out.print("{\"code\":201,\"Msg\":\"非法访问1\"}");
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
		String json2="{";
		json2 += "\"id\":\"" + user.getId()+"\"";
		json2 += ",\"stuId\":\"" + user.getStuId()+"\"";
		json2 += ",\"stuName\":\"" + user.getStuName()+"\"";
		json2 += ",\"stuClass\":\"" + user.getStuClass()+"\"";
		json2 += ",\"sectionName\":\"" + user.getSectionName()+"\"";
		json2 += "}";
		out.print(json2);
	}

}
