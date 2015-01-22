package cn.mini.struts2.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.mini.service.U2UService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("u2UFriendsAction")
public class U2UFriendsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="u2UServiceImpl")
	U2UService u2s=null;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	
	private int addUserId;
	private int removeUserid;
	private String contentType = "text/html;charset=utf-8";
	
	public void addFirend(){
		ServletActionContext.getResponse().setContentType(contentType);
		PrintWriter pw=null;
		try {
			u2s.addFriends((Integer)ActionContext.getContext().getSession().get("id"), addUserId);
			pw=ServletActionContext.getResponse().getWriter();
			pw.print("{'add':'ok'}");
		} catch (Exception e) {
			
			try {
				pw=ServletActionContext.getResponse().getWriter();
				pw.print("{'add':'error'}");
			} catch (IOException e1) {}
		}finally{
			if(pw!=null){
				pw.flush();
				pw.close();		
			}			
		}
		
	}
	public void removeFlans(){
		ServletActionContext.getResponse().setContentType(contentType);
		PrintWriter pw=null;
		try {
			u2s.removeFriend(us.findUserService((Integer)ActionContext.getContext().getSession().get("id")), us.findUserService(removeUserid));
			pw=ServletActionContext.getResponse().getWriter();
			pw.print("remove is ok");
		} catch (Exception e) {	
			try {
				pw=ServletActionContext.getResponse().getWriter();
				pw.print("remove is error");
			} catch (IOException e1) {}
		}finally{
			if(pw!=null){
				pw.flush();
				pw.close();		
			}			
		}
	}
	public void removeFollow(){
		ServletActionContext.getResponse().setContentType(contentType);
		PrintWriter pw=null;
		try {
			u2s.removeFollow(us.findUserService((Integer)ActionContext.getContext().getSession().get("id")), us.findUserService(removeUserid));
			pw=ServletActionContext.getResponse().getWriter();
			pw.print("remove is ok");
		} catch (Exception e) {	
			System.out.println(e);
			try {
				pw=ServletActionContext.getResponse().getWriter();
				pw.print("remove is error");
			} catch (IOException e1) {}
		}finally{
			if(pw!=null){
				pw.flush();
				pw.close();		
			}			
		}
	}
	public int getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(int addUserId) {
		this.addUserId = addUserId;
	}
	public int getRemoveUserid() {
		return removeUserid;
	}
	public void setRemoveUserid(int removeUserid) {
		this.removeUserid = removeUserid;
	}
	
}
