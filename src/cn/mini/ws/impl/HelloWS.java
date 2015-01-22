package cn.mini.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import cn.mini.ws.FristWs;
@WebService
public class HelloWS implements FristWs {

	@WebMethod
	public String sayHello() {
		System.out.println("hello world");
		return "hello world";
	}



}
