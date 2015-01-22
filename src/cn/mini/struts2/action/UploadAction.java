package cn.mini.struts2.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import Utils.DrawPhoto;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.service.UserPhotoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("uploadAction")
public class UploadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contentType = "text/html;charset=utf-8";
	private File myFile;
	private int top, left, right, bottom, rotation;
	private String imgsize;
	@Resource(name = "userPhotoServiceImpl")
	private UserPhotoService ups = null;
	public void uploadUserPhoto() {
		ServletActionContext.getResponse().setContentType(contentType);
		PrintWriter out = null;
		try {
			int userid=(Integer) ActionContext.getContext().getSession().get("id");
			UserBase user=new UserBase();
			user.setId(userid);
			FileInputStream fs=new FileInputStream(myFile);
			if(ups.getUserPhoto(user)==null){				
				ups.saveTempUserPhoto(userid, fs);
			}else {
				ups.updateTempUserPhoto(userid, fs);
			}
			
			out = ServletActionContext.getResponse().getWriter();
			out.print("{'id':'"+ userid+ "'}");
		} catch (Exception e) {
			System.out.println(e);
			try {
				out = ServletActionContext.getResponse().getWriter();
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
		ServletActionContext.getResponse().setContentType(contentType);
		PrintWriter out = null;
		System.out.println("123");
		try {
			if(up!=null){
				DrawPhoto d = new DrawPhoto();
				InputStream is = up.getTempPhoto().getBinaryStream();
				d.loadImg(is);
			    //up.setPosition(left + "," + top + "," + right + ","+ bottom + "," + rotation);
				up.setPosition(imgsize);
				ups.saveUserPhoto(up,
						d.drawBigRect(left, top, right, bottom, 180, 180),
						d.drawBigRect(left, top, right, bottom, 50, 50));
				out = ServletActionContext.getResponse().getWriter();
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
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
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
	


}
