package cn.mini.dao.impl;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.U2UDao;
import cn.mini.domain.UserBase;
import cn.mini.exception.DaoException;

@Repository("u2UDaoImpl")
public class U2UDaoImpl extends BaseDao implements U2UDao {

	@Override
	public Set<UserBase> findFriends(int id) throws DaoException {
		try {
			UserBase user=(UserBase) getSession().get(UserBase.class, id);
			return user.getFollowUsers();
		} catch (Exception e) {
			throw new  DaoException("U2UDao-findFriends:"+e.getMessage(),e);
		}
		
	}

	@Override
	public Set<UserBase> findFriends(String name) throws DaoException {
		try {
			Criteria c=getSession().createCriteria(UserBase.class);
			c.add(Restrictions.eq("name", name));
			UserBase user=(UserBase) c.uniqueResult();
			return user.getFollowUsers();
		} catch (Exception e) {
			throw new  DaoException("U2UDao-findFriends:"+e.getMessage(),e);
		}
	}

	@Override
	public void addFriends(UserBase user) throws DaoException {
		try {
			getSession().update(user);
		} catch (Exception e) {
			throw new  DaoException("U2UDao-addFriends:"+e.getMessage(),e);
		}
	}

	@Override
	public void removeFollow(UserBase user) throws DaoException {
		try {
			getSession().update(user);
		} catch (Exception e) {
			throw new  DaoException("U2UDao-removeFollow:"+e.getMessage(),e);
		}
	}

	@Override
	public void removeFriend(UserBase user) throws DaoException {
		try {
			getSession().update(user);
		} catch (Exception e) {
			throw new  DaoException("U2UDao-removeFriend:"+e.getMessage(),e);
		}
	}	
}
