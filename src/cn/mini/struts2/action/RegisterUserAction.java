package cn.mini.struts2.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String test() {
		Map<String, Object> m=ActionContext.getContext().getParameters();
		Set<java.util.Map.Entry<String, Object>>e =m.entrySet();
		Iterator<java.util.Map.Entry<String, Object>> it=e.iterator();
		while(it.hasNext()){
			java.util.Map.Entry<String, Object> temp=it.next();
			System.out.println(temp.getKey()+"---"+temp.getValue());
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("123");
		Map<String, Object> m=ActionContext.getContext().getParameters();
		Set<java.util.Map.Entry<String, Object>>e =m.entrySet();
		Iterator<java.util.Map.Entry<String, Object>> it=e.iterator();
		while(it.hasNext()){
			java.util.Map.Entry<String, Object> temp=it.next();
			System.out.println(temp.getKey()+"---"+temp.getValue());
		}
		return SUCCESS;
	}

}
