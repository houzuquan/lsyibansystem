package com.yiban.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yiban.dao.YbUserDao;

/**
 * Servlet Filter implementation class LoginFilter
 */
//@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		String stuId = (String)session.getAttribute("stuId");
		String pass = (String)session.getAttribute("pass");
		boolean s = false;
		boolean isLogin = false;
		if(null != session.getAttribute("isLogin") && true == (boolean)session.getAttribute("isLogin")){
			YbUserDao user = (YbUserDao)session.getAttribute("User");
			if(user == null){
				s = true;
//				System.out.println("莫名其妙失去用户数据");
				user = new YbUserDao();
			}
			if(stuId != null && pass != null && true == user.isLogin((String)session.getAttribute("stuId"), (String)session.getAttribute("pass"))){
				if(s == true){
					session.setAttribute("User",user);
				}
				isLogin = true;
			}else{
				session.removeAttribute("isLogin");
				session.removeAttribute("stuId");
				session.removeAttribute("pass");
			}
		}
		if(isLogin){
			chain.doFilter(request, response);
		}else{
			res.sendRedirect(req.getContextPath()+"/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
