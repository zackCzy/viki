package temp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.mini.exception.ProcessExcption;
import cn.mini.filter.Filter;



public class CodeFilter implements Filter {
	@Override
	public void doFilter(HttpServletRequest request,HttpServletResponse response) throws ProcessExcption {
		 HttpSession hs=request.getSession(false);
		 if(hs==null){
			 throw new ProcessExcption("Session Null pointer");
		 } 	 
		 String code=(String) hs.getAttribute("code");
		 String parcode=request.getParameter("code");
		 if((parcode==null||code.equalsIgnoreCase(parcode)==false))
			 throw new ProcessExcption();
	}

	@Override
	public void stopFilter(HttpServletRequest request,
			HttpServletResponse response)throws ProcessExcption {
		// TODO Auto-generated method stub
		
	}
}
