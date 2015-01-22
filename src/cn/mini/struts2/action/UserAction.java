package cn.mini.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.mini.domain.SystemMessage;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserBaseDails;
import cn.mini.service.CommentMessageService;
import cn.mini.service.CommunMessageService;
import cn.mini.service.UserDatumService;
import cn.mini.service.UserLogService;
import cn.mini.service.UserPhotoService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("userAction")
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String acc;
	private int page=0;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	@Resource(name = "userLogServiceImpl")
	private UserLogService uls = null;
	@Resource(name = "userPhotoServiceImpl")
	private UserPhotoService ups = null;
	@Resource(name = "userDatumServiceImpl")
	private UserDatumService uds = null;
	@Resource(name = "commentMessageServiceImpl")
	private CommentMessageService cms = null;
	@Resource(name = "communMessageServiceImpl")
	private CommunMessageService cums = null;
	public String datum(){
		try {
			UserBase user=us.findUserService((Integer) ActionContext.getContext().getSession().get("id"));
			ActionContext.getContext().put("datum", true);
			ActionContext.getContext().put("UserBaseDatum",user.getUserBaseDatum());
		} catch (Exception e) {
			System.out.println(e);
			return "my404";
		}
		return "message";
	}
	public String dails(){
		try {
			UserBase user=new UserBase();
			user.setId((Integer) ActionContext.getContext().getSession().get("id"));
			ActionContext.getContext().put("dails", true);			
			UserBaseDails um=uds.getUserBaseDails(user);	
			ActionContext.getContext().put("UserBaseDatum", um);	
		} catch (Exception e) {
			System.out.println(e);
			return "my404";
		}
		return "message";
	}
	public String workDatum(){
		try {
			UserBase user=new UserBase();
			user.setId((Integer) ActionContext.getContext().getSession().get("id"));
			ActionContext.getContext().put("workDatum", true);
			ActionContext.getContext().put("UserBaseDatum", uds.getUnitDatum(user));
		} catch (Exception e) {
			return "my404";
		}
		return "message";
	}
	public String education(){
		try {
			UserBase user=new UserBase();
			user.setId((Integer) ActionContext.getContext().getSession().get("id"));
			ActionContext.getContext().put("education", true);
			ActionContext.getContext().put("UserBaseDatum", uds.getUnitDatum(user));
		} catch (Exception e) {
			return "my404";
		}
		return "message";
	}
	public String userPhoto(){
		try {
			ActionContext.getContext().put("userPhoto", true);
		} catch (Exception e) {
			return "my404";
		}
		return "message";
	}
	public String spaceDatum() {
		try {		
			UserBase user=new UserBase();
			user.setId((Integer) ActionContext.getContext().getSession().get("id"));
			ActionContext.getContext().put("spaceDatum", true);
			ActionContext.getContext().put("UserBaseDatum", uds.getSpaceDatum(user));
		} catch (Exception e) {
			return "my404";
		}	
		return "message";
	}
	public String draft(){
		int id=(Integer) ActionContext.getContext().getSession().get("id");	
		UserBase user=uls.getDraft(id);
		ActionContext.getContext().put("user",user);	
		ActionContext.getContext().put("userlogs",user.getUserLogs());	
		return "draft";
	}
	public String recycled(){
		int id=(Integer) ActionContext.getContext().getSession().get("id");	
		UserBase user=uls.getRubbish(id);
		ActionContext.getContext().put("user",user);	
		ActionContext.getContext().put("userlogs",user.getUserLogs());	
		return "recycled";
	}
	public String spaceMusic(){
		int id=(Integer) ActionContext.getContext().getSession().get("id");	
		UserBase user=us.findUserService(id);
		ActionContext.getContext().put("musics",user.getUserSpaceMusics());	
		return "spaceMusic";
	}
	
	public String photo(){
		int id=(Integer) ActionContext.getContext().getSession().get("id");	
		try {
			UserBase user=new UserBase();
			user.setId(id);
			ActionContext.getContext().put("photo", ups.getUserPhoto(user).getPosition().split(","));
		} catch (Exception e) {
			int [] i={180,180,0,0};
			ActionContext.getContext().put("photo", i);
		}
		
		return "photo";
	}

	public String HomePageMessage(){
		UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
		ActionContext.getContext().put("user",user);
		ActionContext.getContext().put("commentMessage",user.getComments());
		ActionContext.getContext().put("systemMessage",user.getSystemMess());		
		ActionContext.getContext().put("SystmeMessage",true);
		return "Home";
	}
	public String spaceUserPhoto(){
		ActionContext.getContext().put("space",true);
		return "spaceUserPhoto";
	}
	public String SysMessage(){
		try {
			UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
			List<?> com=cms.getPageCommentMessage(user, 1, 1);
			List<SystemMessage> sys=user.getSystemMess();
			SystemMessage sysm=null;
			Object c=null;
			if(sys.size()>0){
				sysm=sys.get(0);
			}
			if(com.size()>0){
				c=com.get(0);
			}
			ActionContext.getContext().put("user",user);
			ActionContext.getContext().put("commentMessage",c);
			ActionContext.getContext().put("systemMessage",sysm);		
			ActionContext.getContext().put("SystmeMessage",true);
		} catch (Exception e) {
			System.out.println(e+"-----");
		}
		return "UserFriends";
	}
	public String myFlans(){
		UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
		ActionContext.getContext().put("user",user);
		ActionContext.getContext().put("flans",user.getFansUsers());
		ActionContext.getContext().put("myFlans",true);
		ActionContext.getContext().put("title","我的粉丝");
		return "UserFriends";
	}
	public String myFollwer(){
		UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
		ActionContext.getContext().put("user",user);
		ActionContext.getContext().put("flans",user.getFollowUsers());
		ActionContext.getContext().put("myFlans",true);
		ActionContext.getContext().put("title","我关注的人");
		return "UserFriends";
	}
	public String commentMessage(){
		UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
		ActionContext.getContext().put("user",user);
		List<?> commens=cms.getPageCommentMessage(user, page, 7);
		for (int i = 0; i < commens.size(); i++) {
			commens.get(i);
		}
		ActionContext.getContext().put("commentMessage",commens);
		ActionContext.getContext().put("comment",true);
		return "UserFriends";
	}
	public String communMessage(){
		UserBase user=us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
		ActionContext.getContext().put("user",user);
		List<?> communs=cums.getPageCommunMessage(user, page, 7);
		for (int i = 0; i < communs.size(); i++) {
			communs.get(0);
		}
		ActionContext.getContext().put("communMessage",communs);
		ActionContext.getContext().put("commun",true);
		return "UserFriends";
	}
	
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
