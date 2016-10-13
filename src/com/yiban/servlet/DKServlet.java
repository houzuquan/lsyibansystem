package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yiban.bean.StringCode;
import com.yiban.bean.jdbcBean;
import com.yiban.dao.YbUserDao;

/**
 * Servlet implementation class DKServlet
 */
//@WebServlet("/DKServlet")
public class DKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DKServlet() {
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
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		YbUserDao user = (YbUserDao)session.getAttribute("User");
		if(user == null){
			jdbcBean.addLog(null, null, null, "get","尝试访问【"+request.getServletPath()+"】失败，无user信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
		}else{
			jdbcBean.addLog(user.getId(), user.getStuId(), user.getStuName(), "get","尝试访问【"+request.getServletPath()+"】失败；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
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
		String action = request.getParameter("action");
		YbUserDao user = (YbUserDao)session.getAttribute("User");
		if(user == null){
			out.print("{\"code\":201,\"Msg\":\"非法访问2\"}");
			jdbcBean.addLog(null, null, null, "post","尝试访问【"+request.getServletPath()+"】失败，无user信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
		}else if(action != null){
			switch(action){
				case "qd":
					if(user.dk_qd() == true){
						out.print("{\"code\":200,\"Msg\":\"签到成功！\"}");
					}else{
						out.print("{\"code\":201,\"Msg\":\"签到失败！"+user.getErrorMsg()+"\"}");
					}
					break;
				case "qt":
					String text = request.getParameter("text");
					if(user.dk_qt(text) == true){
						out.print("{\"code\":200,\"Msg\":\"签退成功！\"}");
					}else{
						out.print("{\"code\":201,\"Msg\":\"签退失败！"+user.getErrorMsg()+"\"}");
					}
					break;
				case "getDKAllCount":
					HashMap<String,String> dkcount = user.getDKAllCount();
					out.print("{\"qdNum\":\""+dkcount.get("qdNum")+"\",\"qtNum\":\""+dkcount.get("qtNum")+"\"}");
					break;
				case "getDKMonthHaveLog":
					String month = request.getParameter("month");
					ArrayList<String> list = user.getDKMonthHaveLog(month);
					int len = list.size();
					String json = "{\"list\":[]}";
					if(len >= 1){
						json = "{\"list\":[";
						for(int i=0;i<len;i++){
							json += "\""+list.get(i)+"\"";
							if(i<len-1){
								json +=",";
							}
						}
						json +="]}";
					}
					out.print(json);
					break;

				case "getDKDateLog":
					String toDate = request.getParameter("date");
					ArrayList<HashMap<String,String>> list2 = user.getDKDateLog(toDate);
//					System.out.println(list2);
					int len2 = list2.size();
					String json2 = "{\"list\":[]}";
					if(len2 >= 1){
						json2 = "{\"list\":[";
						for(int i=0;i<len2;i++){
							HashMap<String,String> map = list2.get(i);
							json2 += "{\"dklogid\":\"" + map.get("dklogid") 
							+ "\",\"dkid\":\"" + map.get("dkid") 
							+ "\",\"time1\":\"" + map.get("time1") 
							+ "\",\"time2\":\"" + map.get("time2") 
							+ "\",\"ybuserid\":\"" + map.get("ybuserid") 
							+ "\",\"type\":\"" + map.get("type") 
							+ "\",\"text\":\"" + map.get("text") 
							+ "\",\"isqd\":\"" + map.get("isqd") 
							+ "\",\"isqt\":\"" + map.get("isqt") 
							+ "\",\"stuId\":\"" + map.get("stuId") 
							+ "\",\"stuName\":\"" + map.get("stuName") 
							+ "\",\"dktext\":\"" + map.get("dktext") 
							+ "\",\"start1\":\"" + map.get("start1") 
							+ "\",\"start2\":\"" + map.get("start2") 
							+ "\",\"end1\":\"" + map.get("end1") 
							+ "\",\"end2\":\"" + map.get("end2") 
							+ "\",\"run1\":\"" + map.get("run1") 
							+ "\",\"run2\":\"" + map.get("run2") 
							+ "\"}";
							if(i<len2-1){
								json2 +=",";
							}
						}
						json2 +="]}";
					}
					out.print(json2);
					break;
				default:
					out.print("{\"code\":201,\"Msg\":\"非法访问3\"}");
					jdbcBean.addLog(user.getId(), user.getStuId(), user.getStuName(), "post","尝试访问【"+request.getServletPath()+"】失败，非法操作信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
			}
		}else{
			out.print("{\"code\":201,\"Msg\":\"非法访问4\"}");
			jdbcBean.addLog(user.getId(), user.getStuId(), user.getStuName(), "post","尝试访问【"+request.getServletPath()+"】失败，无操作信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
		}
	}
}