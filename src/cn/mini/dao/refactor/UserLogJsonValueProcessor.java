package cn.mini.dao.refactor;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class UserLogJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if (value instanceof UserLog) {
			 JSONObject jo=new JSONObject();
			 UserBase user=((UserBase) value);
			 jo.put("name", user.getUserBaseDatum().getName());
			 jo.put("id", user.getId());
			 jo.put("account", user.getName());
			return JSONArray.fromObject(jo);
		}
		return value.toString();
	}

}
