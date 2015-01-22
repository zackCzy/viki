package cn.mini.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.WebUtils;

public class RegisterAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RegisterAccount() {
		super();
	}
	public void destroy() {
		super.destroy(); 	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token=WebUtils.getToken().replace("+", "/");
		request.getSession().setAttribute("token", token);
		request.getRequestDispatcher("/WEB-INF/jsp/RegisterAccount.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}

