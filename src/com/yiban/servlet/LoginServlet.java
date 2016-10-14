package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yiban.bean.StringCode;
import com.yiban.bean.jdbcBean;
import com.yiban.dao.YbUserDao;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(null != session.getAttribute("isLogin") && true == (boolean)session.getAttribute("isLogin")){
			if(action != null && action.equals("loginout") == true){
				session.setAttribute("goout", true);
				session.invalidate();
				response.sendRedirect("login.jsp");
			}else{
				response.sendRedirect("index.html");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
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
		String action,stuId,pass,yzmText,YzmCode;
		action = request.getParameter("action");
		stuId = request.getParameter("stuId");
		pass = request.getParameter("pass");
		yzmText = request.getParameter("yzmText");
		YzmCode = (String)session.getAttribute("YzmCode");
		int YzmCodeTime = (int)session.getAttribute("YzmCodeTime");
		YbUserDao user = new YbUserDao();
//		System.out.println("�û�����"+stuId+"�����룺"+pass);
		if(YzmCode == null){
			out.print("{\"code\":200,\"Msg\":\"��������\"}");
		}else if(YzmCode.equals(StringCode.MD5(yzmText.toLowerCase()))){
			if(true == user.isLogin(stuId, pass)){
				session.setAttribute("isLogin",true);
				session.setAttribute("id",user.getId());
				session.setAttribute("stuId",stuId);
				session.setAttribute("stuName",user.getStuName());
				session.setAttribute("pass",pass);
				session.setAttribute("User",user);
				user.updateLoginInfo(request);
//				response.sendRedirect("index.html");
				out.print("{\"code\":301,\"Msg\":\"��¼�ɹ�\",\"url\":\""+request.getContextPath()+"/index.html\"}");
	//			System.out.println("��¼�ɹ�");
			}else{
				request.setAttribute("isLogin",false);
				request.setAttribute("error",user.getErrorMsg());
				jdbcBean.addLog(null, null, null, "testlogin",stuId+"���Ե�¼��"+pass+"��ʧ�ܣ���¼IP��"+StringCode.getRealIp(request)+"��UA��"+request.getHeader("user-agent"));
//				request.getRequestDispatcher("login.jsp").forward(request, response);
				out.print("{\"code\":200,\"Msg\":\"�ʺŻ��������\"}");
	//			System.out.println("��¼ʧ��");
			}
		}else{
			out.print("{\"code\":200,\"Msg\":\"��֤�����\"}");
		}
			
	}
}
