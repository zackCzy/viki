package temp;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.beanutils.BeanUtils;


public class BeanPropertyRowMapper implements RowMapper {
		Class<?> clazz;
	public BeanPropertyRowMapper(Class<?> clazz){
		this.clazz=clazz;
	};	
	public Object rowMapper(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		Object o=null;
		try {
			o=clazz.newInstance();
		for (int i = 0; i < count; i++) {
			String name = rsmd.getColumnLabel(i + 1);	
			BeanUtils.setProperty(o, name, rs.getObject(name));
		}
		} catch (IllegalAccessException e) {
			throw new SQLException("���಻֧��Bean����");
		} catch (InvocationTargetException e) {
			throw new SQLException();
		} catch (InstantiationException e) {
			throw new SQLException("ʵ��"+clazz+"ʧ��");
		}
		return o;
	}

}
