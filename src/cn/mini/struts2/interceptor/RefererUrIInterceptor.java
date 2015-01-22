package cn.mini.struts2.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RefererUrIInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation result) throws Exception {
		String referer=ServletActionContext.getRequest().getHeader("referer");
		String path=ServletActionContext.getRequest().getContextPath();

		if(referer.indexOf(path)==1){
			return "my404";
		}
		
		return result.invoke();
	}

}
