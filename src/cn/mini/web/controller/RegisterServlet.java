package cn.mini.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import temp.MyBeanFactory;

import cn.mini.dao.impl.UserDaoImpl;
import cn.mini.domain.UserBase;


public class RegisterServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	public RegisterServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		UserDaoImpl usd=(UserDaoImpl) MyBeanFactory.getInstance().getBean("userDaoImpl");
		try {
			UserBase user=new UserBase();
			UserMessage um=new UserMessage();
			BeanUtils.populate(user, request.getParameterMap());	
			BeanUtils.populate(um, request.getParameterMap());
			um.setUser(user);
			usd.registerUser(um);
		} catch (Exception e) {
			response.getWriter().write("no");	
			return ;
		} 
		hs.invalidate();		
		request.getSession().setAttribute("sgin", request.getParameter("name"));
		response.getWriter().write("yes");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}

