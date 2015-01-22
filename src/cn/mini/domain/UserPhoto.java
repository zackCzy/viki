package cn.mini.domain;

import java.sql.Blob;

public class UserPhoto {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private Boolean status;
	private UserBase user;
	private Blob bigPhoto ;
	private Blob tempPhoto ;
	public Blob getSourcePhoto() {
		return sourcePhoto;
	}
	public void setSourcePhoto(Blob sourcePhoto) {
		this.sourcePhoto = sourcePhoto;
	}
	private Blob sourcePhoto ;
	private String position;
	public Blob getTempPhoto() {
		return tempPhoto;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setTempPhoto(Blob tempPhoto) {
		this.tempPhoto = tempPhoto;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	private Blob smallPhoto ;
	public UserBase getUser() {
		return user;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public Blob getBigPhoto() {
		return bigPhoto;
	}
	public void setBigPhoto(Blob bigPhoto) {
		this.bigPhoto = bigPhoto;
	}
	public Blob getSmallPhoto() {
		return smallPhoto;
	}
	public void setSmallPhoto(Blob smallPhoto) {
		this.smallPhoto = smallPhoto;
	}
}
