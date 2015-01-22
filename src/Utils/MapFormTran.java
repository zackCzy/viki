package Utils;

import java.util.Arrays;
import java.util.List;

public class MapFormTran {
	private String map;
	private String sp;
	public MapFormTran(final String map){
		this.map=map;
	}
	public MapFormTran(final String map,final String sp){
		this.map=map;
		this.sp=sp;
	}
	public List<String> getFormMap(){
		String [] arr=map.split(sp==null?",":sp);
		return Arrays.asList(arr);
	}
}
