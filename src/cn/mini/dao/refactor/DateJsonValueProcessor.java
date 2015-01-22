package cn.mini.dao.refactor;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {
	private String format = "yyyy-MM-dd";

	public DateJsonValueProcessor() {
	}

	public DateJsonValueProcessor(String format) {
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof java.sql.Timestamp[]) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			java.sql.Timestamp[] dates = (java.sql.Timestamp[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value instanceof java.sql.Timestamp) {
			String str = new SimpleDateFormat(format).format((java.sql.Timestamp) value);
			return str;
		}
		return value.toString();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
