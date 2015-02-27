package temp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBeanFactory {
  private static BeanFactory factory=null;
  private static String configXml="applicationContext.xml";
	public static BeanFactory getInstance() {
		if (factory == null) {
			synchronized (MyBeanFactory.class) {
				if (factory == null)
					factory =new ClassPathXmlApplicationContext(configXml);
			}
		}
		return factory;
	}
}
