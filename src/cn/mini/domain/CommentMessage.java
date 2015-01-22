package cn.mini.domain;

import java.util.List;

public class CommentMessage {
	private int id;
	private String content;
	private java.sql.Timestamp date;
	private UserBase user;
	private List<ReviewewComment> reviewewCom;
	private UserBase comUser;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public UserBase getUser() {
		return user;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public List<ReviewewComment> getReviewewCom() {
		return reviewewCom;
	}
	public void setReviewewCom(List<ReviewewComment> reviewewCom) {
		this.reviewewCom = reviewewCom;
	}
	public UserBase getComUser() {
		return comUser;
	}
	public void setComUser(UserBase comUser) {
		this.comUser = comUser;
	}
}
