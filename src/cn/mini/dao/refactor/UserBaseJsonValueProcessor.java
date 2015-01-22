package cn.mini.dao.refactor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import cn.mini.domain.UserBase;

public class UserBaseJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof UserBase[]) {
			UserBase user=(UserBase)value;
			obj[0]=user.getId()+"";
			obj[1]=user.getUserBaseDatum().getName();
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
		 if (value instanceof UserBase) {
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
