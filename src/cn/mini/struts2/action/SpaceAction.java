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
	private String selectRequest(String requestURI){
		try {
			Pattern reg= Pattern.compile("/.*?space/(.*?)/");
			Matcher m=reg.matcher(requestURI);
			if(m.find()){
				return m.group(1);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public String diary(){
		String requestURI=request.getRequestURI();
		try {
			String acc=selectRequest(requestURI);
			ActionContext ac=ActionContext.getContext();		
			if(acc!=null){
				
				if(acc.equals("null")){
					ac.put("return",requestURI);
					return "sgin";
				}
				
				UserBase user=us.findUser(acc);
				ac.put("user",user);			
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ac.getSession().get("sgin"));
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
					ac.put("hostLogs",hostLogs);
					ac.put("conhostLogs",conhostLogs);
					ac.put("newhostLogs",newhostLogs);	
				}
				ac.put("authority",spaceAuthority ? 1:0);
				ac.put("type",1);
				ac.put("logcount",uls.getCount(user));	
				List<UserLog> logListt=uls.getCountDiray(user, 1, 15);
				ac.put("dynamic",logListt);	
				return SUCCESS;
			}
			return "my404";
		} catch (Exception e) {
			return "my404";
		}	
	}
	@Override
	public String execute() throws Exception {
		String requestURI=request.getRequestURI();
		try {
			String acc=selectRequest(requestURI);
			ActionContext ac=ActionContext.getContext();
			if(acc!=null){
				if(acc.equals("null")){//如果访问空间名称为null跳转登录
					ac.put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ac.put("user",user);
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ac.getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}
				List<UserLog> hostLogs=uls.getFireVisibelLog(0, 5);
				List<UserLog> conhostLogs=uls.getConFireLog(0, 5);
				List<UserLog> newhostLogs=uls.getNewsFireLog(0, 5);
				ac.put("hostLogs",hostLogs);
				ac.put("conhostLogs",conhostLogs);
				ac.put("newhostLogs",newhostLogs);	
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				ac.put("authority",spaceAuthority ? 1:0);
				
				if(spaceAuthority){
					ac.getSession().put("token", WebUtils.getToken().replace("+", "/"));
					ac.put("type",2);
					ac.put("logcount",uls.getCount(user));	
					Set <UserBase> ulogs=user.getFollowUsers();
					ulogs.add(user);
					List<UserBase> list=new ArrayList<UserBase>();
					list.addAll(ulogs);	
					List<?> logListt=uns.getSpacsSportNewDao(list, 1, 15);
					ac.put("dynamic",logListt);	
				}else{
					ac.put("type",1);
					ac.put("logcount",uls.getCount(user));	
					List<UserLog> logListt=uls.getNoAuthorityADiray(user, 1, 15);
					ac.put("dynamic",logListt);	
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
		ActionContext ac=ActionContext.getContext();
		ac.put("user",user);
		if(!possword.equals(spacePossWord)){
			this.addFieldError("passwordError" , "密码错误");
			ac.put("accessPath",accessPath);
			return "authority";
		}
		Cookie c=new Cookie(spaceName+"SpacePassIsOk", "ok");
		c.setPath(ServletActionContext.getRequest().getContextPath()+"/user/space/");
		ServletActionContext.getResponse().addCookie(c);
		return accessPath;
	}
	public String datum(){
		String requestURI=request.getRequestURI();
		try {
			String acc=selectRequest(requestURI);
			ActionContext ac=ActionContext.getContext();
			if(acc!=null){
				if(acc.equals("null")){//如果访问空间名称为null跳转登录
					ac.put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ac.put("user",user);
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ac.getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}		
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				ac.put("authority",spaceAuthority ?1:0);
				ac.put("unitDatum",uds.getUnitDatum(user));
				ac.put("userBaseDails",uds.getUserBaseDails(user));
				ac.put("type",3);
				ac.put("logcount",uls.getCount(user));			
				return SUCCESS;
			}
			return "my404";
		} catch (Exception e) {
			return "my404";
		}	
	}
	public String smallSpeak(){
		String requestURI=request.getRequestURI();
		try {
			String acc=selectRequest(requestURI);
			ActionContext ac=ActionContext.getContext();
			if(acc!=null){
				if(acc.equals("null")){//如果访问空间名称为null跳转登录
					ac.put("return",requestURI);
					return "sgin";
				}
				UserBase user=us.findUser(acc);
				ac.put("user",user);
				Boolean spaceAuthority=true;
				try {
					spaceAuthority=user.getName().equals(ac.getSession().get("sgin"));
				} catch (Exception e) {
					spaceAuthority=false;
				}
				String verify=verifyPassword(user,"success",spaceAuthority);			
				if(verify!=null){
					return verify;
				}
				ac.put("authority",spaceAuthority ? 1:0);
				ac.put("diary",true);
				ac.put("user",user);
				ac.put("speakCount",uls.getSpeakCount(user));
				ac.put("logcount",uls.getCount(user));	
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			    cal.set(Calendar.MONTH,  cal.get(Calendar.MONTH));
			    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DATE));
			    cal.set(Calendar.HOUR_OF_DAY, 0);
			    cal.set(Calendar.MINUTE, 0);
			    cal.set(Calendar.SECOND, 0);
			    cal.set(Calendar.MILLISECOND, 0);
				Timestamp t=new Timestamp(cal.getTimeInMillis());
				ac.put("speakDateCount",uls.getSpeakDateCount(user,t));	
				ac.put("type",4);
				ac.getSession().put("token", WebUtils.getToken().replace("+", "/"));
				List<UserLog> logListt=uls.getCountSpeak(user, 1, 15);
				ac.put("dynamic",logListt);	
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
		String tempKey=null;
		try {
			for (Cookie c:cookies) {
				if(c.getName().equals(key)){
					tempKey=c.getValue();
					break;
				}
			}
		} catch (Exception e) {
			
		}
		return tempKey;
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
