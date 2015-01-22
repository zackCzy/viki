package cn.mini.struts2.action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import Utils.WebUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("mainAction")
public class MainAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String home() {

		return "home";
	}
	public String sgin() {

		return "sgin";
	}
	
	public String translator() {
		return "translator";
	}

	public String application() {
		ActionContext.getContext().getSession()
				.put("token", WebUtils.getToken().replace("+", "/"));
		return "application";
	}

	public String exit() {
		ActionContext.getContext().getSession().remove("sgin");
		ActionContext.getContext().getSession().remove("id");
		String path = ServletActionContext.getRequest().getContextPath();
		Cookie csgin = new Cookie("sgin", "");
		csgin.setMaxAge(0);
		csgin.setPath(path);
		Cookie cid = new Cookie("id", "0");
		cid.setPath(path);
		cid.setMaxAge(0);
		Cookie cpass = new Cookie("pptoken", "");
		cpass.setPath(path);
		cpass.setMaxAge(0);
		ServletActionContext.getResponse().addCookie(csgin);
		ServletActionContext.getResponse().addCookie(cid);
		ServletActionContext.getResponse().addCookie(cpass);
		return "translator";
	}
	public void exitLogin() {
		try {
			PrintWriter out=  ServletActionContext.getResponse().getWriter();
			ActionContext.getContext().getSession().remove("sgin");
			ActionContext.getContext().getSession().remove("id");
			String path = ServletActionContext.getRequest().getContextPath();
			Cookie csgin = new Cookie("sgin", "");
			csgin.setMaxAge(0);
			csgin.setPath(path);
			Cookie cid = new Cookie("id", "0");
			cid.setPath(path);
			cid.setMaxAge(0);
			Cookie cpass = new Cookie("pptoken", "");
			cpass.setPath(path);
			cpass.setMaxAge(0);
			ServletActionContext.getResponse().addCookie(csgin);
			ServletActionContext.getResponse().addCookie(cid);
			ServletActionContext.getResponse().addCookie(cpass);
			out.write("exit is ok");
		} catch (Exception e) {
			
		}	
	}
	public String music(){
		return "music";
	}
}
