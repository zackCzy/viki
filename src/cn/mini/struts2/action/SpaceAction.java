package cn.mini.struts2.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import Utils.WebUtils;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.service.SportNewService;
import cn.mini.service.UserDatumService;
import cn.mini.service.UserLogService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SpaceAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String spaceName;
	private String spacePossWord;
	private String accessPath;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	@Resource(name = "userLogServiceImpl")
	private UserLogService uls = null;
	@Resource(name = "userDatumServiceImpl")
	UserDatumService uds=null;
	@Resource(name = "sportNewServiceImpl")
	private SportNewService uns = null;
	public String diary(){
		try {
			String requestURI=request.getRequestURI();
			Pattern reg= Pattern.compile("/.*?space/(.*?)/");
			Matcher m=reg.matcher(requestURI);
			String acc;
			if(m.find()){
				acc=m.group(1);
				if(acc.equals("null")){
					ActionContext.getContext().put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ActionContext.getContext().put("user",user);			
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ActionContext.getContext().getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				if(!spaceAuthority){
					List<UserLog> hostLogs=uls.getFireVisibelLog(0, 5);
					List<UserLog> conhostLogs=uls.getConFireLog(0, 5);
					List<UserLog> newhostLogs=uls.getNewsFireLog(0, 5);
					ActionContext.getContext().put("hostLogs",hostLogs);
					ActionContext.getContext().put("conhostLogs",conhostLogs);
					ActionContext.getContext().put("newhostLogs",newhostLogs);	
				}
				ActionContext.getContext().put("authority",spaceAuthority ? 1:0);
				ActionContext.getContext().put("type",1);
				ActionContext.getContext().put("logcount",uls.getCount(user));	
				List<UserLog> logListt=uls.getCountDiray(user, 1, 15);
				ActionContext.getContext().put("dynamic",logListt);	
				return SUCCESS;
			}
			return "my404";
		} catch (Exception e) {
			return "my404";
		}	
	}
	@Override
	public String execute() throws Exception {
		try {
			String requestURI=request.getRequestURI();
			Pattern reg= Pattern.compile("/.*?space/(.*?)/");
			Matcher m=reg.matcher(requestURI);
			String acc;
			if(m.find()){
				acc=m.group(1);
				if(acc.equals("null")){//如果访问空间名称为null跳转登录
					ActionContext.getContext().put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ActionContext.getContext().put("user",user);
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ActionContext.getContext().getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}
				List<UserLog> hostLogs=uls.getFireVisibelLog(0, 5);
				List<UserLog> conhostLogs=uls.getConFireLog(0, 5);
				List<UserLog> newhostLogs=uls.getNewsFireLog(0, 5);
				ActionContext.getContext().put("hostLogs",hostLogs);
				ActionContext.getContext().put("conhostLogs",conhostLogs);
				ActionContext.getContext().put("newhostLogs",newhostLogs);	
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				ActionContext.getContext().put("authority",spaceAuthority ? 1:0);
				
				if(spaceAuthority){
					ActionContext.getContext().getSession().put("token", WebUtils.getToken().replace("+", "/"));
					ActionContext.getContext().put("type",2);
					ActionContext.getContext().put("logcount",uls.getCount(user));	
					Set <UserBase> ulogs=user.getFollowUsers();
					ulogs.add(user);
					List<UserBase> list=new ArrayList<UserBase>();
					list.addAll(ulogs);	
					List<?> logListt=uns.getSpacsSportNewDao(list, 1, 15);
					ActionContext.getContext().put("dynamic",logListt);	
				}else{
					ActionContext.getContext().put("type",1);
					ActionContext.getContext().put("logcount",uls.getCount(user));	
					List<UserLog> logListt=uls.getNoAuthorityADiray(user, 1, 15);
					ActionContext.getContext().put("dynamic",logListt);	
					return "diary";
				}
				return SUCCESS;
			}
			return "my404";
		} catch (Exception e) {
			return "my404";
		}	
	}
	public String spaceChackAuthority(){
		UserBase user=us.findUser(spaceName);
		String possword=uds.getSpaceDatum(user).getSpacePassWord();
		ActionContext.getContext().put("user",user);
		if(!possword.equals(spacePossWord)){
			this.addFieldError("passwordError" , "密码错误");
			ActionContext.getContext().put("accessPath",accessPath);
			return "authority";
		}
		Cookie c=new Cookie(spaceName+"SpacePassIsOk", "ok");
		c.setPath(ServletActionContext.getRequest().getContextPath()+"/user/space/");
		ServletActionContext.getResponse().addCookie(c);
		return accessPath;
	}
	public String datum(){
		try {
			String requestURI=request.getRequestURI();
			Pattern reg= Pattern.compile("/.*?space/(.*?)/");
			Matcher m=reg.matcher(requestURI);
			String acc;
			if(m.find()){
				acc=m.group(1);
				if(acc.equals("null")){
					ActionContext.getContext().put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ActionContext.getContext().put("user",user);
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ActionContext.getContext().getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}		
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				ActionContext.getContext().put("authority",spaceAuthority ?1:0);
				ActionContext.getContext().put("unitDatum",uds.getUnitDatum(user));
				ActionContext.getContext().put("userBaseDails",uds.getUserBaseDails(user));
				ActionContext.getContext().put("type",3);
				ActionContext.getContext().put("logcount",uls.getCount(user));			
				return SUCCESS;
			}
			return "my404";
		} catch (Exception e) {
			return "my404";
		}	
	}
	public String smallSpeak(){
		try {
			String requestURI=request.getRequestURI();
			Pattern reg= Pattern.compile("/.*?space/(.*?)/");
			Matcher m=reg.matcher(requestURI);
			String acc;
			if(m.find()){
				acc=m.group(1);
				if(acc.equals("null")){
					ActionContext.getContext().put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ActionContext.getContext().put("user",user);
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ActionContext.getContext().getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				ActionContext.getContext().put("authority",spaceAuthority ? 1:0);
				ActionContext.getContext().put("diary",true);
				ActionContext.getContext().put("user",user);
				ActionContext.getContext().put("speakCount",uls.getSpeakCount(user));
				ActionContext.getContext().put("logcount",uls.getCount(user));	
				Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			     cal.set(Calendar.MONTH,  cal.get(Calendar.MONTH));
			     cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DATE));
			     cal.set(Calendar.HOUR_OF_DAY, 0);
			     cal.set(Calendar.MINUTE, 0);
			     cal.set(Calendar.SECOND, 0);
			     cal.set(Calendar.MILLISECOND, 0);
				Timestamp t=new Timestamp(cal.getTimeInMillis());
				ActionContext.getContext().put("speakDateCount",uls.getSpeakDateCount(user,t));	
				ActionContext.getContext().put("type",4);
				ActionContext.getContext().getSession().put("token", WebUtils.getToken().replace("+", "/"));
				List<UserLog> logListt=uls.getCountSpeak(user, 1, 15);
				ActionContext.getContext().put("dynamic",logListt);	
				return SUCCESS;
			}
			return "my404";
		} catch (Exception e) {
			return "my404";
		}	
	}
	private String verifyPassword(UserBase user,String returnUrl,Boolean spaceAuthority){
		 Cookie[] cookies=ServletActionContext.getRequest().getCookies();
		String SpacePassIsOk=getCookie(cookies,user.getName()+"SpacePassIsOk");
		if(SpacePassIsOk==null){
			SpacePassIsOk="no";
		}
		if(!spaceAuthority&&uds.getSpaceDatum(user).getVisible()&&SpacePassIsOk.equals("no")){				
			ActionContext.getContext().put("accessPath",returnUrl);
			return "authority";
		}		
		return null;
	}
	private String getCookie(Cookie [] cookies,String key){
		try {
			for (Cookie c:cookies) {
				if(c.getName().equals(key)){
					return c.getValue();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getSpacePossWord() {
		return spacePossWord;
	}
	public void setSpacePossWord(String spacePossWord) {
		this.spacePossWord = spacePossWord;
	}
	public String getAccessPath() {
		return accessPath;
	}
	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

}
