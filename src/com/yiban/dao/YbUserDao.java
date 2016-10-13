package com.yiban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
			// TODO �Զ����ɵ� catch ��
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(Exception e){
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * @param errorMsg
	 * ���ô�����Ϣ�����ⲿ����
	 */
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
//		System.out.println(errorMsg);
		
	}
	/**
	 * @return ErrorMsg
	 * ���ش�����Ϣ
	 */
	public String getErrorMsg() {
		return ErrorMsg;
	}
	/**
	 * @param stuId
	 * @param pass
	 * @return boolean
	 * ����ѧ�ź����룬�ж��Ƿ��ܹ���¼����¼�ɹ�����true���򷵻�false
	 */
	public boolean isLogin(String stuId,String pass){
		if(stuId == null || pass == null || stuId.equals("") == true || pass.equals("") == true ){
			setErrorMsg("�ʺŻ����벻��Ϊ��");
			return false;
		}
		if(true == this.findUser(stuId)){
			if(true == StringCode.MD5(pass).equals(this.getPass())){
				return true;
			}else{
				setErrorMsg("�ʺŻ��������");
			}
			return false;
		}else{
			setErrorMsg("�������ʺ�");
			return false;
		}
	}
	/**
	 * @param stuId
	 * @return boolean
	 * ͨ��ѧ���ҵ���һ���û�����������Ϣ���ҵ�����true���򷵻�false
	 */
	public boolean findUser(String stuId){
		int id = -1;
		try{
			if(StringCode.isInteger(stuId) == true){
				id=Integer.parseInt(stuId);
			}else{
				id=-1;
			}
		}catch(NumberFormatException e){
			setErrorMsg("�ʺ����Ͳ���ȷ");
			e.printStackTrace();
		}catch(Exception e){
			setErrorMsg("�ʺ����Ͳ���ȷ");
			e.printStackTrace();
		}
		return this.findUser(id);
	}
	/**
	 * @param stuId
	 * @return boolean
	 * ͨ��ѧ���ҵ�һ���û���Ϣ
	 */
	public boolean findUser(int stuId){
		boolean re = false;
		String sql = "SELECT * FROM `YbUser` WHERE `stuId` = ?";
		PreparedStatement ps2 = null;
		try {
			ps2 = conn.prepareStatement(sql);
			ps2.setString(1, Integer.toString(stuId));
			ResultSet rs = ps2.executeQuery();
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
			}
			jdbcBean.free(rs, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			setErrorMsg(e.getMessage());
			System.out.println(ps2);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * @param isQd
	 * @return HashMap<String,String>
	 * �ҵ���ǰ��ǩ��ʱ��Σ�������
	 */
	public HashMap<String,String> findNowDk(boolean isQd){
		/*����һ���������ж��ǻ�ȡǩ��ʱ�仹�ǻ�ȡǩ��ʱ��*/
		HashMap<String,String> dk = new HashMap<String,String>();
		String NowDkSql;
		if(isQd){
			NowDkSql ="select * from `dk` where curtime() >= start1 and curtime() <= start2 ";//��ȡǩ��ʱ��
		}else{
			NowDkSql ="select * from `dk` where curtime() >= end1 and curtime() <= end2 ";//��ȡǩ��ʱ��
		}
		try {
			ps = conn.prepareStatement(NowDkSql);
			ResultSet rs = ps.executeQuery();
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
			jdbcBean.free(rs, ps, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
		return dk;
	}
	/**
	 * @return boolean
	 * ��ǩ��
	 */
	public boolean dk_qd(){
		/*���д�ǩ������*/
		boolean re = false;
		HashMap<String,String> nowdk = this.findNowDk(true);
		if(nowdk == null || nowdk.isEmpty() == true){
			setErrorMsg("���ڴ�ʱ���ڣ�");
			return false;
		}
		if(this.dk_isqd(nowdk) == true){
			setErrorMsg("ǩ��ʧ�ܣ���ǰʱ����Ѿ�ǩ�����ˣ�");
			return false;
		}
		try {
			String dkid = nowdk.get("dkid");
			String qdSql = "insert into `dklog` (`dkid`,`time1`,`ybuserid`,`isqd`) values (?,now(),?,'1')";
			PreparedStatement ps2 = conn.prepareStatement(qdSql);
			ps2.setString(1, dkid);
			ps2.setString(2, this.getId());
			int insertId = ps2.executeUpdate();
			if(insertId > 0){
				re = true;
			}else{
				setErrorMsg("ǩ��ʧ�ܣ����Ժ����ԣ�");
				re = false;
			}
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "qd",this.getStuId() + this.getStuName()+"ǩ���������"+re);
			jdbcBean.free(null, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * @param nowdk
	 * @return boolean
	 * �ж�һ����ʱ����Ƿ����ǩ����¼
	 */
	public boolean dk_isqd(HashMap<String,String> nowdk){
		/*�жϵ�ǰʱ����Ƿ���ڴ�ǩ����Ϣ*/
		boolean re = false;
		if(nowdk == null || nowdk.isEmpty() == true){
			setErrorMsg("���ڴ�ʱ���ڣ�");
			return false;
		}
		String dkid = nowdk.get("dkid");
		String isqdSql = "select * from `dklog` where dkid=? and ybuserid = ? and date(`time1`) = curdate()";
		PreparedStatement ps2;
		try {
			ps2 = conn.prepareStatement(isqdSql);
			ps2.setString(1, dkid);
			ps2.setString(2, this.getId());
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()){
				re = true;
			}else{
				setErrorMsg("��ǰʱ���û�д�ǩ����¼��");
				re = false;
			}
			jdbcBean.free(rs2, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * @param qtText
	 * @return boolean
	 * ��ǩ��
	 */
	public boolean dk_qt(String qtText){
		/*���д�ǩ�˲���*/
		boolean re = false;
		HashMap<String,String> nowdk = this.findNowDk(false);
		if(nowdk == null || nowdk.isEmpty() == true){
			setErrorMsg("���ڴ�ǩ��ʱ���ڣ�");
			return false;
		}
		try {
			String dkid = nowdk.get("dkid");
//			String start1 = nowdk.get("start1");
//			String end2 = nowdk.get("end2");
			String qtSql = "update `dklog` set isqt = '1',text=?,time2=now() where dkid = ? and ybuserid = ? and date(`time1`) = curdate()";
			PreparedStatement ps2 = conn.prepareStatement(qtSql);
			ps2.setString(1, qtText);
			//ps2.setString(2, start1);
			//ps2.setString(3, end2);
			ps2.setString(2, dkid);
			ps2.setString(3, this.getId());
			int insertId = ps2.executeUpdate();
			if(insertId > 0){
				re = true;
			}else{
				setErrorMsg("ǩ��ʧ�ܣ����Ժ����ԣ�\\nԭ�򣺻���������ֵ��δǩ����");
				re = false;
			}
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "qt",this.getStuId() + this.getStuName()+"ǩ�ˣ������"+re+"��ǩ�����ݣ�"+qtText);
			jdbcBean.free(null, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * @return HashMap<String,String>
	 * ͨ�����ݿ���ͼ�㷵�ص�ǰ�û������й�����Ϣ
	 */
	public HashMap<String,String> getMyUserInfo(){
		/*��ȡ��ǰ�û���ȫ����Ϣ*/
		HashMap<String,String> myinfo = new HashMap<String,String>();
		String NowDkSql;
		NowDkSql ="select * from `userInfo` where id=?";//��ȡǩ��ʱ��
		try {
			ps = conn.prepareStatement(NowDkSql);
			ps.setString(1, this.getId());
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData colname = rs.getMetaData();
			int colnum = colname.getColumnCount();
			int i;
			if(rs.next()){
				for(i=1;i<=colnum;i++){
					String cName = colname.getColumnName(i);
					myinfo.put(cName, rs.getString(cName));
				}
//				myinfo.put("id",rs.getString("id"));
//				myinfo.put("stuId",rs.getString("stuId"));
//				myinfo.put("stuName",rs.getString("stuName"));
//				myinfo.put("pass",rs.getString("pass"));
//				myinfo.put("sectionId",rs.getString("sectionId"));
//				myinfo.put("stuClass",rs.getString("stuClass"));
//				myinfo.put("bankId",rs.getString("bankId"));
//				myinfo.put("birthday",rs.getString("birthday"));
//				myinfo.put("birthdayType",rs.getString("birthdayType"));
//				myinfo.put("hobby",rs.getString("hobby"));
//				myinfo.put("phone",rs.getString("phone"));
//				myinfo.put("addTime",rs.getString("addTime"));
//				myinfo.put("addIP",rs.getString("addIP"));
//				myinfo.put("addUa",rs.getString("addUa"));
//				myinfo.put("loginTime",rs.getString("loginTime"));
//				myinfo.put("loginIP",rs.getString("loginIP"));
//				myinfo.put("loginUa",rs.getString("loginUa"));
//				myinfo.put("sectionName",rs.getString("sectionName"));
			}
			jdbcBean.free(rs, ps, null);
		}catch(SQLException e){
			setErrorMsg(e.getMessage());
		}catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return myinfo;
	}
	/**
	 * @param request
	 * ��¼ʱ���µ�ǰ�û��ĵ�¼��Ϣ
	 */
	public void updateLoginInfo(HttpServletRequest request){
		/*���е�¼�����Ǹ��µ�¼����*/
		String Sql = "update `YbUser` set `loginTime`=now(),`loginIP`=?,`loginUa`=?,`loginNum`=`loginNum`+1 where `id`=?";
		PreparedStatement ps2;
		try {
			ps2 = conn.prepareStatement(Sql);
			ps2.setString(1, StringCode.getRealIp(request));
			ps2.setString(2, request.getHeader("user-agent"));
			ps2.setString(3, this.getId());
			ps2.executeUpdate();
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "login",this.getStuId() + this.getStuName()+"��¼ϵͳ��IP��"+StringCode.getRealIp(request)+"��UA��"+request.getHeader("user-agent"));
			jdbcBean.free(null, ps2, null);
		}catch(SQLException e){
			setErrorMsg(e.getMessage());
		}catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * @return
	 * ��ȡ��ǰ�û�������ǩ����ǩ��ͳ����
	 */
	public HashMap<String,String> getDKAllCount(){
		/*��ȡ��ǰ�û��Ĵ�����ͳ��*/
		HashMap<String,String> dkcount = new HashMap<String,String>();
		dkcount.put("qdNum", "0");
		dkcount.put("qtNum", "0");
		String Sql = "select sum(isqd) as 'qdNum',sum(isqt) as 'qtNum' from `dklog` where ybuserid=?";
		PreparedStatement ps2;
		try {
			ps2 = conn.prepareStatement(Sql);
			ps2.setString(1, this.getId());
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()){
				String qdNum = rs2.getString("qdNum");
				String qtNum = rs2.getString("qtNum");
				dkcount.put("qdNum", qdNum == null ? "0" : qdNum );
				dkcount.put("qtNum", qtNum == null ? "0" : qtNum );
			}
			jdbcBean.free(rs2, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return dkcount;
	}
	/**
	 * @param dateMonth
	 * @return ArrayList<String>
	 * ����һ���·���Ϣ����������¶���������ǩ����¼��Ϣ
	 */
	public ArrayList<String> getDKMonthHaveLog(String dateMonth){
		/*ͨ������һ���·ݣ���ȡ����·ݶ�����Щ������ǩ�����ݣ���������ǩ�����ݵ�����*/
		ArrayList<String> list = new ArrayList<String>();
		String sql;
		PreparedStatement ps2;
		try {
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
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				String date = rs2.getString("date");
				if(date != null){
					list.add(date);
				}
			}
			jdbcBean.free(rs2, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param toDate
	 * @return ArrayList<HashMap<String,String>>
	 * ����һ�����ڲ���������������ڵ�ǩ����¼
	 */
	public ArrayList<HashMap<String,String>> getDKDateLog(String toDate,String page){
		/*����һ�����ڣ���ȡ������ڵľ����ǩ����Ϣ*/
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String sql;
		PreparedStatement ps2 = null;
		try {
			if(page == null){//�����ڲ���
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
			}else if(toDate == null){//��ҳ��ʾ
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
			
			ResultSet rs2 = ps2.executeQuery();
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
			jdbcBean.addLog(this.getId(), this.getStuId(), this.getStuName(), "getDKDateLog",this.getStuId() + this.getStuName()+"��ȡ���ڣ�"+toDate+"������");
			jdbcBean.free(rs2, ps2, null);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param allow
	 * @return boolean
	 * ����Ȩ�޴��룬�ж�����û��Ƿ������Ȩ�ޣ����򷵻�true
	 */
	public boolean isHaveAllow(String allow){
		/*�жϵ�ǰ�û��Ƿ�ӵ��ĳ��Ȩ��*/
		return false;
	}
}
