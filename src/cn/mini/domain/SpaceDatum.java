package cn.mini.domain;

import org.hibernate.annotations.ColumnDefault;

public class SpaceDatum {
	private int id;
	private UserBase user;
	private String nickName,spacePassWord;
	@ColumnDefault("false")
	private Boolean visible=false,isMusic=true;
	private Double rank;
	
	public Boolean getIsMusic() {
		return isMusic;
	}
	public void setIsMusic(Boolean isMusic) {
		this.isMusic = isMusic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserBase getUser() {
		return user;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public String getSpacePassWord() {
		return spacePassWord;
	}
	public void setSpacePassWord(String spacePassWord) {
		this.spacePassWord = spacePassWord;
	}
	public Double getRank() {
		return rank;
	}
	public void setRank(Double rank) {
		this.rank = rank;
	}
	
}
