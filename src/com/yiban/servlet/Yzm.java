package com.yiban.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yiban.bean.StringCode;
import com.yiban.bean.Verify;

/**
 * Servlet implementation class Yzm
 */
@WebServlet("/Yzm")
public class Yzm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Yzm() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// 设置响应头 Content-type类型
		response.setContentType("image/png");
		// 以下三句是用于设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		OutputStream os = response.getOutputStream();
		Verify img = new Verify();
		boolean[] UserZh = {true,false};
		int[] codeLen = {3,5};
		int zh = img.mt_rand(0, 1);
		img.setUseZh(UserZh[zh]);
		img.setLength(codeLen[zh]);
//		img.setUseImgBg(UserZh[img.mt_rand(0, 1)]);
//		if(img.isUseImgBg()){
//			img.setUseNoise(false);
//			img.setUseCurve(false);
//		}
		img.entry();
		String[] str = img.getCodeStr();
		String code = "";
		for(int i=0;i<str.length;i++){
			code += str[i];
		}
		session.setAttribute("YzmCode", StringCode.MD5(code.toLowerCase()));
		session.setAttribute("YzmCodeNowTime", System.currentTimeMillis());//验证码生成时间。ms
		// 输出图像到页面
		ImageIO.write(img.getImage(), "PNG", os);
	}
	public void drawImage(){
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
