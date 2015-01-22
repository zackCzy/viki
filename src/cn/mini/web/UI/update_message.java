package cn.mini.web.UI;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.mini.domain.User;
import cn.mini.service.impl.UserServices;

public class update_message extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public update_message() {
		super();
	}
	public void destroy() {
		super.destroy(); 	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs= request.getSession(false);
		try {
			UserServices use=new UserServices();
			Map<String,String> m=new LinkedHashMap<String,String>();
			User user=use.getUser((String)hs.getAttribute("sgin"));
			m.put("温柔", user.getPersonality()!=null&&user.getPersonality().indexOf("温柔")>0?"checked":"");
			m.put("开朗", user.getPersonality()!=null&&user.getPersonality().indexOf("开朗")>0? "checked":"");
			m.put("沉默", user.getPersonality()!=null&&user.getPersonality().indexOf("沉默")>0? "checked":"");
			m.put("稳重", user.getPersonality()!=null&&user.getPersonality().indexOf("稳重")>0? "checked":"");
			m.put("内向",user.getPersonality()!=null&& user.getPersonality().indexOf("内向")>0? "checked":"");
			m.put("粗犷", user.getPersonality()!=null&&user.getPersonality().indexOf("粗犷")>0? "checked":"");
			m.put("成熟",user.getPersonality()!=null&& user.getPersonality().indexOf("成熟")>0? "checked":"");
			m.put("自卑", user.getPersonality()!=null&&user.getPersonality().indexOf("自卑")>0? "checked":"");

			request.setAttribute("user",user);
			request.setAttribute("nature",m);
		
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);	
		} catch (Exception e) {
			System.out.println(e);
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
