package temp;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public final class DBManager {
	private static final Log log = LogFactory.getLog(DBManager.class);
	private static final String configFile = "../config/Dtbcs.properties";
	private DBManager() {}
	
	public  static JdbcTemplate jdbc= null;
	static {
		Properties dbProperties = new Properties();
		try {
			InputStream in=DBManager.class.getClassLoader().getResourceAsStream(configFile);		
			dbProperties.load(in);	
			jdbc =new JdbcTemplate(BasicDataSourceFactory.createDataSource(dbProperties));
			dbProperties=null;
			in=null;
		} catch (Exception e) {
			log.error(e);
			throw new Error(e);
		}
	}

	public static JdbcTemplate getJdbcTemplate(){
		return jdbc;
	}

}