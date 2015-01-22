package cn.mini.formbean;

import java.util.Arrays;

import cn.mini.domain.UnitDatum;

public class UnitDatumForm {
	private UnitDatum um;
	public UnitDatumForm(){};
	public UnitDatumForm(UnitDatum um){
		this.um=um;
	};
	public UnitDatum getUm() {
		return um;
	}

	public void setUm(UnitDatum um) {
		this.um = um;
	}

	public Boolean CheckBean() {
		if(um.getSchoolAddress().length()>50){
			return false;
		}
		if(um.getWorkUnit().length()>50){
			return false;
		}
		String [] schoolType={"大学","高中","初中","小学","其他"};
		
		if(Arrays.asList(schoolType).indexOf(um.getSchoolType())==-1){
			return false;
		}
		return true;
	}
}
