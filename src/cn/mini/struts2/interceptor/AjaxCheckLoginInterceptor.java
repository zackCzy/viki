package cn.mini.struts2.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AjaxCheckLoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	@Override
	public String intercept(ActionInvocation result) throws Exception {	
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		try {
			if(ActionContext.getContext().getSession().get("sgin")==null){
				Cookie[] cs=ServletActionContext.getRequest().getCookies();
				String sgin=null;
				String pass=null;
				int id=0;
				for (Cookie c:cs) {
					if(c.getName().equals("sgin"))sgin=c.getValue();
					if(c.getName().equals("id"))id=Integer.parseInt(c.getValue()); 
					if(c.getName().equals("pptoken"))pass=c.getValue();
					if(sgin!=null&&id!=0&&pass!=null){
						break;
					}
				}
				if(id==us.sginUserService(sgin, pass)){		
					ActionContext.getContext().getSession().put("sgin",sgin);
					ActionContext.getContext().getSession().put("id",id);
					ActionContext.getContext().getSession().put("pptoken", pass);
					return result.invoke();
				}else {			
					out.write("you login has expired");
					return null;
				}
			}else {
				return result.invoke();
			}
			
		} catch (Exception e) {
			out.write("you login has expired");
			return null;
		}finally{
			out.close();
		}
	}

}
