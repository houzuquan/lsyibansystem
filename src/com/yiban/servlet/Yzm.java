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
		// ������Ӧͷ Content-type����
		response.setContentType("image/jpeg");
		// ������������������ҳ�治����
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		OutputStream os = response.getOutputStream();
		Verify img = new Verify();
		if(img.mt_rand(1, 2) == 1){//��ĸ
			img.setLength(5);
		}else{//����
			img.setUseZh(true);
			img.setLength(3);
		}
		img.entry();
		String[] str = img.getCodeStr();
		String code = "";
		for(int i=0;i<str.length;i++){
			code += str[i];
		}
//		System.out.println(code);
//		System.out.println(code.toLowerCase());
		session.setAttribute("YzmCode", StringCode.MD5(code.toLowerCase()));
		session.setAttribute("YzmCodeTime", 60);
		// ���ͼ��ҳ��
		ImageIO.write(img.getImage(), "JPEG", os);
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
