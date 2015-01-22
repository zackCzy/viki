package cn.mini.web.UI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userImg
 */
@WebServlet("/userImg")
public class userImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public userImg() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("123");
		FileInputStream in=new FileInputStream("C:\\Users\\zack\\Desktop\\f52b613636323130343533cb10.jpg");
		byte b[]=new byte[1024];
		response.setHeader("content-type", "image/jpeg");
		OutputStream os=response.getOutputStream();
		int len=0;
		while ((len=in.read(b))!=-1) {
			os.write(b, 0, len);	
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
