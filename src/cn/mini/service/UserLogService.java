package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;
import cn.mini.exception.ServiceException;

public interface UserLogService {
	public void saveLog(UserLog user, int id) throws ServiceException;

	public UserBase getUserLogs(int id) throws ServiceException;

	public void updateLog(int Id, UserLog userlog) throws ServiceException;

	public UserLog getUserLog(int id) throws ServiceException;

	public void removeUserLog(int id) throws ServiceException;

	public void removeRubbishUserLog(int id) throws ServiceException;

	public UserBase getDiray(int id) throws ServiceException;

	public UserBase getDiray(UserBase us) throws ServiceException;

	public UserBase getDraft(int id) throws ServiceException;

	public UserBase getRubbish(int id) throws ServiceException;

	public void recoveryRubbishUserLog(int id) throws ServiceException;

	public void updateLog(UserLog userlog) throws ServiceException;

	public List<UserLog> getDatetDiray(UserBase user, java.sql.Timestamp date)
			throws ServiceException;

	public List<UserLog> getCountDiray(UserBase user, int page, int pageSize)
			throws ServiceException;

	public List<UserLog> getCountSpeak(UserBase user, int page, int pageSize)
			throws ServiceException;

	public Long getCount(UserBase user) throws ServiceException;

	public Long getSpeakCount(UserBase user) throws DaoException;

	public Long getSpeakDateCount(UserBase user, java.sql.Timestamp date)
			throws ServiceException;
	public void updateLogVisitors(UserLog userlog) throws ServiceException;
	public  List<UserLog> getFireVisibelLog(int page,int pageSize) throws ServiceException;
	public List<UserLog> getConFireLog(int page,int pageSize) throws ServiceException;
	public List<UserLog> getNewsFireLog(int page,int pageSize) throws ServiceException;
}
