package com.yiban.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbcBean {
	public static String driver;
	public static String url;
	public static String user;
	public static String password;
	public static Properties pr=new Properties();
	static {
		try{
			pr.load(jdbcBean.class.getClassLoader().getResourceAsStream("db.properties"));
			driver=pr.getProperty("driver");
			url=pr.getProperty("url");
			user=pr.getProperty("user");
			password=pr.getProperty("password");
			Class.forName(driver);
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	public static void addLog(String ybuserid,String stuId,String stuName,String type,String text){
		try {
			Connection conn = getConnection();
			String qdSql = "insert into `actionLog` (`YbUserid`,`stuId`,`stuName`,`type`,`text`) values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(qdSql);
			ps.setString(1, ybuserid);
			ps.setString(2, stuId);
			ps.setString(3, stuName);
			ps.setString(4, type);
			ps.setString(5, text);
			ps.executeUpdate();
			free(null,ps,conn);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	public static void free(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(st != null){
					st.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
}
