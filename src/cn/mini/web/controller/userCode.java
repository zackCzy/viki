package cn.mini.web.controller;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.DrawsImages;



public class userCode extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {		

		DrawsImages di=new DrawsImages(100,30);
		di.setBackground(Color.WHITE);
		di.setConten(4, 22);
		di.setLine(3, Color.red);
		di.setLine(3, Color.WHITE);
		di.setLine(3, Color.ORANGE);	
		HttpSession hs=request.getSession();
		hs.setAttribute("code", di.getResult().toUpperCase());

		response.setHeader("content-type", "image/jpeg");
		response.setHeader("cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
							
		di.InputImage(response.getOutputStream());	
		response.getOutputStream().close();
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
