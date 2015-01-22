package temp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mini.domain.User;
import cn.mini.exception.ProcessExcption;
import cn.mini.filter.Filter;

public class UserBeanFilter implements Filter {
	User u=null;
	public UserBeanFilter(User u){
		this.u=u;
	}
	
	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response) throws ProcessExcption {
		String name=u.getName();
		String pass=u.getPassword();
		if(!name.matches("([A-Za-z]+[0-9]+)|([0-9]+[A-Za-z]){1,3}")||name==null||name.trim().equals("")||pass.length()<7){
			throw new ProcessExcption("�?��ʽ����ȷ");
		}
	}

	@Override
	public void stopFilter(HttpServletRequest request,
			HttpServletResponse response) throws ProcessExcption {
		// TODO Auto-generated method stub

	}

}
