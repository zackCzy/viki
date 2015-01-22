package cn.mini.web.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.Md5Compute;

import cn.mini.service.impl.UserServices;

public class sgin_on extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public sgin_on() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserServices use = new UserServices();
		try {
			if (use.userLogin(request.getParameter("userName"),request.getParameter("password"))){
				request.getSession().setAttribute("sgin",request.getParameter("userName"));
				response.getWriter().write(Md5Compute.getmd5(request.getParameter("password")));
			}
			response.getWriter().write("no");				
		} catch (NoSuchAlgorithmException e) {
			response.getWriter().write("no");
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
