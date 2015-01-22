package cn.mini.dao.refactor;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class StringJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if(key.equals("noHtmlLog")){
			String str=(String)value;
			if(str.length()>350){
				str=str.substring(0,350);
			}
			return  str;
		}
		return value;
	}

}
