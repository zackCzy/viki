package cn.mini.service;

import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;

public interface UserLogService {
	public void saveLog(UserLog user, int id) throws RuntimeException;

	public UserBase getUserLogs(int id) throws RuntimeException;

	public void updateLog(int Id, UserLog userlog) throws RuntimeException;

	public UserLog getUserLog(int id) throws RuntimeException;

	public void removeUserLog(int id) throws RuntimeException;

	public void removeRubbishUserLog(int id) throws RuntimeException;

	public UserBase getDiray(int id) throws RuntimeException;

	public UserBase getDiray(UserBase us) throws RuntimeException;

	public UserBase getDraft(int id) throws RuntimeException;

	public UserBase getRubbish(int id) throws RuntimeException;

	public void recoveryRubbishUserLog(int id) throws RuntimeException;

	public void updateLog(UserLog userlog) throws RuntimeException;

	public List<UserLog> getDatetDiray(UserBase user, java.sql.Timestamp date)
			throws RuntimeException;

	public List<UserLog> getCountDiray(UserBase user, int page, int pageSize)
			throws RuntimeException;

	public List<UserLog> getCountSpeak(UserBase user, int page, int pageSize)
			throws RuntimeException;

	public Long getCount(UserBase user) throws RuntimeException;

	public Long getSpeakCount(UserBase user) throws DaoException;

	public Long getSpeakDateCount(UserBase user, java.sql.Timestamp date)
			throws DaoException;
}
