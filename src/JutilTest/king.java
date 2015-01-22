package JutilTest;

import org.springframework.stereotype.Component;

@Component("king")
public class king implements DoWork{
	@Override
	public void dowork() {
		System.out.println("处理政务");	
	}

}
