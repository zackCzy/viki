package cn.mini.dao;

import java.math.BigInteger;
import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.DaoException;

public interface SpaceSearchDao {
  public List<UserBase> searchUser(String name,int page,int pageSize) throws DaoException;
  public List<UserLog> searchLog(String logName,int page,int pageSize) throws DaoException;
  public List<UserLog> searchSmallSpeak(String logName,int page,int pageSize) throws DaoException;
  public BigInteger searchUserCount(String name)throws DaoException;
  public Long searchLogCount(String logName)throws DaoException;
  public Long searchSmallSpeakCount(String logName)throws DaoException;
}
