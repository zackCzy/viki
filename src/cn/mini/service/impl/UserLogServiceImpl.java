package cn.mini.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.UserDao;
import cn.mini.dao.UserLogDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;
import cn.mini.exception.ServiceException;
import cn.mini.exception.UserServiceException;
import cn.mini.service.UserLogService;

@Service("userLogServiceImpl")
public class UserLogServiceImpl implements UserLogService {
	@Resource(name = "userLogDaoImpl")
	private UserLogDao uld = null;
	@Resource(name = "userDaoImpl")
	private UserDao ud = null;

	@Override
	public void saveLog(UserLog user, int id) throws RuntimeException {
		if (id <= 0)
			throw new UserServiceException("user login has expired");
		UserBase us = ud.findUser(id);

		user.setModifyDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		user.setUser(us);
		uld.saveLog(user);
	}

	@Override
	public UserBase getUserLogs(int id) throws RuntimeException {
		if (id<= 0)throw new UserServiceException("user login has expired");
	    UserBase user=uld.getUserLogs(id);
		return user;
	}
	@Override
	public UserLog getUserLog(int id) throws RuntimeException {
		
		if (id<= 0)throw new UserServiceException("user login has expired");
		return uld.getUserLog(id);
	}

	@Override
	public void updateLog(int id, UserLog userlog) throws RuntimeException {
		if (id <= 0)
			throw new UserServiceException("user login has expired");
		UserBase us = ud.findUser(id);
		userlog.setUser(us);
		userlog.setModifyDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		uld.updateLog(userlog);
	}
	@Override
	public void updateLogVisitors(UserLog userlog) throws RuntimeException {
		uld.updateLog(userlog);
	}
	@Override
	public void updateLog(UserLog userlog) throws RuntimeException {
		userlog.setModifyDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		uld.updateLog(userlog);
	}
	@Override
	public void removeUserLog(int id) throws RuntimeException {
		uld.removeUserLog(id);	
	}

	@Override
	public UserBase getDiray(int id) throws RuntimeException {
		UserBase us =ud.findUser(id);
		us.setUserLogs(uld.getDiray(us));
		return us;
	}
	@Override
	public UserBase getDiray(UserBase us) throws RuntimeException {
		us.setUserLogs(uld.getDiray(us));
		return us;
	}
	@Override
	public UserBase getDraft(int id) throws RuntimeException {
		UserBase us =ud.findUser(id);
		us.setUserLogs(uld.getDraft(us));
		return us;
	}

	@Override
	public UserBase getRubbish(int id) throws RuntimeException {
		UserBase us =ud.findUser(id);
		us.setUserLogs(uld.getRubbish(us));
		return us;
	}

	@Override
	public void removeRubbishUserLog(int id) throws RuntimeException {
		uld.removeRubbishUserLog(id);	
	}

	@Override
	public void recoveryRubbishUserLog(int id) throws RuntimeException {
		UserLog uslog=uld.getUserLog(id);
		uslog.setRubbish(false);
		uld.updateLog(uslog);
	}

	@Override
	public List<UserLog> getDatetDiray(UserBase user, Timestamp date)
			throws RuntimeException {
		return uld.getDatetDiray(user, date);
	}

	@Override
	public List<UserLog> getCountDiray(UserBase user, int page, int pageSize)
			throws DaoException {		
		return uld.getCountDiray(user, page, pageSize);
	}

	@Override
	public Long getCount(UserBase user) throws RuntimeException {		
		return uld.getCount(user);
	}

	@Override
	public List<UserLog> getCountSpeak(UserBase user, int page, int pageSize)
			throws RuntimeException {
		return uld.getCountSpeak(user, page, pageSize);
	}

	@Override
	public Long getSpeakCount(UserBase user) throws DaoException {
		return uld.getSpeakCount(user);
	}

	@Override
	public Long getSpeakDateCount(UserBase user, Timestamp date)
			throws DaoException {
		return uld.getSpeakDateCount(user, date);
	}

	@Override
	public List<UserLog> getFireVisibelLog(int page, int pageSize)
			throws ServiceException {
		return uld.getFireVisibelLog(page, pageSize);
	}

	@Override
	public List<UserLog> getConFireLog(int page, int pageSize)
			throws ServiceException {
		return uld.getConFireLog(page, pageSize);
	}

	@Override
	public List<UserLog> getNewsFireLog(int page, int pageSize)
			throws ServiceException {
		return uld.getNewsFireLog(page, pageSize);
	}
}
