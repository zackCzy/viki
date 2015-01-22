package cn.mini.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.Translator;

public class select_words extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public select_words() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Translator trans=new Translator();
		response.setCharacterEncoding("UTF-8");
		try {
			String src = request.getParameter("src");
			String res = request.getParameter("res");
			String text = request.getParameter("word");
			String result = (src.equals("yue") | res.equals("yue")) ? trans
					.yueWord(text, src, res) : trans.quicklyWord(text, src, res);
			response.getOutputStream().write(result.getBytes("UTF-8"));
		} catch (Exception e) {
			response.getWriter().write("no");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
