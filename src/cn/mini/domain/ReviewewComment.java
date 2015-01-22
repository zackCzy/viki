package cn.mini.domain;

public class ReviewewComment {
	private int id;
	private String content;
	private java.sql.Timestamp date;
	private Comment comment;
	private UserBase user;
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
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
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
	
}
