package temp;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cn.mini.dao.Handle;

public class getAccountHandel implements Handle {
	private String name;
	public getAccountHandel(String name){
		this.name=name;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Object handleDao(NamedParameterJdbcTemplate jdbc) {
		String sql="SELECT COUNT(*) FROM `usermessage` WHERE name=? ";
		 Object[]  args=new Object [] {name};		
		return   jdbc.getJdbcOperations().queryForInt(sql, args)>0? true:false;
	}

}
