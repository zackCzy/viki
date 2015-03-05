package cn.mini.struts2.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import cn.mini.dao.refactor.BooleanJsonValueProcessor;
import cn.mini.dao.refactor.DateJsonValueProcessor;
import cn.mini.dao.refactor.StringJsonValueProcessor;
import cn.mini.dao.refactor.UserBaseJsonValueProcessor;
import cn.mini.domain.Comment;
import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;
import cn.mini.service.LogCommentService;
import cn.mini.service.SportNewService;
import cn.mini.service.UserLogService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("jsonComAction")
public class JsonComAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<JSONObject> list;  
	private int newSport,userId,page,logId;
	private JSONArray logLists;
	@Resource(name="logCommentServiceImpl")
	LogCommentService lcs=null;
	@Resource(name = "userServiceImpl")
	private UserService us = null;
	@Resource(name = "sportNewServiceImpl")
	private SportNewService uns = null;
	@Resource(name = "userLogServiceImpl")
	private UserLogService uls = null;
	
	public String com(){
		List<Comment> comments=lcs.getLogCom(logId);
		Boolean flag=false;
		try {
			if(comments.get(0).getUser().getId()==(Integer)ActionContext.getContext().getSession().get("id")){
				flag=true;
			}
		} catch (Exception e) {}
		
		list=new ArrayList<JSONObject>();
		for(Comment c:comments){
			JSONObject o=new JSONObject();	
			o.put("comText", c.getContent());
			o.put("name", c.getComUser().getUserBaseDatum().getName());
			o.put("account", c.getComUser().getName());
			o.put("id", c.getComUser().getId());
			List<ReviewewComment> relist=c.getReviewewCom();
			JSONArray reviewewJsonList=new JSONArray();	
			for (ReviewewComment reviewewCom:relist) {
				JSONObject reviewew=new JSONObject();	
				reviewew.put("comText", reviewewCom.getContent());
				reviewew.put("name", reviewewCom.getUser().getUserBaseDatum().getName());
				reviewew.put("comName", reviewewCom.getComUser().getUserBaseDatum().getName());
				reviewew.put("account", reviewewCom.getComUser().getName());
				reviewew.put("id", reviewewCom.getComUser().getId());
				if(flag){
					reviewew.put("comId", reviewewCom.getId());
				}
				reviewew.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm" ).format(reviewewCom.getDate()));
				reviewewJsonList.add(reviewew);
			}
			
			if(flag){
				o.put("comId", c.getId());
			}
			o.put("commentId", c.getId());
			o.put("reviewewCom",reviewewJsonList);
			o.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm" ).format(c.getDate()));
			list.add(o);
		}
		return SUCCESS;
	}
	public String getContent(){
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor( java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		config.registerJsonValueProcessor(UserBase.class, new UserBaseJsonValueProcessor());
		config.registerJsonValueProcessor(String.class, new StringJsonValueProcessor());
		config.registerJsonValueProcessor(Boolean.class, new BooleanJsonValueProcessor());
		config.setJsonPropertyFilter(new PropertyFilter() {		
			@Override
			public boolean apply(Object obj, String key, Object arg2) {
				return (!(key.equals("noHtmlLog") || key.equals("user") ||key.equals("id")||key.equals("modifyDate")||key.equals("commentNum") ||key.equals("visibleNum")||key.equals("logName")||key.equals("smallSpeak")));		
			}
		});
		List<?> logListt=null;
		if(newSport==2&&(Integer)ActionContext.getContext().getSession().get("id")==userId){
			UserBase user=us.findUserService(userId);	
			Set <UserBase> ulogs=user.getFollowUsers();
			List<UserBase> list=new ArrayList<UserBase>();
			list.add(user);
			list.addAll(ulogs);	
			logListt=uns.getSpacsSportNewDao(list, page, 15);
		}else if(newSport==4){
			UserBase user=us.findUserService(userId);	
			logListt=uls.getCountSpeak(user, page, 15);
		}else if(newSport==1){
			UserBase user=us.findUserService(userId);	
			logListt=uls.getCountDiray(user, page, 15);
		}
		logLists = JSONArray.fromObject(logListt,config); 
		return SUCCESS;
	}
	
	public List<JSONObject> getList() {
		return list;
	}

	public void setList(List<JSONObject> list) {
		this.list = list;
	}
	@JSON(serialize=false)
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}
	@JSON(serialize=false)
	public int getNewSport() {
		return newSport;
	}
	public void setNewSport(int newSport) {
		this.newSport = newSport;
	}
	@JSON(serialize=false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public JSONArray getLogLists() {
		return logLists;
	}
	public void setLogLists(JSONArray logLists) {
		this.logLists = logLists;
	}
}
