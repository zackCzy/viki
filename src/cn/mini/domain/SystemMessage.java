package cn.mini.domain;

import org.hibernate.annotations.ColumnDefault;


public class SystemMessage {
	private int id;
	private String messageContent;
	private String messageTitle;
	@ColumnDefault("false")
	private Boolean visible=false;
	private java.sql.Timestamp date;
	private UserBase user;
	public String getMessageContent() {
		return messageContent;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
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
}
