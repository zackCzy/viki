package cn.mini.dao.refactor;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class BooleanJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return arg0;
	}

	@Override
	public Object processObjectValue(String arg0, Object obj, JsonConfig arg2) {
		
		return obj.toString();
	}

}
