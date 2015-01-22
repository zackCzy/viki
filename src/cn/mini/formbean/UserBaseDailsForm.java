package cn.mini.formbean;

import java.util.Arrays;
import java.util.List;

import cn.mini.domain.UserBaseDails;

public class UserBaseDailsForm {
	private UserBaseDails userDails;
	public UserBaseDailsForm(){};
	public UserBaseDailsForm(UserBaseDails userDails){
		this.userDails=userDails;
	};
	public UserBaseDails getUserDails() {
		return userDails;
	}
	public void setUserDails(UserBaseDails userDails) {
		this.userDails = userDails;
	}
	public Boolean CheckBean(){
		String[] list={"未知","苗条","丰满","中等身材","高大","小巧","运动型","健美"};
		if(Arrays.asList(list).indexOf( userDails.getFigure())==-1){
			return false;
		}
		String[] list1={"未知","单身","恋爱","已婚","离异"};
		if(Arrays.asList(list1).indexOf(userDails.getMaritalStatus())==-1){
			return false;
		}
		String[] list2 ={"抽烟习惯","憎恶抽烟","从不抽烟","偶尔抽烟","已戒烟"};
		if(Arrays.asList(list2).indexOf(userDails.getSmokHabits())==-1){
			return false;
		}
		String[] list3 ={"饮酒习惯","憎恶饮酒","从不饮酒","偶尔饮酒","已戒酒"};
		if(Arrays.asList(list3).indexOf(userDails.getDrinkHabits())==-1){
			return false;
		}
		String[] list4={"睡眠习惯","早睡早起","爱睡懒觉","经常熬夜","经常失眠"};
		if(Arrays.asList(list4).indexOf(userDails.getSleepHabits())==-1){
			return false;
		}
		String[] list6={"未知","初中","高中","大学","硕士","小学","中专/技校","大专","博士","其他"};
		if(Arrays.asList(list6).indexOf(userDails.getEducation())==-1){
			return false;
		}
		String[] list7={"未知","白领","医生","IT人员","学生","服务","旅游","自由职业","政府部门","建筑","其他"};
		if(Arrays.asList(list7).indexOf(userDails.getVoc())==-1){
			return false;
		}	
		if(userDails.getMotto().length()>80){
			return false;
		}
		String[] list5=	{"","温柔","沉默","开朗","稳重","内向","粗犷","成熟","自卑"};
		String[] listForm=userDails.getPersonality().split(",");
		List<String> lists=Arrays.asList(list5);
		Boolean flag=true;
		for (int i = 0; i < listForm.length; i++) {
			if( lists.indexOf(listForm[i])==-1){
				flag=false;
				continue;
			}
		}
		return flag;
	}

}
