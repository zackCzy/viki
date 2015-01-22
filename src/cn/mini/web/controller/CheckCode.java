package cn.mini.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckCode() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession(false);
		try {
			String secode = (String) hs.getAttribute("code");
			String code = (String) request.getParameter("code");
			if (!code.equalsIgnoreCase(secode)) {
				response.getWriter().write("no");
				return;
			}
			response.getWriter().write("yes");
		} catch (Exception e) {
			response.getWriter().write("no");
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
