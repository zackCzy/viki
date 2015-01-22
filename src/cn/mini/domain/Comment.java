package cn.mini.domain;

import java.util.List;

public class Comment {
	private int id;
	private String content;
	private java.sql.Timestamp date;
	private UserLog userlog;
	private UserBase user;
	private List<ReviewewComment> reviewewCom;
	private UserBase comUser;

	public List<ReviewewComment> getReviewewCom() {
		return reviewewCom;
	}
	public void setReviewewCom(List<ReviewewComment> reviewewCom) {
		this.reviewewCom = reviewewCom;
	}
	public UserBase getUser() {
		return user;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public UserBase getComUser() {
		return comUser;
	}
	public void setComUser(UserBase comUser) {
		this.comUser = comUser;
	}
	public UserLog getUserlog() {
		return userlog;
	}
	public void setUserlog(UserLog userlog) {
		this.userlog = userlog;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.sql.Timestamp getDate() {
		return date;
	}
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
