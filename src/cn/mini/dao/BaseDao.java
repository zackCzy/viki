package cn.mini.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory=null;
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
