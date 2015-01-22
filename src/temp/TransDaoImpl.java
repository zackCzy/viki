package temp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import temp.DBManager;

import cn.mini.dao.Dao;
import cn.mini.dao.Handle;
import cn.mini.domain.Word;

public class TransDaoImpl implements Dao {
	private JdbcTemplate jdbc=DBManager.getJdbcTemplate();


	public TransDaoImpl() {};
	@Override
	public int insert(Object bean) {
		return 0;
	}

	@Override
	public int update(Object bean) {
		return 0;
	}

	@Override
	public int remove(String ...name) {
		return 0;
	}

	@Override
	public Object select(String ...key) {
	
		String sql = "SELECT name,worde,usap,ukp FROM `word` WHERE name=?";
		return jdbc.queryForObject(sql, key,new BeanPropertyRowMapper<Word>(Word.class));		
	}
	@Override
	public Object handleDao(Handle hd) {
		return null;
	}

	
}
