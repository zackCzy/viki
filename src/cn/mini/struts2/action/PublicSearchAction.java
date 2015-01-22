package cn.mini.struts2.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import cn.mini.service.UserMusicService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("publicSearchAction")
public class PublicSearchAction extends ActionSupport implements ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="userMusicServiceImpl")
	private UserMusicService umsl=null;
	private HttpServletResponse response=null;
	private String searchName;
	private int pageIndex,pageSize;
	public void music(){
		PrintWriter out=null;
		if(!searchName.isEmpty()){
			JSONArray array = JSONArray.fromObject(umsl.Search(searchName, pageIndex,pageSize==0 ? 6:pageSize )); 
			ActionContext.getContext().put("json",array);
			try {
				out=response.getWriter();
				out.write(array.toString());
			} catch (Exception e) {
				if(out!=null){
					out.close();
				}
			}
		}
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
	  this.response=response;
	  this.response.setCharacterEncoding("UTF-8");
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
