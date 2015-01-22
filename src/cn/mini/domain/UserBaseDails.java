package cn.mini.domain;

public class UserBaseDails {
	private int id;
	private UserBase user;
	private String  personalAddress, voc,
			figure, smokHabits, drinkHabits, education, 
			motto, personality, maritalStatus, sleepHabits;

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

	public String getPersonalAddress() {
		return personalAddress;
	}

	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}

	public String getVoc() {
		return voc;
	}

	public void setVoc(String voc) {
		this.voc = voc;
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

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSleepHabits() {
		return sleepHabits;
	}

	public void setSleepHabits(String sleepHabits) {
		this.sleepHabits = sleepHabits;
	}
			
}
