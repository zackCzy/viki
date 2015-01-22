package cn.mini.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import cn.mini.dao.BaseDao;
import cn.mini.dao.SpaceSearchDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;

@Repository("spaceSearchDaoImpl")
public class SpaceSearchDaoImpl extends BaseDao implements SpaceSearchDao {

	@Override
	public List<UserBase> searchUser(String name, int page, int pageSize)
			throws DaoException {
		try {
			String sql="select * from user_base where id=(select user_id from space_datum where nick_name like ?)";
			SQLQuery  q=getSession().createSQLQuery(sql).addEntity("user_base",UserBase.class);
			q.setString(0,"%"+name+"%");
			q.setFirstResult((page-1)*pageSize);
			q.setMaxResults(pageSize);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("SpaceSearchDao:"+e.getMessage(),e);
		}

	}
	@Override
	public List<UserLog> searchLog(String logName, int page, int pageSize)
			throws DaoException {
		try {
			String hql="from UserLog where logName like? or noHtmlLog like? and draft=? and rubbish=? and smallSpeak=? and visible=?";
			Query q=getSession().createQuery(hql);
			q.setString(0,"%"+logName+"%");
			q.setString(1,"%"+logName+"%");
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			q.setBoolean(4, false);
			q.setBoolean(5, true);
			q.setFirstResult((page-1)*pageSize);
			q.setMaxResults(pageSize);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("SpaceSearchDao:"+e.getMessage(),e);
		}
		
	}

	@Override
	public List<UserLog> searchSmallSpeak(String logName, int page, int pageSize)
			throws DaoException {
		try {
			String hql="from UserLog where logName like? or noHtmlLog like? and draft=? and rubbish=? and smallSpeak=? and visible=?";
			Query q=getSession().createQuery(hql);
			q.setString(0,"%"+logName+"%");
			q.setString(1,"%"+logName+"%");
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			q.setBoolean(4, true);
			q.setBoolean(5, true);
			q.setFirstResult((page-1)*pageSize);
			q.setMaxResults(pageSize);
			return q.list();
		} catch (Exception e) {
			throw new DaoException("SpaceSearchDao:"+e.getMessage(),e);
		}
		
	}
	@Override
	public BigInteger searchUserCount(String name) throws DaoException {
		try {
			String sql="select COUNT(*) from user_base where id=(select user_id from space_datum where nick_name like?)";
			SQLQuery  q=getSession().createSQLQuery(sql);
			q.setString(0,"%"+name+"%");
			return (BigInteger) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("SpaceSearchDao:"+e.getMessage(),e);
		}

	}
	@Override
	public Long searchLogCount(String logName) throws DaoException {
		try {
			String hql="select COUNT(*) from UserLog as user_log where logName like? or noHtmlLog like ? and draft=? and rubbish=? and smallSpeak=? and visible=?";
			Query q=getSession().createQuery(hql);
			q.setString(0,"%"+logName+"%");
			q.setString(1,"%"+logName+"%");
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			q.setBoolean(4, false);
			q.setBoolean(5, true);
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			
			throw new DaoException("SpaceSearchDao:"+e.getMessage(),e);
		}
	}
	@Override
	public Long searchSmallSpeakCount(String logName) throws DaoException {
		try {
			String hql="select COUNT(*) from UserLog as user_log where logName like? or noHtmlLog like ? and draft=? and rubbish=? and smallSpeak=? and visible=?";
			Query q=getSession().createQuery(hql);
			q.setString(0,"%"+logName+"%");
			q.setString(1,"%"+logName+"%");
			q.setBoolean(2, false);
			q.setBoolean(3, false);
			q.setBoolean(4, true);
			q.setBoolean(5, true);
			return (Long) q.uniqueResult();
		} catch (Exception e) {
			throw new DaoException("SpaceSearchDao:"+e.getMessage(),e);
		}

	}

}
