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
	private int newSport,userId,page,logId;

	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String cteateDiary() {
		ActionContext ac=ActionContext.getContext();
		ac.getSession().put("logToken", WebUtils.getToken().replace("+", "/"));
		ac.put("diary", "saveDiary");
		ac.put("draft", "saveDraft");
		ac.put("title", "发布文字");
		return "cteateDiary";
	}
	public String modifyDiary() {
		ActionContext ac=ActionContext.getContext();
		UserLog ul=uls.getUserLog(logId);
		if(ul.getSmallSpeak()){
			return "my404";
		}
		if(ul.getUser().getId()!=(Integer)ac.getSession().get("id")){
			return "my404";
		}
		ac.getSession().put("logToken", WebUtils.getToken().replace("+", "/"));
		ac.put("title", "修改文字");
		ac.put("diary", "updateDiary");
		ac.put("draft", "updateDraft");
		ac.put("log",ul);
		ac.put("logid",logId);
		return "modifyDiary";
	}
	public String readDiary() {
		UserLog ul=uls.getUserLog(logId);
		if(ul==null){
			return "noDiary";
		}
		if(ul.getSmallSpeak()){
			return "my404";
		}
		Set<UserBase> visitors=ul.getVisitors();
		int caller=0;
		ActionContext ac=ActionContext.getContext();
		try {
			caller=(Integer) ActionContext.getContext().getSession().get("id");
		} catch (Exception e) {
			ac.put("authority",0);	
		}
		try {
			if(caller==0){
				ul.setVisibleNum(ul.getVisibleNum()+1);
			}else if(caller!=ul.getUser().getId()){				
				if(visitors.add(us.findUserService(caller))){
					ul.setVisibleNum(ul.getVisibleNum()+1);
					uls.updateLogVisitors(ul);
				}
			}else{
				ac.put("authority",1);	
			}
		} catch (Exception e) {
			System.out.println(e);
			return "my404";
		}
		ac.put("log",ul);
		ac.put("logid",logId);
		ac.put("logUser",ul.getUser());
		ac.put("visitors",visitors);
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
			if(out!=null){
				out.write("removeDiary is error");
			}
		}
	}
	
	public void removeRubbish() {
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(logId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("removeRubbish user log error");
				return;
			}
			uls.removeRubbishUserLog(logId);
			out.write("removeRubbish user log ok");
		} catch (Exception e) {
			if(out!=null){
				out.write("removeRubbish user log error");
			}
		}
	}
	public void removeDraft(){
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(logId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("removeDraft user log error");
				return;
			}
			uls.removeUserLog(logId);
			out.write("removeDraft user log ok");
		} catch (Exception e) {
			if(out!=null){
				out.write("removeDraft user log error");
			}
		}
	}
	public void recoveryRubbish(){
		PrintWriter out=null;
		try {
			int userID=(Integer) ActionContext.getContext().getSession().get("id");
			UserLog userlog=uls.getUserLog(logId);
			out=ServletActionContext.getResponse().getWriter();
			if(userlog.getUser().getId()!=userID){
				out.write("recoveryRubbish user log error");
				return;
			}
			uls.recoveryRubbishUserLog(logId);
			out.write("recoveryRubbish user log ok");
		} catch (Exception e) {
			if(out!=null){
				out.write("recoveryRubbish user log error");
			}
		}
	}
	public void saveDiary() {
		ActionContext ac=ActionContext.getContext();
		UserLogHandle ulh=new UserLogHandle(ac){
			public void handleUserLog() throws RuntimeException {
				super.ac.getSession().remove("logToken");
				int id = (Integer) super.ac.getSession().get("id");
				userlog.setDraft(false);
				uls.saveLog(userlog, id);
				super.out.write("save user log ok");
			}	
		};
		ulh.initHandle();
	}	
//---------------------------------------------------------------------------------------------
	private abstract class UserLogHandle{
		public abstract void handleUserLog()throws RuntimeException;
		private PrintWriter out=null;
		private ActionContext ac;
		public UserLogHandle(ActionContext ac){
			this.ac=ac;
		}
		public UserLogHandle(){}
		public void initHandle(){
			try {
				if (userlog.getNoHtmlLog().equals("")) {
					out.write("save user log error");
					return;
				}
				String setoken = (String) ac.getSession().get("logToken");

				if (!token.equals(setoken)) {
					out.write("save user log error");
					return;
				}
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				out=ServletActionContext.getResponse().getWriter();
				this.handleUserLog();
			} catch (Exception e) {
				if(out!=null){
					out.write("save user log error");
				}
			}
		}
	}
	public void updateDiary() {
		ActionContext ac=ActionContext.getContext();
		UserLogHandle ulh=new UserLogHandle(ac){
			public void handleUserLog() throws RuntimeException {
				super.ac.getSession().remove("logToken");	
				UserLog reUserLog=uls.getUserLog(userlog.getId());
				reUserLog.setLogName(userlog.getLogName());
				reUserLog.setLogContent(userlog.getLogContent());
				reUserLog.setVisible(userlog.getVisible());
				reUserLog.setType(userlog.getType());
				reUserLog.setDraft(false);
				reUserLog.setNoHtmlLog(userlog.getNoHtmlLog());
				uls.updateLog((Integer)super.ac.getSession().get("id"), reUserLog);
				super.out.write("save user log ok");
			}	
		};
		ulh.initHandle();
	}
	public void saveDraft() {
		ActionContext ac=ActionContext.getContext();
		UserLogHandle ulh=new UserLogHandle(ac){
			public void handleUserLog() throws RuntimeException {
				super.ac.getSession().remove("logToken");
				int id = (Integer) super.ac.getSession().get("id");
				userlog.setDraft(true);
				uls.saveLog(userlog, id);
				super.out.write("save user log ok");
			}	
		};
		ulh.initHandle();
	}	
	public void updateDraft() {
		ActionContext ac=ActionContext.getContext();
		UserLogHandle ulh=new UserLogHandle(ac){
			public void handleUserLog() throws RuntimeException {
				super.ac.getSession().remove("logToken");
				UserLog reUserLog=uls.getUserLog(userlog.getId());
				reUserLog.setLogName(userlog.getLogName());
				reUserLog.setLogContent(userlog.getLogContent());
				reUserLog.setVisible(userlog.getVisible());
				reUserLog.setType(userlog.getType());
				reUserLog.setDraft(true);
				reUserLog.setNoHtmlLog(userlog.getNoHtmlLog());
				uls.updateLog((Integer) super.ac.getSession().get("id"), reUserLog);
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
			return;
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
