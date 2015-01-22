package cn.mini.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface FristWs {
	@WebMethod
	public String sayHello();
}
