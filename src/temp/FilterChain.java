package temp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mini.exception.ProcessExcption;
import cn.mini.filter.Filter;


public class FilterChain implements Filter {

	List<Filter> filters=new ArrayList<Filter>();

	public FilterChain addFliter(Filter f){
		filters.add(f);
		return this;
	}
	
	@Override
	public void doFilter(HttpServletRequest request,HttpServletResponse response) throws ProcessExcption {
		for(Filter f : filters){
			f.doFilter(request, response);
		}
		stopFilter(request,response);
	}

	@Override
	public void stopFilter(HttpServletRequest request,
			HttpServletResponse response) throws ProcessExcption {
		for(Filter f : filters){
			f.stopFilter(request, response);	 
		}
	}

}
