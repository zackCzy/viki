package cn.mini.struts2.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import cn.mini.domain.UserBase;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller("backStageAction")
public class backStageAction extends ActionSupport implements ServletResponseAware{
	/**
	 * 后台管理action
	 */
	private static final long serialVersionUID = 1L;
	private int page,userID;
	private String userName;

	private static int PAGE_SIZE = 25;
	private HttpServletResponse response;
	@Resource(name = "userServiceImpl")
	private UserService us = null;

	public String admin() {
		List<UserBase> users = us.findUserAll(page, PAGE_SIZE);
		ActionContext.getContext().put("users", users);
		return "admin";
	}
	//删除用户
	public void removeUser() throws IOException {
		try {
			UserBase user=us.findUser(userName);
			us.removeUser(user);
			response.getWriter().write("remove is ok");
		} catch (Exception e) {
			response.getWriter().write("remove is error");
		}
		
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;	
	}

}
