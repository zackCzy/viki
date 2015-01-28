package cn.mini.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.mini.dao.BaseDao;
import cn.mini.dao.UserDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

@Component("userDaoImpl")
public class UserDaoImpl extends BaseDao implements UserDao {
			
	@Override
	public void registerUser(UserBase um) throws DaoException {
		try {
			getSession().save(um);
		} catch (Exception e) {
			throw  new DaoException("UserDao-registerUser:"+e.getMessage(), e);
		}			
	}
	@Override
	public void updateUser(Object bean) throws DaoException {
		try {			
			getSession().update(bean);
		} catch (Exception e) {
			throw  new DaoException("UserDao-updateUser:"+e.getMessage(), e);
		}	
	}


	@Override
	public void removeUser(UserBase user) throws DaoException {
		try {
			getSession().delete(user);
		} catch (Exception e) {
			throw  new DaoException("UserDao-removeUser:"+e.getMessage(), e);
		}	
	}
	@Override
	public int sginUser(String name, String password) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBase.class);
			c.add(Restrictions.eq("name", name));
			c.add(Restrictions.eq("password", password));
			return ((UserBase) c.uniqueResult()).getId();
		} catch (Exception e) {
			throw  new DaoException("UserDao-sginUser:"+e.getMessage(), e);
		}			
	}

	@Override
	public int selectUser(String name) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBase.class);
			c.setReadOnly(true);	
			c.add(Restrictions.eq("name", name));
			UserBase user=(UserBase) c.uniqueResult();
			if(user.getId()<=0)throw  new DaoException("user exist");
			return user.getId();
		} catch (Exception e){
			throw  new DaoException("UserDao-selectUser:"+e.getMessage(), e);
		}	
	}
	@Override
	public UserBase findUser(int id){
		try {
			
			return (UserBase) getSession().get(UserBase.class, id);
		} catch (Exception e) {
			throw  new DaoException("UserDao-findUser:"+e.getMessage(), e);
		}
	}
	@Override
	public UserBase findUser(String name) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBase.class);
			c.add(Restrictions.eq("name",name));
			return (UserBase) c.uniqueResult();
		} catch (Exception e) {
			throw  new DaoException("UserDao-findUser:"+e.getMessage(), e);
		}
	}
	@Override
	public List<UserBase> findUserList(String email) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBase.class);
			c.add(Restrictions.eq("email",email));
			return c.list();
		} catch (Exception e) {
			throw  new DaoException("UserDao-findUserList:"+e.getMessage(), e);
		}		
	}
	@Override
	public List<UserBase> findUserAll(int page,int pageSize) throws DaoException {
		try {
			String hql = "from UserBase";
			Query q = getSession().createQuery(hql);
			q.setFirstResult((page - 1) * pageSize);
			q.setMaxResults(pageSize);	
			return q.list();
		} catch (Exception e) {
			throw  new DaoException("UserDao-findUserAll:"+e.getMessage(), e);
		}	
	}
}
