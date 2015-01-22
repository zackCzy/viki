package cn.mini.struts2.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import cn.mini.domain.Comment;
import cn.mini.domain.ReviewewComment;
import cn.mini.domain.UserBase;
import cn.mini.service.LogCommentService;
import cn.mini.service.ReviewewCommentService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("commentAction")
public class CommentAction extends ActionSupport implements ServletResponseAware{

	private Comment c;
	private ReviewewComment rc;
	private int id,commentID,userId;
	private HttpServletResponse response;
	@Resource(name="logCommentServiceImpl")
	private LogCommentService lcs=null;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	@Resource(name="reviewewCommentServiceImpl")
	private ReviewewCommentService rcs=null;
	
	private static final long serialVersionUID = 1L;
	public void save() throws Exception{
		PrintWriter out=null;
		try {	
			out=response.getWriter();
			 java.sql.Timestamp date=new java.sql.Timestamp(new java.util.Date().getTime());
			c.setDate(date);	
			UserBase comUser=isLogin();
			if(comUser==null){
				out.write("you login has expired");
				return;
			}
			c.setComUser(comUser);
			lcs.saveLogCom(id, c);
			JSONObject json=new JSONObject();
			json.put("spaceName", comUser.getUserBaseDatum().getName());
			json.put("id", comUser.getId());
			json.put("name", comUser.getName());
			if(userId==c.getUser().getId()){
				json.put("comId", c.getId());
			}
			json.put("commentId", c.getId());
			out.write(json.toString());
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally{
			if(out!=null){
				out.close();
			}
		}
		
	}
	public void remove() throws Exception{
		PrintWriter out=null;
		try {
			out=response.getWriter();
			UserBase user=new UserBase();
			user.setId((Integer)ActionContext.getContext().getSession().get("id"));
			lcs.removeLogCom(id,user);
			out.write("remove is ok");
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	public void saveReviewewComment() throws Exception{
		PrintWriter out=null;
		try {	
			out=response.getWriter();
			 java.sql.Timestamp date=new java.sql.Timestamp(new java.util.Date().getTime());
			rc.setDate(date);	
			UserBase comUser=isLogin();
			if(comUser==null){
				out.write("you login has expired");
				return;
			}
			UserBase user=us.findUserService(userId);
			Comment com=lcs.getComment(commentID);
			rc.setComUser(comUser);
			rc.setUser(user);	
			rc.setComment(com);
			rcs.saveReviewewCom(rc);
			JSONObject json=new JSONObject();
			json.put("replyName", rc.getUser().getUserBaseDatum().getName());
			json.put("userName", rc.getComUser().getUserBaseDatum().getName());	
			json.put("comUserID", comUser.getId());	
			json.put("commentId", com.getId());	
			
			out.write(json.toString());
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	private UserBase isLogin(){
		try {
			return us.findUserService((Integer)ActionContext.getContext().getSession().get("id"));
		} catch (Exception e) {
			return null;
		}
	}
	public Comment getC() {
		return c;
	}
	public void setC(Comment c) {
		this.c = c;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ReviewewComment getRc() {
		return rc;
	}
	public void setRc(ReviewewComment rc) {
		this.rc = rc;
	}
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;	
	}
}
