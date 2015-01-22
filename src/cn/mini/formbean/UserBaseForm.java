package cn.mini.formbean;

import cn.mini.domain.UserBase;


public class UserBaseForm {
	private UserBase user;

	public UserBase getUser() {
		return user;
	}
	public void setUser(UserBase user) {
		this.user = user;
	}
	public UserBaseForm(){};
	public UserBaseForm(UserBase user){
		this.user=user;
	};
	public Boolean CheckBean(){
		try {
			if(!user.getName().matches("([A-Za-z]+[0-9]+)|([0-9]+[A-Za-z]){7,16}")||user.getName()==null||user.getName().trim().equals("")){
				return false;
			}
			if(!(user.getPassword().length()>=8)&&!user.getPassword().matches("\\s")){
				return false;
			}
			if(!user.getEmail().matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
				return false;
			}
			

		} catch (Exception e) {			
			return false;
		}
		return true;
	}
}
