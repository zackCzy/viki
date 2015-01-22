package cn.mini.struts2.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.mini.domain.UserBase;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	@Override
	public String intercept(ActionInvocation result) {
		try {
			String sgin=(String) ActionContext.getContext().getSession().get("sgin");
			int id=(Integer) ActionContext.getContext().getSession().get("id");
			if(sgin==null||id<0){
				throw new Exception();
			}else{
				UserBase user=us.findUserService(id);
				if(!user.getActive().equals("")){
					ActionContext.getContext().put("state", "很抱歉,您的账号还未激活。请尽快前往邮箱激活。");
					return "activeAccount";
				}
			}
			return result.invoke();
		} catch (Exception e) {
			HttpServletRequest request= ServletActionContext.getRequest();
			ActionContext.getContext().put("return",request.getRequestURI());
			return "sgin";
		}	
		
	}

}
