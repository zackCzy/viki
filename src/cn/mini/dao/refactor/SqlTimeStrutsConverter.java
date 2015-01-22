package cn.mini.dao.refactor;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class SqlTimeStrutsConverter extends StrutsTypeConverter {
	@Override
	public Object convertFromString(Map arg0, String[] value, Class arg2) {

		return null;
	}

	@Override
	public String convertToString(Map arg0, Object o) {
		if(o instanceof java.sql.Timestamp){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").format((java.sql.Timestamp) o);
		}
		return null;
	}

}
