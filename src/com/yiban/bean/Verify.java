package com.yiban.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

public class Verify {
	protected HashMap<String,Object> _config =	new HashMap<String,Object>();
	protected BufferedImage image = null;
	Graphics g = null;
	private String  seKey = "houkunlin.cn";// 验证码加密密钥
	private String  codeSet = "2345678abcdefhijkmnpqrstuvwxyzABCDEFGHJKLMNPQRTUVWXY";// 验证码字符集合
	private int     expire = 1800;// 验证码过期时间（s）
	private boolean useZh = false;// 使用中文验证码 
	private String  zhSet = "们以我到他会作时要动国产的一是工就年阶义发成部民可出能方进在了不和有大这主中人上为来分生对于学下级地个用同行面说种过命度革而多子后自社加小机也经力线本电高量长党得实家定深法表着水理化争现所二起政三好十战无农使性前等反体合斗路图把结第里正新开论之物从当两些还天资事队批点育重其思与间内去因件日利相由压员气业代全组数果期导平各基或月毛然如应形想制心样干都向变关问比展那它最及外没看治提五解系林者米群头意只明四道马认次文通但条较克又公孔领军流入接席位情运器并飞原油放立题质指建区验活众很教决特此常石强极土少已根共直团统式转别造切九你取西持总料连任志观调七么山程百报更见必真保热委手改管处己将修支识病象几先老光专什六型具示复安带每东增则完风回南广劳轮科北打积车计给节做务被整联步类集号列温装即毫知轴研单色坚据速防史拉世设达尔场织历花受求传口断况采精金界品判参层止边清至万确究书术状厂须离再目海交权且儿青才证低越际八试规斯近注办布门铁需走议县兵固除般引齿千胜细影济白格效置推空配刀叶率述今选养德话查差半敌始片施响收华觉备名红续均药标记难存测士身紧液派准斤角降维板许破述技消底床田势端感往神便贺村构照容非搞亚磨族火段算适讲按值美态黄易彪服早班麦削信排台声该击素张密害侯草何树肥继右属市严径螺检左页抗苏显苦英快称坏移约巴材省黑武培著河帝仅针怎植京助升王眼她抓含苗副杂普谈围食射源例致酸旧却充足短划剂宣环落首尺波承粉践府鱼随考刻靠够满夫失包住促枝局菌杆周护岩师举曲春元超负砂封换太模贫减阳扬江析亩木言球朝医校古呢稻宋听唯输滑站另卫字鼓刚写刘微略范供阿块某功套友限项余倒卷创律雨让骨远帮初皮播优占死毒圈伟季训控激找叫云互跟裂粮粒母练塞钢顶策双留误础吸阻故寸盾晚丝女散焊功株亲院冷彻弹错散商视艺灭版烈零室轻血倍缺厘泵察绝富城冲喷壤简否柱李望盘磁雄似困巩益洲脱投送奴侧润盖挥距触星松送获兴独官混纪依未突架宽冬章湿偏纹吃执阀矿寨责熟稳夺硬价努翻奇甲预职评读背协损棉侵灰虽矛厚罗泥辟告卵箱掌氧恩爱停曾溶营终纲孟钱待尽俄缩沙退陈讨奋械载胞幼哪剥迫旋征槽倒握担仍呀鲜吧卡粗介钻逐弱脚怕盐末阴丰雾冠丙街莱贝辐肠付吉渗瑞惊顿挤秒悬姆烂森糖圣凹陶词迟蚕亿矩康遵牧遭幅园腔订香肉弟屋敏恢忘编印蜂急拿扩伤飞露核缘游振操央伍域甚迅辉异序免纸夜乡久隶缸夹念兰映沟乙吗儒杀汽磷艰晶插埃燃欢铁补咱芽永瓦倾阵碳演威附牙芽永瓦斜灌欧献顺猪洋腐请透司危括脉宜笑若尾束壮暴企菜穗楚汉愈绿拖牛份染既秋遍锻玉夏疗尖殖井费州访吹荣铜沿替滚客召旱悟刺脑措贯藏敢令隙炉壳硫煤迎铸粘探临薄旬善福纵择礼愿伏残雷延烟句纯渐耕跑泽慢栽鲁赤繁境潮横掉锥希池败船假亮谓托伙哲怀割摆贡呈劲财仪沉炼麻罪祖息车穿货销齐鼠抽画饲龙库守筑房歌寒喜哥洗蚀废纳腹乎录镜妇恶脂庄擦险赞钟摇典柄辩竹谷卖乱虚桥奥伯赶垂途额壁网截野遗静谋弄挂课镇妄盛耐援扎虑键归符庆聚绕摩忙舞遇索顾胶羊湖钉仁音迹碎伸灯避泛亡答勇频皇柳哈揭甘诺概宪浓岛袭谁洪谢炮浇斑讯懂灵蛋闭孩释乳巨徒私银伊景坦累匀霉杜乐勒隔弯绩招绍胡呼痛峰零柴簧午跳居尚丁秦稍追梁折耗碱殊岗挖氏刃剧堆赫荷胸衡勤膜篇登驻案刊秧缓凸役剪川雪链渔啦脸户洛孢勃盟买杨宗焦赛旗滤硅炭股坐蒸凝竟陷枪黎救冒暗洞犯筒您宋弧爆谬涂味津臂障褐陆啊健尊豆拔莫抵桑坡缝警挑污冰柬嘴啥饭塑寄赵喊垫丹渡耳刨虎笔稀昆浪萨茶滴浅拥穴覆伦娘吨浸袖珠雌妈紫戏塔锤震岁貌洁剖牢锋疑霸闪埔猛诉刷狠忽灾闹乔唐漏闻沈熔氯荒茎男凡抢像浆旁玻亦忠唱蒙予纷捕锁尤乘乌智淡允叛畜俘摸锈扫毕璃宝芯爷鉴秘净蒋钙肩腾枯抛轨堂拌爸循诱祝励肯酒绳穷塘燥泡袋朗喂铝软渠颗惯贸粪综墙趋彼届墨碍启逆卸航衣孙龄岭骗休借";
	private boolean useImgBg = false;// 使用背景图片 
	private int     fontSize = 25;// 验证码字体大小(px)
	private boolean useCurve = true;// 是否画混淆曲线
	private boolean useNoise = true;// 是否添加杂点
	private int     imageH = 0;// 验证码图片高度
	private int     imageW = 0;// 验证码图片宽度
	private int     length = 5;// 验证码位数
	private String  fontttf = "";// 验证码字体，不设置随机获取
	private int[]   bg = {243, 251, 254};// 背景颜色
	private boolean reset = true;// 验证成功后是否重置
    private String[] 	codeStr = null;//存储验证码内容
    private String nowFilePath = null;//当前文件所在目录
	String ttfPath = "Verify/ttfs/";//英文字体的相对路径
	public Verify(){
		this.setImageW(160);
		this.setImageH(40);
		nowFilePath = this.getClass().getResource("/").getPath();
    }
	public void entry(){
		image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics(); // 该画笔画在image上
		g.setColor(new Color(bg[0],bg[1],bg[2]));
		g.fillRect(0, 0, imageW, imageH);
		if(this.useImgBg) {
			// 绘制背景
            this._background();
        }
        if (this.useNoise) {
            // 绘杂点
            this._writeNoise();
        } 
        if (this.useCurve) {
            // 绘干扰线
            this._writeCurve();
        }
        // 绘验证码
        codeStr = new String[length]; // 验证码
        int codeNX = 0; // 验证码第N个字符的左边距
    	int codeNY = (int)(fontSize*1.2);// 验证码第N个字符的上边距
    		codeNX += 10;
    	int fontWidth = (imageW-20)/length;//一个字体的所占的宽度
        if(useZh){ // 中文验证码
        	ttfPath = "Verify/zhttfs/";
    		g.setFont(loadFont(30));// 载入字体文件
        	char[] zh = zhSet.toCharArray();//把字符串转换成字符数组
            for (int i = 0; i<length; i++) {
            	codeStr[i] = new Character(zh[mt_rand(0, zh.length-1)]).toString();//从字符数组中取出字符然后转换成为字符串类型
                g.setColor(new Color(mt_rand(1,150),mt_rand(1,150),mt_rand(1,150)));
                RotateString(codeStr[i], codeNX, codeNY, g, mt_rand(-30,30));
                codeNX += fontWidth;
            }
        }else{
        	char[] zh = codeSet.toCharArray();//把字符串转换成字符数组
            for (int i = 0; i<length; i++) {
        		g.setFont(loadFont(30));// 载入字体文件
            	codeStr[i] = new Character(zh[mt_rand(0, zh.length-1)]).toString();
                g.setColor(new Color(mt_rand(1,150),mt_rand(1,150),mt_rand(1,150)));
                RotateString(codeStr[i], codeNX, codeNY, g, mt_rand(-40,40));
                codeNX += fontWidth;
            }
        }
        
	}
	private void _background() {
		File file = new File(nowFilePath + "Verify/bgs/");
		String[] fileNames = file.list();
		int r = mt_rand(0,fileNames.length-1);
		String fileNamePath = nowFilePath + "Verify/bgs/" + fileNames[r];
		File ttfFile = new File(fileNamePath);
		Image bi = null;
        try {
			bi = ImageIO.read(ttfFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
        g.drawImage(bi, 0, 0, imageW, imageH, null);
	}
	private void _writeNoise() {
		Random random = new Random();
		//产生随即干扰点
		g.setFont(new Font("宋体", Font.PLAIN, 12));// 载入字体文件
		g.setColor(new Color(mt_rand(180,225), mt_rand(180,225), mt_rand(180,225)));
		for (int i = 0; i < 20; i++) {
			int x1 = random.nextInt(imageW);
			int y1 = random.nextInt(imageH);
			RotateString(new Character(codeSet.charAt(mt_rand(0, codeSet.length()-1))).toString(), x1, y1, g, mt_rand(-90,90));
//			g.drawOval(x1, y1, 2, 2);
		}
	}
	private void _writeCurve() {
		int px = 0,py = 0;
		// 曲线前部分
		int A = mt_rand(1, imageH/2);                  // 振幅
		int b = mt_rand(-imageH/4, imageH/4);   // Y轴方向偏移量
		int f = mt_rand(-imageH/4, imageH/4);   // X轴方向偏移量
		int T = mt_rand(imageH, imageW*2);  // 周期
		double w = (2* Math.PI)/T;
		
		int px1 = 0;  // 曲线横坐标起始位置
		int px2 = mt_rand(imageW/2, imageW * 0.8);  // 曲线横坐标结束位置
		
        if (w!=0) {
    		g.setColor(new Color(mt_rand(155,225),mt_rand(155,225),mt_rand(155,225)));
        	for (px=px1; px<=px2; ++px) {
                py = (int)(A * Math.sin(w*px + f)+ b + imageH/2);  // y = Asin(ωx+φ) + b
                int i = (int) (fontSize/5);
                while (i > 0) {
                    g.drawOval(px + i , py + i, 1, 1);
                    i--;
                }
            }
        }

        // 曲线后部分
        A = mt_rand(1, imageH/2);                  // 振幅
        f = mt_rand(-imageH/4, imageH/4);   // X轴方向偏移量
        T = mt_rand(imageH, imageW*2);  // 周期
        w = (2* Math.PI)/T;
        b = (int)(py - A * Math.sin(w*px + f) - imageH/2);
        px1 = px2;
        px2 = imageW;

        if (w!=0) {
    		g.setColor(new Color(mt_rand(155,225),mt_rand(155,225),mt_rand(155,225)));
        	for (px=px1; px<=px2; ++px) {
                py = (int)(A * Math.sin(w*px + f)+ b + imageH/2);  // y = Asin(ωx+φ) + b
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
	public Font loadFont(float fontSize)  //第一个参数是外部字体名，第二个是字体大小
	{
		try
		{
			File file = new File(nowFilePath + ttfPath);
			String[] fileNames = file.list();
			int r = mt_rand(0,fileNames.length-1);
			String fileNamePath = nowFilePath + ttfPath + fileNames[r];
			File ttfFile = new File(fileNamePath);
			FileInputStream aixing = new FileInputStream(ttfFile);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			aixing.close();
			return dynamicFontPt;
		}
		catch(Exception e)//异常处理
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Font("宋体", Font.PLAIN, 30);
		}
	}
	/** 
	* 旋转并且画出指定字符串 
	* @param s 需要旋转的字符串 
	* @param x 字符串的x坐标 
	* @param y 字符串的Y坐标 
	* @param g 画笔g 
	* @param degree 旋转的角度 
	*/
	private void RotateString(String s,int x,int y,Graphics g,int degree){
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.translate(x-1, y+3);// 平移原点到图形环境的中心,这个方法的作用实际上就是将字符串移动到某一个位置
	    g2d.rotate(degree* Math.PI / 180);// 旋转文本
	    g2d.drawString( s, 0, 0);// 特别需要注意的是,这里的画笔已经具有了上次指定的一个位置,所以这里指定的其实是一个相对位置
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
