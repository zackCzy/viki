package cn.mini.domain;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

public class UserLog {
	private UserBase user;
	private Set<UserBase> visitors;
	private String logName, logContent, type, noHtmlLog;
	@ColumnDefault("false")
	private boolean visible=true, draft=false, rubbish=false,smallSpeak=false;
	private java.sql.Timestamp modifyDate;
	private int suppot, id;
	private int commentNum;
	private int visibleNum;
	private List<Comment> com;

	public boolean getSmallSpeak() {
		return smallSpeak;
	}

	public void setSmallSpeak(boolean smallSpeak) {
		this.smallSpeak = smallSpeak;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getVisibleNum() {
		return visibleNum;
	}

	public void setVisibleNum(int visibleNum) {
		this.visibleNum = visibleNum;
	}

	public Set<UserBase> getVisitors() {
		return visitors;
	}

	public void setVisitors(Set<UserBase> visitors) {
		this.visitors = visitors;
	}

	public List<Comment> getCom() {
		return com;
	}

	public void setCom(List<Comment> com) {
		this.com = com;
	}

	public String getNoHtmlLog() {
		return noHtmlLog;
	}

	public boolean isRubbish() {
		return rubbish;
	}

	public void setRubbish(boolean rubbish) {
		this.rubbish = rubbish;
	}

	public void setNoHtmlLog(String noHtmlLog) {
		this.noHtmlLog = noHtmlLog;
	}

	public java.sql.Timestamp getModifyDate() {

		return modifyDate;
	}

	public void setModifyDate(java.sql.Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean getDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public UserBase getUser() {
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public int getSuppot() {
		return suppot;
	}

	public void setSuppot(int suppot) {
		this.suppot = suppot;
	}
}
