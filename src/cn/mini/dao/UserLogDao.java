package cn.mini.dao;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;

public interface UserLogDao {
	public void saveLog(UserLog userlog) throws DaoException;
	public UserBase getUserLogs(int id) throws DaoException;
	public void updateLog(UserLog userlog) throws DaoException;
	public UserLog getUserLog(int id)  throws DaoException;
	public void removeUserLog(int id)  throws DaoException;
	public void removeRubbishUserLog(int id)  throws DaoException;
	public List<UserLog> getDiray(UserBase user)  throws DaoException;
	public List<UserLog> getDraft(UserBase user)  throws DaoException;
	public List<UserLog> getRubbish(UserBase user)  throws DaoException;
	public List<UserLog> getDatetDiray(UserBase user,java.sql.Timestamp date)  throws DaoException;
	public List<UserLog> getCountDiray(UserBase user,int page,int pageSize)  throws DaoException;
	public List<UserLog> getCountSpeak(UserBase user,int page,int pageSize)  throws DaoException;
	public Long getCount(UserBase user)  throws DaoException;
	public Long getSpeakCount(UserBase user)  throws DaoException;
	public Long getSpeakDateCount(UserBase user,java.sql.Timestamp date)  throws DaoException;
	public List<UserLog> getFireVisibelLog(int page,int pageSize) throws DaoException;
	public List<UserLog> getConFireLog(int page,int pageSize) throws DaoException;
	public List<UserLog> getNewsFireLog(int page,int pageSize) throws DaoException;
}
