package temp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import cn.mini.dao.Dao;
import cn.mini.dao.Handle;
import cn.mini.domain.User;
import cn.mini.exception.DaoException;

public class UserDaoImpl3 implements Dao {
	
	private NamedParameterJdbcTemplate jdbc=new NamedParameterJdbcTemplate(DBManager.getJdbcTemplate());
	public UserDaoImpl3() {};
	@Override
	public int insert(Object bean) {
		String sql = "INSERT INTO `usermessage`(name,sex,addr,info,voc,password,birthday) VALUES (:name,:sex,:addr,:info,:voc,:password,:birthday)";
		SqlParameterSource sp = new BeanPropertySqlParameterSource(bean);
		return jdbc.update(sql, sp);//update(sql, sp);	
	}
	@Override
	public int update(Object bean) {
		try {
			String sql = "UPDATE `usermessage` SET `sex`=:sex,`info`=:info,`voc`=:voc,`birthday`=:birthday,`blood_group`=:bloodGroup,`personal_address`=:personalAddress,`figure`=:figure,`smok_habits`=:smokHabits,`drink_habits`=:drinkHabits,`education`=:education,`school_address`=:schoolAddress,`school_time`=:schoolTime,`work_unit`=:workUnit,`work_time`=:workTime,`motto`=:motto,`personality`=:personality,`marital_status`=:maritalStatus,`sleep_habits`=:sleepHabits,`school_type`=:schoolType WHERE name=:name";
			SqlParameterSource sp = new BeanPropertySqlParameterSource(bean);
			return jdbc.update(sql, sp);
		} catch (Exception e) {
			System.err.println("321");
			throw new DaoException();
		}
	}

	@Override
	public int remove(String... key) {
		return 0;
	}

	@Override
	public Object select(String... key) {
		try {
			String sql = "SELECT * FROM `usermessage` WHERE name=?";
			return jdbc.getJdbcOperations().queryForObject(sql, key,new BeanPropertyRowMapper<User>(User.class));
		} catch (Exception e) {
			throw new DaoException();
		}	
	}

	@Override
	public Object handleDao(Handle hd) {
		return hd.handleDao(jdbc);
	}

}
