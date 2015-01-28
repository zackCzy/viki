package cn.mini.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mini.dao.SpaceSearchDao;
import cn.mini.domain.UserBase;
import cn.mini.domain.UserLog;
import cn.mini.exception.ServiceException;
import cn.mini.service.SpaceSearchService;

@Service("spaceSearchServiceImpl")
public class SpaceSearchServiceImpl implements SpaceSearchService {

	@Resource(name="spaceSearchDaoImpl")
	SpaceSearchDao ssd=null;
	@Override
	public List<UserBase> searchUser(String name, int page, int pageSize)
			throws ServiceException {
		try {
			return ssd.searchUser(name, page, pageSize);
		} catch (ServiceException e) {
			throw new ServiceException("SpaceSearchServiceImpl-searchUser:"+e.getMessage(),e);
		}
		
	}

	@Override
	public List<UserLog> searchLog(String logName, int page, int pageSize)
			throws RuntimeException {
		try {
			return ssd.searchLog(logName, page, pageSize);
		} catch (ServiceException e) {
			throw new ServiceException("SpaceSearchServiceImpl-searchLog:"+e.getMessage(),e);
		}
		
	}

	@Override
	public List<UserLog> searchSmallSpeak(String logName, int page, int pageSize)
			throws RuntimeException {
		try {
			return ssd.searchSmallSpeak(logName, page, pageSize);
		} catch (ServiceException e) {
			throw new ServiceException("SpaceSearchServiceImpl-searchSmallSpeak:"+e.getMessage(),e);
		}
		
	}

	@Override
	public BigInteger searchUserCount(String name) throws RuntimeException {
		try {
			return ssd.searchUserCount(name);
		} catch (ServiceException e) {
			throw new ServiceException("SpaceSearchServiceImpl-searchUserCount:"+e.getMessage(),e);
		}
		
	}

	@Override
	public Long searchLogCount(String logName) throws RuntimeException {
		try {
			return ssd.searchLogCount(logName);
		} catch (ServiceException e) {
			throw new ServiceException("SpaceSearchServiceImpl-searchLogCount:"+e.getMessage(),e);
		}
		
	}

	@Override
	public Long searchSmallSpeakCount(String logName)
			throws RuntimeException {
		try {
			return ssd.searchSmallSpeakCount(logName);
		} catch (ServiceException e) {
			throw new ServiceException("SpaceSearchServiceImpl-searchSmallSpeakCount:"+e.getMessage(),e);
		}
		
	}

}
