package com.yiban.servlet;

import java.io.IOException;

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
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(null != session.getAttribute("isLogin") && true == (boolean)session.getAttribute("isLogin")){
			if(action != null && action.equals("loginout") == true){
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
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action,stuId,pass;
		action = request.getParameter("action");
		stuId = request.getParameter("stuId");
		pass = request.getParameter("pass");
		YbUserDao user = new YbUserDao();
//		System.out.println("ÓÃ»§Ãû£º"+stuId+"£»ÃÜÂë£º"+pass);
		if(true == user.isLogin(stuId, pass)){
			session.setAttribute("isLogin",true);
			session.setAttribute("stuId",stuId);
			session.setAttribute("pass",pass);
			session.setAttribute("User",user);
			user.updateLoginInfo(request);
			response.sendRedirect("index.html");
//			System.out.println("µÇÂ¼³É¹¦");
		}else{
			session.removeAttribute("isLogin");
			session.removeAttribute("stuId");
			session.removeAttribute("pass");
			request.setAttribute("isLogin",false);
			request.setAttribute("error",user.getErrorMsg());
			jdbcBean.addLog(null, null, null, "testlogin",stuId+"³¢ÊÔµÇÂ¼¡¾"+pass+"¡¿Ê§°Ü£»µÇÂ¼IP£º"+StringCode.getRealIp(request)+"£»UA£º"+request.getHeader("user-agent"));
			request.getRequestDispatcher("login.jsp").forward(request, response);
//			System.out.println("µÇÂ¼Ê§°Ü");
		}
	}
}
