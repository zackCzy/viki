package cn.mini.struts2.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import Utils.WebUtils;
import cn.mini.dao.refactor.BooleanJsonValueProcessor;
import cn.mini.dao.refactor.DateJsonValueProcessor;
import cn.mini.dao.refactor.UserBaseJsonValueProcessor;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.service.SportNewService;
import cn.mini.service.UserLogService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("userFunctionAction")
public class UserFunctionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource(name = "userLogServiceImpl")
	private UserLogService uls = null;
	@Resource(name = "userServiceImpl")
	private UserService us = null;
	@Resource(name = "sportNewServiceImpl")
	private SportNewService uns = null;
	private UserLog userlog;
	private String token;
	private int newSport,userId,page;

	public String cteateDiary() {
		ActionContext.getContext().getSession().put("token", WebUtils.getToken().replace("+", "/"));
		ActionContext.getContext().put("diary", "saveDiary");
		ActionContext.getContext().put("draft", "saveDraft");
		ActionContext.getContext().put("title", "发布文字");
		return "cteateDiary";
	}
	public String modifyDiary() {
		UserLog ul=uls.getUserLog(userId);
		if(ul.getSmallSpeak()){
			return "my404";
		}
		if(ul.getUser().getId()!=(Integer)ActionContext.getContext().getSession().get("id")){
			return "my404";
		}
		ActionContext.getContext().getSession().put("token", WebUtils.getToken().replace("+", "/"));
		ActionContext.getContext().put("title", "修改文字");
		ActionContext.getContext().put("diary", "updateDiary");
		ActionContext.getContext().put("draft", "updateDraft");
		ActionContext.getContext().put("log",ul);
		ActionContext.getContext().put("logid",userId);
		return "modifyDiary";
	}
	public String readDiary() {
		UserLog ul=uls.getUserLog(userId);
		if(ul==null){
			return "noDiary";
		}
		if(ul.getSmallSpeak()){

			return "my404";
		}
		int caller=(Integer) ActionContext.getContext().getSession().get("id");
		Set<UserBase> visitors=ul.getVisitors();
		if(caller>0&&caller!=ul.getUser().getId()){				
			if(visitors.add(us.findUserService(caller))){
				ul.setVisibleNum(ul.getVisibleNum()+1);
				uls.updateLog(ul);
			}
			ActionContext.getContext().put("authority",0);	
		}else{
			ActionContext.getContext().put("authority",1);	
		}
		ActionContext.getContext().put("log",ul);
		ActionContext.getContext().put("logid",userId);
		ActionContext.getContext().put("logUser",ul.getUser());
		ActionContext.getContext().put("visitors",visitors);
		return "readDiary";
	}
	public void removeDiary(){
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(userId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("removeDiary is error");
				return;
			}
			uls.removeUserLog(userId);
			out.write("removeDiary is ok");
		} catch (Exception e) {
			System.out.println(e);
			if(out!=null){
				out.write("removeDiary is error");
			}
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	public void removeRubbish() {
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(userId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("removeRubbish user log error");
				return;
			}
			uls.removeRubbishUserLog(userId);
			out.write("removeRubbish user log ok");
		} catch (Exception e) {
			if(out!=null){
				out.write("removeRubbish user log error");
			}
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	public void removeDraft(){
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(userId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("removeDraft user log error");
				return;
			}
			uls.removeUserLog(userId);
			out.write("removeDraft user log ok");
		} catch (Exception e) {
			if(out!=null){
				out.write("removeDraft user log error");
			}
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	public void recoveryRubbish(){
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(userId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("recoveryRubbish user log error");
				return;
			}
			uls.recoveryRubbishUserLog(userId);
			out.write("recoveryRubbish user log ok");
		} catch (Exception e) {
			if(out!=null){
				out.write("recoveryRubbish user log error");
			}
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
//---------------------------------------------------------------------------------------------
	private abstract class UserLogHandle{
		public abstract void handleUserLog()throws RuntimeException;
		private PrintWriter out=null;
		public void initHandle(){
			try {
				if (userlog.getNoHtmlLog().equals("")) {
					out.write("save user log error");
					return;
				}
				String setoken = (String) ActionContext.getContext().getSession().get("token");
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				out=ServletActionContext.getResponse().getWriter();
				if (!token.equals(setoken)) {
					out.write("save user log error");
					return;
				}
				this.handleUserLog();
			} catch (Exception e) {
				if(out!=null){
					out.write("save user log error");
				}
			}finally{
				if(out!=null){
					out.close();
				}
			}
		}
	}
	public void saveDiary() {
		UserLogHandle ulh=new UserLogHandle(){
			public void handleUserLog() throws RuntimeException {
				ActionContext.getContext().getSession().remove("token");
				int id = (Integer) ActionContext.getContext().getSession().get("id");
				userlog.setDraft(false);
				uls.saveLog(userlog, id);
				super.out.write("save user log ok");
			}	
		};
		ulh.initHandle();
	}	
	public void updateDiary() {
		UserLogHandle ulh=new UserLogHandle(){
			public void handleUserLog() throws RuntimeException {
				ActionContext.getContext().getSession().remove("token");
				userlog.setDraft(false);
				uls.updateLog((Integer) ActionContext.getContext().getSession().get("id"), userlog);
				super.out.write("save user log ok");
			}	
		};
		ulh.initHandle();
	}
	public void saveDraft() {
		UserLogHandle ulh=new UserLogHandle(){
			public void handleUserLog() throws RuntimeException {
				ActionContext.getContext().getSession().remove("token");
				int id = (Integer) ActionContext.getContext().getSession().get("id");
				userlog.setDraft(true);
				uls.saveLog(userlog, id);
				super.out.write("save user log ok");
			}	
		};
		ulh.initHandle();
	}	
	public void updateDraft() {
		UserLogHandle ulh=new UserLogHandle(){
			public void handleUserLog() throws RuntimeException {
				ActionContext.getContext().getSession().remove("token");
				userlog.setDraft(true);
				uls.updateLog((Integer) ActionContext.getContext().getSession().get("id"), userlog);
				super.out.write("save user log ok");
			}
		};
		ulh.initHandle();
	}
	public void saveSpeak() {
		UserLogHandle ulh=new UserLogHandle(){
			public void handleUserLog() throws RuntimeException {
				int id = (Integer) ActionContext.getContext().getSession().get("id");
				userlog.setSmallSpeak(true);
				uls.saveLog(userlog, id);
				JsonConfig config=new JsonConfig();
				config.registerJsonValueProcessor( java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
				config.registerJsonValueProcessor(UserBase.class, new UserBaseJsonValueProcessor());
				config.setJsonPropertyFilter(new PropertyFilter() {		
					@Override
					public boolean apply(Object obj, String key, Object arg2) {
						return (!(key.equals("noHtmlLog") || key.equals("user") ||key.equals("id")||key.equals("modifyDate")||key.equals("commentNum") ||key.equals("visibleNum")||key.equals("logName")));		
					}
				});
				List<UserLog> listLog=new ArrayList<UserLog>();
				listLog.add(userlog);
				JSONArray array = JSONArray.fromObject(listLog,config); 
				super.out.write(array.toString());	
			}	
		};
		ulh.initHandle();
	}	
	//获取空间动态
	public void getDraft(){
		PrintWriter out =null;
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor( java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		config.registerJsonValueProcessor(UserBase.class, new UserBaseJsonValueProcessor());
		config.registerJsonValueProcessor(Boolean.class, new BooleanJsonValueProcessor());
		config.setJsonPropertyFilter(new PropertyFilter() {		
			@Override
			public boolean apply(Object obj, String key, Object arg2) {
				return (!(key.equals("noHtmlLog") || key.equals("user") ||key.equals("id")||key.equals("modifyDate")||key.equals("commentNum") ||key.equals("visibleNum")||key.equals("logName")||key.equals("smallSpeak")));		
			}
		});
		try {		
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			out =ServletActionContext.getResponse().getWriter();	
			List<?> logListt=null;
			if(newSport==2&&(Integer)ActionContext.getContext().getSession().get("id")==userId){
				UserBase user=us.findUserService(userId);	
				Set <UserBase> ulogs=user.getFollowUsers();
				ulogs.add(user);
				List<UserBase> list=new ArrayList<UserBase>();
				list.addAll(ulogs);	
				logListt=uns.getSpacsSportNewDao(list, page, 15);
			}else if(newSport==4){
				UserBase user=us.findUserService(userId);	
				logListt=uls.getCountSpeak(user, page, 15);
			}else if(newSport==1){
				UserBase user=us.findUserService(userId);	
				logListt=uls.getCountDiray(user, page, 15);
			}
			JSONArray array = JSONArray.fromObject(logListt,config); 
			out.write(array.toString());	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}

	public UserLog getUserlog() {
		return userlog;
	}

	public void setUserlog(UserLog userlog) {
		this.userlog = userlog;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNewSport() {
		return newSport;
	}
	public void setNewSport(int newSport) {
		this.newSport = newSport;
	}

}
