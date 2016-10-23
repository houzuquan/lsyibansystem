package com.yiban.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    private HttpServletRequest request =null;
    private HttpServletResponse response = null;
    private HttpSession session = null;
    private YbUserDao user = null;
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
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		
		out.print("{\"code\":201,\"Msg\":\"非法访问1\"}");
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
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.user = (YbUserDao)session.getAttribute("User");
		String action = request.getParameter("action");
		String backStr = null;
		if(this.user == null){
			backStr = "{\"code\":201,\"Msg\":\"非法访问2\"}";
			jdbcBean.addLog(null, null, null, "post","尝试访问【"+request.getServletPath()+"】失败，无user信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
		}else if(action != null){
			switch(action){
				case "qd":
					backStr = this.qd();
					break;
				case "qt":
					backStr = this.qt();
					break;
				case "getDKAllCount":
					backStr = this.getDKAllCount();
					break;
				case "getDKMonthHaveLog":
					backStr = this.getDKMonthHaveLog();
					break;
				case "getDKDateLog":
					backStr = this.getDKDateLog();
					break;
				case "getDKForPage":
					backStr = this.getDKForPage();
					break;
				case "getSection":
					backStr = this.getSection();
					break;
				case "searchOtherDK":
					backStr = this.searchOtherDK();
					break;
				default:
					backStr = "{\"code\":201,\"Msg\":\"非法访问3\"}";
					jdbcBean.addLog(user.getId(), user.getStuId(), user.getStuName(), "post","尝试访问【"+request.getServletPath()+"】失败，非法操作信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
			}
		}else{
			backStr = "{\"code\":201,\"Msg\":\"非法访问4\"}";
			jdbcBean.addLog(user.getId(), user.getStuId(), user.getStuName(), "post","尝试访问【"+request.getServletPath()+"】失败，无操作信息；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
		}
		out.print(backStr);
	}
	public String qd(){
		String re = null;
		if(user.dk_qd() == true){
			re = "{\"code\":200,\"Msg\":\"签到成功！\"}";
		}else{
			re = "{\"code\":201,\"Msg\":\"签到失败！"+user.getErrorMsg()+"\"}";
		}
		return re;
	}
	public String qt(){
		String re = null;
		String text = request.getParameter("text");
		if(user.dk_qt(text) == true){
			re = "{\"code\":200,\"Msg\":\"签退成功！\"}";
		}else{
			re = "{\"code\":201,\"Msg\":\"签退失败！"+user.getErrorMsg()+"\"}";
		}
		return re;
	}
	public String getDKAllCount(){
		String re = null;
		HashMap<String,String> dkcount = user.getDKAllCount();
		re = "{\"qdNum\":\""+dkcount.get("qdNum")+"\",\"qtNum\":\""+dkcount.get("qtNum")+"\"}";
		return re;
	}
	public String getDKMonthHaveLog(){
		String re = null;
		String month = request.getParameter("month");
		ArrayList<String> list = user.getDKMonthHaveLog(month);
		if(list == null){
			re = "{\"code\":201,\"Msg\":\""+user.getErrorMsg()+"\"}";
		}else{
			re = this.getArrayListToString(list);
		}
		return re;
	}
	public String getDKDateLog(){
		String re = null;
		ArrayList<HashMap<String,String>> list = null;
		String toDate = request.getParameter("date");
		list = user.getDKDateLog(toDate,null);
		if(list == null){
			re = "{\"code\":201,\"Msg\":\""+user.getErrorMsg()+"\"}";
		}else{
			re = this.getListHashMapToString(list);
		}
		return re;
	}
	public String getDKForPage(){
		String re = null;
		ArrayList<HashMap<String,String>> list = null;
		String page = request.getParameter("page");
		list = user.getDKDateLog(null,page);
		if(list == null){
			re = "{\"code\":201,\"Msg\":\""+user.getErrorMsg()+"\"}";
		}else{
			re = this.getListHashMapToString(list);
		}
		return re;
	}
	public String getSection(){
		String re = null;
		ArrayList<HashMap<String, String>> list = user.getSection();
		if(list == null){
			re = "{\"code\":201,\"Msg\":\""+user.getErrorMsg()+"\"}";
		}else{
			re = this.getListHashMapToString(list);
		}
		return re;
	}
	public String searchOtherDK(){
		String re = null;
		String page = request.getParameter("page");
		String section = request.getParameter("section");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		ArrayList<HashMap<String, String>> list = user.searchOtherDK(page, section,time1,time2);
		if(list == null){
			re = "{\"code\":201,\"Msg\":\""+user.getErrorMsg()+"\"}";
		}else{
			re = this.getListHashMapToString(list);
		}
		return re;
	}
	private String getListHashMapToString(ArrayList<HashMap<String, String>> list){
		int len = list.size();
		StringBuffer json = new StringBuffer("{\"list\":[");
		for(int i=0;i<len;i++){
			HashMap<String,String> map = list.get(i);
			Iterator<String> it = map.keySet().iterator();
			json.append("{");
			while(it.hasNext()){
				String key = it.next();
				json.append("\""+key+"\":\""+map.get(key)+"\"");
				if(it.hasNext()){
					json.append(",");
				}
			}
			json.append("}");
			if(i<len-1){
				json.append(",");
			}
		}
		json.append("]}");
		return json.toString();
	}
	private String getArrayListToString(ArrayList<String> list){
		int len = list.size();
		StringBuffer json = new StringBuffer("{\"list\":[");
		for(int i=0;i<len;i++){
			String value = list.get(i);
			json.append("\""+value+"\"");
			if(i<len-1){
				json.append(",");
			}
		}
		json.append("]}");
		return json.toString();
	}
}