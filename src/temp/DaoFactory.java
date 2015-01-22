package temp;

import java.io.InputStream;
import java.util.Properties;

import cn.mini.dao.impl.TransDaoImpl;
import cn.mini.dao.impl.UserDaoImpl3;


public class DaoFactory {
	private static DaoFactory instance = null;
    private String config="../config/handleDao.properties";
	private DaoFactory() {}

	public static DaoFactory getInstance() {
		if (instance == null) {
			synchronized (DaoFactory.class) {
				if (instance == null)
					instance = new DaoFactory();
			}
		}
		return instance;
	}

	private Object getClass(String className) {
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = DaoFactory.class.getClassLoader().getResourceAsStream(config);
			p.load(in);
			in.close();
			return Class.forName(p.getProperty(className)).newInstance();
		} catch (Exception e) {
			throw new ExceptionInInitializerError("factory pase error");
		}
	}

	public TransDaoImpl getTransDao() {
		return (TransDaoImpl) getClass("TransClass");
	}
	public UserDaoImpl3 getUserDao(){
		return (UserDaoImpl3) getClass("UserClass");
	}
}
