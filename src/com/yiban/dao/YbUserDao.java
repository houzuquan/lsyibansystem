package com.yiban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.yiban.bean.StringCode;
import com.yiban.bean.jdbcBean;
import com.yiban.struct.YbUserStruct;

public class YbUserDao extends YbUserStruct {
	private Connection conn;
	private PreparedStatement ps;
	private String ErrorMsg;
	public YbUserDao(){
		try{
			conn=jdbcBean.getConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(Exception e){
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	private Connection getConn() throws SQLException{
		if(conn == null || conn.isClosed()){
			conn=jdbcBean.getConnection();
		}
		System.out.println("数据库连接是否关闭："+conn.isClosed());
		return conn;
	}
	/**
	 * @param errorMsg
	 * 设置错误信息，共外部调用
	 */
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
//		System.out.println(errorMsg);
		
	}
	/**
	 * @return ErrorMsg
	 * 返回错误信息
	 */
	public String getErrorMsg() {
		return ErrorMsg;
	}
	/**
	 * @param stuId
	 * @param pass
	 * @return boolean
	 * 传入学号和密码，判断是否能够登录，登录成功返回true否则返回false
	 */
	public boolean isLogin(String stuId,String pass){
		if(stuId == null || pass == null || stuId.equals("") == true || pass.equals("") == true ){
			setErrorMsg("帐号或密码不能为空");
			return false;
		}
		if(true == this.findUser(stuId)){
			if(this.getIsLogin().equals("0")){
				setErrorMsg("该用户不可登录");
			}else if(true == StringCode.MD5(pass).equals(this.getPass())){
				return true;
			}else{
				setErrorMsg("帐号或密码错误");
			}
			return false;
		}else{
			setErrorMsg("不存在帐号");
			return false;
		}
	}
	/**
	 * @param stuId
	 * @return boolean
	 * 通过学号找到第一个用户，并设置信息，找到返回true否则返回false
	 */
	public boolean findUser(String stuId){
		int id = -1;
		boolean re = false;
		try{
			if(StringCode.isInteger(stuId) == true){
				id=Integer.parseInt(stuId);
				re = this.findUser(id);
			}else{
				id=-1;
				setErrorMsg("帐号类型不正确");
			}
		}catch(NumberFormatException e){
			setErrorMsg("帐号类型不正确");
			e.printStackTrace();
		}catch(Exception e){
			setErrorMsg("帐号类型不正确");
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * @param stuId
	 * @return boolean
	 * 通过学号找到第一个用户，并设置信息，找到返回true否则返回false
	 */
	public boolean findUser(int stuId){
		boolean re = false;
		String sql = "SELECT * FROM (SELECT a.*,b.sectionName,b.allow FROM `YbUser` a LEFT OUTER JOIN `section` b ON a.sectionId=b.sectionId ) d WHERE d.`stuId` = ?";
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		Logger log = Logger.getLogger(this.getClass().getName());
		try {
			Connection conn = this.getConn();
			ps2 = conn.prepareStatement(sql);
			ps2.setString(1, Integer.toString(stuId));
			log.info("查找用户："+ps2.toString());
			rs = ps2.executeQuery();
			if(rs.next()){
				re = true;
				this.setId(rs.getString("id"));
				this.setStuId(rs.getString("stuId"));
				this.setStuName(rs.getString("stuName"));
				this.setPass(rs.getString("pass"));
				this.setSectionId(rs.getString("sectionId"));
				this.setStuClass(rs.getString("stuClass"));
				this.setBankId(rs.getString("bankId"));
				this.setBirthday(rs.getString("birthday"));
				this.setBirthdayType(rs.getString("birthdayType"));
				this.setHobby(rs.getString("hobby"));
				this.setPhone(rs.getString("phone"));
				this.setAddTime(rs.getString("addTime"));
				this.setAddIP(rs.getString("addIP"));
				this.setAddUa(rs.getString("addUa"));
				this.setLoginTime(rs.getString("loginTime"));
				this.setLoginIP(rs.getString("loginIP"));
				this.setLoginUa(rs.getString("loginUa"));
				this.setLoginNum(rs.getString("loginNum"));
				this.setIsLogin(rs.getString("isLogin"));
				this.setOtherAllow(rs.getString("otherAllow"));
				this.setSectionName(rs.getString("sectionName"));
				this.setSectionAllow(rs.getString("allow"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(rs, ps2, null);
		}
		return re;
	}
	/**
	 * @param isQd 判断是签到时间还是签退时间
	 * @return HashMap<String,String>
	 * 找到当前的签到时间段，并返回
	 */
	public HashMap<String,String> findNowDk(boolean isQd){
		/*传入一个参数，判断是获取签到时间还是获取签退时间*/
		HashMap<String,String> dk = new HashMap<String,String>();
		String NowDkSql;
		if(isQd){
			NowDkSql ="select * from `dk` where curtime() >= start1 and curtime() < end1 and isrun=1";//获取签到时间
		}else{
			NowDkSql ="select * from `dk` where curtime() >= end1 and curtime() <= end2 and isrun=1";//获取签退时间
		}
		ResultSet rs = null;
		try {
			Connection conn = this.getConn();
			ps = conn.prepareStatement(NowDkSql);
			rs = ps.executeQuery();
			ResultSetMetaData colname = rs.getMetaData();
			int colnum = colname.getColumnCount();
			int i;
			if(rs.next()){
				for(i=1;i<=colnum;i++){
					String cName = colname.getColumnName(i);
					dk.put(cName, rs.getString(cName));
				}
			}else{
				return null;
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		finally{
			jdbcBean.free(rs, ps, null);
		}
		return dk;
	}
	/**
	 * @return boolean
	 * 打卡签到
	 */
	public boolean dk_qd(){
		/*进行打卡签到操作*/
		boolean re = false;
		HashMap<String,String> nowdk = this.findNowDk(true);
		if(nowdk == null || nowdk.isEmpty() == true){
			setErrorMsg("不在打卡时间内！");
			return false;
		}
		if(this.dk_isqd(nowdk) == true){
			setErrorMsg("签到失败，当前时间段已经签到过了！");
			return false;
		}
		PreparedStatement ps2 = null;
		try {
			String dkid = nowdk.get("dkid");
			String qdSql = "insert into `dklog` (`dkid`,`time1`,`ybuserid`,`isqd`) values (?,now(),?,'1')";
			Connection conn = this.getConn();
			ps2 = conn.prepareStatement(qdSql);
			ps2.setString(1, dkid);
			ps2.setString(2, this.getId());
			int insertId = ps2.executeUpdate();
			if(insertId > 0){
				re = true;
			}else{
				setErrorMsg("签到失败，请稍候重试！");
				re = false;
			}
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "qd",this.getStuId() + this.getStuName()+"签到；结果："+re);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(null, ps2, null);
		}
		return re;
	}
	/**
	 * @param nowdk 传入当前时间段打卡时间信息
	 * @return boolean
	 * 判断一个打卡时间段是否存在签到记录
	 */
	public boolean dk_isqd(HashMap<String,String> nowdk){
		/*判断当前时间段是否存在打卡签到信息*/
		boolean re = false;
		if(nowdk == null || nowdk.isEmpty() == true){
			setErrorMsg("不在打卡时间内！");
			return false;
		}
		String dkid = nowdk.get("dkid");
		String isqdSql = "select * from `dklog` where dkid=? and ybuserid = ? and date(`time1`) = curdate()";
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			Connection conn = this.getConn();
			ps2 = conn.prepareStatement(isqdSql);
			ps2.setString(1, dkid);
			ps2.setString(2, this.getId());
			rs2 = ps2.executeQuery();
			if(rs2.next()){
				re = true;
			}else{
				setErrorMsg("当前时间段没有打卡签到记录！");
				re = false;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(rs2, ps2, null);
		}
		return re;
	}
	/**
	 * @param qtText 签退内容
	 * @return boolean
	 * 打卡签退
	 */
	public boolean dk_qt(String qtText){
		/*进行打卡签退操作*/
		boolean re = false;
		HashMap<String,String> nowdk = this.findNowDk(false);
		if(nowdk == null || nowdk.isEmpty() == true){
			setErrorMsg("不在打卡签退时间内！");
			return false;
		}
		PreparedStatement ps2 = null;
		try {
			String dkid = nowdk.get("dkid");
//			String start1 = nowdk.get("start1");
//			String end2 = nowdk.get("end2");
			String qtSql = "update `dklog` set isqt = '1',text=?,time2=now() where dkid = ? and ybuserid = ? and date(`time1`) = curdate()";
			Connection conn = this.getConn();
			ps2 = conn.prepareStatement(qtSql);
			ps2.setString(1, qtText);
			//ps2.setString(2, start1);
			//ps2.setString(3, end2);
			ps2.setString(2, dkid);
			ps2.setString(3, this.getId());
			int insertId = ps2.executeUpdate();
			if(insertId > 0){
				re = true;
			}else{
				setErrorMsg("签退失败，请稍候重试！\\n原因：或许您本次值班未签到！");
				re = false;
			}
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "qt",this.getStuId() + this.getStuName()+"签退；结果："+re+"；签退内容："+qtText);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(null, ps2, null);
		}
		return re;
	}
	/**
	 * @param request 请求信息 HttpServletRequest ，更新登录信息
	 * 登录时更新当前用户的登录信息
	 */
	public void updateLoginInfo(HttpServletRequest request){
		/*进行登录操作是更新登录数据*/
		String Sql = "update `YbUser` set `loginTime`=now(),`loginIP`=?,`loginUa`=?,`loginNum`=`loginNum`+1 where `id`=?";
		PreparedStatement ps2 = null;
		try {
			Connection conn = this.getConn();
			ps2 = conn.prepareStatement(Sql);
			ps2.setString(1, StringCode.getRealIp(request));
			ps2.setString(2, request.getHeader("user-agent"));
			ps2.setString(3, this.getId());
			ps2.executeUpdate();
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "login",this.getStuId() + this.getStuName()+"登录系统；IP："+StringCode.getRealIp(request)+"；UA："+request.getHeader("user-agent"));
			jdbcBean.free(null, ps2, null);
		}catch(SQLException e){
			setErrorMsg(e.getMessage());
		}catch (Exception e) {
			// TODO 自动生成的 catch 块
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(null, ps2, null);
		}
	}
	/**
	 * @return HashMap<String,String> 返回打卡总数统计
	 * 获取当前用户的所有签到和签退统计数
	 */
	public HashMap<String,String> getDKAllCount(){
		/*获取当前用户的打卡数量统计*/
		HashMap<String,String> dkcount = new HashMap<String,String>();
		dkcount.put("qdNum", "0");
		dkcount.put("qtNum", "0");
		String Sql = "select sum(isqd) as 'qdNum',sum(isqt) as 'qtNum' from `dklog` where ybuserid=?";
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			Connection conn = this.getConn();
			ps2 = conn.prepareStatement(Sql);
			ps2.setString(1, this.getId());
			rs2 = ps2.executeQuery();
			if(rs2.next()){
				String qdNum = rs2.getString("qdNum");
				String qtNum = rs2.getString("qtNum");
				dkcount.put("qdNum", qdNum == null ? "0" : qdNum );
				dkcount.put("qtNum", qtNum == null ? "0" : qtNum );
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(rs2, ps2, null);
		}
		return dkcount;
	}
	/**
	 * @param dateMonth 传入月份，找出这个月的打卡日期信息
	 * @return ArrayList<String>
	 * 传入一个月份信息，返回这个月都有哪天有签到记录信息
	 */
	public ArrayList<String> getDKMonthHaveLog(String dateMonth){
		/*通过传入一个月份，获取这个月份都有哪些日子有签到内容，并返回有签到内容的日期*/
		ArrayList<String> list = new ArrayList<String>();
		String sql;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			Connection conn = this.getConn();
			if(dateMonth == null || dateMonth.equals("")){
				sql = "select DISTINCT date(`time1`) as 'date' from `dklog` where `ybuserid`=? and date_format(`time1`,'%Y-%m')=date_format(now(),'%Y-%m')";
				ps2 = conn.prepareStatement(sql);
				ps2.setString(1, this.getId());
			}else{
				sql = "select DISTINCT date(`time1`) as 'date' from `dklog` where `ybuserid`=? and date_format(`time1`,'%Y-%m')=date_format(?,'%Y-%m')";
				ps2 = conn.prepareStatement(sql);
				ps2.setString(1, this.getId());
				ps2.setString(2, dateMonth);
			}
			rs2 = ps2.executeQuery();
			while(rs2.next()){
				String date = rs2.getString("date");
				if(date != null){
					list.add(date);
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(rs2, ps2, null);
		}
		return list;
	}
	/**
	 * @param toDate 传入日期，找到这天的具体打卡信息
	 * @return ArrayList<HashMap<String,String>> 返回打卡信息键值对数组
	 * 传入一个日期参数，返回这个日期的签到记录
	 */
	public ArrayList<HashMap<String,String>> getDKDateLog(String toDate,String page){
		/*传入一个日期，获取这个日期的具体打卡签到信息*/
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String sql;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			Connection conn = this.getConn();
			if(page == null){//按日期查找
				if(toDate == null || toDate.equals("")){
					sql = "select * from `viewdklog` where `ybuserid`=? and date(`time1`)=curdate()";
					ps2 = conn.prepareStatement(sql);
					ps2.setString(1, this.getId());
				}else{
					sql = "select * from `viewdklog` where `ybuserid`=? and date(`time1`)=date(?)";
					ps2 = conn.prepareStatement(sql);
					ps2.setString(1, this.getId());
					ps2.setString(2, toDate);
				}
			}else if(toDate == null){//分页显示
				if(page == null || page.equals("")){
					sql = "select * from `viewdklog` where `ybuserid`=? order by `time1` desc limit 0,10";
					ps2 = conn.prepareStatement(sql);
					ps2.setString(1, this.getId());
				}else{
					int start = 0;
					if(StringCode.isInteger(page)){
						start = (Integer.parseInt(page)-1)*10;
					}
					sql = "select * from `viewdklog` where `ybuserid`=? order by `time1` desc limit ?,10";
					ps2 = conn.prepareStatement(sql);
					ps2.setString(1, this.getId());
					ps2.setInt(2, start);
				}
			}
//			System.out.println("toDate:"+toDate+";page:"+page);
//			System.out.println(ps2.toString());
			
			rs2 = ps2.executeQuery();
			ResultSetMetaData colname = rs2.getMetaData();
			int colnum = colname.getColumnCount();
			int i;
			while(rs2.next()){
				HashMap<String,String> map = new HashMap<String,String>();
				for(i=1;i<=colnum;i++){
					String cName = colname.getColumnName(i);
					map.put(cName, rs2.getString(cName));
				}
				list.add(map);
			}
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "getDKDateLog",this.getStuId() + this.getStuName()+"获取日期："+toDate+"打卡内容");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(rs2, ps2, null);
		}
		return list;
	}
	/**
	 * @return ArrayList<HashMap<String,String>> 返回部门键值对数组
	 * 返回所有部门信息
	 */
	public ArrayList<HashMap<String,String>> getSection(){
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String sql;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try {
			Connection conn = this.getConn();
			sql = "SELECT `sectionId` as 'id',`sectionName` as 'text' FROM `section`";
			ps2 = conn.prepareStatement(sql);
			rs2 = ps2.executeQuery();
			ResultSetMetaData colname = rs2.getMetaData();
			int colnum = colname.getColumnCount();
			int i;
			while(rs2.next()){
				HashMap<String,String> map = new HashMap<String,String>();
				for(i=1;i<=colnum;i++){
					String cName = colname.getColumnName(i);
					map.put(cName, rs2.getString(cName));
				}
				list.add(map);
			}
			jdbcBean.free(rs2, ps2, null);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.free(rs2, ps2, null);
		}
		return list;
	}
	/**
	 * @param page 页数，
	 * @param section 部门id
	 * @param time1 开始时间
	 * @param time2 结束时间
	 * @return ArrayList<HashMap<String,String>>
	 * 查找一个时间段内的某个部门的打卡信息，更具页数显示相关页信息
	 */
	public ArrayList<HashMap<String,String>> searchOtherDK(String page,String section,String time1,String time2){
		if(!this.isHaveAllow("21,")){
			this.setErrorMsg("没有权限访问该选项！");
			return null;
		}
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String sql;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int start = 0;
		if(StringCode.isInteger(page)){
			start = (Integer.parseInt(page)-1)*10;
		}
		String ttime = "";
		if(time1 != null && !time1.equals("")){
			ttime+=" and date(b.time1)>='"+time1+"' ";
		}
		if(time2 != null && !time2.equals("")){
			ttime+=" and date(b.time1)<='"+time2+"' ";
		}
		try {
			Connection conn = this.getConn();
			if(section.equals("0")){
				if(ttime.equals("")){
					sql="SELECT c.stuName 'name',a.sectionName 'sectionName',b.time1,b.time2,b.text,b.isqd,b.isqt,d.text 'dktext',d.start1,d.start2,d.end1,d.end2,d.run1,d.run2 FROM `section` a,`dklog` b,`YbUser` c,`dk` d WHERE a.sectionid=c.sectionid and b.ybuserid=c.id and d.dkid=b.dkid order by b.time1 desc limit ?,10";
					ps2 = conn.prepareStatement(sql);
					ps2.setInt(1, start);
				}else{
					sql = "SELECT c.stuName 'name',a.sectionName 'sectionName',b.time1,b.time2,b.text,b.isqd,b.isqt,d.text 'dktext',d.start1,d.start2,d.end1,d.end2,d.run1,d.run2 FROM `section` a,`dklog` b,`YbUser` c,`dk` d WHERE a.sectionid=c.sectionid and b.ybuserid=c.id and d.dkid=b.dkid :ttime order by b.time1 desc limit ?,10";
					sql=sql.replaceAll(":ttime", ttime);
					ps2 = conn.prepareStatement(sql);
					ps2.setInt(1, start);
				}
			}else{
				if(ttime.equals("")){
					sql = "SELECT c.stuName 'name',a.sectionName 'sectionName',b.time1,b.time2,b.text,b.isqd,b.isqt,d.text 'dktext',d.start1,d.start2,d.end1,d.end2,d.run1,d.run2 FROM `section` a,`dklog` b,`YbUser` c,`dk` d WHERE a.sectionid=c.sectionid and b.ybuserid=c.id and d.dkid=b.dkid and c.sectionid=? order by b.time1 desc limit ?,10";
					ps2 = conn.prepareStatement(sql);
					ps2.setString(1, section);
					ps2.setInt(2, start);
				}else{
					sql = "SELECT c.stuName 'name',a.sectionName 'sectionName',b.time1,b.time2,b.text,b.isqd,b.isqt,d.text 'dktext',d.start1,d.start2,d.end1,d.end2,d.run1,d.run2 FROM `section` a,`dklog` b,`YbUser` c,`dk` d WHERE a.sectionid=c.sectionid and b.ybuserid=c.id and d.dkid=b.dkid and c.sectionid=? :ttime order by b.time1 desc limit ?,10";
					sql=sql.replaceAll(":ttime", ttime);
					ps2 = conn.prepareStatement(sql);
					ps2.setString(1, section);
					ps2.setInt(2, start);
				}
			}
			rs2 = ps2.executeQuery();
			ResultSetMetaData colname = rs2.getMetaData();
			int colnum = colname.getColumnCount();
			int i;
			while(rs2.next()){
				HashMap<String,String> map = new HashMap<String,String>();
				for(i=1;i<=colnum;i++){
					String cName = colname.getColumnName(i);
					map.put(cName, rs2.getString(cName));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			this.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		finally{
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "searchOtherDK",this.getStuId() + this.getStuName()+"获取其他部门："+section+"第"+page+"页打卡内容");
			jdbcBean.free(rs2, ps2, null);
		}
		return list;
	}
	/**
	 * @param allow 权限代码
	 * @return boolean
	 * 传入权限代码，判断这个用户是否有相关权限，有则返回true
	 */
	public boolean isHaveAllow(String allow){
		/*判断当前用户是否拥有某种权限*/
		String sectionAllow = this.getSectionAllow();
		String otherAllow = this.getOtherAllow();
		if(sectionAllow != null && sectionAllow.indexOf("1,") >= 0){
			return true;
		}
		if(otherAllow != null && otherAllow.indexOf("1,") >= 0){
			return true;
		}
		if(sectionAllow != null && sectionAllow.indexOf(allow) >= 0){
			return true;
		}
		if(otherAllow != null && otherAllow.indexOf(allow) >= 0){
			return true;
		}
		return false;
	}
	public void Destroyed(){
		jdbcBean.free(null, ps, conn);
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
