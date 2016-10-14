package com.yiban.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.yiban.bean.jdbcBean;

/**
 * Application Lifecycle Listener implementation class SessionDestoryedListener
 *
 */
@WebListener
public class SessionDestoryedListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionDestoryedListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession session = arg0.getSession();
		if(null != session.getAttribute("isLogin") && true == (boolean)session.getAttribute("isLogin")){
			String str="";
			String loginout="loginout";
			if(null != session.getAttribute("goout") && true == (boolean)session.getAttribute("goout")){
				str="用户手动退出";
				loginout="LoginOut";
			}else{
				str="系统自动退出退出";
				loginout="autoLoginOut";
			}
			jdbcBean.addLog((String)session.getAttribute("id"), (String)session.getAttribute("stuId"), (String)session.getAttribute("stuName"), loginout,str);
		}
    }
}
