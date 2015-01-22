package cn.mini.dao.refactor;

import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ContextDestroyListener implements ServletContextListener {

	  
    private static Logger logger = LoggerFactory.getLogger(ContextDestroyListener.class);  
  
    public static final List<String> MANUAL_DESTROY_THREAD_IDENTIFIERS = Arrays.asList("QuartzScheduler", "scheduler_Worker");  
  
    @Override  
    public void contextInitialized(ServletContextEvent sce) {  
		ConvertUtils.register(new  Converter(){
			public Object convert(Class type, Object value) {
				if(value==null)return null;
				if(!(value instanceof String)){
					return value;
				}
				String str=(String)value;
				if(str.trim().equals(""))return null;
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
				DateLocaleConverter dc=new DateLocaleConverter();
				if(!str.matches("[0-9\\-]*"))return null;
				 try {	
					dc.convert(str, "yyyy-MM-dd");
					return new  Date(sd.parse(str).getTime());
				} catch (ParseException e) {
					return null;
				}			
			}
			
		},Date.class);
    }  
  
    @Override  
    public void contextDestroyed(ServletContextEvent sce) {  
        destroyJDBCDrivers();  
        destroySpecifyThreads();  
        
    }  
  
    private void destroySpecifyThreads() {  
        final Set<Thread> threads = Thread.getAllStackTraces().keySet();  
        for (Thread thread : threads) {  
            if (needManualDestroy(thread)) {  
                synchronized (this) {  
                    try {  
                        thread.stop();  
                        logger.debug(String.format("Destroy  %s successful", thread));  
                    } catch (Exception e) {  
                        logger.warn(String.format("Destroy %s error", thread), e);  
                    }  
                }  
            }  
        }  
    }  
  
    private boolean needManualDestroy(Thread thread) {  
        final String threadName = thread.getName();  
        for (String manualDestroyThreadIdentifier : MANUAL_DESTROY_THREAD_IDENTIFIERS) {  
            if (threadName.contains(manualDestroyThreadIdentifier)) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
    private void destroyJDBCDrivers() {  
        final Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();  
        Driver driver;  
        while (drivers.hasMoreElements()) {  
            driver = (Driver) drivers.nextElement();  
            try {  
                DriverManager.deregisterDriver(driver);  
                logger.debug(String.format("Deregister JDBC driver %s successful", driver));  
            } catch (SQLException e) {  
                logger.warn(String.format("Deregister JDBC driver %s error", driver), e);  
            }  
        }  
    }  
}  