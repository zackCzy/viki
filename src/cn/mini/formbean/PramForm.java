package cn.mini.formbean;

import java.util.Map;

public class PramForm {
	private Map<String,String> form;
	
	public Map<String, String> getForm() {
		return form;
	}
	public void setForm(Map<String, String> form) {
		this.form = form;
	}
	public Boolean CheckBean(){
		Boolean flag=true;
		try {			
			for (String s:form.keySet()) {
				if(s.equals(form.get(s))){
					flag= false;
					continue;
				}
			}
		} catch (Exception e) {			
			return false;
		}
		return flag;
	}
}
