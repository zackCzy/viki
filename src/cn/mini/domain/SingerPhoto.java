package cn.mini.domain;

import java.sql.Blob;


public class SingerPhoto {
	private int id;
	private String singerName;
	private Blob bigPhoto;
	private Blob smallPhoto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
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
