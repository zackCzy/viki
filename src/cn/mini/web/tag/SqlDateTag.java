package cn.mini.web.tag;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SqlDateTag extends SimpleTagSupport{

	private static final long serialVersionUID = 1L;
	private Date date;
	private String timeType,replaceStr;
	
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public void setReplaceStr(String replaceStr) {
		this.replaceStr = replaceStr;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void doTag() throws JspException, IOException {

		String day;
		int type= timeType.equals("year") ? Calendar.YEAR : timeType.equals("month") ?Calendar.MONTH : timeType.equals("day") ? Calendar.DATE :Calendar.YEAR;
		String replace=replaceStr==null ? "" :replaceStr;
		if(date!=null){
			Calendar c=Calendar.getInstance();	
			c.setTime(date);			
			if(timeType.equals("month")){
				day=c.get(type)+1+"";
			}else{
				day=c.get(type)+"";
			}
		}else{
			day=replace;
		}
		this.getJspContext().getOut().write(day);
		super.doTag();
	}
}
