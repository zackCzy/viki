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
			throw new DaoException("UserLogDao-saveLog:"+e.getMessage(), e);
		}

	}

	@Override
	public UserBase getUserLogs(int id) throws DaoException {
		try {
			UserBase ubs = (UserBase) getSession().load(UserBase.class, id);
			return ubs;
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getUserLogs:"+e.getMessage(), e);
		}
	}

	@Override
	public UserLog getUserLog(int id) throws DaoException {
		try {
			Criteria c = getSession().createCriteria(UserLog.class);
			c.add(Restrictions.eq("id", id));
			return (UserLog) c.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getUserLog:"+e.getMessage(), e);
		}
	}

	@Override
	public void updateLog(UserLog userlog) throws DaoException {
		try {
			getSession().update(userlog);
		} catch (Exception e) {
			throw new DaoException("UserLogDao-updateLog:"+e.getMessage(), e);
		}
	}

	

	@Override
	public List<UserLog> getDiray(UserBase user) throws DaoException {
		String hql="from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? order by id desc";
		Query q = getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, false);
		try {
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getDiray:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getDraft(UserBase user) throws DaoException {
		String hql="from  UserLog as user_log  where user=? and draft=? and rubbish=? order by id desc";
		Query q = getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setBoolean(1, true);
		q.setBoolean(2, false);
		try {
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getDraft:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getRubbish(UserBase user) throws DaoException {
		String hql="from  UserLog as user_log  where user=?  and rubbish=? order by id desc";
		Query q = getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setBoolean(1, true);
		try {
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getRubbish:"+e.getMessage(), e);
		}
	}

	@Override
	public void removeRubbishUserLog(int id) throws DaoException {
		try {
			getSession().delete(getSession().get(UserLog.class, id));
		} catch (Exception e) {
			throw new DaoException("UserLogDao-removeRubbishUserLog:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getDatetDiray(UserBase user, Timestamp date)
			throws DaoException {
		String hql="from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? and modifyDate>=? order by id desc";
		Query q = getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, false);
		q.setParameter(4, date);
		try {
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getDatetDiray:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getCountDiray(UserBase user, int page, int pageSize)
			throws DaoException {
		Query q = getSession().createQuery("from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? order by id desc");
		q.setParameter(0, user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, false);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);	
		try {			
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getCountDiray:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getNoAuthorityADiray(UserBase user, int page, int pageSize)
			throws DaoException {
		Query q = getSession().createQuery("from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? and visible=? order by id desc");
		q.setParameter(0, user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, false);
		q.setBoolean(4, true);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);	
		try {			
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getNoAuthorityADiray:"+e.getMessage(), e);
		}
	}
	
	@Override
	public Long getCount(UserBase user) throws DaoException {
		String hql = "SELECT COUNT(*) from UserLog where user=? and draft=? and rubbish=? and smallSpeak=?";
		Query q = getSession().createQuery(hql);
		q.setParameter(0,user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, false);
		try {
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getCount:"+e.getMessage(),e);
		}
	}

	@Override
	public List<UserLog> getCountSpeak(UserBase user, int page, int pageSize)
			throws DaoException {
		String hql="from  UserLog as user_log  where user=? and draft=? and rubbish=? and smallSpeak=? order by id desc";
		Query q = getSession().createQuery(hql);
		q.setParameter(0, user);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, true);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);		
		try {		
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getCountSpeak:"+e.getMessage(), e);
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
		try {
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getSpeakCount:"+e.getMessage(), e);
		}
		
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
		try {	
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getSpeakCount:"+e.getMessage(), e);
		}
		
	}
	@Override
	public List<UserLog> getFireVisibelLog(int page,int pageSize) throws DaoException {
		
		String hql="from  UserLog as user_log  where draft=? and rubbish=? and smallSpeak=? and visible=? order by visibleNum desc";				 
		Query q=getSession().createQuery(hql);
		q.setBoolean(0, false);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, true);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		try {	
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getFireVisibelLog:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getConFireLog(int page, int pageSize)
			throws DaoException {
		String hql="from UserLog where draft=? and rubbish=? and smallSpeak=? and visible=? order by commentNum desc";				 
		Query q=getSession().createQuery(hql);
		q.setBoolean(0, false);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, true);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		try {	
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getConFireLog:"+e.getMessage(), e);
		}
	}

	@Override
	public List<UserLog> getNewsFireLog(int page, int pageSize) throws DaoException {
		String hql="from UserLog where draft=? and rubbish=? and smallSpeak=? and visible=? order by modifyDate desc";				 
		Query q=getSession().createQuery(hql);
		q.setBoolean(0, false);
		q.setBoolean(1, false);
		q.setBoolean(2, false);
		q.setBoolean(3, true);
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		try {	
			return q.list();
		} catch (Exception e) {
			throw new DaoException("UserLogDao-getNewsFireLog:"+e.getMessage(), e);
		}
	}

	@Override
	public void removeUserLog(UserLog log) throws DaoException {
		try {
			UserLog userl =log;
			userl.setRubbish(true);
			userl.setModifyDate(new Timestamp(new java.util.Date().getTime()));
			getSession().update(userl);
		} catch (Exception e) {
			throw new DaoException("UserLogDao-removeUserLog:"+e.getMessage(), e);
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
			throw new DaoException("UserLogDao-removeUserLog:"+e.getMessage(), e);
		}

	}

	@Override
	public void removeRubbishUserLog(UserLog log) throws DaoException {
		try {
			getSession().delete(log);
		} catch (Exception e) {
			throw new DaoException("UserLogDao-removeRubbishUserLog:"+e.getMessage(), e);
		}
	}
}
