package cn.mini.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mini.exception.ProcessExcption;



public interface Filter {
	public void doFilter(HttpServletRequest request,HttpServletResponse response)throws ProcessExcption ;
	public void stopFilter(HttpServletRequest request,HttpServletResponse response) throws ProcessExcption ;
}
