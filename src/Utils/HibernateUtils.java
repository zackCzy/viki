package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtils {

	private HibernateUtils() {
	};

	private static SessionFactory sf = null;
	static {
		Configuration cfg = new Configuration();
		cfg.configure();

		/*
		 * 3.5使用 cfg.buildSessionFactory() 
		 * 4.0使用 ServiceRegistry serviceRegistry=new ServiceRegistryBuilder()
		 * .applySettings(cfg.getProperties()).buildServiceRegistry();
		 */
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).build();
		sf = cfg.buildSessionFactory(serviceRegistry);

	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}

	public static Session getSesstion() {
		return sf.openSession();
	}
	public static void closeSesstion(Session session){
		if(session!=null)session.close();
	}
	public static Session getCurrentSesstion(){
		return sf.getCurrentSession();
	}
}
