package cn.mini.domain;

import java.sql.Date;

public class UserBaseDatum {
	private int id;
	private UserBase user;
	private String sex,name,bloodGroup,addr,info;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Date birthday;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public UserBase getUser() {
		return user;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
