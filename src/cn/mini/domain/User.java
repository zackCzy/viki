package cn.mini.domain;

import java.sql.Date;


public class User {
	private String name, sex , addr, info, voc , password,bloodGroup,personalAddress,figure,smokHabits
	,drinkHabits,education,schoolAddress,workUnit,motto,personality,maritalStatus,sleepHabits,schoolType;
	private Date birthday,workTime,schoolTime;
	public User(){}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public String getSleepHabits() {
		return sleepHabits;
	}
	public void setSleepHabits(String sleepHabits) {
		this.sleepHabits = sleepHabits;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getName() {
		return name;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getPersonalAddress() {
		return personalAddress;
	}
	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}
	public String getFigure() {
		return figure;
	}
	public void setFigure(String figure) {
		this.figure = figure;
	}
	public String getSmokHabits() {
		return smokHabits;
	}
	public void setSmokHabits(String smokHabits) {
		this.smokHabits = smokHabits;
	}
	public String getDrinkHabits() {
		return drinkHabits;
	}
	public void setDrinkHabits(String drinkHabits) {
		this.drinkHabits = drinkHabits;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
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
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getPersonality() {
		return personality;
	}
	public void setPersonality(String personality) {
		this.personality = personality;
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
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getVoc() {
		return voc;
	}
	public void setVoc(String voc) {
		this.voc = voc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	
}
