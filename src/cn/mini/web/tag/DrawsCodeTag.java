package cn.mini.web.tag;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import Utils.DrawsImages;


public class DrawsCodeTag extends SimpleTagSupport {
	private String path;

	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public void doTag() throws JspException, IOException {
		PageContext page=(PageContext) this.getJspContext();
		HttpServletRequest request= (HttpServletRequest) page.getRequest();
		HttpServletResponse response= (HttpServletResponse) page.getResponse();
		String header=request.getHeader("referer");
		if(header!=null&&header.indexOf(path)>0){
			DrawsImages di = new DrawsImages(100, 30);
			di.setBackground(Color.WHITE);
			di.setConten(4, 22);
			di.setLine(3, Color.red);
			di.setLine(3, Color.WHITE);
			di.setLine(3, Color.ORANGE);
			page.getSession().setAttribute("code",di.getResult().toUpperCase());			
			response.setHeader("content-type", "image/jpeg");
			response.setHeader("cache-Control", "no-cache");
			response.setHeader("pragma", "no-cache");
			di.InputImage( response.getOutputStream());
		}
		super.doTag();
	}

}
