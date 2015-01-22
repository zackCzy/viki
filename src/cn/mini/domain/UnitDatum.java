package cn.mini.domain;

import java.sql.Date;

public class UnitDatum {
	private int id;
	private UserBase user;
	private Date workTime, schoolTime;
	private String schoolAddress,workUnit,schoolType;
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
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	public Date getSchoolTime() {
		return schoolTime;
	}
	public void setSchoolTime(Date schoolTime) {
		this.schoolTime = schoolTime;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
}
