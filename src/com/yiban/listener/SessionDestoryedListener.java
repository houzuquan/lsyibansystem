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
			jdbcBean.addLog(null, (String)session.getAttribute("stuId"), (String)session.getAttribute("stuName"), "loginout","ÍË³ö");
		}
    }
}
