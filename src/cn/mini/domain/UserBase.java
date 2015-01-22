package cn.mini.domain;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class UserBase {
	private int id;
	private String name, password, email;
	private Date date;
	private String active;
	private List<UserLog> userLogs;
	private List<UserSpaceMusic> userSpaceMusics;
	private Set<UserBase> followUsers;
	private Set<UserBase> fansUsers;

	private List<Comment> comments;
	private List<Comment> myComments;

	private List<SystemMessage> systemMess;
	private List<SystemMessage> friendMess;
	private List<SystemMessage> noFriendMess;
	private List<SystemMessage> flansMess;
	private UserBaseDatum userBaseDatum;
	private UserBaseDails userBaseDails;
	private UnitDatum unitDatums;
	private SpaceDatum spaceDatums;


	public UnitDatum getUnitDatums() {
		return unitDatums;
	}

	public void setUnitDatums(UnitDatum unitDatums) {
		this.unitDatums = unitDatums;
	}

	public SpaceDatum getSpaceDatums() {
		return spaceDatums;
	}

	public void setSpaceDatums(SpaceDatum spaceDatums) {
		this.spaceDatums = spaceDatums;
	}

	public List<Comment> getMyComments() {
		return myComments;
	}

	public void setMyComments(List<Comment> myComments) {
		this.myComments = myComments;
	}



	public UserBaseDatum getUserBaseDatum() {
		return userBaseDatum;
	}

	public void setUserBaseDatum(UserBaseDatum userBaseDatum) {
		this.userBaseDatum = userBaseDatum;
	}

	public UserBaseDails getUserBaseDails() {
		return userBaseDails;
	}

	public void setUserBaseDails(UserBaseDails userBaseDails) {
		this.userBaseDails = userBaseDails;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SystemMessage> getSystemMess() {
		return systemMess;
	}

	public void setSystemMess(List<SystemMessage> systemMess) {
		this.systemMess = systemMess;
	}

	public List<SystemMessage> getFriendMess() {
		return friendMess;
	}

	public void setFriendMess(List<SystemMessage> friendMess) {
		this.friendMess = friendMess;
	}

	public List<SystemMessage> getNoFriendMess() {
		return noFriendMess;
	}

	public void setNoFriendMess(List<SystemMessage> noFriendMess) {
		this.noFriendMess = noFriendMess;
	}

	public List<SystemMessage> getFlansMess() {
		return flansMess;
	}

	public void setFlansMess(List<SystemMessage> flansMess) {
		this.flansMess = flansMess;
	}

	public List<UserSpaceMusic> getUserSpaceMusics() {
		return userSpaceMusics;
	}

	public void setUserSpaceMusics(List<UserSpaceMusic> userSpaceMusics) {
		this.userSpaceMusics = userSpaceMusics;
	}

	public List<UserLog> getUserLogs() {
		return userLogs;
	}

	public void setUserLogs(List<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

	public Set<UserBase> getFollowUsers() {
		return followUsers;
	}

	public void setFollowUsers(Set<UserBase> followUsers) {
		this.followUsers = followUsers;
	}

	public Set<UserBase> getFansUsers() {
		return fansUsers;
	}

	public void setFansUsers(Set<UserBase> fansUsers) {
		this.fansUsers = fansUsers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
