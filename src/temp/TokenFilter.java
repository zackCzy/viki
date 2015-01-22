package temp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.mini.exception.ProcessExcption;
import cn.mini.filter.Filter;


public class TokenFilter implements Filter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response) throws ProcessExcption {
		HttpSession hs=request.getSession(false);	
		if(hs==null) throw new ProcessExcption("Session Null pointer");
		String token=request.getParameter("token");		
		String serToken=(String) hs.getAttribute("token");	
		if(token==null||serToken==null||token.equalsIgnoreCase(serToken)==false){
			throw new ProcessExcption();
		}
		
	}

	@Override
	public void stopFilter(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
}
