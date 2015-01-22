package cn.mini.struts2.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import cn.mini.domain.SearchMusic;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserSpaceMusic;
import cn.mini.service.UserMusicService;
import cn.mini.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("musicAction")
public class MusicAction extends ActionSupport implements ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="userMusicServiceImpl")
	private UserMusicService umsl=null;
	@Resource(name="userServiceImpl")
	private UserService us=null;
	private String userName;
	private String searchName;
	private int pageIndex,id,pageSize;
	private UserSpaceMusic myMusic;
    private HttpServletResponse response;
	public String myMusic(){
		try {
			UserBase user=us.findUserService(id);	
			List<UserSpaceMusic> usms=user.getUserSpaceMusics();
			List<SearchMusic> list=new ArrayList<SearchMusic>();
			for(UserSpaceMusic temp:usms){
				SearchMusic m=new SearchMusic();
				BeanUtils.copyProperties(m, temp);
				list.add(m);
			}
			JSONArray array = JSONArray.fromObject(list); 
			ActionContext.getContext().put("json",array);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "musicList";
	}

	public String searchMusic(){
		if(!searchName.isEmpty()){
			JSONArray array = JSONArray.fromObject(umsl.Search(searchName, pageIndex,pageSize==0 ? 6:pageSize )); 
			ActionContext.getContext().put("json",array);
		}
		return "musicList";
	}
	public void addMusic() throws IOException {	
		if(id>0){
			try {
				int userId=(Integer)ActionContext.getContext().getSession().get("id");
				umsl.createMusic(id,userId);
				this.response.getWriter().write("add music is ok"); 
			} catch (NullPointerException e) {
				this.response.getWriter().write("add music is error");
			} catch (RuntimeException e){
				this.response.getWriter().write("add music is error");
			}
		}
	}
	public void addInternationMusic() throws IOException{
		try {
			int userId=(Integer)ActionContext.getContext().getSession().get("id");
			umsl.createInternationMusic(myMusic, userId);
			this.response.getWriter().write("add music is ok");
		} catch (RuntimeException e) {
			this.response.getWriter().write("add music is error");
		}		
	}
	public void removeMusic() throws IOException{
		try {		
			umsl.removeMusic(id);
			this.response.getWriter().write("remove music is ok");
		} catch (RuntimeException e) {
			this.response.getWriter().write("remove music is error");
		}		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserSpaceMusic getMyMusic() {
		return myMusic;
	}

	public void setMyMusic(UserSpaceMusic myMusic) {
		this.myMusic = myMusic;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	
}
