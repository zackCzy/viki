package cn.mini.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class exit_account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public exit_account() {
		super();
	}
	public void destroy() {
		super.destroy(); 	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession hs=request.getSession(false);
		if(hs!=null)	hs.invalidate();
		//System.out.println(request.getHeader("referer"));
		response.sendRedirect("trans");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
