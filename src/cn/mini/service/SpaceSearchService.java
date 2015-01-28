package cn.mini.service;

import java.math.BigInteger;
import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.ServiceException;

public interface SpaceSearchService {
	  public List<UserBase> searchUser(String name,int page,int pageSize) throws ServiceException;
	  public List<UserLog> searchLog(String logName,int page,int pageSize) throws ServiceException;
	  public List<UserLog> searchSmallSpeak(String logName,int page,int pageSize) throws ServiceException;
	  public BigInteger searchUserCount(String name)throws ServiceException;
	  public Long searchLogCount(String logName)throws ServiceException;
	  public Long searchSmallSpeakCount(String logName)throws ServiceException;
}
