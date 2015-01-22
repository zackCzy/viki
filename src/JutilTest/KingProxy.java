package JutilTest;

import org.springframework.stereotype.Component;

@Component("kingproxy")
public class KingProxy {
	public void before(){
		System.out.println("起床");
		System.out.println("吃饭");
	}
	public void After(){
		System.out.println("吃饭");
		System.out.println("找妃子");
	}
}
