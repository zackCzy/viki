package cn.mini.service;

import java.math.BigInteger;
import java.util.List;

import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;

public interface SpaceSearchService {
	  public List<UserBase> searchUser(String name,int page,int pageSize) throws RuntimeException;
	  public List<UserLog> searchLog(String logName,int page,int pageSize) throws RuntimeException;
	  public List<UserLog> searchSmallSpeak(String logName,int page,int pageSize) throws RuntimeException;
	  public BigInteger searchUserCount(String name)throws RuntimeException;
	  public Long searchLogCount(String logName)throws RuntimeException;
	  public Long searchSmallSpeakCount(String logName)throws RuntimeException;
}
