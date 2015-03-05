package cn.mini.struts2.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.mini.struts2.action.UserCheckAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Redirectinterceptor extends AbstractInterceptor {

	/**
	 * 301重定向拦截器
	 * 阻止没指定域名访问 进行301重定向
	 */
	private static final long serialVersionUID = 1L;
	private static String URL_NAME;
	private static String GOTO_URL_NAME;

	@Override
	public String intercept(ActionInvocation result) throws Exception {	
		if(URL_NAME==null||GOTO_URL_NAME==null){
			Properties p = new Properties();
			InputStream in=null;
			in=UserCheckAction.class.getClassLoader().getResourceAsStream("../config/config.properties");
			try {
				p.load(in);
				URL_NAME=p.getProperty("URL_NAME");
				GOTO_URL_NAME=p.getProperty("GOTO_URL_NAME");	
			} catch (IOException e) {
				System.out.println("301重定向拦截器:"+e);
			}finally{
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						System.out.println("301重定向拦截器 字节流关闭失败:"+e);
					}
				}
				p.clear();
				p=null;
				in=null;
			}
		}
		if(!URL_NAME.equals("null")&&!GOTO_URL_NAME.equals("null")){
			HttpServletRequest request=ServletActionContext.getRequest();
			String urlName=request.getServerName();
			if(urlName.indexOf(URL_NAME)==-1){
				HttpServletResponse response = ServletActionContext.getResponse();
				String url=request.getServletPath();
				String param=request.getQueryString();
				if(param!=null){
					url+="?"+param;
				}
				response.sendRedirect(GOTO_URL_NAME+url);
				return null;
			}
		}
		return result.invoke();
	}
}
