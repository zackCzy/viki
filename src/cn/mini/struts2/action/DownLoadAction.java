package cn.mini.struts2.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.mini.domain.SingerPhoto;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserPhoto;
import cn.mini.service.SingerPhotoService;
import cn.mini.service.UserPhotoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("downLoadAction")
public class DownLoadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private InputStream stream = null;
	private String singerName;
	private String postion;
	@Resource(name = "userPhotoServiceImpl")
	private UserPhotoService ups = null;
	@Resource(name = "singerPhotoServiceImpl")
	SingerPhotoService sps = null;

	@SuppressWarnings("deprecation")
	public String singerPhoto() {
		try {
			SingerPhoto sp = null;
			sp = sps.getSingerPhoto(java.net.URLDecoder.decode(singerName,"utf-8"));
			ActionContext.getContext().put("contentType", "image/jpeg");
			ActionContext.getContext().put("fileName",sp.getSingerName() + ".jpg");
			ActionContext.getContext().put("allowCaching", false);
			stream = sp.getBigPhoto().getBinaryStream();
		} catch (Exception e) {
			try {
				stream=new FileInputStream(ServletActionContext.getRequest().getRealPath("/images/miniMusic/songphoto.jpg"));
				return "singerPhoto";
			} catch (FileNotFoundException e1) {
				throw new RuntimeException(e1.getMessage(), e1);
			}
		}
		return "singerPhoto";
	}

	public String downLoadPhoto() {
		try {
			UserBase user=new UserBase();
			user.setId(id);
			UserPhoto up = ups.getUserPhoto(user);
			ActionContext.getContext().put("contentType", "image/jpeg");
			ActionContext.getContext().put("allowCaching", true);
			stream = up.getTempPhoto().getBinaryStream();
		} catch (Exception e) {
			String url = ServletActionContext.getServletContext().getRealPath(
					"image/userHead.jpg");
			System.out.println(url);
			try {
				stream = new FileInputStream(url);
			} catch (FileNotFoundException e1) {
				
			}
		}
		return "singerPhoto";
	}

	public String getPhoto() {
		try {
			UserBase user=new UserBase();
			user.setId(id);
			UserPhoto up = ups.getUserPhoto(user);
			ActionContext.getContext().put("contentType", "image/jpeg");
			ActionContext.getContext().put("allowCaching", false);
			if (up==null||up.getSourcePhoto() == null) {
				String url = ServletActionContext.getServletContext()
				.getRealPath("image/userHead.jpg");
				stream = new FileInputStream(url);
			} else {
				stream = up.getSourcePhoto().getBinaryStream();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return "singerPhoto";
	}
	public String getBigPhoto() {
		try {
			UserBase user=new UserBase();
			user.setId(id);
			UserPhoto up = ups.getUserPhoto(user);
			ActionContext.getContext().put("contentType", "image/jpeg");
			ActionContext.getContext().put("allowCaching", false);
			if (up!=null&&up.getSourcePhoto() != null) {
				stream = up.getBigPhoto().getBinaryStream();
			} else {
				String url = ServletActionContext.getServletContext()
						.getRealPath("image/userHead.jpg");
				stream = new FileInputStream(url);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return "singerPhoto";
	}
	public String getSmallPhoto() {
		try {
			UserBase user=new UserBase();
			user.setId(id);
			UserPhoto up = ups.getUserPhoto(user);
			ActionContext.getContext().put("contentType", "image/jpeg");
			ActionContext.getContext().put("allowCaching", false);
			if (up!=null&&up.getSourcePhoto() != null) {
				stream = up.getSmallPhoto().getBinaryStream();
			} else {
				String url = ServletActionContext.getServletContext()
						.getRealPath("image/userHead.jpg");
				stream = new FileInputStream(url);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return "singerPhoto";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}
	
}
