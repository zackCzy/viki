package cn.mini.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mini.service.impl.UserServices;

public class UserExsitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserExsitServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 	
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws ServletException , IOException {
		UserServices use=new UserServices();
		if(use.userExist(request.getParameter("user"))){
			response.getWriter().write("no");
			return ;
		}
		response.getWriter().write("yes");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,IOException{
		doGet(request, response);
	}

}
