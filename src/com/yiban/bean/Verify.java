package com.yiban.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Random;

public class Verify {
	protected HashMap<String,Object> _config =	new HashMap<String,Object>();
	protected BufferedImage image = null;
	Graphics g = null;
	private String  seKey = "houkunlin.cn";// ��֤�������Կ
	private String  codeSet = "2345678abcdefhijkmnpqrstuvwxyzABCDEFGHJKLMNPQRTUVWXY";// ��֤���ַ�����
	private int     expire = 1800;// ��֤�����ʱ�䣨s��
	private boolean useZh = false;// ʹ��������֤�� 
	private String  zhSet = "�����ҵ�������ʱҪ��������һ�ǹ�������巢�ɲ���ɳ��ܷ������˲����д�����������Ϊ����������ѧ�¼��ظ���ͬ����˵�ֹ����ȸ�����Ӻ������С��Ҳ�����߱������������ʵ�Ҷ������ˮ��������������������ʮս��ũʹ��ǰ�ȷ���϶�·ͼ�ѽ�������¿���֮��ӵ���Щ�������¶�����������˼�����ȥ�����������ѹԱ��ҵ��ȫ�������ڵ�ƽ��������ëȻ��Ӧ�����������ɶ������ʱ�չ�������û���������ϵ������Ⱥͷ��ֻ���ĵ����ϴ���ͨ�����Ͽ��ֹ�����������ϯλ����������ԭ�ͷ�������ָ��������ںܽ̾��ش˳�ʯǿ�������Ѹ���ֱ��ͳʽת�����о���ȡ������������־�۵���ôɽ�̰ٱ��������汣��ί�ָĹܴ�������֧ʶ�������Ϲ�רʲ���;�ʾ������ÿ�����������Ϲ����ֿƱ�������Ƹ��������������༯������װ����֪���е�ɫ����ٷ�ʷ����������֯�������󴫿ڶϿ��ɾ����Ʒ�вβ�ֹ��������ȷ������״��������Ŀ����Ȩ�Ҷ����֤��Խ�ʰ��Թ�˹��ע�첼�����������ر��̳�������ǧʤϸӰ�ð׸�Ч���ƿ��䵶Ҷ������ѡ���»������ʼƬʩ���ջ�������������ҩ����Ѵ��ʿ����Һ��׼��ǽ�ά�������������״����ƶ˸������ش幹���ݷǸ���ĥ�������ʽ���ֵ��̬���ױ�������������̨���û������ܺ���ݺ����ʼ��������Ͼ��ݼ���ҳ�����Կ�Ӣ��ƻ���Լ�Ͳ�ʡ���������ӵ۽�����ֲ������������ץ���縱����̸Χʳ��Դ�������ȴ����̻����������׳߲��зۼ������濼�̿�������ʧ��ס��֦�־����ܻ���ʦ������Ԫ����ɰ�⻻̫ģƶ�����ｭ��Ķľ����ҽУ���ص�����Ψ�们վ�����ֹĸ�д��΢�Է�������ĳ�����������൹���������ù�Զ���Ƥ����ռ����Ȧΰ��ѵ�ؼ��ҽ��ƻ���������ĸ�����ֶ���˫��������ʴ����˿Ůɢ��������Ժ�䳹����ɢ�����������������Ѫ��ȱ��ò�����ǳ���������������̴���������������Ͷ��ū����ǻӾഥ�����ͻ��˶��ٻ����δͻ�ܿ�����ʪƫ�Ƴ�ִ����կ�����ȶ�Ӳ��Ŭ�����Ԥְ������Э�����ֻ���ì������ٸ�������������ͣ����Ӫ�ո���Ǯ��������ɳ�˳��ַ�е�ذ����İ��������۵��յ���ѽ�ʰɿ��ֽ�������������ĩ�������ڱ������������������𾪶ټ�����ķ��ɭ��ʥ���մʳٲ��ھؿ��������԰ǻ�����������������ӡ�伱�����˷�¶��Ե�������������Ѹ��������ֽҹ������׼�����ӳ��������ɱ���׼辧�尣ȼ��������ѿ��������̼��������ѿ����б��ŷ��˳������͸˾Σ������Ц��β��׳����������������ţ��Ⱦ�����������Ƽ�ֳ�����ݷô���ͭ��������ٺ�����Դ��ظ���϶¯����úӭ��ճ̽�ٱ�Ѯ�Ƹ�������Ը���������̾䴿������������³�෱�������׶ϣ�ذܴ�����ν�л��ܻ���ڹ��ʾ����ǳ���������Ϣ������������黭�������������躮ϲ��ϴʴ���ɸ���¼������֬ׯ��������ҡ���������������Ű²��ϴ�;�������Ұ�ž�ıŪ�ҿ�����ʢ��Ԯ���Ǽ���������Ħæ�������˽����������������Ʊܷ�������Ƶ�������Ҹ�ŵ����Ũ��Ϯ˭��л�ڽ���Ѷ���鵰�պ������ͽ˽������̹����ù�����ո��伨���ܺ���ʹ�������������ж�����׷���ۺļ���������о�Ѻպ��غ���Ĥƪ��פ��������͹�ۼ���ѩ�������������߲��������ڽ������˹�̿������������ǹ���ð������Ͳ���λ�����Ϳζ����Ϻ�½�����𶹰�Ī��ɣ�·쾯���۱�����ɶ���ܼ��Ժ��浤�ɶ��ٻ���ϡ���������ǳӵѨ������ֽ����������Ϸ��������ò�����η��ɰ���������ˢ�ݺ���������©�������Ȼľ��з������Բ����ҳ�����ײ����ȳ����ǵ������������ɨ������оү���ؾ����Ƽ��ڿ��׹��ð��ѭ��ף���Ͼ����������ݴ���ι�������Ź�ó����ǽ���˽�ī������ж����������ƭ�ݽ�";
	private boolean useImgBg = false;// ʹ�ñ���ͼƬ 
	private int     fontSize = 25;// ��֤�������С(px)
	private boolean useCurve = true;// �Ƿ񻭻�������
	private boolean useNoise = true;// �Ƿ������ӵ�
	private int     imageH = 0;// ��֤��ͼƬ�߶�
	private int     imageW = 0;// ��֤��ͼƬ����
	private int     length = 5;// ��֤��λ��
	private String  fontttf = "";// ��֤�����壬�����������ȡ
	private int[]   bg = {243, 251, 254};// ������ɫ
	private boolean reset = true;// ��֤�ɹ����Ƿ�����
//	private _image   = NULL;     // ��֤��ͼƬʵ��
    private int[] 	_color   = null;     // ��֤��������ɫ
    private String[] 	codeStr = null;
    private String nowFilePath = null;
	String ttfPath = "Verify/ttfs/";
	public Verify(){
		this.setImageW(160);
		this.setImageH(40);
		nowFilePath = this.getClass().getResource("/").getPath();
    }
	public void entry(){
		_color = new int[3];
		_color[0]=mt_rand(1,150);
		_color[1]=mt_rand(1,150);
		_color[2]=mt_rand(1,150);
		image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics(); // �û��ʻ���image��
		g.fillRect(0, 0, imageW, imageH);
		if(this.useImgBg) {
//            this._background();
        }
        
        if (this.useNoise) {
            // ���ӵ�
            this._writeNoise();
        } 
        if (this.useCurve) {
            // �������
            this._writeCurve();
        }
        // ����֤��
        codeStr = new String[length]; // ��֤��
        int codeNX = 0; // ��֤���N���ַ�����߾�
        if(useZh){ // ������֤��
//            for (i = 0; i<length; i++) {
//                code[i] = iconv_substr(zhSet,floor(mt_rand(0,mb_strlen($this->zhSet,'utf-8')-1)),1,'utf-8');
//            }
        	ttfPath = "Verify/zhttfs/";
    		g.setFont(loadFont(30));
        	codeNX  += 10;
        	int fontWidth = (imageW-20)/length;
        	char[] zh = zhSet.toCharArray();
            for (int i = 0; i<length; i++) {
            	codeStr[i] = new Character(zh[mt_rand(0, zh.length-1)]).toString();
                g.setColor(new Color(mt_rand(1,150),mt_rand(1,150),mt_rand(1,150)));
                g.drawString(codeStr[i], codeNX, 30);
                codeNX  += fontWidth;//mt_rand(fontSize*1, fontSize*1.3);
            }
        }else{
    		g.setFont(loadFont(30));
        	codeNX  += 10;
        	int fontWidth = (imageW-20)/length;
            for (int i = 0; i<length; i++) {
            	codeStr[i] = new Character(codeSet.charAt(mt_rand(0, codeSet.length()-1))).toString();
                g.setColor(new Color(mt_rand(1,150),mt_rand(1,150),mt_rand(1,150)));
                g.drawString(codeStr[i], codeNX, 30);
                codeNX  += fontWidth;//mt_rand(fontSize*1, fontSize*1.3);
            }
        }
        
	}
	private void _background() {
		// TODO �Զ����ɵķ������
		
	}
	private void _writeNoise() {
		// TODO �Զ����ɵķ������
		Random random = new Random();
		//�����漴���ŵ�
		for (int i = 0; i < 50; i++) {
			g.setColor(new Color(mt_rand(150,225), mt_rand(150,225), mt_rand(150,225)));
			int x1 = random.nextInt(imageW);
			int y1 = random.nextInt(imageH);
			g.drawOval(x1, y1, 2, 2);
		}
	}
	private void _writeCurve() {
		 int px = 0,py = 0;
	        
	        // ����ǰ����
	     int A = mt_rand(1, imageH/2);                  // ���
	     int b = mt_rand(-imageH/4, imageH/4);   // Y�᷽��ƫ����
	     int f = mt_rand(-imageH/4, imageH/4);   // X�᷽��ƫ����
	     int T = mt_rand(imageH, imageW*2);  // ����
	     double w = (2* Math.PI)/T;
	                        
	     int px1 = 0;  // ���ߺ�������ʼλ��
	     int px2 = mt_rand(imageW/2, imageW * 0.8);  // ���ߺ��������λ��

         g.setColor(new Color(mt_rand(155,255),mt_rand(155,255),mt_rand(155,255)));
	        for (px=px1; px<=px2; px = px + 1) {
	            if (w!=0) {
	                py = (int)(A * Math.sin(w*px + f)+ b + imageH/2);  // y = Asin(��x+��) + b
	                int i = (int) (fontSize/5);
	                while (i > 0) {	
//	                    imagesetpixel($this->_image, px + i , py + i, $this->_color);  
	                	// ����(while)ѭ�������ص��imagettftext��imagestring�������Сһ�λ�����������whileѭ��������Ҫ�úܶ�
	                    g.drawOval(px + i , py + i, 1, 1);
	                    i--;
	                }
	            }
	        }
	        
	        // ���ߺ󲿷�
	        A = mt_rand(1, imageH/2);                  // ���		
	        f = mt_rand(-imageH/4, imageH/4);   // X�᷽��ƫ����
	        T = mt_rand(imageH, imageW*2);  // ����
	        w = (2* Math.PI)/T;		
	        b = (int)(py - A * Math.sin(w*px + f) - imageH/2);
	        px1 = px2;
	        px2 = imageW;

//            g.setColor(new Color(_color[0],_color[1],_color[2]));
	        for (px=px1; px<=px2; px=px+ 1) {
	            if (w!=0) {
	                py = (int)(A * Math.sin(w*px + f)+ b + imageH/2);  // y = Asin(��x+��) + b
	                int i = (int) (fontSize/5);
	                while (i > 0) {	
	                    g.drawOval(px + i , py + i, 1, 1);
	                    i--;
	                }
	            }
	        }
	}
	public int mt_rand(double min,double max){
		return (int)(min+Math.random()*(max-min+1));
	}
	public Font loadFont(float fontSize)  //��һ���������ⲿ���������ڶ����������С
	{
		try
		{
			File file = new File(nowFilePath + ttfPath);
			String[] fileNames = file.list();
//			if(fileNames.length == 0){
//				throw new Exception();
//			}
//			System.out.println("�ļ���Ŀ��"+fileNames.length);
			int r = mt_rand(0,fileNames.length-1);
			String fileNamePath = nowFilePath + ttfPath + fileNames[r];
//			for(int i=0;i<fileNames.length;i++){
//				System.out.print(fileNames[i]+"��");
//			}
//			System.out.println();
//			System.out.println("��"+r+"��");
//			System.out.println(fileNamePath);
			File ttfFile = new File(fileNamePath);
			FileInputStream aixing = new FileInputStream(ttfFile);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			aixing.close();
			return dynamicFontPt;
		}
		catch(Exception e)//�쳣����
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Font("����", Font.PLAIN, 30);
		}
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public String getSeKey() {
		return seKey;
	}
	public void setSeKey(String seKey) {
		this.seKey = seKey;
	}
	public String getCodeSet() {
		return codeSet;
	}
	public void setCodeSet(String codeSet) {
		this.codeSet = codeSet;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public boolean isUseZh() {
		return useZh;
	}
	public void setUseZh(boolean useZh) {
		this.useZh = useZh;
	}
	public String getZhSet() {
		return zhSet;
	}
	public void setZhSet(String zhSet) {
		this.zhSet = zhSet;
	}
	public boolean isUseImgBg() {
		return useImgBg;
	}
	public void setUseImgBg(boolean useImgBg) {
		this.useImgBg = useImgBg;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public boolean isUseCurve() {
		return useCurve;
	}
	public void setUseCurve(boolean useCurve) {
		this.useCurve = useCurve;
	}
	public boolean isUseNoise() {
		return useNoise;
	}
	public void setUseNoise(boolean useNoise) {
		this.useNoise = useNoise;
	}
	public int getImageH() {
		return imageH;
	}
	public void setImageH(int imageH) {
		this.imageH = imageH;
	}
	public int getImageW() {
		return imageW;
	}
	public void setImageW(int imageW) {
		this.imageW = imageW;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getFontttf() {
		return fontttf;
	}
	public void setFontttf(String fontttf) {
		this.fontttf = fontttf;
	}
	public int[] getBg() {
		return bg;
	}
	public void setBg(int[] bg) {
		this.bg = bg;
	}
	public boolean isReset() {
		return reset;
	}
	public void setReset(boolean reset) {
		this.reset = reset;
	}
	public String[] getCodeStr() {
		return codeStr;
	}
}
