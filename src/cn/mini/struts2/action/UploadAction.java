package cn.mini.struts2.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import Utils.DrawPhoto;
import Utils.Uploader;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.service.UserPhotoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("uploadAction")
public class UploadAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contentType = "text/html;charset=utf-8";
	private File upfile;
	public File getUpfile() {
		return upfile;
	}
	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}
	private int top, left, right, bottom, rotation;
	private String imgsize;
	@Resource(name = "userPhotoServiceImpl")
	private UserPhotoService ups = null;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void uploadEdit(){
		
		try {
			Uploader up = new Uploader(upfile,request);
			
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		    up.setSavePath("upload");
		    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		    up.setAllowFiles(fileType);
		    up.setMaxSize(10000); //单位KB
		    up.upload();
		    
		    String callback = request.getParameter("callback");
		    String result = "{\"name\":\""+ up.getFileName()+".png" +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
		    result = result.replaceAll( "\\\\", "\\\\" );
		    result = result.replaceAll( "\\\\", "\\\\" );
		    if( callback == null ){
		        response.getWriter().print( result );
		    }else{
		        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	public void uploadUserPhoto() {
		response.setContentType(contentType);
		PrintWriter out = null;
		try {
			int userid=(Integer) ActionContext.getContext().getSession().get("id");
			UserBase user=new UserBase();
			user.setId(userid);
			FileInputStream fs=new FileInputStream(upfile);
			if(ups.getUserPhoto(user)==null){				
				ups.saveTempUserPhoto(userid, fs);
			}else {
				ups.updateTempUserPhoto(userid, fs);
			}
			
			out = response.getWriter();
			out.print("{'id':'"+ userid+ "'}");
		} catch (Exception e) {
			System.out.println(e);
			try {
				out = response.getWriter();
				out.print("{error:userPhoto is exist}");
			} catch (IOException e1) {
			}
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}
	public void saveUserPhoto() {
		int upId=(Integer) ActionContext.getContext().getSession().get("id");
		UserBase user=new UserBase();
		user.setId(upId);
		UserPhoto up = ups.getUserPhoto(user);
		response.setContentType(contentType);
		PrintWriter out = null;
		try {
			if(up!=null){
				DrawPhoto d = new DrawPhoto();
				InputStream is = up.getTempPhoto().getBinaryStream();
				d.loadImg(is);
				up.setPosition(imgsize);
				ups.saveUserPhoto(up,
						d.drawBigRect(left, top, right, bottom, 180, 180),
						d.drawBigRect(left, top, right, bottom, 50, 50));
				out = response.getWriter();
				out.print("{'save':'ok'}");
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getBottom() {
		return bottom;
	}
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public String getImgsize() {
		return imgsize;
	}
	public void setImgsize(String imgsize) {
		this.imgsize = imgsize;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	


}
