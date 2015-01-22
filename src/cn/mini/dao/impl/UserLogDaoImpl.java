package cn.mini.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.UserLogDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;

@Repository("userLogDaoImpl")
public class UserLogDaoImpl extends BaseDao implements UserLogDao {

	@Override
	public void saveLog(UserLog userlog) throws DaoException {
		try {
			getSession().save(userlog);
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}

	}

	@Override
	public UserBase getUserLogs(int id) throws DaoException {
		try {
			UserBase ubs = (UserBase) getSession().load(UserBase.class, id);
			return ubs;
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}
	}

	@Override
	public UserLog getUserLog(int id) throws DaoException {
		try {
			Criteria c = getSession().createCriteria(UserLog.class);
			c.add(Restrictions.eq("id", id));
			return (UserLog) c.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}
	}

	@Override
	public void updateLog(UserLog userlog) throws DaoException {
		try {
			getSession().update(userlog);
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}
	}

	@Override
	public void removeUserLog(int id) throws DaoException {
		try {
			UserLog userl = (UserLog) getSession().get(UserLog.class, id);
			userl.setRubbish(true);
			userl.setModifyDate(new Timestamp(new java.util.Date().getTime()));
			getSession().update(userl);
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}

	}

	@Override
	public List<UserLog> getDiray(UserBase user) throws DaoException {
		try {
			Query q = getSession()
					.createQuery(
							"from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? order by id desc");
			q.setParameter(0, user);
			q.setBoolean(1, false);
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getDraft(UserBase user) throws DaoException {
		try {
			Query q = getSession()
					.createQuery(
							"from  UserLog as user_log  where user=? and draft=? and rubbish=? order by id desc");
			q.setParameter(0, user);
			q.setBoolean(1, true);
			q.setBoolean(2, false);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getRubbish(UserBase user) throws DaoException {
		try {
			Query q = getSession()
					.createQuery(
							"from  UserLog as user_log  where user=?  and rubbish=? order by id desc");
			q.setParameter(0, user);
			q.setBoolean(1, true);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao:"+e.getMessage(), e);
		}
	}

	@Override
	public void removeRubbishUserLog(int id) throws DaoException {
		try {
			getSession().delete(getSession().get(UserLog.class, id));
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getDatetDiray(UserBase user, Timestamp date)
			throws DaoException {
		try {
			Query q = getSession()
					.createQuery(
							"from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? and modifyDate>=? order by id desc");
			q.setParameter(0, user);
			q.setBoolean(1, false);
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			q.setParameter(4, date);
			return q.list();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getCountDiray(UserBase user, int page, int pageSize)
			throws DaoException {
		try {
			Query q = getSession().createQuery("from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? order by id desc");
			q.setParameter(0, user);
			q.setBoolean(1, false);
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			q.setFirstResult((page - 1) * pageSize);
			q.setMaxResults(pageSize);				
			return q.list();
		} catch (Exception e) {
			System.out.println(e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public Long getCount(UserBase user) throws DaoException {
		try {
			String hql = "SELECT COUNT(*) from UserLog where user=? and draft=? and rubbish=? and smallSpeak=?";
			Query q = getSession().createQuery(hql);
			q.setParameter(0,user);
			q.setBoolean(1, false);
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(),e);
		}
	}

	@Override
	public List<UserLog> getCountSpeak(UserBase user, int page, int pageSize)
			throws DaoException {
		try {
			Query q = getSession().createQuery("from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? order by id desc");
			q.setParameter(0, user);
			q.setBoolean(1, false);
			q.setBoolean(2, false);
			q.setBoolean(3, true);
			q.setFirstResult((page - 1) * pageSize);
			q.setMaxResults(pageSize);				
			return q.list();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public Long getSpeakCount(UserBase user) throws DaoException {
		String hql="SELECT COUNT(*) from UserLog where user=? and draft=? and rubbish=? and smallSpeak=?";
		Query q=getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setParameter(1, false);
		q.setParameter(2, false);
		q.setParameter(3, true);	
		return (Long) q.uniqueResult();
	}

	@Override
	public Long getSpeakDateCount(UserBase user, Timestamp date)
			throws DaoException {
		String hql="SELECT COUNT(*) from UserLog where user=? and draft=? and rubbish=? and smallSpeak=? and modifyDate>=?";
						 
		Query q=getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, true);	
		q.setParameter(4, date);	
		return (Long) q.uniqueResult();
	}

}
