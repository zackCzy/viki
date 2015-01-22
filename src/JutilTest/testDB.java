package JutilTest;

import java.io.IOException;

import javax.mail.MessagingException;

import Utils.MiniMailUtil;



public class testDB {
	public static void main(String [] args) throws IOException{
		MiniMailUtil mini=MiniMailUtil.getInstance();
		try {
			mini.sendMail("请激活你的帐号，完成注册", "亲爱的"+"a66210453"+":<br><br>欢迎加入Mini!<br><br>	请点击下面的链接完成注册:<br><br>"+
			"http://www.douban.com/accounts/register?confirmation=9d6afeca29e4f6ca"+"<br><br>如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进Mini。"
					, "496928424@qq.com");
		} catch (MessagingException e) {
			System.err.println(e);
		}
	}
}
