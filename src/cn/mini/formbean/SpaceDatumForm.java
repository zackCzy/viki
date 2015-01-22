package cn.mini.formbean;

import cn.mini.domain.SpaceDatum;

public class SpaceDatumForm {
	private SpaceDatum sp;
	public SpaceDatumForm(){};
	public SpaceDatumForm(SpaceDatum sp){
		this.sp=sp;
	};
	public Boolean CheckBean(){
		if(sp.getNickName().length()>12){
			return false;
		}
		return true;
	}

}
