package cn.mini.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.mini.domain.User;


public class SaveUserServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SaveUserServlets() {
		super();
	}
	public void destroy() {
		super.destroy(); 	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			User u=new  User();
			BeanUtils.populate(u, request.getParameterMap());
			u.setName((String)request.getSession(false).getAttribute("sgin"));
			UserServices use=new UserServices();
			if(use.updateUser(u)){
				response.getWriter().write("yes");	
			}else{
				response.getWriter().write("no");	
			}
		} catch (Exception e) {
			response.getWriter().write("no");	
		} 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
