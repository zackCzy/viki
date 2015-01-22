package Utils;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class MyBeanUtil {
  private static BeanFactory factory=null;
  private static String configXml="applicationContext.xml";
  		static{
				factory =new ClassPathXmlApplicationContext(configXml);
		}
  	public static Object get(String clazz){
  		return factory.getBean(clazz);
  	}
  	public static <T> T get(Class<T> clazz){
  		return factory.getBean(clazz);
  	}
  	 
}
