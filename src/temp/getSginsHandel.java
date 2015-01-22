package temp;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cn.mini.dao.Handle;

public class getSginsHandel implements Handle {
	Object[] args;
	public getSginsHandel(Object ...args){
		this.args=args;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Object handleDao(NamedParameterJdbcTemplate jdbc) {
		String sql="SELECT COUNT(*) FROM usermessage WHERE name=? and password=?";
		return  jdbc.getJdbcOperations().queryForInt(sql, args) <= 0 ? false : true;

	}

}
