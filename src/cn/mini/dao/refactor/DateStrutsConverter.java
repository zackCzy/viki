package cn.mini.dao.refactor;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateStrutsConverter extends StrutsTypeConverter {
	SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Object convertFromString(Map arg0, String[] value, Class arg2) {
		if(value!=null&&value.length==1&&!value[0].isEmpty()){
			if(!value[0].matches("[0-9\\-]*"))return null;
			DateLocaleConverter dc=new DateLocaleConverter();
			 try {	
				dc.convert(value[0], "yyyy-MM-dd");
				return new  Date(sd.parse(value[0]).getTime());
			} catch (ParseException e) {
				return null;
			}			
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object o) {
		if(o instanceof java.sql.Date){
			return sd.format(new Date(((java.sql.Date) o).getTime()));
		}
		return null;
	}

}
