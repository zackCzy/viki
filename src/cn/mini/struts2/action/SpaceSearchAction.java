package cn.mini.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.service.SpaceSearchService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("spaceSearch")
public class SpaceSearchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchName;
	private int page;
	private int pageSize=10;
	@Resource(name="spaceSearchServiceImpl")
	SpaceSearchService sss=null;
	public String user(){
		try {
			List<UserBase> users=sss.searchUser(searchName, page, pageSize);
			ActionContext.getContext().put("searchCount", sss.searchUserCount(searchName));
			ActionContext.getContext().put("searchName", searchName);
			ActionContext.getContext().put("users", users);
			ActionContext.getContext().put("type", "找人");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return SUCCESS;
	}
	public String log(){
		try {
			
			List<UserLog> logs=sss.searchLog(searchName, page, pageSize);
			ActionContext.getContext().put("searchCount", sss.searchLogCount(searchName));
			ActionContext.getContext().put("searchName", searchName);
			ActionContext.getContext().put("logs", logs);
			ActionContext.getContext().put("type", "博客");
		} catch (Exception e) {
			System.out.println(e);
		}	
		return SUCCESS;
	}
	public String smallSpeak(){
		try {
			List<UserLog> logs=sss.searchSmallSpeak(searchName, page, pageSize);
			ActionContext.getContext().put("searchCount", sss.searchSmallSpeakCount(searchName));
			ActionContext.getContext().put("searchName", searchName);
			ActionContext.getContext().put("smallSpeaks", logs);
			ActionContext.getContext().put("type", "V说");
		} catch (Exception e) {
			System.out.println(e);
		}	
		return SUCCESS;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
